package net.simpleframework.organization.impl;

import static net.simpleframework.common.I18n.$m;
import static net.simpleframework.ctx.permission.IPermissionHandler.sj_all_account;
import static net.simpleframework.ctx.permission.IPermissionHandler.sj_anonymous;
import static net.simpleframework.ctx.permission.IPermissionHandler.sj_lock_account;
import static net.simpleframework.ctx.permission.IPermissionHandler.sj_manager;
import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.common.ado.IParamsValue;
import net.simpleframework.common.ado.query.IDataQuery;
import net.simpleframework.organization.ERoleChartMark;
import net.simpleframework.organization.ERoleMark;
import net.simpleframework.organization.ERoleType;
import net.simpleframework.organization.IDepartment;
import net.simpleframework.organization.IRole;
import net.simpleframework.organization.IRoleChart;
import net.simpleframework.organization.IRoleChartManager;
import net.simpleframework.organization.OrganizationException;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class RoleChartManager extends AbstractOrganizationManager<IRoleChart, RoleChart> implements
		IRoleChartManager {

	@Override
	public IDataQuery<? extends IRoleChart> query(final IDepartment dept) {
		return dept == null ? query("departmentid is null") : query("departmentid=?", dept.getId());
	}

	@Override
	public IRoleChart getRoleChartByName(final String name) {
		return getBean("name=?", name);
	}

	@Override
	public IRoleChart getSystemChart() {
		IRoleChart roleChart = getRoleChartByName(IRoleChart.systemRoleChart);
		if (roleChart == null) {
			roleChart = createBean();
			roleChart.setName(IRoleChart.systemRoleChart);
			roleChart.setText($m("RoleChartManager.0"));
			roleChart.setChartMark(ERoleChartMark.builtIn);
			roleChart.setDescription($m("RoleChartManager.1"));
			insert(roleChart);
		}
		return roleChart;
	}

	{
		final IRoleChart sc = getSystemChart();
		final RoleManager roleMgr = roleMgr();

		for (final String r : new String[] { sj_all_account, sj_lock_account, sj_anonymous,
				sj_manager }) {
			IRole role = roleMgr.getRoleByName(r);
			if (role == null) {
				role = roleMgr.createBean();
				role.setName(r);
				role.setText($m("RoleChartManager." + r));
				role.setRoleChartId(sc.getId());
				role.setRoleMark(ERoleMark.builtIn);
				if (sj_manager.equals(r)) {
					role.setRoleType(ERoleType.normal);
				} else {
					role.setRoleType(ERoleType.handle);
				}
				roleMgr.insert(role);
			}
		}

		addListener(new TableEntityAdapterEx() {
			@Override
			public void beforeDelete(final ITableEntityService service, final IParamsValue paramsValue) {
				super.beforeDelete(service, paramsValue);

				for (final IRoleChart chart : coll(paramsValue)) {
					// 内置视图
					if (chart.getChartMark() == ERoleChartMark.builtIn) {
						throw OrganizationException.of($m("RoleChartManager.2"));
					}

					// 已存在角色
					if (roleMgr().queryRoot(chart).getCount() > 0) {
						throw OrganizationException.of($m("RoleChartManager.3"));
					}
				}
			}
		});
	}
}
