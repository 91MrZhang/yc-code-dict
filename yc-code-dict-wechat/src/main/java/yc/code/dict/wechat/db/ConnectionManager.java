package yc.code.dict.wechat.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;

import yc.code.dict.wechat.context.WeChatApplicationContext;

/**
 * ConnectionManager
 * 数据库连接处理的工具类
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class ConnectionManager {

	/**
	 * ThreadLocal处理当前执行的链接,主要用在事务控制和回滚
	 */
	public static ThreadLocal<Connection> currentConn = new ThreadLocal<>();

	/**
	 * 从连接池获取连接
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			DataSource dataSource = WeChatApplicationContext.INSTANCE.getDataSource();
			Connection connection = dataSource.getConnection();
			currentConn.set(connection);
			return connection;
		} catch (SQLException e) {
			System.out.println("Can't GetConnection From DataSource");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 关闭Conn还给连接池
	 */
	public static void closeConnection(Connection conn) {
		if (conn == null) {
			DbUtils.closeQuietly(conn);
		} else {
			try {
				// 关闭之前要将自动提交恢复，再还给还给数据库连接池
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("Close Connection Can't Change AutoCommit to Default");
				e.printStackTrace();
			}
			currentConn.remove();
			DbUtils.closeQuietly(conn);
		}

	}

	/**
	 * 关闭Conn还给连接池
	 */
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		if (conn == null) {
			DbUtils.closeQuietly(conn);
		} else {
			try {
				// 关闭之前要将自动提交恢复，再还给还给数据库连接池
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("Close Connection Can't Change AutoCommit to Default");
				e.printStackTrace();
			}
			currentConn.remove();
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * 事务开启
	 */
	public static void beginTransaction() {
		Connection conn = currentConn.get();
		if (conn == null) {
			conn = getConnection();
			currentConn.set(conn);
		}
		try {
			conn.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println("Can't Open Transaction!");
			e.printStackTrace();
		}
	}

	/**
	 * 提交
	 * 未开启事务的情况下，不用commit
	 * 因为默认的DML语句JDBC都是自动提交的
	 */
	public static void commitTransaction() {
		try {
			Connection conn = currentConn.get();
			if (null != conn) {
				conn.commit();
			}
		} catch (Exception e) {
			System.out.println("Commit Error!");
			e.printStackTrace();
		}
	}

	/**
	 * 回滚
	 * 未开启事务的情况下，不用commit
	 * 因为默认的DML语句JDBC都是自动提交的，回滚也什么都滚不回来
	 */
	public static void rollbackTransaction() {
		try {
			Connection conn = currentConn.get();
			if (conn != null) {
				conn.rollback();
				currentConn.remove();
			}
		} catch (Exception e) {
			System.out.println("Rollback Error!");
			e.printStackTrace();
		}
	}
	
}
