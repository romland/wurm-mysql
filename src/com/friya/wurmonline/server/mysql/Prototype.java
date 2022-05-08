package com.friya.wurmonline.server.mysql;

// WARNING: We have 'localhost' in settings of Wurm.ini now this CAN cause problems under windows. 
//			For some reason that can get dirt fucking slow compared to 127.0.0.1 (bumped into this problem in the past)

/*
------------------------------------------------------------------------------------------------------------------------------
  WHEN STARTING WITH GUI
[10:07:18 PM 685] INFO org.gotti.wurmunlimited.modloader.ModLoaderShared: Loaded net.bdew.wurm.timerfix.TimerFix as timerfix (unversioned)
[10:07:18 PM 685] INFO org.gotti.wurmunlimited.modloader.ModLoaderShared: Loaded mod.wurmonline.mods.upkeepcosts.UpkeepCosts as upkeepcosts (unversioned)
[10:07:18 PM 685] INFO org.gotti.wurmunlimited.modloader.ModLoaderShared: Loaded com.friya.wurmonline.server.vamps.Mod as vamps (unversioned)
[10:07:18 PM 716] INFO org.gotti.wurmunlimited.mods.serverpacks.ServerPackMod: Added pack CFD7F66B95D24E2C2CD574E1FFB5691CACCB71A7 for pack mods\serverpacks\reign1.jar
[10:07:18 PM 779] INFO com.wurmonline.server.gui.WurmServerGuiMain: WurmServerGuiMain starting
[10:07:19 PM 161] INFO com.friya.wurmonline.server.vamps.Vampires: DbConnector.setUseSqlite()
[10:07:19 PM 176] INFO com.wurmonline.server.gui.WurmServerGuiController: Creating starting game directories from original directories
[10:07:25 PM 333] WARNING com.wurmonline.server.gui.WurmServerGuiController: Starting directories were not all successfully created
<end>

	DID:
	made abooboo: I was modifying the non-static version of sqlite variable
------------------------------------------------------------------------------------------------------------------------------

Later error (need to set in config, I hope):
[10:23:07 PM 688] WARNING com.wurmonline.server.database.ConnectionDescriptor: No class found for database driver: 
[10:23:07 PM 694] WARNING com.wurmonline.server.database.ConnectionDescriptor: No class found for database driver: 


	DID
		added jars:
			mm.mysql-2.0.14-bin.jar
			jdbc2_0-stdext.jar
			jta-spec1_0_1.jar

------------------------------------------------------------------------------------------------------------------------------
[10:45:14 PM 976] WARNING com.wurmonline.server.DbConnector: Problem opening the loginDbcon
com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure Last packet sent to the server was 0 ms ago.
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
	at java.lang.reflect.Constructor.newInstance(Unknown Source)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	at com.mysql.jdbc.SQLError.createCommunicationsException(SQLError.java:1074)
	at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2104)
	at com.mysql.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:729)
	at com.mysql.jdbc.JDBC4Connection.<init>(JDBC4Connection.java:46)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
	at java.lang.reflect.Constructor.newInstance(Unknown Source)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	at com.mysql.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:302)
	at com.mysql.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:283)
	at com.wurmonline.server.database.MysqlConnectionBuilder.buildConnection(MysqlConnectionBuilder.java:128)

	DID:
	Heh, apparently I had a root password on DB, so added that: fnurt (it was blank prior)

------------------------------------------------------------------------------------------------------------------------------
[10:50:20 PM 169] INFO com.friya.wurmonline.server.vamps.Vampires: DbConnector.initialize()
[10:50:20 PM 170] WARNING com.wurmonline.server.database.ConnectionDescriptor: No class found for database driver: 
[10:50:20 PM 177] WARNING com.wurmonline.server.database.ConnectionDescriptor: No class found for database driver: 
[10:50:20 PM 181] INFO com.wurmonline.server.Servers: Loading all servers.
[10:50:22 PM 515] WARNING com.wurmonline.server.DbConnector: Problem opening the loginDbcon
com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure

Last packet sent to the server was 0 ms ago.
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
	at java.lang.reflect.Constructor.newInstance(Unknown Source)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	at com.mysql.jdbc.SQLError.createCommunicationsException(SQLError.java:1074)
	at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2104)
	at com.mysql.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:729)
	at com.mysql.jdbc.JDBC4Connection.<init>(JDBC4Connection.java:46)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
	at java.lang.reflect.Constructor.newInstance(Unknown Source)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	at com.mysql.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:302)
	at com.mysql.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:283)
	at com.wurmonline.server.database.MysqlConnectionBuilder.buildConnection(MysqlConnectionBuilder.java:128)
	at com.wurmonline.server.DbConnector.refreshDbConnection(DbConnector.java:424)
	at com.wurmonline.server.DbConnector.refreshConnectionForSchema(DbConnector.java:585)
	at com.wurmonline.server.DbConnector.getLoginDbCon(DbConnector.java:442)
	at com.wurmonline.server.Servers.loadAllServers(Servers.java:659)
	at com.wurmonline.server.gui.WurmServerGuiController.buildSelectServerBox(WurmServerGuiController.java:1865)
	at com.wurmonline.server.gui.WurmServerGuiController.initialize(WurmServerGuiController.java:1747)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at sun.reflect.misc.Trampoline.invoke(Unknown Source)
	at sun.reflect.GeneratedMethodAccessor1.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at sun.reflect.misc.MethodUtil.invoke(Unknown Source)
	at javafx.fxml.FXMLLoader.loadImpl(Unknown Source)
	at javafx.fxml.FXMLLoader.loadImpl(Unknown Source)
	at javafx.fxml.FXMLLoader.load(Unknown Source)
	at com.wurmonline.server.gui.WurmServerGuiMain.start(WurmServerGuiMain.java:148)
	at com.sun.javafx.application.LauncherImpl.lambda$launchApplication1$163(Unknown Source)
	at com.sun.javafx.application.PlatformImpl.lambda$runAndWait$176(Unknown Source)
	at com.sun.javafx.application.PlatformImpl.lambda$null$174(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at com.sun.javafx.application.PlatformImpl.lambda$runLater$175(Unknown Source)
	at com.sun.glass.ui.InvokeLaterDispatcher$Future.run(Unknown Source)
	at com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
	at com.sun.glass.ui.win.WinApplication.lambda$null$149(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
Caused by: java.net.UnknownHostException: Adventure
	at java.net.Inet6AddressImpl.lookupAllHostAddr(Native Method)
	at java.net.InetAddress$2.lookupAllHostAddr(Unknown Source)
	at java.net.InetAddress.getAddressesFromNameService(Unknown Source)
	at java.net.InetAddress.getAllByName0(Unknown Source)
	at java.net.InetAddress.getAllByName(Unknown Source)
	at java.net.InetAddress.getAllByName(Unknown Source)
	at com.mysql.jdbc.StandardSocketFactory.connect(StandardSocketFactory.java:246)
	at com.mysql.jdbc.MysqlIO.<init>(MysqlIO.java:276)
	at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2027)
	... 38 more

NOTE: Caused by: java.net.UnknownHostException: Adventure
NOTE: On headless: Caused by: java.net.UnknownHostException: Creative_org

In com/wurmonline/server/gui/WurmServerGuiController.java we have:
2021 / * 2021 * /       Constants.dbHost = this.currentDir;
2022 / * 2022 * /       Constants.dbPort = "";
2023 / * 2023 * /       Constants.loginDbHost = this.currentDir;
2024 / * 2024 * /       Constants.loginDbPort = "";
2025 / * 2025 * /       Constants.siteDbHost = this.currentDir;
2026 / * 2026 * /       Constants.siteDbPort = "";

	TESTED:
	Can we safely just reload the constants? :) We could, but undid this for now since 
	I am not sure if it's needed if we run headless. Can always return to this afterwards.
			Constants.load();

------------------------------------------------------------------------------------------------------------------------------

Headless:
[11:59:03 PM 140] INFO com.wurmonline.server.Servers: Loading all servers.
[11:59:05 PM 481] WARNING com.wurmonline.server.DbConnector: Problem opening the loginDbcon
com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure

Last packet sent to the server was 0 ms ago.
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
	at java.lang.reflect.Constructor.newInstance(Unknown Source)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	at com.mysql.jdbc.SQLError.createCommunicationsException(SQLError.java:1074)
	at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2104)
	at com.mysql.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:729)
	at com.mysql.jdbc.JDBC4Connection.<init>(JDBC4Connection.java:46)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
	at java.lang.reflect.Constructor.newInstance(Unknown Source)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	at com.mysql.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:302)
	at com.mysql.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:283)
	at com.wurmonline.server.database.MysqlConnectionBuilder.buildConnection(MysqlConnectionBuilder.java:128)
	at com.wurmonline.server.DbConnector.refreshDbConnection(DbConnector.java:424)
	at com.wurmonline.server.DbConnector.refreshConnectionForSchema(DbConnector.java:585)
	at com.wurmonline.server.DbConnector.getLoginDbCon(DbConnector.java:442)
	at com.wurmonline.server.Servers.loadAllServers(Servers.java:659)
	at com.wurmonline.server.gui.WurmServerGuiController.initServer(WurmServerGuiController.java:462)
	at com.wurmonline.server.gui.WurmServerGuiController.startDB(WurmServerGuiController.java:411)
	at com.wurmonline.server.gui.WurmServerGuiMain.main(WurmServerGuiMain.java:132)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at javassist.Loader.run(Loader.java:288)
	at org.gotti.wurmunlimited.serverlauncher.DelegatedLauncher.main(DelegatedLauncher.java:30)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at javassist.Loader.run(Loader.java:288)
	at org.gotti.wurmunlimited.serverlauncher.ServerLauncher.main(ServerLauncher.java:33)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at PatchedLauncher.main(PatchedLauncher.java:20)
Caused by: java.net.UnknownHostException: Creative_org
	at java.net.Inet6AddressImpl.lookupAllHostAddr(Native Method)
	at java.net.InetAddress$2.lookupAllHostAddr(Unknown Source)
	at java.net.InetAddress.getAddressesFromNameService(Unknown Source)
	at java.net.InetAddress.getAllByName0(Unknown Source)
	at java.net.InetAddress.getAllByName(Unknown Source)
	at java.net.InetAddress.getAllByName(Unknown Source)
	at com.mysql.jdbc.StandardSocketFactory.connect(StandardSocketFactory.java:246)
	at com.mysql.jdbc.MysqlIO.<init>(MysqlIO.java:276)
	at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2027)
	... 34 more

[11:59:05 PM 485] INFO com.wurmonline.server.Servers: Loaded 0 servers from the database
[11:59:05 PM 491] INFO com.friya.wurmonline.server.vamps.Vampires: DbConnector.closeAll()
[11:59:05 PM 491] INFO com.wurmonline.server.DbConnector: Starting to close all Database Connections.
[11:59:05 PM 491] INFO com.wurmonline.server.DbConnector: Finished closing all Database Connections.
[11:59:05 PM 492] INFO com.wurmonline.server.gui.WurmServerGuiMain: WurmServerGuiMain finished

dbconnector:
117     public static Connection getLoginDbCon() throws SQLException {
118         return DbConnector.refreshConnectionForSchema(WurmDatabaseSchema.LOGIN);
119     }

com/wurmonline/server/DbConnector.java:            
	newConnectors.put(WurmDatabaseSchema.LOGIN, new DbConnector((ConnectionBuilder)this.builderForSchema(loginConnectionDescriptor, WurmDatabaseSchema.LOGIN), "loginDbcon"));

The databases we have are:
343         CREATURES("WURMCREATURES"),
344         DEITIES("WURMDEITIES"),
345         ECONOMY("WURMECONOMY"),
346         ITEMS("WURMITEMS"),
347         LOGIN("WURMLOGIN"),
348         LOGS("WURMLOGS"),
349         PLAYERS("WURMPLAYERS"),
350         TEMPLATES("WURMTEMPLATES"),
351         ZONES("WURMZONES"),
352         SITE("WURMSITE");

	DID:
	CREATE DATABASE WURMLOGIN;

------------------------------------------------------------------------------------------------------------------------------
Same error as the one above above

HAH, added to my hooks (temporary fix for now)
	Constants.load();

------------------------------------------------------------------------------------------------------------------------------

[12:15:12 AM 359] INFO com.wurmonline.server.Servers: Loading all servers.
[12:15:12 AM 598] WARNING com.wurmonline.server.Servers: Failed to load all servers!Table 'wurmlogin.servers' doesn't exist
com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table 'wurmlogin.servers' doesn't exist
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
	at java.lang.reflect.Constructor.newInstance(Unknown Source)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	at com.mysql.jdbc.Util.getInstance(Util.java:381)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:1031)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:957)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3376)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3308)
	at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:1837)
	at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:1961)
	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2543)
	at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1737)
	at com.mysql.jdbc.PreparedStatement.executeQuery(PreparedStatement.java:1888)
	at com.wurmonline.server.Servers.loadAllServers(Servers.java:661)
	at com.wurmonline.server.gui.WurmServerGuiController.initServer(WurmServerGuiController.java:462)
	at com.wurmonline.server.gui.WurmServerGuiController.startDB(WurmServerGuiController.java:411)
	at com.wurmonline.server.gui.WurmServerGuiMain.main(WurmServerGuiMain.java:132)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at javassist.Loader.run(Loader.java:288)
	at org.gotti.wurmunlimited.serverlauncher.DelegatedLauncher.main(DelegatedLauncher.java:30)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at javassist.Loader.run(Loader.java:288)
	at org.gotti.wurmunlimited.serverlauncher.ServerLauncher.main(ServerLauncher.java:33)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at PatchedLauncher.main(PatchedLauncher.java:20)

[12:15:12 AM 599] INFO com.wurmonline.server.Servers: Loaded 0 servers from the database
<snip>

DID:
	executed sql-batch file: wurmlogin.sql on WURMLOGIN db

------------------------------------------------------------------------------------------------------------------------------

[12:48:52 AM 065] INFO com.wurmonline.server.Servers: Loaded 0 servers from the database
[12:48:52 AM 066] INFO com.wurmonline.server.Servers: Created properties table in the database
[12:48:52 AM 075] OFF com.wurmonline: 
----------------------------------------------------------------
Wurm Server logging started at Fri Jan 06 00:48:52 CET 2017
----------------------------------------------------------------
[12:48:52 AM 084] SEVERE org.gotti.wurmunlimited.serverlauncher.DelegatedLauncher: null
java.lang.NullPointerException
	at com.wurmonline.server.gui.WurmServerGuiController.startDB(WurmServerGuiController.java:436)
	at com.wurmonline.server.gui.WurmServerGuiMain.main(WurmServerGuiMain.java:132)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at javassist.Loader.run(Loader.java:288)
	at org.gotti.wurmunlimited.serverlauncher.DelegatedLauncher.main(DelegatedLauncher.java:30)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at javassist.Loader.run(Loader.java:288)
	at org.gotti.wurmunlimited.serverlauncher.ServerLauncher.main(ServerLauncher.java:33)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Unknown Source)
	at PatchedLauncher.main(PatchedLauncher.java:20)

DID:
	This is likely because I don't have any servers in the database... SO:
	executed: insertwurmlogin.sql
		- BUT: Had to remove the transaction specific commands (different from mysql most likely)

------------------------------------------------------------------------------------------------------------------------------
That worked, got:
	[12:51:48 AM 001] INFO com.wurmonline.server.Servers: Loaded 1 servers from the database
	[12:51:48 AM 002] INFO com.wurmonline.server.Servers: Created properties table in the database

so, next:
[12:51:48 AM 306] INFO com.wurmonline.server.deities.Deities: Loading deities 
[12:51:48 AM 316] WARNING com.wurmonline.server.DbConnector: Problem opening the deityDbcon
com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown database 'wurmdeities'
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)
	at java.lang.reflect.Constructor.newInstance(Unknown Source)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	<snip>

This is the same but with another DB... So gonna rince/repeat making all tables and inserting all data...

------------------------------------------------------------------------------------------------------------------------------
While creating databases:
	* unsigned smallint does not work in mysql, so replaced with just smallint (could generate bugs this, if they overly rely
	  100% on database to give proper values back
	CREATE TABLE CALENDAR
	(
	    EVENTID                 UNSIGNED SMALLINT(5)   NOT NULL PRIMARY KEY,
	    STARTTIME               UNSIGNED BIGINT(20)    NOT NULL,
	    TYPE                    UNSIGNED SMALLINT(5)   NOT NULL
	);

	* no idea waht the fuck this is ... made it varchar(255) with 'game-server' as default:
    	SOURCE                  VARCHAR(255) DEFAULT 'game-server' NOT NULL
	CREATE TABLE SERVER_STATS_TYPE
	(
	    STATISTIC_ID            VARCHAR(30)   NOT NULL PRIMARY KEY,
	    NAME                    VARCHAR(45)   NOT NULL,
	    DESCRIPTION             VARCHAR(45)   NOT NULL,
	    SOURCE                  'game-server' NOT NULL
	);

	* same as above:
	CREATE TABLE SERVER_STATS_LOG 
		:
    	SERVER_ID               UNSIGNED INT(11)       NOT NULL,
    	:

	* OK! So this means the tables can NOT all be in the same database, that's a bummer!
	  Error Code: 1050. Table 'position' already exists
	  Going through and checking syntax problems of all anyway (will create separate DB's later)

	* DATE is not allowed in this context, should be DATETIME
  	CREATED                   DATE          NOT NULL DEFAULT CURRENT_TIMESTAMP,
	so: CREATED                   DATETIME          NOT NULL DEFAULT CURRENT_TIMESTAMP,

------------------------------------------------------------------------------------------------------------------------------
	By iterating over the .sql files in the sqlite directory, we did:
		- Created databases
		- Inserted tables
		- Inserted data

------------------------------------------------------------------------------------------------------------------------------
UNSOLVED SO FAR:
	Modloader throws an error based on what is defined in Wurm, why, I do not know. It could just
	happily go on using Sqlite. Let's make it so!

java.lang.RuntimeException: Only Sqlite is currently supported
	at org.gotti.wurmunlimited.modsupport.ModSupportDb.checkSqlite(ModSupportDb.java:38)
	at org.gotti.wurmunlimited.modsupport.ModSupportDb.getModSupportDb(ModSupportDb.java:24)
	at org.gotti.wurmunlimited.modsupport.IdFactory.init(IdFactory.java:22)
	at org.gotti.wurmunlimited.modsupport.IdFactory.<init>(IdFactory.java:18)
	at org.gotti.wurmunlimited.modsupport.IdFactory.getInstance(IdFactory.java:35)
	at org.gotti.wurmunlimited.modsupport.IdFactory.getIdFor(IdFactory.java:68)
	at org.gotti.wurmunlimited.modsupport.ItemTemplateBuilder.<init>(ItemTemplateBuilder.java:39)
	at com.friya.wurmonline.server.vamps.items.SmallRat.onItemTemplatesCreated(SmallRat.java:28)
	at com.friya.wurmonline.server.vamps.Mod.addItems(Mod.java:215)
	at com.friya.wurmonline.server.vamps.Mod.onItemTemplatesCreated(Mod.java:205)
	at org.gotti.wurmunlimited.modloader.server.ServerHook.lambda$fireOnItemTemplatesCreated$3(ServerHook.java:49)


------------------------------------------------------------------------------------------------------------------------------
SOLVED
[03:06:25 PM 431] WARNING com.wurmonline.server.epic.EpicMission: Failed to save epic mission status.
java.sql.SQLException: Field 'ID' doesn't have a default value
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:1056)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:957)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3376)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3308)
	at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:1837)
	at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:1961)
	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2543)
	at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1737)
	at com.mysql.jdbc.PreparedStatement.executeUpdate(PreparedStatement.java:2022)
	at com.mysql.jdbc.PreparedStatement.executeUpdate(PreparedStatement.java:1940)
	at com.mysql.jdbc.PreparedStatement.executeUpdate(PreparedStatement.java:1925)
	at com.wurmonline.server.epic.EpicMission.save(EpicMission.java:105)
	at com.wurmonline.server.epic.EpicMission.<init>(EpicMission.java:84)
	at com.wurmonline.server.epic.EpicEntity.sendNewScenarioWebCommand(EpicEntity.java:386)
	at com.wurmonline.server.epic.EpicEntity.checkWinCondition(EpicEntity.java:1062)
	at com.wurmonline.server.epic.HexMap.pollAllEntities(HexMap.java:194)
	at com.wurmonline.server.Server.run(Server.java:2061)
	at java.util.TimerThread.mainLoop(Unknown Source)
	at java.util.TimerThread.run(Unknown Source)

	FIXED, DID:
		* adding auto-index on the id column
		ALTER TABLE `wurmdeities`.`epicmissions` CHANGE COLUMN `ID` `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ;
	

------------------------------------------------------------------------------------------------------------------------------
Fixed so that modloader got patched to NOT rely on Constants.dbHost -- and NOT check if Sqlite is enabled by
simply patching it in preInit()

------------------------------------------------------------------------------------------------------------------------------
Found one more that need AutoInc, getting the hunch that a lot more may need it:
	ALTER TABLE `wurmzones`.`focuszones` CHANGE COLUMN `ID` `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ;


------------------------------------------------------------------------------------------------------------------------------
TODO: Well! We are working nicely, let's optimize shit!

*	31467 # Time: 170107  7:35:35
	31468 # User@Host: root[root] @ localhost [127.0.0.1]  Id:   143
	31469 # Query_time: 0.472285  Lock_time: 0.000501 Rows_sent: 0  Rows_examined: 6
	31470 SET timestamp=1483770935;
	31471 UPDATE SERVERPROPERTIES SET PROPVAL='' WHERE PROPKEY='ADMINPASSWORD';
	-> 0.47 sec?!
	-> did: ALTER TABLE `wurmlogin`.`serverproperties` ADD PRIMARY KEY (`PROPKEY`)  COMMENT '';

*	31754 # Time: 170107  7:36:06
	31755 # User@Host: root[root] @ localhost [127.0.0.1]  Id:   149
	31756 # Query_time: 0.383636  Lock_time: 0.000000 Rows_sent: 0  Rows_examined: 0
	31757 SET timestamp=1483770966;
	31758 insert into CREATURES (NAME, TEMPLATENAME, SEX,CENTIMETERSHIGH, CENTIMETERSLONG, CENTIMETERSWIDE, INVENTORYID,BODYID, BUILDINGID,HUNGER,THIRST, STAMINA,KINGDOM,FAT,STEALT

*	22689 # User@Host: root[root] @ localhost [127.0.0.1]  Id:    81
	22690 # Query_time: 0.148846  Lock_time: 0.000000 Rows_sent: 0  Rows_examined: 0
	22691 SET timestamp=1483763219;
	22692 insert into CREATURES (NAME, TEMPLATENAME, SEX,CENTIMETERSHIGH, CENTIMETERSLONG, CENTIMETERSWIDE, INVENTORYID,BODYID, BUILDINGID,HUNGER,THIRST, STAMINA,KINGDOM,FAT,STEALT

*	32315 # User@Host: root[root] @ localhost [127.0.0.1]  Id:   149
	32316 # Query_time: 0.144800  Lock_time: 0.000000 Rows_sent: 0  Rows_examined: 0
	32317 SET timestamp=1483771037;
	32318 insert into POSITION (POSX, POSY, POSZ, ROTATION,ZONEID,LAYER,ONBRIDGE, WURMID) values (4486.38,513.50415,0.0,296.0,546,0,-10,1139542065153);

*	31645 # User@Host: root[root] @ localhost [127.0.0.1]  Id:   149
	31646 # Query_time: 0.139513  Lock_time: 0.000000 Rows_sent: 0  Rows_examined: 1
	31647 SET timestamp=1483770948;
	31648 update CREATURES set KINGDOM=0 WHERE WURMID=1138921308161;

*	77269 # Time: 170107 16:22:11
	77270 # User@Host: root[root] @ localhost [127.0.0.1]  Id:   143
	77271 # Query_time: 0.042357  Lock_time: 0.000500 Rows_sent: 0  Rows_examined: 1
	77272 SET timestamp=1483802531;
	77273 UPDATE SERVERS SET SKILLDAYSWITCH=1483802531471,SKILLWEEKSWITCH=1483802531471,NEXTEPICPOLL=1483803345971,FATIGUESWITCH=1483798261846,NEXTHOTA=0,WORLDTIME=6128500,TILEREST


------------------------------------------------------------------------------------------------------------------------------
TODO: Import existing data from Sqlite

*/



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;

