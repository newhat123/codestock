package com.ak.entity;
// Generated 2015-8-26 18:26:08 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SRoldef generated by hbm2java
 */
@Entity
@Table(name = "s_roldef", catalog = "akdbs")
public class SRoldef implements java.io.Serializable {

	private Integer did;
	private String fname;

	public SRoldef() {
	}

	public SRoldef(String fname) {
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

	@Column(name = "fname", length = 40)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

}
