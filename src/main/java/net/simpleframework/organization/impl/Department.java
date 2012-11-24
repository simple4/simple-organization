package net.simpleframework.organization.impl;

import net.simpleframework.common.ID;
import net.simpleframework.ctx.ado.ITreeBeanManagerAware;
import net.simpleframework.organization.EDepartmentType;
import net.simpleframework.organization.IDepartment;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class Department extends AbstractOrganizationBean implements IDepartment {

	private ID parentId;

	private EDepartmentType departmentType;

	@Override
	public ID getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(final ID parentId) {
		((ITreeBeanManagerAware<IDepartment>) context().getDepartmentMgr()).assertParentId(this,
				parentId);
		this.parentId = parentId;
	}

	@Override
	public EDepartmentType getDepartmentType() {
		return departmentType == null ? EDepartmentType.department : departmentType;
	}

	@Override
	public void setDepartmentType(final EDepartmentType departmentType) {
		this.departmentType = departmentType;
	}

	private static final long serialVersionUID = 4763200601974069965L;
}
