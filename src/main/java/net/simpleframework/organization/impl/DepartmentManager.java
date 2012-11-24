package net.simpleframework.organization.impl;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.ado.db.ITableEntityService;
import net.simpleframework.common.ado.IParamsValue;
import net.simpleframework.organization.IDepartment;
import net.simpleframework.organization.IDepartmentManager;
import net.simpleframework.organization.OrganizationException;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class DepartmentManager extends AbstractOrganizationManager<IDepartment, Department>
		implements IDepartmentManager {

	{
		addListener(new TableEntityAdapterEx() {
			@Override
			public void beforeDelete(final ITableEntityService service, final IParamsValue paramsValue) {
				super.beforeDelete(service, paramsValue);

				for (final IDepartment dept : coll(paramsValue)) {
					// 存在子部门
					if (queryChildren(dept).getCount() > 0) {
						throw OrganizationException.of($m("DepartmentManager.0"));
					}
					// 存在用户
					if (userMgr().query("departmentId=?", dept.getId()).getCount() > 0) {
						throw OrganizationException.of($m("DepartmentManager.1"));
					}
				}
			}
		});
	}
}
