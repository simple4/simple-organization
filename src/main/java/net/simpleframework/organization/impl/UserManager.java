package net.simpleframework.organization.impl;

import java.io.InputStream;

import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.common.ID;
import net.simpleframework.common.StringUtils;
import net.simpleframework.organization.IAccount;
import net.simpleframework.organization.IAccountManager;
import net.simpleframework.organization.IUser;
import net.simpleframework.organization.IUserManager;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class UserManager extends AbstractOrganizationManager<IUser, User> implements IUserManager {

	@Override
	public IAccount getAccount(final Object id) {
		IUser user = null;
		if (id instanceof IUser) {
			user = (IUser) id;
		}
		final IAccountManager accountMgr = accountMgr();
		IAccount account = accountMgr.getBean(user != null ? user.getId() : id);
		if (account == null && (user != null || (user = getBean(id)) != null)) {
			account = accountMgr.createBean();
			account.setId(user.getId());
			account.setName(StringUtils.hash(user));
			accountMgr.insert(account);
		}
		return account;
	}

	@Override
	public InputStream getPhoto(final IUser user) {
		UserLob lob;
		if (user != null
				&& (lob = getEntityService(UserLob.class).getBean(user.getId(), UserLob.class)) != null) {
			return lob.getPhoto();
		}
		return null;
	}

	@Override
	public void updatePhoto(final Object id, final InputStream photo) {
		final ITableEntityService entity = getEntityService(UserLob.class);
		UserLob lob = entity.getBean(id, UserLob.class);
		if (lob == null) {
			lob = new UserLob();
			lob.setId(ID.Gen.id(id));
			lob.setPhoto(photo);
			entity.insert(lob);
		} else {
			lob.setPhoto(photo);
			entity.update(lob);
		}
	}
}
