package net.simpleframework.organization.impl;

import java.util.Date;

import net.simpleframework.common.ID;
import net.simpleframework.common.bean.AbstractTextDescriptionBean;
import net.simpleframework.organization.IUser;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class User extends AbstractTextDescriptionBean implements IUser {
	private static final long serialVersionUID = -4938630954415307539L;

	/** 性别 **/
	private String sex;

	/** 生日 **/
	private Date birthday;

	/* ----------------------联系方式------------------------- */
	/** 邮件 **/
	private String email;

	/** 家庭电话 **/
	private String homePhone;

	/** 办公电话 **/
	private String officePhone;

	/** 移动电话 **/
	private String mobile;

	/** 地址 **/
	private String address;

	/** 所在地 **/
	private String hometown;

	/** 邮政编码 **/
	private String postcode;

	private String qq;

	private String msn;

	private ID departmentId;

	/** 排序 **/
	private long oorder;

	@Override
	public ID getDepartmentId() {
		return departmentId;
	}

	@Override
	public void setDepartmentId(final ID departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public long getOorder() {
		return oorder;
	}

	@Override
	public void setOorder(final long oorder) {
		this.oorder = oorder;
	}

	@Override
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(final Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String getSex() {
		return sex;
	}

	public void setSex(final String sex) {
		this.sex = sex;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(final String hometown) {
		this.hometown = hometown;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String getMobile() {
		return mobile;
	}

	@Override
	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(final String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(final String officePhone) {
		this.officePhone = officePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(final String postcode) {
		this.postcode = postcode;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(final String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(final String msn) {
		this.msn = msn;
	}
}
