package net.simpleframework.organization.impl;

import net.simpleframework.common.ID;
import net.simpleframework.ctx.ado.ITreeBeanManagerAware;
import net.simpleframework.organization.ERoleMark;
import net.simpleframework.organization.ERoleType;
import net.simpleframework.organization.IRole;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class Role extends AbstractOrganizationBean implements IRole {
	/**
	 * 关联的角色视图id
	 */
	private ID roleChartId;

	private ID parentId;

	private ERoleType roleType;

	private String ruleHandler, ruleScript;

	private ERoleMark roleMark;

	@Override
	public ID getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(final ID parentId) {
		((ITreeBeanManagerAware<IRole>) context().getRoleMgr()).assertParentId(this, parentId);
		this.parentId = parentId;
	}

	@Override
	public ERoleType getRoleType() {
		return roleType == null ? ERoleType.normal : roleType;
	}

	@Override
	public void setRoleType(final ERoleType roleType) {
		this.roleType = roleType;
	}

	@Override
	public ID getRoleChartId() {
		return roleChartId;
	}

	@Override
	public void setRoleChartId(final ID roleChartId) {
		this.roleChartId = roleChartId;
	}

	@Override
	public String getRuleHandler() {
		final Class<?> rHandleClass = AbstractRoleHandler.rHandleRegistry.get(getName());
		if (rHandleClass != null) {
			return rHandleClass.getName();
		}
		return ruleHandler;
	}

	@Override
	public void setRuleHandler(final String ruleHandler) {
		this.ruleHandler = ruleHandler;
	}

	@Override
	public String getRuleScript() {
		return ruleScript;
	}

	@Override
	public void setRuleScript(final String ruleScript) {
		this.ruleScript = ruleScript;
	}

	@Override
	public ERoleMark getRoleMark() {
		return roleMark == null ? ERoleMark.normal : roleMark;
	}

	@Override
	public void setRoleMark(final ERoleMark roleMark) {
		this.roleMark = roleMark;
	}

	private static final long serialVersionUID = -911479175612535742L;
}
