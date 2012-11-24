package net.simpleframework.organization.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.simpleframework.common.ObjectEx;
import net.simpleframework.organization.IOrganizationContext;
import net.simpleframework.organization.IRoleHandler;
import net.simpleframework.organization.IUser;
import net.simpleframework.organization.OrganizationContextFactory;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public abstract class AbstractRoleHandler extends ObjectEx implements IRoleHandler {

	public IOrganizationContext getModuleContext() {
		return OrganizationContextFactory.get();
	}

	@Override
	public Collection<IUser> members(final Map<String, Object> variables) {
		return null;
	}

	static Map<String, Class<? extends IRoleHandler>> rHandleRegistry = new HashMap<String, Class<? extends IRoleHandler>>();

	public static void registRoleHandler(final String role,
			final Class<? extends IRoleHandler> rHandleClass) {
		rHandleRegistry.put(role, rHandleClass);
	}
}