class Prototype
{
	/*
	-- default DB settings:
	USE_SITE_DB=true
	SITE_DB_DRIVER=org.gjt.mm.mysql.Driver
	SITE_DB_PORT=1
	SITE_DB_HOST=localhost
	SITE_DB_USER=
	SITE_DB_PASS=

	USEDB=true
	DB_DRIVER=
	DBPATH=.
	DB_HOST=localhost
	DB_PORT=1
	DB_USER=root
	DB_PASS=

	USE_LOGIN_DB=true
	LOGIN_DB_DRIVER=
	LOGIN_DB_HOST=localhost
	LOGIN_DB_PORT=1
	LOGIN_DB_USER=
	LOGIN_DB_PASS=

	USE_POOLED_DB=false
	ANALYSE_ALL_DB_TABLES=false
	OPTIMISE_ALL_DB_TABLES=false
	DBSTATS=false
	CHECK_ALL_DB_TABLES=false
	PRUNEDB=true
	PREPSTATEMENTS=false
	*/
	private static Logger logger = Logger.getLogger(Prototype.class.getName());
	

	public static void doThing()
	{
		//com.wurmonline.server.DbConnector - sqlite = false
		
		//localhost 33306 root

		// initialize(boolean)
		//	set it before entering method

		// returns: D:\Program Files (x86)\Steam\steamapps\common\Wurm Unlimited\WurmServerLauncher
		// so add: Creative\sqlite\
		String currentDir = System.getProperty("user.dir");
		logger.info("Current dir using System:" + currentDir);

		if(false) {
			String sqlDir = currentDir + File.separator + "Creative_org" + File.separator + "sqlite";
			
			File folder = new File(sqlDir);
			File[] dir = folder.listFiles();
			
			String fn = null;
			String dbName = null;
	
			Connection mConnection;
			Statement stmt;
			int res;
			
			String SERVER = "127.0.0.1";
			String PORT = "33306";
			String USER = "root";
			String PASSWORD = "fnurt";
	
			try {
			    Class.forName("com.mysql.jdbc.Driver");
			    Connection dblessCon = DriverManager.getConnection("jdbc:mysql://" + SERVER + ":" + PORT + "/?" + "user=" + USER + "&password=" + PASSWORD);
	
			    //
			    // Create databases and tables
			    //
				for (int i = 0; i < dir.length; i++) {
					if (dir[i].isFile() == false) {
						continue;
					}
					
					fn = dir[i].getName();
					
					if(fn.endsWith(".sql") == false) {
						continue;
					}
		
					if(fn.startsWith("export") || fn.startsWith("insert")) {
						continue;
					}
					
					dbName = fn.split("\\.sql")[0].toUpperCase();
		
					logger.info("File " + dir[i].getName() + " -- " + dbName);
	
	
				    // DROP database -- this is, eh, something we don't want when we know everything is working
				    stmt = dblessCon.createStatement();
				    res = stmt.executeUpdate("DROP DATABASE " + dbName);
				    logger.info("DROP DATABASE " + dbName + ", Res: " + res);		// returns 0 by default it seems
				    stmt.close();
	
				    // Create database
				    stmt = dblessCon.createStatement();
				    res = stmt.executeUpdate("CREATE DATABASE " + dbName);
				    logger.info("CREATE DATABASE " + dbName + ", Res: " + res);		// returns 1 if all is well, it seems
				    stmt.close();
	
				    // Execute CREATE TABLE statements in this file
				    mConnection = DriverManager.getConnection("jdbc:mysql://" + SERVER + ":" + PORT + "/" + dbName + "?" + "user=" + USER + "&password=" + PASSWORD);
					String fullFn = sqlDir + File.separator + fn;
					
					logger.info("Executing statements in: " + fullFn);
	
					// http://stackoverflow.com/questions/717436/create-mysql-database-from-java
					ScriptRunner runner = new ScriptRunner(mConnection, false, false);
					
					FileReader fr = new FileReader(fullFn);
					BufferedReader br = new BufferedReader(fr);
					runner.runScript(br);
					fr.close();
					br.close();
	
				    mConnection.close();
				} // for
				
				//
				// Insert data into the tables we just created
				//
				for (int i = 0; i < dir.length; i++) {
					if (dir[i].isFile() == false) {
						continue;
					}
					
					fn = dir[i].getName();
					
					if(fn.startsWith("insert") == false || fn.endsWith(".sql") == false) {
						continue;
					}
		
					dbName = fn.split("\\.sql")[0].replace("insert", "").toUpperCase();
		
					logger.info("File " + dir[i].getName() + " -- " + dbName);
	
				    // Execute INSERTS in this file
				    mConnection = DriverManager.getConnection("jdbc:mysql://" + SERVER + ":" + PORT + "/" + dbName + "?" + "user=" + USER + "&password=" + PASSWORD);
					String fullFn = sqlDir + File.separator + fn;
	
					logger.info("Executing statements in: " + fullFn);
	
					// http://stackoverflow.com/questions/717436/create-mysql-database-from-java
					ScriptRunner runner = new ScriptRunner(mConnection, false, false);

					FileReader fr = new FileReader(fullFn);
					BufferedReader br = new BufferedReader(fr);
					runner.runScript(br);
					fr.close();
					br.close();
	
				    mConnection.close();
				} // for
	
			} catch (SQLException | ClassNotFoundException | IOException e) {
			    logger.log(Level.SEVERE, "Unable to get mysql driver or connect to server: ", e);
			}
		} // if false

		// With this enabled, we'll break Ago's modloader
		//
		// ...actually, reverting to patching Ago's modloader instead!
		patchIsUseSqlite("Creative_org");

		// So, instead we patch every usage of isUseSqlite() in Wurm proper
		//patchIsUseSqliteCalls();
		

	}

