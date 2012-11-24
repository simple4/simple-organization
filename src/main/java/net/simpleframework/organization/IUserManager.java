package net.simpleframework.organization;

import java.io.InputStream;

import net.simpleframework.ctx.ado.IBeanManagerAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IUserManager extends IBeanManagerAware<IUser> {

	/**
	 * 根据用户获取账号
	 * 
	 * @param user
	 * @return
	 */
	IAccount getAccount(Object id);

	/**
	 * 获取用户的头像
	 * 
	 * @param user
	 * @return
	 */
	InputStream getPhoto(IUser user);

	/**
	 * 更改用户的照片
	 * 
	 * @param id
	 * @param photo
	 */
	void updatePhoto(Object id, InputStream photo);
}
