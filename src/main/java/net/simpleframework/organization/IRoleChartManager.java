package net.simpleframework.organization;

import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.ctx.ado.IBeanManagerAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IRoleChartManager extends IBeanManagerAware<IRoleChart> {

	/**
	 * 获取部门下的角色视图
	 * 
	 * @param dept
	 *           null返回全局视图
	 * @return
	 */
	IDataQuery<? extends IRoleChart> query(IDepartment dept);

	/**
	 * 根据名称获取视图
	 * 
	 * @param name
	 * @return
	 */
	IRoleChart getRoleChartByName(String name);

	/**
	 * 获取系统缺省角色视图
	 * 
	 * @return
	 */
	IRoleChart getSystemChart();
}
