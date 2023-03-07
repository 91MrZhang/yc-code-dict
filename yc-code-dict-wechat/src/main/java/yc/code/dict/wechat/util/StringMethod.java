package yc.code.dict.wechat.util;

/**
 * StringMethod
 *
 * <p>主要处理一些空值字符串
 * <p>为了不和主流开发包StringUtils冲突，所以在这里用StringMethod命名
 * 
 * @author 91MrZhang
 * @since 1.0.0
 */
public class StringMethod {

	public static boolean notEmpty(Object str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	public static String toStrictStr(Object str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return (String) str;
		}
	}
}
