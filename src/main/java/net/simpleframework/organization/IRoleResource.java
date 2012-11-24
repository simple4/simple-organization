package net.simpleframework.organization;

import net.simpleframework.common.ID;
import net.simpleframework.common.bean.IDescriptionBeanAware;
import net.simpleframework.common.bean.IIdBeanAware;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public interface IRoleResource extends IIdBeanAware, IDescriptionBeanAware {

	/**
	 * 与资源关联的角色id
	 * 
	 * @return
	 */
	ID getRoleId();

	void setRoleId(ID roleId);

	/**
	 * 资源类型被定义一个整型，由使用者确定其具体值
	 * 
	 * @return
	 */
	int getResourceType();

	void setResourceType(int resourceType);

	/**
	 * 资源id
	 * 
	 * @return
	 */
	ID getResourceId();

	void setResourceId(ID resourceId);

	/**
	 * 资源的文本，好查看
	 * 
	 * @return
	 */
	String getResourceText();

	void setResourceText(String resourceText);
}
