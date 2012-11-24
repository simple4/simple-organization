package net.simpleframework.organization.impl;

import net.simpleframework.common.bean.AbstractTextDescriptionBean;
import net.simpleframework.organization.IOrganizationContext;
import net.simpleframework.organization.OrganizationContextFactory;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
@SuppressWarnings("serial")
public abstract class AbstractOrganizationBean extends AbstractTextDescriptionBean {

	private String name;

	/** 排序 **/
	private long oorder;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public long getOorder() {
		return oorder;
	}

	public void setOorder(final long oorder) {
		this.oorder = oorder;
	}

	protected IOrganizationContext context() {
		return OrganizationContextFactory.get();
	}
}
