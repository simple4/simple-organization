package net.simpleframework.organization.impl;

import net.simpleframework.common.ID;
import net.simpleframework.organization.ERoleChartMark;
import net.simpleframework.organization.IRoleChart;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class RoleChart extends AbstractOrganizationBean implements IRoleChart {

	private ID departmentId;

	/**
	 * 是否内置的
	 */
	private ERoleChartMark chartMark;

	@Override
	public ID getDepartmentId() {
		return departmentId;
	}

	@Override
	public void setDepartmentId(final ID departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public ERoleChartMark getChartMark() {
		return chartMark == null ? ERoleChartMark.normal : chartMark;
	}

	@Override
	public void setChartMark(final ERoleChartMark chartMark) {
		this.chartMark = chartMark;
	}

	private static final long serialVersionUID = 7240516228770129459L;
}
