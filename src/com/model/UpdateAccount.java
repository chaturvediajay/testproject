package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenerationTime;
@Entity
@Table(name="updateaccount")
public class UpdateAccount {
	public UpdateAccount() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "status")
	private boolean status;
	
	@Column( name = "createDate", columnDefinition = "TIMESTAMP default Current_TIMESTAMP", nullable = false ) 
    @Temporal( value = TemporalType.TIMESTAMP ) 
    @org.hibernate.annotations.Generated(value=GenerationTime.INSERT) 
    private Date createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getComment() {
		return comment.toLowerCase().trim();
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
