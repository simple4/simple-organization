package net.simpleframework.organization;

import java.util.Date;

import net.simpleframework.common.bean.IIdBeanAware;
import net.simpleframework.common.bean.INameBeanAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IAccount extends IIdBeanAware, INameBeanAware {
	/**
	 * 系统管理员，启动时创建
	 */
	static final String admin = "admin";

	/* 密码 */
	String getPassword();

	void setPassword(String password);

	/* 状态 */
	EAccountStatus getStatus();

	void setStatus(EAccountStatus status);

	/* 标识 */
	EAccountMark getAccountMark();

	void setAccountMark(EAccountMark accountMark);

	/* 是否登录 */
	boolean isLogin();

	void setLogin(boolean login);

	/* 创建时间 */
	Date getCreateDate();

	void setCreateDate(Date createDate);

	/* 最后一次登录时间 */
	Date getLastLoginDate();

	void setLastLoginDate(Date lastLoginDate);

	/* 最后一次登录IP */
	String getLastLoginIP();

	void setLastLoginIP(String lastLoginIP);

	/* 总登录次数 */
	int getLoginTimes();

	void setLoginTimes(int loginTimes);

	/* 总在线时间 */
	long getOnlineMillis();

	void setOnlineMillis(long onlineMillis);

	boolean isMailbinding();

	void setMailbinding(boolean mailbinding);

	boolean isMobilebinding();

	void setMobilebinding(boolean mobilebinding);
}
