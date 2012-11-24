package net.simpleframework.organization.impl;

import net.simpleframework.common.ID;
import net.simpleframework.common.bean.AbstractIdBean;
import net.simpleframework.organization.ERoleMemberType;
import net.simpleframework.organization.IRoleMember;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class RoleMember extends AbstractIdBean implements IRoleMember {
	private ID roleId;

	private ERoleMemberType memberType;

	private ID memberId;

	private boolean primaryRole;

	private String description;

	@Override
	public ID getRoleId() {
		return roleId;
	}

	@Override
	public void setRoleId(final ID roleId) {
		this.roleId = roleId;
	}

	@Override
	public ERoleMemberType getMemberType() {
		return memberType;
	}

	@Override
	public void setMemberType(final ERoleMemberType memberType) {
		this.memberType = memberType;
	}

	@Override
	public ID getMemberId() {
		return memberId;
	}

	@Override
	public void setMemberId(final ID memberId) {
		this.memberId = memberId;
	}

	@Override
	public boolean isPrimaryRole() {
		return primaryRole;
	}

	@Override
	public void setPrimaryRole(final boolean primaryRole) {
		this.primaryRole = primaryRole;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	private static final long serialVersionUID = -6268885963912176924L;
}