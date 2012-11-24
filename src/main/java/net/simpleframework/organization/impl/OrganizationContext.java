package net.simpleframework.organization.impl;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ado.db.common.DbColumn;
import net.simpleframework.ado.db.common.DbTable;
import net.simpleframework.ctx.Module;
import net.simpleframework.ctx.ado.AbstractADOModuleContext;
import net.simpleframework.organization.IAccountManager;
import net.simpleframework.organization.IDepartmentManager;
import net.simpleframework.organization.IOrganizationContext;
import net.simpleframework.organization.IRoleChartManager;
import net.simpleframework.organization.IRoleManager;
import net.simpleframework.organization.IRoleMemberManager;
import net.simpleframework.organization.IUserManager;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class OrganizationContext extends AbstractADOModuleContext implements
		IOrganizationContext {

	@Override
	public void onInit() throws Exception {
		super.onInit();

		dataServiceFactory
				.putEntityService(Account.class, new DbTable("sf_organization_account"))
				.putEntityService(User.class, new DbTable("sf_organization_user"))
				.putEntityService(UserLob.class, new DbTable("sf_organization_user_lob", true))
				.putEntityService(Department.class, new DbTable("sf_organization_department"))
				.putEntityService(Role.class,
						new DbTable("sf_organization_role").setDefaultOrder(DbColumn.order))
				.putEntityService(RoleChart.class,
						new DbTable("sf_organization_rolechart").setDefaultOrder(DbColumn.order))
				.putEntityService(RoleMember.class, new DbTable("sf_organization_rolemember"));
	}

	@Override
	protected Module createModule() {
		return new Module().setName("simple-organization").setText($m("OrganizationContext.0"))
				.setOrder(1);
	}

	@Override
	public IAccountManager getAccountMgr() {
		return singleton(AccountManager.class);
	}

	@Override
	public IUserManager getUserMgr() {
		return singleton(UserManager.class);
	}

	@Override
	public IDepartmentManager getDepartmentMgr() {
		return singleton(DepartmentManager.class);
	}

	@Override
	public IRoleManager getRoleMgr() {
		return singleton(RoleManager.class);
	}

	@Override
	public IRoleMemberManager getRoleMemberMgr() {
		return singleton(RoleMemberManager.class);
	}

	@Override
	public IRoleChartManager getRoleChartMgr() {
		return singleton(RoleChartManager.class);
	}
}
