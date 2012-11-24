package net.simpleframework.organization;

import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.ctx.ado.IBeanManagerAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IRoleMemberManager extends IBeanManagerAware<IRoleMember> {

	/**
	 * 获取指定角色的成员
	 * 
	 * @param role
	 * @return 当角色类型为规则角色时，返回空集合
	 */
	IDataQuery<? extends IRoleMember> queryMembers(IRole role);

	/**
	 * 设置当前成员为主要成员
	 * 
	 * @param member
	 */
	void setPrimary(IRoleMember member);
}
