package net.simpleframework.organization;

import net.simpleframework.ctx.ado.IADOModuleContext;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IOrganizationContext extends IADOModuleContext {

	/**
	 * 获取账号管理器
	 * 
	 * @return
	 */
	IAccountManager getAccountMgr();

	/**
	 * 获取用户管理器
	 * 
	 * @return
	 */
	IUserManager getUserMgr();

	/**
	 * 获取部门管理器
	 * 
	 * @return
	 */
	IDepartmentManager getDepartmentMgr();

	/**
	 * 获取角色管理器
	 * 
	 * @return
	 */
	IRoleManager getRoleMgr();

	/**
	 * 获取角色成员管理器
	 * 
	 * @return
	 */
	IRoleMemberManager getRoleMemberMgr();

	/**
	 * 获取角色视图管理器
	 * 
	 * @return
	 */
	IRoleChartManager getRoleChartMgr();
}
