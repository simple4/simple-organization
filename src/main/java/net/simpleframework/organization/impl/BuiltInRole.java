package net.simpleframework.organization.impl;

import java.util.Map;

import net.simpleframework.organization.EAccountStatus;
import net.simpleframework.organization.IAccount;
import net.simpleframework.organization.IUser;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class BuiltInRole {

	public static class All extends AbstractRoleHandler {

		@Override
		public boolean isMember(final IUser user, final Map<String, Object> variables) {
			final IAccount account = getModuleContext().getUserMgr().getAccount(user.getId());
			return account != null && account.getStatus() == EAccountStatus.normal;
		}
	}

	public static class Lock extends AbstractRoleHandler {

		@Override
		public boolean isMember(final IUser user, final Map<String, Object> variables) {
			final IAccount account = getModuleContext().getUserMgr().getAccount(user.getId());
			return account != null && account.getStatus() == EAccountStatus.locked;
		}
	}

	public static class Anonymous extends AbstractRoleHandler {

		@Override
		public boolean isMember(final IUser user, final Map<String, Object> variables) {
			return true;
		}
	}
}
