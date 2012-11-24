package net.simpleframework.organization;

import net.simpleframework.common.SimpleRuntimeException;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class OrganizationException extends SimpleRuntimeException {
	private static final long serialVersionUID = 6885538428759225872L;

	public OrganizationException(final String msg, final Throwable cause) {
		super(msg, cause);
	}

	public static OrganizationException of(final Throwable throwable) {
		return _of(OrganizationException.class, null, throwable);
	}

	public static OrganizationException of(final String msg) {
		return _of(OrganizationException.class, msg, null);
	}
}
