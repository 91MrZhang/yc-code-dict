package yc.code.dict.wechat.db;

import yc.code.dict.wechat.config.AppInfo;
import yc.code.dict.wechat.db.schema.AbstractWeChatDbTable;
import yc.code.dict.wechat.db.schema.MSSQLWeChatDbTable;
import yc.code.dict.wechat.db.schema.MysqlWeChatDbTable;
import yc.code.dict.wechat.db.schema.OracleWeChatDbTable;
import yc.code.dict.wechat.db.schema.PostgreSQLWeChatDbTable;

/**
 * 微信数据库初始化类
 *
 * <p>指定了数据源，调用这个方法可以在项目启动时初始化一些DDL脚本
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class WeChatDataBaseInitialize {
	
	public void init() {
		autoCreateTable();
	}
	
	public void autoCreateTable() {
		try {
			AbstractWeChatDbTable autoWeChatDbTable;
			if (DbType.Oracle.equals(AppInfo.DB_TYPE)) autoWeChatDbTable = new OracleWeChatDbTable();
			else if (DbType.Mysql.equals(AppInfo.DB_TYPE)) autoWeChatDbTable = new MysqlWeChatDbTable();
			else if (DbType.MSSQL.equals(AppInfo.DB_TYPE)) autoWeChatDbTable = new MSSQLWeChatDbTable();
			else if (DbType.PostgreSQL.equals(AppInfo.DB_TYPE)) autoWeChatDbTable = new PostgreSQLWeChatDbTable();
			else return;
			autoWeChatDbTable.initWeChatTable();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("something wrong when autoCreateTable!");
		}
	}
}
