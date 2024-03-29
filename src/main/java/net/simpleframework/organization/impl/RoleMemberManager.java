package net.simpleframework.organization.impl;

import java.util.ArrayList;

import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.organization.ERoleMemberType;
import net.simpleframework.organization.IRole;
import net.simpleframework.organization.IRoleMember;
import net.simpleframework.organization.IRoleMemberManager;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class RoleMemberManager extends AbstractOrganizationManager<IRoleMember, RoleMember>
		implements IRoleMemberManager {

	@Override
	public IDataQuery<? extends IRoleMember> queryMembers(final IRole role) {
		return query("roleid=?", role.getId());
	}

	@Override
	public void setPrimary(final IRoleMember member) {
		if (member == null || member.getMemberType() != ERoleMemberType.user
				|| member.isPrimaryRole()) {
			return;
		}

		final ArrayList<IRoleMember> beans = new ArrayList<IRoleMember>();
		member.setPrimaryRole(true);
		beans.add(member);

		final IDataQuery<? extends IRoleMember> qd = query("membertype=? and memberid=?",
				ERoleMemberType.user, member.getMemberId());
		IRoleMember member2;
		while ((member2 = qd.next()) != null) {
			if (!member2.getId().equals(member.getId()) && member2.isPrimaryRole()) {
				member2.setPrimaryRole(false);
				beans.add(member2);
			}
		}

		update(new String[] { "primaryrole" }, beans.toArray(new IRoleMember[beans.size()]));
	}
}
