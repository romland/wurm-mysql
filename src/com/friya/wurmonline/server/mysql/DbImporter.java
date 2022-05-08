package com.friya.wurmonline.server.mysql;

import java.util.logging.Logger;

public class DbImporter
{
    private static Logger logger = Logger.getLogger(DbImporter.class.getName());
	private static DbImporter instance;


	private DbImporter()
	{
	}


	static DbImporter getInstance()
	{
		if(instance == null) {
			instance = new DbImporter();
		}

		return instance; 
	}


	void start(String sqliteDir)
	{
	}
	
	private void dumpSqliteDatabases(String tmpDir)
	{
	}
}
