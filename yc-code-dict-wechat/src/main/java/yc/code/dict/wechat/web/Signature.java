package yc.code.dict.wechat.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import yc.code.dict.wechat.config.AppInfo;

/**
 * Signature
 *
 * <p>接入时，微信会发送请求,让两边验证
 * <p>如果报AES之类的错,百度一下,替换一下JRE包即可
 *
 * @see <a href="https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html">微信公众号接入指南</a>
 *	
 * @author 91MrZhang
 * @since 1.0.0
 */
public class Signature {
	
    /**
     * 验证签名
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp	时间戳
     * @param nonce	随机数
     * @param echostr 随机字符串
     * @return 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
     */
    public static String checkSignature(String signature, String timestamp, String nonce, String echostr) {
        String[] arr = new String[] { AppInfo.TOKEN, timestamp, nonce };
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (String s : arr) {
            content.append(s);
        }
        String tmpStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (tmpStr != null && tmpStr.equals(signature.toUpperCase())) {
        	return echostr;
        } else {
        	return "";
        }
    }

    /**
     * 字典排序
     */
    public static void sort(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[i]) < 0) {
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    
    
    /**
     * 将字节数组转换为十六进制字符串
     */
	public static String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (byte b : byteArray) {
            strDigest.append(byteToHexStr(b));
        }
        return strDigest.toString();
    }
	
    /**
     * 将字节转换为十六进制字符串
     *
     */
    public static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return new String(tempArr);
    }
}