	/**
	 * THIS IS TEMPORARY. 
	 * 
	 * Need to convince Ago to modify modloader to always use Sqlite despite what WU is using.
	 * 
	 */
	static private void patchIsUseSqliteCalls()
	{
		// * 		com/wurmonline/server/DbConnector.java: 2 + the method
		patchIsUseSqliteCall("com.wurmonline.server.DbConnector", "initialize", 1, false);			// second method in class
		patchIsUseSqliteCall("com.wurmonline.server.DbConnector", null, 0, true);					// modify constructor
		
		// * 		com/wurmonline/server/Items.java: 3
        patchSqlQueryInField("com.wurmonline.server.Items", "moveItemsToFreezerFor", 0, "moveItemsToFreezerForPlayer", "INSERT IGNORE INTO FROZENITEMS (SELECT * FROM ITEMS WHERE OWNERID=?");
        patchSqlQueryInField("com.wurmonline.server.Items", "returnItemsFromFreezerFor", 0, "returnItemsFromFreezerForPlayer", "INSERT IGNORE INTO ITEMS (SELECT * FROM FROZENITEMS WHERE OWNERID=?");
        patchSqlQueryInField("com.wurmonline.server.Items", "returnItemFromFreezer", 0, "returnItemFromFreezer", "INSERT IGNORE INTO ITEMS (SELECT * FROM FROZENITEMS WHERE WURMID=?");

		// * 		com/wurmonline/server/items/ItemMetaData.java: 1
        patchSqlQueryInField("com.wurmonline.server.items.ItemMetaData", "saveKeys", 0, "INSERT_ITEMKEYS", "INSERT IGNORE INTO ITEMKEYS (LOCKID,KEYID)VALUES(?,?)");
        
		// * 		com/wurmonline/server/items/ItemDbStrings.java: 1
		patchIsUseSqliteCall("com.wurmonline.server.items.ItemDbStrings", "createData", 0, false);	// first method in class

		// * 		com/wurmonline/server/utils/DbIndexManager.java: 3
		patchIsUseSqliteCall("com.wurmonline.server.utils.DbIndexManager", "createIndexes", 0, false);	// first method in class
		patchIsUseSqliteCall("com.wurmonline.server.utils.DbIndexManager", "removeIndexes", 0, false);	// first method in class
		patchIsUseSqliteCall("com.wurmonline.server.utils.DbIndexManager", "repairDatabaseTables", 0, false);	// first method in class
		
		// * 		com/wurmonline/server/economy/DbEconomy.java: 1
		patchIsUseSqliteCall("com.wurmonline.server.economy.DbEconomy", "transaction", 0, false);	// first method in class

		// * 		com/wurmonline/server/economy/LocalSupplyDemand.java: 1
        patchSqlQueryInField("com.wurmonline.server.economy.LocalSupplyDemand", "increaseAllDemands", 0, "INCREASE_ALL_DEMANDS", "UPDATE LOCALSUPPLYDEMAND SET DEMAND=GREATEST(-200.0,DEMAND*1.1)");

		// * 		com/wurmonline/server/players/PlayerMetaData.java: 3
        patchSqlQueryInField("com.wurmonline.server.players.PlayerMetaData", "save", 0, "INSERT_FRIEND", "INSERT IGNORE INTO FRIENDS(WURMID,FRIEND,CATEGORY) VALUES(?,?,?)");
        patchSqlQueryInField("com.wurmonline.server.players.PlayerMetaData", "save", 0, "INSERT_ENEMY", "INSERT IGNORE INTO ENEMIES(WURMID,ENEMY) VALUES(?,?)");
        patchSqlQueryInField("com.wurmonline.server.players.PlayerMetaData", "save", 0, "INSERT_IGNORED", "INSERT IGNORE INTO IGNORED(WURMID,IGNOREE) VALUES(?,?)");

		// * 		com/wurmonline/server/players/Achievement.java: 2
        patchSqlQueryInField("com.wurmonline.server.players.Achievement", "create", 0, "INSERT", "INSERT IGNORE INTO ACHIEVEMENTS (PLAYER,ACHIEVEMENT,COUNTER) VALUES (?,?,?)");
        patchSqlQueryInField("com.wurmonline.server.players.Achievement", "create", 0, "INSERT_TRANSFER", "INSERT IGNORE INTO ACHIEVEMENTS (PLAYER,ACHIEVEMENT,COUNTER,ADATE) VALUES (?,?,?,?)");

		// * 		com/wurmonline/server/players/DbPlayerInfo.java: 3
        patchSqlQueryInField("com.wurmonline.server.players.DbPlayerInfo", "saveFriend", 0, "ADD_FRIEND", "INSERT IGNORE INTO FRIENDS (WURMID,FRIEND,CATEGORY) VALUES(?,?,?)");
        patchSqlQueryInField("com.wurmonline.server.players.DbPlayerInfo", "saveEnemy", 0, "ADD_ENEMY", "IGNORE INTO ENEMIES (WURMID,ENEMY) VALUES(?,?)");
        patchSqlQueryInField("com.wurmonline.server.players.DbPlayerInfo", "saveIgnored", 0, "ADD_IGNORED", "INSERT IGNORE INTO IGNORED (WURMID,IGNOREE) VALUES(?,?)");
        
		// * 		com/wurmonline/server/players/EpicPlayerTransferMetaData.java: 2
        patchSqlQueryInField("com.wurmonline.server.players.EpicPlayerTransferMetaData", "save", 0, "INSERT_FRIEND", "INSERT IGNORE INTO FRIENDS(WURMID,FRIEND,CATEGORY) VALUES(?,?,?)");
        patchSqlQueryInField("com.wurmonline.server.players.EpicPlayerTransferMetaData", "save", 0, "INSERT_IGNORED", "INSERT IGNORE INTO IGNORED(WURMID,IGNOREE) VALUES(?,?)");

		// * 		com/wurmonline/server/players/AchievementGenerator.java: 1
        patchSqlQueryInField("com.wurmonline.server.players.AchievementGenerator", "insertAchievementTemplate", 0, "INSERT", "INSERT IGNORE INTO ACHIEVEMENTTEMPLATES (NUMBER,NAME,TRIGGERON,DESCRIPTION,CREATORNAME,ATYPE,PLAYUPDATE) VALUES (?,?,?,?,?,?,?)");

		// * 		com/wurmonline/server/support/Tickets.java: 1
		patchIsUseSqliteCall("com.wurmonline.server.support.Tickets", "dbLoadAllTickets", 0, false);	// first method in class
		
		// * 		com/wurmonline/server/epic/MissionHelper.java: 1
        patchSqlQueryInField("com.wurmonline.server.epic.MissionHelper", "setHelps", 0, "INSERT_MISSION_HELPER", "INSERT IGNORE INTO MISSIONHELPERS (NUMS, MISSIONID, PLAYERID) VALUES(?,?,?)");

        // Well, the below does not work because dbHost contains a PATH, it's not empty as I initially thought/saw (no idea how I missed it) 
        
        //
        // The GUI handler hardcode dbPorts, dbHosts *groan* ... make it not do that.
        //
        
        // com.wurmonline.server.gui.WurmServerGuiController buildDatabaseComboBox()
        // hook on call to DbConnector.closeAll();
        
        // com.wurmonline.server.gui.WurmServerGuiController startDB()
        // hook on call to DbConnector.closeAll();

		try {
			CtClass ctc = HookManager.getInstance().getClassPool().get("com.wurmonline.server.gui.WurmServerGuiController");
	        ctc.getDeclaredMethods("buildDatabaseComboBox")[0].instrument(new ExprEditor(){
	            public void edit(MethodCall m) throws CannotCompileException {
	                if (m.getMethodName().equals("closeAll")) {
	                	logger.info("closeAll patch to undo change of Constants");
	                    m.replace("com.wurmonline.server.Constants.load(); $_ = $proceed($$);");
	                    return;
	                }
	            }
	        });

	        ctc.getDeclaredMethods("startDB")[0].instrument(new ExprEditor(){
	            public void edit(MethodCall m) throws CannotCompileException {
	                if (m.getMethodName().equals("closeAll")) {
	                	logger.info("closeAll patch to undo change of Constants");
	                    m.replace("com.wurmonline.server.Constants.load(); $_ = $proceed($$);");
	                    return;
	                }
	            }
	        });
		} catch (NotFoundException | CannotCompileException e) {
			logger.log(Level.SEVERE, "Failed", e);
		}
	}
	
