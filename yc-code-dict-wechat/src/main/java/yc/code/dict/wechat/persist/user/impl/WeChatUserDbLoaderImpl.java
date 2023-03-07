package yc.code.dict.wechat.persist.user.impl;

import yc.code.dict.wechat.data.WeChatUser;
import yc.code.dict.wechat.persist.KeyNotFoundException;
import yc.code.dict.wechat.persist.PersistenceException;
import yc.code.dict.wechat.persist.user.WeChatUserDbLoader;

/**
 * 等哪天工作不忙的时候再把这个给实现了
 *
 * <p>稍等片刻
 *
 * @author 91MrZhang
 * @since 1.0.0
 */
public class WeChatUserDbLoaderImpl implements WeChatUserDbLoader{

	@Override
	public WeChatUser loadByPk1(String paramPk1) throws KeyNotFoundException, PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeChatUser loadByOpenId(String paramOpenId) throws KeyNotFoundException, PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeChatUser loadByUnionid(String paramUnionid) throws KeyNotFoundException, PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
