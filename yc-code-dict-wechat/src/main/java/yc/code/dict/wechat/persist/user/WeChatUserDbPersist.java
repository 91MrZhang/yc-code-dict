package yc.code.dict.wechat.persist.user;

import yc.code.dict.wechat.data.WeChatUser;
import yc.code.dict.wechat.persist.KeyNotFoundException;
import yc.code.dict.wechat.persist.PersistenceException;

public interface WeChatUserDbPersist {

	void persist(WeChatUser paramWeChatUser) throws PersistenceException;

	void delete(WeChatUser paramWeChatUser) throws KeyNotFoundException, PersistenceException;

	void deleteByPk1(String paramPk1) throws KeyNotFoundException, PersistenceException;

}
