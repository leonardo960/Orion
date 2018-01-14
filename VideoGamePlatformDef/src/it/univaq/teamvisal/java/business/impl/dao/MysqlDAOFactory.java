package it.univaq.teamvisal.java.business.impl.dao;

public class MysqlDAOFactory {
	private static MysqlDAOFactory instance;
	
	private MysqlGameManager mysqlGameManager;
	private MysqlMessageManager mysqlMessageManager;
	private MysqlReviewManager mysqlReviewManager;
	private MysqlUserManager mysqlUserManager;
	
	private MysqlDAOFactory(){
		mysqlGameManager = null;
		mysqlMessageManager = null;
		mysqlReviewManager = null;
		mysqlUserManager = null;
	}
	
	static public MysqlDAOFactory getInstance(){
		if(!(instance instanceof MysqlDAOFactory)){
			instance = new MysqlDAOFactory();
		}
		
	    return instance;
	}
	
	public MysqlGameManager getMysqlGameManager(){
		if(mysqlGameManager == null){
			mysqlGameManager = new MysqlGameManager();
		}
		
	    return mysqlGameManager;
	}
	
	public MysqlMessageManager getMysqlMessageManager(){
		if(mysqlMessageManager == null){
			mysqlMessageManager = new MysqlMessageManager();
		}
		
	    return mysqlMessageManager;
	}
	
	public MysqlReviewManager getMysqlReviewManager(){
		if(mysqlReviewManager == null){
			mysqlReviewManager = new MysqlReviewManager();
		}
		
	    return mysqlReviewManager;
	}
	
	public MysqlUserManager getMysqlUserManager(){
		if(mysqlUserManager == null){
			mysqlUserManager = new MysqlUserManager();
		}
		
	    return mysqlUserManager;
	}
	
	
}
