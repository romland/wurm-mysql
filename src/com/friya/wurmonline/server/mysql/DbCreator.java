package com.friya.wurmonline.server.mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public class DbCreator
{
    private static Logger logger = Logger.getLogger(DbCreator.class.getName());
	private static DbCreator instance;


	private DbCreator()
	{
	}
	

	static DbCreator getInstance()
	{
		if(instance == null) {
			instance = new DbCreator();
		}

		return instance; 
	}


	void create(String sqliteDir)
	{
		try {
		    ArrayList<String> created = createDatabases(
		    		sqliteDir,
		    		Mod.dbConfig.get("DB_HOST"),
		    		Mod.dbConfig.get("DB_PORT"),
		    		Mod.dbConfig.get("DB_USER"),
		    		Mod.dbConfig.get("DB_PASS")
		    );
		    if(Mod.isInsertDefaultDataInNewDatabases()) {
		    	insertDefaultData(
			    		created,
			    		sqliteDir,
			    		Mod.dbConfig.get("DB_HOST"),
			    		Mod.dbConfig.get("DB_PORT"),
			    		Mod.dbConfig.get("DB_USER"),
			    		Mod.dbConfig.get("DB_PASS")
		    	);
		    }

		} catch (SQLException | ClassNotFoundException | IOException e) {
		    logger.log(Level.SEVERE, "DbCreator failed: ", e);
		}
	}

	
	/**
	 * Create databases and tables.
	 * 
	 * @param sqliteDir
	 * @param dbServer
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private ArrayList<String> createDatabases(String sqliteDir, String dbServer, String dbPort, String dbUser, String dbPassword)
			throws ClassNotFoundException, SQLException, IOException, FileNotFoundException
	{
		File folder = new File(sqliteDir);
		File[] dir = folder.listFiles();
		
		ArrayList<String> createdDatabases = new ArrayList<String>();
		
		Statement stmt;
		int res;
		Class.forName("com.mysql.jdbc.Driver");
		Connection dblessCon = DriverManager.getConnection(getConnectionString(dbServer, dbPort, dbUser, dbPassword, null));

		for (int i = 0; i < dir.length; i++) {
			if (dir[i].isFile() == false) {
				continue;
			}
			
			String fn = dir[i].getName();
			
			if(fn.endsWith(".sql") == false || fn.startsWith("export") || fn.startsWith("insert")) {
				continue;
			}
			
			String dbName = fn.split("\\.sql")[0].toUpperCase();

			// Only drop databases if flag is set AND if we will recreate database afterwards.
			if(Mod.isDeleteDatabasesIfTheyExist() && Mod.isCreateDatabasesIfTheyDoNotExist()) {
				stmt = dblessCon.createStatement();

				// returns 0 by default
				res = stmt.executeUpdate("DROP DATABASE " + dbName);
				logger.info("DROP DATABASE " + dbName + ", Res: " + res);
				stmt.close();
			}

			if(dbExists(dblessCon, dbName) == false && Mod.isCreateDatabasesIfTheyDoNotExist()) {
			    stmt = dblessCon.createStatement();

			    // returns 1 if all is well
			    res = stmt.executeUpdate("CREATE DATABASE " + dbName);
			    logger.info("CREATE DATABASE " + dbName + ", Res: " + res);
			    stmt.close();

			    if(Mod.isCreateTablesOnDatabaseCreation()) {
					Connection mConnection = DriverManager.getConnection(getConnectionString(dbServer, dbPort, dbUser, dbPassword, dbName));
					String fullFn = sqliteDir + File.separator + fn;
					
					executeSqlScript(fullFn, mConnection);
					
					mConnection.close();
			    }
			}
		}

		dblessCon.close();
		
		return createdDatabases;
	}


	private boolean dbExists(Connection connection, String dbName)
	{
		boolean exists = false;

		try {
			ResultSet resultSet = connection.getMetaData().getCatalogs();

			while (resultSet.next()) {
				logger.info(resultSet.getString(1));
				if(resultSet.getString(1).toUpperCase().equals(dbName)) {
					logger.info("Database exists: " + dbName);
					exists = true;
					break;
				}
			}
		
			resultSet.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return exists;
	}

	/**
	 * Insert data into the tables we just created.
	 * 
	 * @param sqliteDir
	 * @param dbServer
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @throws SQLException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void insertAllDefaultData(String sqliteDir, String dbServer, String dbPort, String dbUser, String dbPassword) throws SQLException, IOException, FileNotFoundException
	{
		File folder = new File(sqliteDir);
		File[] dir = folder.listFiles();

		for (int i = 0; i < dir.length; i++) {
			if (dir[i].isFile() == false) {
				continue;
			}
			
			String fn = dir[i].getName();
			
			if(fn.startsWith("insert") == false || fn.endsWith(".sql") == false) {
				continue;
			}

			String dbName = fn.split("\\.sql")[0].replace("insert", "").toUpperCase();

			Connection mConnection = DriverManager.getConnection(getConnectionString(dbServer, dbPort, dbUser, dbPassword, dbName));
			String fullFn = sqliteDir + File.separator + fn;
			executeSqlScript(fullFn, mConnection);

		    mConnection.close();
		}
	}


	private void insertDefaultData(ArrayList<String> databaseNames, String sqliteDir, String dbServer, String dbPort, String dbUser, String dbPassword)
			throws SQLException, IOException, FileNotFoundException
	{
		for(String dbName : databaseNames) {
			String fn = "insert" + dbName + ".sql";

			if((new File(sqliteDir + File.separator + fn).exists()) == false) {
				throw new FileNotFoundException(fn);
			}

			Connection mConnection = DriverManager.getConnection(getConnectionString(dbServer, dbPort, dbUser, dbPassword, dbName));
			String fullFn = sqliteDir + File.separator + fn;
			executeSqlScript(fullFn, mConnection);
		    mConnection.close();
		}
	}
	

	/**
	 * 
	 * 
	 * @param dbServer
	 * @param dbPort
	 * @param dbUser
	 * @param dbPassword
	 * @param dbName
	 * @return
	 */
	private String getConnectionString(String dbServer, String dbPort, String dbUser, String dbPassword, @Nullable String dbName)
	{
		return "jdbc:mysql://" + dbServer + ":" + dbPort + "/" + (dbName == null ? "" : dbName) + "?" + "user=" + dbUser + "&password=" + dbPassword;
	}


	/**
	 * See http://stackoverflow.com/questions/717436/create-mysql-database-from-java
	 * 
	 * @param fullFn
	 * @param mConnection
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private boolean executeSqlScript(String fullFn, Connection mConnection) throws IOException, SQLException
	{
		logger.info("Executing statements in: " + fullFn);

		ScriptRunner runner = new ScriptRunner(mConnection, false, false);

		FileReader fr = new FileReader(fullFn);
		BufferedReader br = new BufferedReader(fr);
		runner.runScript(br);
		fr.close();
		br.close();
		
		return true;
	}
}
