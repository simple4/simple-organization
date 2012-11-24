package net.simpleframework.organization.impl;

import java.io.Serializable;

import net.simpleframework.ctx.ado.AbstractBeanDbManager;
import net.simpleframework.organization.IOrganizationContext;
import net.simpleframework.organization.OrganizationContextFactory;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class AbstractOrganizationManager<T extends Serializable, M extends T> extends
		AbstractBeanDbManager<T, M> {

	@Override
	public IOrganizationContext getModuleContext() {
		return OrganizationContextFactory.get();
	}

	public AccountManager accountMgr() {
		return (AccountManager) getModuleContext().getAccountMgr();
	}

	public UserManager userMgr() {
		return (UserManager) getModuleContext().getUserMgr();
	}

	public DepartmentManager departmentMgr() {
		return (DepartmentManager) getModuleContext().getDepartmentMgr();
	}

	public RoleChartManager roleChartMgr() {
		return (RoleChartManager) getModuleContext().getRoleChartMgr();
	}

	public RoleManager roleMgr() {
		return (RoleManager) getModuleContext().getRoleMgr();
	}

	public RoleMemberManager roleMemberMgr() {
		return (RoleMemberManager) getModuleContext().getRoleMemberMgr();
	}
}
