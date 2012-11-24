package net.simpleframework.organization;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IAccountSession {

	static final String LOGIN_KEY = "$$login";

	/**
	 * 获取登录对象
	 * 
	 * @return
	 */
	Object getLogin();

	/**
	 * 设置登录对象
	 * 
	 * @param login
	 */
	void setLogin(Object login);

	/**
	 * 获取能自动登录的对象
	 */
	Object autoLogin();

	/**
	 * 注销
	 */
	void logout();

	/**
	 * 
	 * 
	 * @return
	 */
	long getOnlineMillis();

	/**
	 * 获取ip地址
	 * 
	 * @return
	 */
	String getRemoteAddr();
}
