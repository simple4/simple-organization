package net.simpleframework.organization.impl;

import static net.simpleframework.common.I18n.$m;
import static net.simpleframework.ctx.permission.IPermissionHandler.sj_all_account;
import static net.simpleframework.ctx.permission.IPermissionHandler.sj_anonymous;
import static net.simpleframework.ctx.permission.IPermissionHandler.sj_lock_account;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.ID;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.ado.IParamsValue;
import net.simpleframework.common.ado.query.DataQueryUtils;
import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.common.script.IScriptEval;
import net.simpleframework.common.script.ScriptEvalFactory;
import net.simpleframework.ctx.permission.IPermissionHandler;
import net.simpleframework.organization.ERoleMark;
import net.simpleframework.organization.ERoleMemberType;
import net.simpleframework.organization.ERoleType;
import net.simpleframework.organization.IAccount;
import net.simpleframework.organization.IRole;
import net.simpleframework.organization.IRoleChart;
import net.simpleframework.organization.IRoleHandler;
import net.simpleframework.organization.IRoleManager;
import net.simpleframework.organization.IRoleMember;
import net.simpleframework.organization.IUser;
import net.simpleframework.organization.OrganizationException;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class RoleManager extends AbstractOrganizationManager<IRole, Role> implements IRoleManager {

	@Override
	public IRoleChart roleChart(final IRole role) {
		return role == null ? null : roleChartMgr().getBean(role.getRoleChartId());
	}

	@Override
	public IRole getRoleByName(final String name) {
		return getBean("name=?", name);
	}

	@Override
	public IRole getRoleByText(final IRoleChart roleChart, final String text) {
		if (roleChart == null) {
			return query("text=?", text).next();
		}
		return getBean("rolechartid=? and text=?", roleChart.getId(), text);
	}

	@Override
	public IDataQuery<Role> queryRoot(final IRoleChart roleChart) {
		if (roleChart == null) {
			return DataQueryUtils.nullQuery();
		}
		return query("rolechartid=? and parentid is null", roleChart.getId());
	}

	@Override
	public IDataQuery<Role> queryRoles(final IRoleChart roleChart) {
		if (roleChart == null) {
			return DataQueryUtils.nullQuery();
		}
		return query("rolechartid=?", roleChart.getId());
	}

	@Override
	public boolean isMember(final IUser user, final IRole role, final Map<String, Object> variables) {
		if (role == null) {
			return false;
		}
		if (role != null && IPermissionHandler.sj_anonymous.equals(role.getName())) {
			return true;
		}
		if (user == null) {
			return false;
		}
		final ERoleType jt = role.getRoleType();
		if (jt == ERoleType.normal) {
			final IDataQuery<? extends IRoleMember> dq = members(role);
			IRoleMember jm;
			while ((jm = dq.next()) != null) {
				final ID memberId = jm.getMemberId();
				if (jm.getMemberType() == ERoleMemberType.user) {
					if (user.getId().equals(memberId)) {
						return true;
					}
				} else {
					if (isMember(user, getBean(memberId), variables)) {
						return true;
					}
				}
			}
		} else if (jt == ERoleType.handle) {
			final IRoleHandler rhandle;
			final String rClass;
			if (StringUtils.hasText(rClass = role.getRuleHandler())
					&& (rhandle = (IRoleHandler) ClassUtils.newInstance(rClass)) != null) {
				return rhandle.isMember(user, variables);
			}
		} else if (jt == ERoleType.script) {
			variables.put("role", role);
			variables.put("user", user);
			final IScriptEval scriptEval = ScriptEvalFactory.createDefaultScriptEval(variables);
			final Object o = scriptEval.eval(role.getRuleScript());
			if (o instanceof Boolean) {
				return (Boolean) o;
			}
		}
		return false;
	}

	@Override
	public boolean isMember(final IUser user, final String roleRule,
			final Map<String, Object> variables) {
		if (!StringUtils.hasText(roleRule)) {
			return false;
		}
		for (final String rr : StringUtils.split(roleRule)) {
			if (rr.startsWith("#")) { // #开头则认为是用户名
				final IUser user2 = userMgr().getBean(rr.substring(1));
				if (user2 != null && user2.getId().equals(user.getId())) {
					return true;
				}
			} else {
				if (isMember(user, getBean(rr), variables)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isAdmin(final IUser user, final Map<String, Object> variables) {
		IAccount account;
		if (user != null && (account = userMgr().getAccount(user.getId())) != null
				&& account.getName().equals(IAccount.admin)) {
			return true;
		}
		return roleMgr().isMember(user, IPermissionHandler.sj_manager, variables);
	}

	@Override
	public Collection<? extends IUser> users(final IRole role, final Map<String, Object> variables) {
		final LinkedHashSet<IUser> users = new LinkedHashSet<IUser>();
		if (role != null) {
			final ERoleType jt = role.getRoleType();
			if (jt == ERoleType.normal) {
				final IDataQuery<? extends IRoleMember> dq = members(role);
				IRoleMember jm;
				while ((jm = dq.next()) != null) {
					final ID memberId = jm.getMemberId();
					if (jm.getMemberType() == ERoleMemberType.user) {
						final IUser user = userMgr().getBean(memberId);
						if (user != null) {
							users.add(user);
						}
					} else {
						users.addAll(users(getBean(memberId), variables));
					}
				}
			} else if (jt == ERoleType.handle) {
				final IRoleHandler rhandle;
				final String rClass;
				if (StringUtils.hasText(rClass = role.getRuleHandler())
						&& (rhandle = (IRoleHandler) ClassUtils.newInstance(rClass)) != null) {
					users.addAll(rhandle.members(variables));
				}
			}
		}
		return users;
	}

	@Override
	public IDataQuery<? extends IRoleMember> members(final IRole role) {
		return roleMemberMgr().queryMembers(role);
	}

	{
		addListener(new TableEntityAdapterEx() {

			@Override
			public void beforeDelete(final ITableEntityService service, final IParamsValue paramsValue) {
				super.beforeDelete(service, paramsValue);

				for (final IRole role : coll(paramsValue)) {
					// 含有孩子
					if (queryChildren(role).getCount() > 0) {
						throw OrganizationException.of($m("RoleManager.1"));
					}
					// 内置角色
					if (role.getRoleMark() == ERoleMark.builtIn) {
						throw OrganizationException.of($m("RoleManager.0"));
					}
				}
			}

			@Override
			public void afterDelete(final ITableEntityService service, final IParamsValue paramsValue) {
				super.afterDelete(service, paramsValue);
				for (final IRole role : coll(paramsValue)) {
					final ID rid = role.getId();
					roleMemberMgr().deleteWith("roleId=? or (membertype=? and memberid=?)", rid,
							ERoleMemberType.role, rid);
				}
			}
		});

		AbstractRoleHandler.registRoleHandler(sj_all_account, BuiltInRole.All.class);
		AbstractRoleHandler.registRoleHandler(sj_lock_account, BuiltInRole.Lock.class);
		AbstractRoleHandler.registRoleHandler(sj_anonymous, BuiltInRole.Anonymous.class);
	}
}
