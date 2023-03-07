package yc.code.dict.wechat.persist;

/**
 * 自定义异常KeyNotFoundException
 *
 * <p>目的是使用本工具包对数据库DML时
 * <p>可以向外抛出异常,方便引用的项目做事务管理
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class KeyNotFoundException extends PersistenceException {

	private static final long serialVersionUID = -4636555015500439531L;

	public KeyNotFoundException(String msg) {
		super(msg);
	}

	public KeyNotFoundException(Throwable nestedException) {
		super(nestedException);
	}

	public KeyNotFoundException(String msg, Exception nestedException) {
		super(msg, nestedException);
	}
}
