package yc.code.dict.wechat.context;

import javax.sql.DataSource;

import yc.code.dict.wechat.config.AppInfo;
import yc.code.dict.wechat.config.WeChatMainConfig;
import yc.code.dict.wechat.db.DbType;
import yc.code.dict.wechat.db.WeChatDataBaseInitialize;

/**
 * 微信工具包注册容器
 *
 * <p>1、需要在引用微信工具包的项目启动时，初始化该类并注册
 * <P>2、把微信公众号APP的信息注册上
 * <P>3、如果需要工具包来帮助你做DB持久化，就把DataSource也注册进来
 * <P>4、Redis目前暂未适配
 * 
 * @see WeChatMainConfig
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public enum WeChatApplicationContext {
	
	INSTANCE;
	
	private DataSource dataSource = null;

	/**
	 * 公众号主要信息注入
	 */
	public WeChatApplicationContext registerWeChatInfo(WeChatMainConfig weChatMainConfig) {
		AppInfo.setWeChatMainConfig(weChatMainConfig);
		return this;
	}
	
	/**
	 * 数据源注入
	 * 
	 * <p>非必须项
	 * <p>如果注入的话,可以使用Persist相关方法
	 * <p>推荐直接将Spring中的DataSouce引入
	 *
	 */
	public WeChatApplicationContext registerDataSouce(DataSource dataSourceParam, DbType dbtype) {
		this.dataSource = dataSourceParam;
		AppInfo.DB_TYPE = dbtype;
		return this;
	}
	
	/**
	 * 数据源注入(自动建表)
	 * 
	 * <p>非必须项
	 * <p>如果注入的话,可以使用Persist相关方法
	 * <p>推荐直接将Spring中的DataSouce引入
	 *
	 * @return WeChatGzhConfigContext
	 */
	public WeChatApplicationContext registerDataSource(DataSource dataSourceParam, DbType dbtype, String tablePrefix) {
		registerDataSouce(dataSourceParam,dbtype);
		AppInfo.setAutoTablePrefix(tablePrefix);
		WeChatDataBaseInitialize weChatDatabaseInitialize = new WeChatDataBaseInitialize();
		weChatDatabaseInitialize.init();
		return this;
	}
	
	/**
	 * 获取上下文中数据源
	 * 
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}
}
