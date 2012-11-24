package net.simpleframework.organization;

import net.simpleframework.common.ID;
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
public interface IRole extends IIdBeanAware, ITreeBeanAware, INameBeanAware, ITextBeanAware,
		IDescriptionBeanAware, IOrderBeanAware {

	/**
	 * 获取角色的类型
	 * 
	 * @return
	 */
	ERoleType getRoleType();

	void setRoleType(ERoleType roleType);

	/**
	 * 获取角色所在的视图id
	 * 
	 * @return
	 */
	ID getRoleChartId();

	void setRoleChartId(ID roleChartId);

	/**
	 * 获取接口型规则角色的名称，全类名
	 * 
	 * @return
	 */
	String getRuleHandler();

	void setRuleHandler(String ruleHandle);

	/**
	 * 获取脚本型规则角色的脚本内容
	 * 
	 * @return
	 */
	String getRuleScript();

	void setRuleScript(String ruleScript);

	/**
	 * 返回角色标记
	 * 
	 * @return
	 */
	ERoleMark getRoleMark();

	void setRoleMark(ERoleMark roleMark);
}
