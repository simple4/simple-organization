package net.simpleframework.organization;

import net.simpleframework.common.bean.IDescriptionBeanAware;
import net.simpleframework.common.bean.IIdBeanAware;
import net.simpleframework.common.bean.INameBeanAware;
import net.simpleframework.common.bean.IOrderBeanAware;
import net.simpleframework.common.bean.ITextBeanAware;
import net.simpleframework.common.bean.ITreeBeanAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IDepartment extends IIdBeanAware, INameBeanAware, ITextBeanAware,
		IDescriptionBeanAware, ITreeBeanAware, IOrderBeanAware {

	/**
	 * 获取部门的类型
	 * 
	 * @return
	 */
	EDepartmentType getDepartmentType();

	void setDepartmentType(EDepartmentType departmentType);
}
