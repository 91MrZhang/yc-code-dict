package yc.code.dict.wechat.persist;

/**
 * 自定义异常PersistenceException
 *
 * <p>目的是使用本工具包对数据库DML时
 * <p>可以向外抛出异常,方便引用的项目做事务管理
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class PersistenceException extends Exception {

	private static final long serialVersionUID = 5121826862955076866L;

	public PersistenceException(String msg) {
		super(msg);
	}

	public PersistenceException(Throwable nestedException) {
		this(nestedException.toString(), nestedException);
	}

	public PersistenceException(String msg, Throwable persistenceException) {
		super(msg, persistenceException);
	}

	public String getFullMessageTrace() {
		StringBuilder sb = new StringBuilder(toString());
		Throwable exc = getCause();
		if (exc != null)
			sb.append(exc);
		return sb.toString();
	}
}
