package net.simpleframework.organization;

import static net.simpleframework.common.I18n.$m;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public enum ERoleMemberType {
	/**
	 * 用户
	 */
	user {

		@Override
		public String toString() {
			return $m("ERoleMemberType.user");
		}
	},

	/**
	 * 角色，不能嵌套
	 */
	role {

		@Override
		public String toString() {
			return $m("ERoleMemberType.role");
		}
	}
}