	static private void patchSqlQueryInField(String className, String methodName, int methodNum, String fieldName, String sql)
	{
		logger.info("patchSqlQueryInField()");
		
		try {
	        CtClass ctc = HookManager.getInstance().getClassPool().get(className);
	        ctc.getDeclaredMethods(methodName)[methodNum].instrument(new ExprEditor(){
	            public void edit(FieldAccess m) throws CannotCompileException {
	                if (m.getFieldName().equals(fieldName)) {
	                	logger.info("mySql patch field: " + className + "." + methodName + " " + fieldName);
	                    m.replace("$_ = \"" + sql + "\";");
	                    return;
	                }

	            }
	        });
		} catch (NotFoundException | CannotCompileException e) {
			logger.log(Level.SEVERE, "Failed", e);
		}
	}

	static private void patchIsUseSqliteCall(String className, String methodName, int methodNum, boolean isConstructor)
	{
		logger.info("patchIsUseSqliteCall()");
		
		try {
			CtClass ctc = HookManager.getInstance().getClassPool().get(className);
			if(isConstructor) {
		        ctc.getDeclaredConstructors()[methodNum].instrument(new ExprEditor(){
		            public void edit(MethodCall m) throws CannotCompileException {
		                if (m.getMethodName().equals("isUseSqlite")) {
		                	logger.info("mySql patch call: " + className + "." + methodName);
		                    m.replace("$_ = false;");
		                    return;
		                }
		            }
		        });
			} else {
		        ctc.getDeclaredMethods(methodName)[methodNum].instrument(new ExprEditor(){
		            public void edit(MethodCall m) throws CannotCompileException {
		                if (m.getMethodName().equals("isUseSqlite")) {
		                	logger.info("mySql patcher: " + className + "." + methodName + " method call");
		                    m.replace("$_ = false;");
		                    return;
		                }
		            }
		        });
			}

		} catch (NotFoundException | CannotCompileException e) {
			logger.log(Level.SEVERE, "Failed", e);
		}
	}
	
