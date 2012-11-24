package net.simpleframework.organization;

import java.util.Map;

import net.simpleframework.common.ID;
import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.ctx.ado.IBeanManagerAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IAccountManager extends IBeanManagerAware<IAccount> {
	static final int ALL = -1, STATE_NORMAL_ID = -11, STATE_REGISTRATION_ID = -12,
			STATE_LOCKED_ID = -13, STATE_DELETE_ID = -14;

	static final int ONLINE_ID = -2;

	static final int NO_DEPARTMENT_ID = -3;

	static final int DEPARTMENT_ID = -4;

	IAccount getAccountByName(String name);

	/**
	 * 根据账号获取用户
	 * 
	 * @param account
	 * @return
	 */
	IUser getUser(Object id);

	/**
	 * 账号保存及插入操作
	 * 
	 * @param id
	 * @param name
	 * @param password
	 * @param accountMark
	 * @param userData
	 */
	void doSave(Object id, String name, String password, EAccountMark accountMark,
			Map<String, String> userData);

	/**
	 * 恢复删除的账号
	 * 
	 * @param ids
	 * @return
	 */
	int undelete(Object... ids);

	/**
	 * 设置登录帐号
	 * 
	 * @param accountSession
	 * @param loginId
	 */
	void setLogin(IAccountSession accountSession, Object loginId);

	/**
	 * 获取当前的登录帐号
	 * 
	 * @param accountSession
	 * @return
	 */
	ID getLoginId(IAccountSession accountSession);

	/**
	 * 注销
	 * 
	 * @param accountSession
	 */
	void logout(IAccountSession accountSession);

	/**
	 * 检测密码是否正确
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	boolean verifyPassword(IAccount account, String password);

	/**
	 * 根据类型获取账号列表。
	 * 
	 * @param type
	 * @return
	 */
	IDataQuery<? extends IAccount> query(int type);

	/**
	 * 获取部门下的账号
	 * 
	 * @param dept
	 * @return
	 */
	IDataQuery<? extends IAccount> query(IDepartment dept);
}
