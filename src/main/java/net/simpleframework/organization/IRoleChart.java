package net.simpleframework.organization;

import net.simpleframework.common.ID;
import net.simpleframework.common.bean.IDescriptionBeanAware;
import net.simpleframework.common.bean.IIdBeanAware;
import net.simpleframework.common.bean.INameBeanAware;
import net.simpleframework.common.bean.IOrderBeanAware;
import net.simpleframework.common.bean.ITextBeanAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IRoleChart extends IIdBeanAware, INameBeanAware, ITextBeanAware,
		IDescriptionBeanAware, IOrderBeanAware {
	static final String systemRoleChart = "sys_rolechart";

	/**
	 * 获取角色视图所在的部门id。返回null表示全局视图
	 * 
	 * @return
	 */
	ID getDepartmentId();

	void setDepartmentId(ID departmentId);

	/**
	 * 
	 * @return
	 */
	ERoleChartMark getChartMark();

	void setChartMark(ERoleChartMark chartMark);
}