package net.simpleframework.organization;

import static net.simpleframework.common.I18n.$m;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public enum EAccountStatus {

	/**
	 * 正常
	 */
	normal {
		@Override
		public String toString() {
			return $m("EAccountStatus.normal");
		}
	},

	/**
	 * 注册
	 */
	registration {
		@Override
		public String toString() {
			return $m("EAccountStatus.registration");
		}
	},

	/**
	 * 锁定
	 */
	locked {
		@Override
		public String toString() {
			return $m("EAccountStatus.locked");
		}
	},

	/**
	 * 删除
	 */
	delete {
		@Override
		public String toString() {
			return $m("EAccountStatus.delete");
		}
	}
}
