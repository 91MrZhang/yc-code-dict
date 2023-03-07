package yc.code.dict.wechat.db.schema;

import java.sql.Connection;
import java.sql.Statement;

import yc.code.dict.wechat.config.AppInfo;
import yc.code.dict.wechat.db.ConnectionManager;

/**
 * Oracl自动建表实现类
 *
 * <p>建了一个用户表，一个Token缓存表
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class OracleWeChatDbTable extends AbstractWeChatDbTable{
	
	@Override
	public void initWeChatTable() {
		createWeChatUserTable();
		createWeChatTokenTable();
		
	}
	
	private static String USER_TABLE_SCRIPT = "create table {@tablePrefix@}_WECHAT_USER(\r\n" + 
											"pk1 VARCHAR2(16),	\r\n" + 
											"unionid NVARCHAR2(256),\r\n" + 
											"openid NVARCHAR2(256),\r\n" + 
											"nickname NVARCHAR2(256),\r\n" + 
											"sex number(1) DEFAULT 0 null,\r\n" + 
											"language NVARCHAR2(50),\r\n" + 
											"city NVARCHAR2(100),\r\n" + 
											"province NVARCHAR2(100),\r\n" + 
											"country NVARCHAR2(100),\r\n" + 
											"headimgurl NVARCHAR2(500),\r\n" + 
											"subscribe_time number(10) DEFAULT 0 null,\r\n" + 
											"remark NVARCHAR2(600),\r\n" + 
											"groupid NVARCHAR2(600),\r\n" + 
											"tagid_list NVARCHAR2(600),\r\n" + 
											"subscribe_scene NVARCHAR2(256),\r\n" + 
											"qr_scene NVARCHAR2(256),\r\n" + 
											"qr_scene_str NVARCHAR2(256),\r\n" + 
											"bind_time DATE DEFAULT sysdate,\r\n" +
											"disbind_time DATE,\r\n" +
											"status number(1) DEFAULT 0 null\r\n" + 
											")";

	private static String USER_TABLE_PRIMARY_KEY = "alter table {@tablePrefix@}_WECHAT_USER add constraint {@tablePrefix@}_WECHAT_USER_PK1 primary key(pk1)";
	
	
	private static String TOKEN_TABLE_SCRIPT = "CREATE TABLE {@tablePrefix@}_WECHAT_TOEKN(	\r\n" + 
												"PK1 VARCHAR2(16), \r\n" + 
												"token_type VARCHAR2(20), \r\n" + 
												"token_value NVARCHAR2(1000), \r\n" + 
												"apply_time  DATE DEFAULT sysdate, \r\n" + 
												"expires_in NUMBER(10,0) DEFAULT 0\r\n" + 
												")";
	private void createWeChatUserTable() {
		if (isWeChatUserTableExsit()) {
			return;
		}
		Connection connection  = null;
		USER_TABLE_SCRIPT = USER_TABLE_SCRIPT.replace("{@tablePrefix@}", AppInfo.getAutoTablePrefix());
		USER_TABLE_PRIMARY_KEY = USER_TABLE_PRIMARY_KEY.replace("{@tablePrefix@}", AppInfo.getAutoTablePrefix());
		try {
			connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(USER_TABLE_SCRIPT);
			statement.execute(USER_TABLE_PRIMARY_KEY);
			ConnectionManager.closeConnection(connection);
			System.out.println("WeChatUserTable create Complete!");
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.closeConnection(connection);
			System.out.println("auto create WeChatUserTable fail!");
		}
	}

	private void createWeChatTokenTable() {
		if (isWeChatTokenTableExsit()) {
			return;
		}
		Connection connection  = null;
		TOKEN_TABLE_SCRIPT = TOKEN_TABLE_SCRIPT.replace("{@tablePrefix@}", AppInfo.getAutoTablePrefix());
		try {
			connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(TOKEN_TABLE_SCRIPT);
			ConnectionManager.closeConnection(connection);
			System.out.println("WeChatTokenTable create Complete!");
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.closeConnection(connection);
			System.out.println("auto create WeChatTokenTable fail!");
		}
	}

	private Boolean isWeChatUserTableExsit() {
		Connection connection  = null;
		String sql = "select count(1) from " + AppInfo.getAutoTablePrefix() + "_WECHAT_USER";
		try {
			connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(sql);
			ConnectionManager.closeConnection(connection);
			return true;
		} catch (Exception e) {
			ConnectionManager.closeConnection(connection);
			return false;
		}
	}
	
	private Boolean isWeChatTokenTableExsit() {
		Connection connection  = null;
		String sql = "select count(1) from " + AppInfo.getAutoTablePrefix() + "_WECHAT_TOEKN";
		try {
			connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(sql);
			ConnectionManager.closeConnection(connection);
			return true;
		} catch (Exception e) {
			ConnectionManager.closeConnection(connection);
			return false;
		}
	}


}
