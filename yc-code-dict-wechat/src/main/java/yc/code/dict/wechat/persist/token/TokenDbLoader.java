package yc.code.dict.wechat.persist.token;

import yc.code.dict.wechat.data.Token;
import yc.code.dict.wechat.persist.KeyNotFoundException;
import yc.code.dict.wechat.persist.PersistenceException;

public interface TokenDbLoader {

	Token loadByType(String type) throws KeyNotFoundException, PersistenceException;

}
