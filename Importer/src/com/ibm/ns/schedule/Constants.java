package com.ibm.ns.schedule;

public class Constants {
	
	//split char
	public static String FILE_LINE_SPLIT_MARK=":::";
	
	//date file path
	public static String FILE_PATH="C:\\\\Users\\\\IBM_ADMIN\\\\Desktop\\\\ScheduleImport";
	
	/*
	 * MongoDB 相关配置
	 */
	//MongoDB 数据库IP
//	public final static String MONGODB_HOST="9.110.168.184";
//	public final static String MONGODB_HOST="192.168.0.111";
	public final static String MONGODB_HOST="9.110.168.104";
	//MongoDB 端口
	public final static int MONGDB_PORT=27017;
	//MongoDB 实例名
	public final static String MONGODB_NAME="test";
	
	/*
	 * ImportTask 相关配置
	 */
	//轮训时间间隔，单位秒
	public final static int IMPORT_TASK_INTERVAL=10;

}
