package net.simpleframework.organization;

import static net.simpleframework.common.I18n.$m;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public enum ERoleType {

	/**
	 * 正常的角色类型，需要分配角色成员
	 */
	normal {

		@Override
		public String toString() {
			return $m("ERoleType.normal");
		}
	},

	/**
	 * 接口类型
	 */
	handle {

		@Override
		public String toString() {
			return $m("ERoleType.handle");
		}
	},

	/**
	 * 表达式返回Boolean，判断是否角色成员
	 */
	script {

		@Override
		public String toString() {
			return $m("ERoleType.script");
		}
	}
}
