package net.simpleframework.organization.impl;

import static net.simpleframework.common.I18n.$m;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.ado.db.common.SQLValue;
import net.simpleframework.ado.db.event.TableEntityAdapter;
import net.simpleframework.common.ID;
import net.simpleframework.common.ado.IParamsValue;
import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.common.bean.BeanUtils;
import net.simpleframework.common.coll.ParameterMap;
import net.simpleframework.organization.EAccountMark;
import net.simpleframework.organization.EAccountStatus;
import net.simpleframework.organization.ERoleMemberType;
import net.simpleframework.organization.IAccount;
import net.simpleframework.organization.IAccountManager;
import net.simpleframework.organization.IAccountSession;
import net.simpleframework.organization.IDepartment;
import net.simpleframework.organization.IUser;
import net.simpleframework.organization.IUserManager;
import net.simpleframework.organization.OrganizationException;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class AccountManager extends AbstractOrganizationManager<IAccount, Account> implements
		IAccountManager {

	@Override
	public IAccount getAccountByName(final String name) {
		return getBean("name=?", name);
	}

	@Override
	public IUser getUser(final Object id) {
		IAccount account = null;
		if (id instanceof IAccount) {
			account = (IAccount) id;
		}
		final IUserManager userMgr = userMgr();
		IUser user = userMgr.getBean(account != null ? account.getId() : id);
		if (user == null && (account != null || (account = getBean(id)) != null)) {
			user = userMgr.createBean();
			user.setId(account.getId());
			user.setText(account.getName());
			userMgr.insert(user);
		}
		return user;
	}

	@Override
	public ID getLoginId(final IAccountSession accountSession) {
		final IAccount login = getBean(accountSession.getLogin());
		if (login != null) {
			return login.getId();
		} else {
			accountSession.logout();
			return null;
		}
	}

	@Override
	public void setLogin(final IAccountSession accountSession, final Object loginId) {
		final IAccount login = getBean(loginId);
		if (login != null) {
			login.setLogin(true);
			login.setLastLoginIP(accountSession.getRemoteAddr());
			login.setLastLoginDate(new Date());
			login.setLoginTimes(login.getLoginTimes() + 1);

			update(new String[] { "login", "lastLoginIP", "lastLoginDate", "loginTimes" }, login);
			accountSession.setLogin(login.getId());
		}
	}

	@Override
	public void logout(final IAccountSession accountSession) {
		final IAccount login = getBean(accountSession.getLogin());
		if (login != null) {
			login.setOnlineMillis(login.getOnlineMillis() + accountSession.getOnlineMillis());
			login.setLogin(false);
			update(new String[] { "login", "onlineMillis" }, login);
		}
		accountSession.logout();
	}

	@Override
	public boolean verifyPassword(final IAccount account, final String password) {
		return account.getPassword().equals(Account.encrypt(password));
	}

	@Override
	public IDataQuery<? extends IAccount> query(final IDepartment dept) {
		final String uTable = userMgr().getEntityService().getTable().getName();
		final String aTable = getEntityService().getTable().getName();
		final StringBuilder sql = new StringBuilder();
		sql.append("select a.* from ").append(aTable).append(" a left join ").append(uTable)
				.append(" u on a.id=u.id where u.departmentid=? and a.status<>?");
		return getQueryService().query(
				new SQLValue(sql.toString(), dept.getId(), EAccountStatus.delete), getBeanClass());
	}

	@Override
	public IDataQuery<? extends IAccount> query(final int type) {
		final String uTable = userMgr().getEntityService().getTable().getName();
		final String aTable = getEntityService().getTable().getName();
		final StringBuilder sql = new StringBuilder();
		final ArrayList<Object> params = new ArrayList<Object>();
		sql.append("select a.* from ").append(aTable).append(" a left join ").append(uTable)
				.append(" u on a.id=u.id");
		if (type == ALL) {
			sql.append(" where a.status<>?");
			params.add(EAccountStatus.delete);
		} else if (type == ONLINE_ID) {
			sql.append(" where a.login=? and a.status=?");
			params.add(Boolean.TRUE);
			params.add(EAccountStatus.normal);
		} else if (type == NO_DEPARTMENT_ID) {
			sql.append(" where u.departmentid is null and a.status<>?");
			params.add(EAccountStatus.delete);
		} else if (type == DEPARTMENT_ID) {
			sql.append(" where u.departmentid is not null and a.status<>?");
			params.add(EAccountStatus.delete);
		} else if (type >= STATE_DELETE_ID && type <= STATE_NORMAL_ID) {
			sql.append(" where a.status=?");
			params.add(EAccountStatus.values()[STATE_NORMAL_ID - type]);
		}
		return getQueryService()
				.query(new SQLValue(sql.toString(), params.toArray()), getBeanClass());
	}

	@Override
	public void doSave(final Object id, final String name, final String password,
			final EAccountMark accountMark, final Map<String, String> userData) {
		final TableEntityAdapter adapter = new TableEntityAdapterEx() {
			@Override
			public void afterUpdate(final ITableEntityService service, final Object[] beans) {
				afterInsert(service, beans);
			}

			@Override
			public void afterInsert(final ITableEntityService service, final Object[] beans) {
				final IUserManager userMgr = userMgr();
				final ID id = ((IAccount) beans[0]).getId();
				IUser user = userMgr.getBean(id);
				if (user == null) {
					user = userMgr.createBean();
					user.setId(id);
					for (final Map.Entry<String, String> e : userData.entrySet()) {
						BeanUtils.setProperty(user, e.getKey(), e.getValue());
					}
					userMgr.insert(user);
				} else {
					for (final Map.Entry<String, String> e : userData.entrySet()) {
						BeanUtils.setProperty(user, e.getKey(), e.getValue());
					}
					userMgr.update(user);
				}
			}
		};

		IAccount account = getBean(id);
		final boolean insert = account == null;
		if (insert) {
			account = createBean();
			account.setCreateDate(new Date());
			account.setAccountMark(accountMark);
		}
		account.setName(name);
		if (password != null && !password.equals(account.getPassword())) {
			account.setPassword(Account.encrypt(password));
		}
		if (insert) {
			getEntityService().insertTransaction(adapter, account);
		} else {
			getEntityService().updateTransaction(adapter, account);
		}
	}

	@Override
	public int delete(final Object... ids) {
		int i = 0;
		if (ids != null) {
			for (final Object id : ids) {
				final Account account = getBean(id);
				if (account == null) {
					continue;
				}
				if (account.getStatus() == EAccountStatus.delete) {
					super.delete(id);
				} else {
					account.setStatus(EAccountStatus.delete);
					update(new String[] { "status" }, account);
				}
				i++;
			}
		}
		return i;
	}

	@Override
	public int undelete(final Object... ids) {
		int i = 0;
		if (ids != null) {
			for (final Object id : ids) {
				final Account account = getBean(id);
				if (account == null || account.getStatus() != EAccountStatus.delete) {
					continue;
				}
				account.setStatus(EAccountStatus.normal);
				update(new String[] { "status" }, account);
				i++;
			}
		}
		return i;
	}

	{
		final IAccount admin = getAccountByName(IAccount.admin);
		if (admin == null) {
			doSave(null, IAccount.admin, "admin", EAccountMark.builtIn,
					new ParameterMap().add("text", IAccount.admin));
		}

		addListener(new TableEntityAdapterEx() {
			@Override
			public void beforeDelete(final ITableEntityService service, final IParamsValue paramsValue) {
				super.beforeDelete(service, paramsValue);
				for (final IAccount account : coll(paramsValue)) {
					if (account.getAccountMark() == EAccountMark.builtIn) {
						throw OrganizationException.of($m("AccountManager.0"));
					}
				}
			}

			@Override
			public void beforeUpdate(final ITableEntityService service, final Object[] beans) {
				super.beforeUpdate(service, beans);
				for (final Object bean : beans) {
					final IAccount account = (IAccount) bean;
					if (account.getStatus() == EAccountStatus.delete
							&& account.getAccountMark() == EAccountMark.builtIn) {
						throw OrganizationException.of($m("AccountManager.0"));
					}
				}
			}

			private void deleteMember(final IAccount account) {
				// 删除成员角色
				roleMemberMgr().deleteWith("membertype=? and memberid=?", ERoleMemberType.user,
						account.getId());
			}

			@Override
			public void afterDelete(final ITableEntityService service, final IParamsValue paramsValue) {
				super.afterDelete(service, paramsValue);
				for (final IAccount account : coll(paramsValue)) {
					// 删除用户
					userMgr().delete(account.getId());
					deleteMember(account);
				}
			}

			@Override
			public void afterUpdate(final ITableEntityService service, final Object[] beans) {
				super.afterUpdate(service, beans);
				for (final Object bean : beans) {
					final IAccount account = (IAccount) bean;
					if (account.getStatus() == EAccountStatus.delete) {
						// 删除成员角色
						deleteMember(account);
					}
				}
			}
		});
	}
}
