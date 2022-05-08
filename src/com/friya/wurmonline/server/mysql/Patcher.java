package com.friya.wurmonline.server.mysql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.classhooks.InvocationHandlerFactory;

import com.wurmonline.server.Constants;
import com.wurmonline.server.DbConnector;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtPrimitiveType;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class Patcher
{
    private static Logger logger = Logger.getLogger(Patcher.class.getName());
	private static Patcher instance;

	private Patcher()
	{
	}
	
	static Patcher getInstance()
	{
		if(instance == null) {
			instance = new Patcher();
		}

		return instance; 
	}

	void patch(String sqliteDirName, String modSupportDbName)
	{
		patchLauncher(sqliteDirName, modSupportDbName);
		patchServer();
	}
	

	private void patchLauncher(String sqliteDirName, String modSupportDbName)
	{
    	logger.info("patching Ago's modloader (!)");

    	try {
			CtClass ctc;
			ctc = HookManager.getInstance().getClassPool().get("org.gotti.wurmunlimited.modsupport.ModSupportDb");

			CtMethod m = ctc.getDeclaredMethod("getDbConnectionString");
	        m.setBody("{"
	        		+ "return \"jdbc:sqlite:" + sqliteDirName.replace("\\", "/") + "/" + modSupportDbName + "\";"
	        		+ "}");
        	logger.info("Applied patch #1 to modloader");

	        ctc.getDeclaredMethods("checkSqlite")[0].instrument(new ExprEditor(){
	            public void edit(MethodCall m) throws CannotCompileException {
	                if (m.getMethodName().equals("isUseSqlite")) {
	                	logger.info("Applied patch #2 to modloader");
	                    m.replace("$_ = true;");
	                    return;
	                }
	            }
	        });
		} catch (NotFoundException | CannotCompileException e) {
			logger.log(Level.SEVERE, "Failed", e);
		}
	}
	

	private void patchServer()
	{
		String descriptor = Descriptor.ofMethod(CtPrimitiveType.voidType, new CtClass[] {
			CtPrimitiveType.booleanType
		});
		HookManager.getInstance().registerHook("com.wurmonline.server.DbConnector", "initialize", descriptor, new InvocationHandlerFactory()
		{
			@Override
			public InvocationHandler createInvocationHandler() {
				return new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

						// TODO: We don't want this here, if this is the problem, we should just see to it that the DB vars don't get overridden or use our own
						logger.log(Level.WARNING, "DbConnector.initialize() Resetting constants to their proper values!");
						Constants.dbHost = Mod.dbConfig.get("DB_HOST");
						Constants.dbPort = ":" + Mod.dbConfig.get("DB_PORT");
						Constants.loginDbHost = Mod.dbConfig.get("DB_HOST");
						Constants.loginDbPort = ":" + Mod.dbConfig.get("DB_PORT");
						Constants.siteDbHost = Mod.dbConfig.get("DB_HOST");
						Constants.siteDbPort = ":" + Mod.dbConfig.get("DB_PORT");
						
						// Expensive! But hopefully this is not called that often!
				        Field field = DbConnector.class.getDeclaredField("sqlite");
				        field.setAccessible(true);

				        //field.setBoolean(proxy, false);		// ruhroh, this modifies the class, the field is static!
				        field.setBoolean(null, false);			// null == modify static!

						Object result = method.invoke(proxy, args);
						return result;
					}
				};
			}
		});

		descriptor = Descriptor.ofMethod(CtPrimitiveType.voidType, new CtClass[] {
			CtPrimitiveType.booleanType
		});
		HookManager.getInstance().registerHook("com.wurmonline.server.DbConnector", "setUseSqlite", descriptor, new InvocationHandlerFactory()
		{
			@Override
			public InvocationHandler createInvocationHandler() {
				return new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						args[0] = (Object)false;
						Object result = method.invoke(proxy, args);
						return result;
					}
				};
			}
		});


		descriptor = Descriptor.ofMethod(CtPrimitiveType.voidType, new CtClass[] {
		});
		HookManager.getInstance().registerHook("com.wurmonline.server.DbConnector", "closeAll", descriptor, new InvocationHandlerFactory()
		{
			@Override
			public InvocationHandler createInvocationHandler() {
				return new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// Expensive! But hopefully this is not called that often!
				        Field field = DbConnector.class.getDeclaredField("sqlite");
				        field.setAccessible(true);
				        //field.setBoolean(proxy, false);		// ruhroh, this modifies the class, the field is static!
				        field.setBoolean(null, false);			// null == modify static!

						Object result = method.invoke(proxy, args);
						return result;
					}
				};
			}
		});
	}

}
