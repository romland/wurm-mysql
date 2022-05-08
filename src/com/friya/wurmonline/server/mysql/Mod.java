package com.friya.wurmonline.server.mysql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

import com.wurmonline.server.ServerDirInfo;
import com.wurmonline.server.Servers;


/**
 * Entry point.
 * 
 * @author Friya
 */
public class Mod implements WurmServerMod, Initable, Configurable, PreInitable, ServerStartedListener
{
    private static Logger logger = Logger.getLogger(Mod.class.getName());
    
    static HashMap<String, String> dbConfig = null;

    private String gameName = "Creative";
    private boolean enabled = true;
    static private boolean createDatabasesIfTheyDoNotExist = false;
    static private boolean createTablesOnDatabaseCreation = false;
    static private boolean deleteDatabasesIfTheyExist = false;
    static private String modSupportDbName = "modsupport-with-mysql.db";
    static private boolean importExistingWorld = false;
    static private boolean insertDefaultDataInNewDatabases = false;


    public void configure(Properties properties)
	{
		logger.log(Level.INFO, "configure called");

		enabled = Boolean.valueOf(properties.getProperty("enabled", String.valueOf(enabled))).booleanValue();
		gameName = String.valueOf(properties.getProperty("gameName", String.valueOf(gameName)));
		setDeleteDatabasesIfTheyExist(Boolean.valueOf(properties.getProperty("deleteDatabasesIfTheyExist", String.valueOf(isDeleteDatabasesIfTheyExist()))).booleanValue());
		setCreateDatabasesIfTheyDoNotExist(Boolean.valueOf(properties.getProperty("createDatabasesIfTheyDoNotExist", String.valueOf(isCreateDatabasesIfTheyDoNotExist()))).booleanValue());
		setCreateTablesOnDatabaseCreation(Boolean.valueOf(properties.getProperty("createTablesOnDatabaseCreation", String.valueOf(isCreateTablesOnDatabaseCreation()))).booleanValue());
		modSupportDbName = String.valueOf(properties.getProperty("modSupportDbName", String.valueOf(modSupportDbName)));
		importExistingWorld = Boolean.valueOf(properties.getProperty("importExistingWorld", String.valueOf(importExistingWorld))).booleanValue();
		setInsertDefaultDataInNewDatabases(Boolean.valueOf(properties.getProperty("insertDefaultDataInNewDatabases", String.valueOf(isInsertDefaultDataInNewDatabases()))).booleanValue());
		
		logger.log(Level.INFO, "all configure completed");
	}


	public void preInit()
	{
		if(!enabled) {
			return;
		}

		logger.log(Level.INFO, "preInit called");

		if(importExistingWorld) {
			throw new RuntimeException("Importing an existing world is not implemented.");
		}
		
		String currentDir = System.getProperty("user.dir");
		String gameDir = currentDir + File.separator + gameName;
		String sqliteDir = gameDir + File.separator + "sqlite";

		dbConfig = getPropertiesFromFile(gameDir + File.separator + ServerDirInfo.getConstantsFileName(), new String[]{"DB_HOST", "DB_PORT", "DB_USER", "DB_PASS"});

		verifySettings(gameDir);
		
		DbCreator.getInstance().create(sqliteDir);

		if(importExistingWorld) {
			DbImporter.getInstance().start(sqliteDir);
		}

		Patcher.getInstance().patch(sqliteDir, modSupportDbName);

		logger.log(Level.INFO, "all preInit completed");
	}


	public void init()
    {
		if(!enabled) {
			return;
		}

		logger.log(Level.INFO, "init called");
		logger.log(Level.INFO, "all init completed");
    }

	
	public void onServerStarted()
	{
	}


	private boolean verifySettings(String gameDir)
	{
		logger.info("config filename:" + ServerDirInfo.getConstantsFileName());
		logger.info("DB_HOST: " + dbConfig.get("DB_HOST"));
		logger.info("DB_PORT: " + dbConfig.get("DB_PORT"));
		logger.info("DB_USER: " + dbConfig.get("DB_USER"));
		logger.info("DB_PASS: " + (dbConfig.get("DB_PASS") == null || dbConfig.get("DB_PASS").length() == 0 ? "is NOT set" : "is set but hidden here"));

		if(gameName == null || gameName.length() == 0) {
			throw new RuntimeException("gameName is either not specified in 'mysq.properties'");
		}
		
		if((new File(gameDir).exists()) == false) {
			throw new RuntimeException("game directory " + gameDir + " does not exist");
		}

		if(insertDefaultDataInNewDatabases && importExistingWorld) {
			throw new RuntimeException("Conflicting options, can't both insert default data AND import existing world");
		}

		return true;
	}
	
	
	private HashMap<String, String> getPropertiesFromFile(String fullFileName, String[] properties)
	{
		Properties props = new Properties();
		File file = null;
		HashMap<String, String> ret = new HashMap<String, String>();

		try {
			file = new File(fullFileName);
			logger.info("Loading configuration file at " + fullFileName);
			FileInputStream fis = new FileInputStream(file);
			props.load(fis);
			fis.close();
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to load properties at " + file.getAbsolutePath(), e);
		}

		for(Entry<Object,Object> e : props.entrySet()) {
			String k = e.getKey().toString();
			String v = e.getValue().toString();
			
			if(Stream.of(properties).anyMatch(x -> x.equals(k))) {
				logger.info("Found property: " + k);
				ret.put(k, v);
			}
		}
		
		if(properties.length != ret.size()) {
			throw new RuntimeException("Could not find all properties " + Arrays.toString(properties) + " in " + fullFileName);
		}
		
		return ret;
	}


	static public boolean isTestEnv()
	{
		return Servers.localServer.getName().equals("Friya");
	}


	static boolean isDeleteDatabasesIfTheyExist()
	{
		return Mod.deleteDatabasesIfTheyExist;
	}


	private void setDeleteDatabasesIfTheyExist(boolean deleteDatabasesIfTheyExist)
	{
		Mod.deleteDatabasesIfTheyExist = deleteDatabasesIfTheyExist;
	}


	static boolean isCreateDatabasesIfTheyDoNotExist()
	{
		return createDatabasesIfTheyDoNotExist;
	}


	private static void setCreateDatabasesIfTheyDoNotExist(boolean createDatabasesIfTheyDoNotExist)
	{
		Mod.createDatabasesIfTheyDoNotExist = createDatabasesIfTheyDoNotExist;
	}


	static boolean isCreateTablesOnDatabaseCreation()
	{
		return createTablesOnDatabaseCreation;
	}


	private static void setCreateTablesOnDatabaseCreation(boolean createTablesOnDatabaseCreation)
	{
		Mod.createTablesOnDatabaseCreation = createTablesOnDatabaseCreation;
	}


	static boolean isInsertDefaultDataInNewDatabases()
	{
		return insertDefaultDataInNewDatabases;
	}


	private static void setInsertDefaultDataInNewDatabases(boolean insertDefaultDataInNewDatabases)
	{
		Mod.insertDefaultDataInNewDatabases = insertDefaultDataInNewDatabases;
	}
}
