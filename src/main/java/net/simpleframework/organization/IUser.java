package net.simpleframework.organization;

import java.util.Date;

import net.simpleframework.common.ID;
import net.simpleframework.common.bean.IDescriptionBeanAware;
import net.simpleframework.common.bean.IIdBeanAware;
import net.simpleframework.common.bean.IOrderBeanAware;
import net.simpleframework.common.bean.ITextBeanAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IUser extends IIdBeanAware, IOrderBeanAware, ITextBeanAware, IDescriptionBeanAware {
	/**
	 * 用户的部门id
	 * 
	 * @return
	 */
	ID getDepartmentId();

	void setDepartmentId(final ID departmentId);

	Date getBirthday();

	String getEmail();

	void setEmail(String email);

	String getMobile();

	void setMobile(final String mobile);

	String getSex();
}
