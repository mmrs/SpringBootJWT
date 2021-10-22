package com.dbbl.mrt.recharge.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dbbl.mrt.recharge.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "MP_USER")
public class MPUser extends BaseModel {

	@Id
	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "P_DATA")
	private String pData;

	@Column(name = "MAKER_ID")
	private String makerId;

	@Column(name = "MAKER_TIME")
	private Date makerTime;

	@Column(name = "CHECKER_ID")
	private String checkerId;

	@Column(name = "CHECKER_TIME")
	private Date checkerTime;

	@Column(name = "STATUS")
	Integer status;

	@Column(name = "ROLES")
	private String roles;

	@Column(name = "SECRET_KEY")
	private String secretKey;
}
