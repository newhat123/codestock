package com.ak.entity;
// Generated 2016-1-3 8:43:31 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SRole generated by hbm2java
 */
@Entity
@Table(name = "s_role", catalog = "akdbs")
public class SRole implements java.io.Serializable {

	private Integer did;
	private SUser SUser;
	private int rolDid;
	private String fname;

	public SRole() {
	}

	public SRole(SUser SUser, int rolDid) {
		this.SUser = SUser;
		this.rolDid = rolDid;
	}

	public SRole(SUser SUser, int rolDid, String fname) {
		this.SUser = SUser;
		this.rolDid = rolDid;
		this.fname = fname;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "did", unique = true, nullable = false)
	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "use_did", nullable = false)
	public SUser getSUser() {
		return this.SUser;
	}

	public void setSUser(SUser SUser) {
		this.SUser = SUser;
	}

	@Column(name = "rol_did", nullable = false)
	public int getRolDid() {
		return this.rolDid;
	}

	public void setRolDid(int rolDid) {
		this.rolDid = rolDid;
	}

	@Column(name = "fname", length = 40)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

}
