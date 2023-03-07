package yc.code.dict.wechat.persist.user;

import yc.code.dict.wechat.data.WeChatUser;
import yc.code.dict.wechat.persist.KeyNotFoundException;
import yc.code.dict.wechat.persist.PersistenceException;

public interface WeChatUserDbLoader {

	WeChatUser loadByPk1(String paramPk1) throws KeyNotFoundException, PersistenceException;
	
	WeChatUser loadByOpenId(String paramOpenId) throws KeyNotFoundException, PersistenceException;
	
	WeChatUser loadByUnionid(String paramUnionid) throws KeyNotFoundException, PersistenceException;
	
	//目前阶段无必要实现下属方法
	//List<WeChatUser> loadAll() throws KeyNotFoundException, PersistenceException;

}