	/**
	 * FOR NOW, WAIT FOR Ago's Modlauncher to hopefully get an update (PM'd Ago about it)
	 * 
	 * We don't want to use this one. Modloader uses isUseSqlite() too, so until that
	 * one is patched we should just hook into and change all occurrences of the call
	 * in the various places instead.
	 * 
	 */
	static private void patchIsUseSqlite(String serverDirName)
	{
        // 1:
        // Okay, so we need to patch the reference to dbHost and dbPort in buildConnectors, and I have no 
        // fucking idea how that will work with generic abstract classes. In fact. I gave up before even
        // attempting.
        
        // 2:
        // Can we patch Ago's modloader? Let's find out.
		try {
			CtClass ctc;
			ctc = HookManager.getInstance().getClassPool().get("org.gotti.wurmunlimited.modsupport.ModSupportDb");

			CtMethod m = ctc.getDeclaredMethod("getDbConnectionString");
	        m.setBody("{"
	        		+ "return \"jdbc:sqlite:" + System.getProperty("user.dir").replace("\\", "/") + "/" + serverDirName + "/sqlite/modsupport-mysqlpatch.db\";"
	        		+ "}");

	        
	        ctc.getDeclaredMethods("checkSqlite")[0].instrument(new ExprEditor(){
	            public void edit(MethodCall m) throws CannotCompileException {
	                if (m.getMethodName().equals("isUseSqlite")) {
	                	logger.info("patching Ago's modloader 2 (!)");
	                    m.replace("$_ = true;");
	                    return;
	                }
	            }
	        });
		} catch (NotFoundException | CannotCompileException e) {
			logger.log(Level.SEVERE, "Failed", e);
		}

		// --- 
		// some hooks to force it into MySQL
		
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
						
						logger.log(Level.INFO, "DbConnector.initialize() WARNING: Reloading constants!");

						// TODO: We don't want this here, if this is the problem, we should just see to it that the DB vars don't get overridden or use our own
						Constants.load();
						
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
						
						logger.log(Level.INFO, "DbConnector.setUseSqlite() WARNING: Reloading constants!");

						// TODO: We don't want this here, if this is the problem, we should just see to it that the DB vars don't get overridden or use our own
						Constants.load();
						
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
						
						logger.log(Level.INFO, "DbConnector.closeAll() WARNING: Reloading constants!");

						// TODO: We don't want this here, if this is the problem, we should just see to it that the DB vars don't get overridden or use our own
						Constants.load();

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
