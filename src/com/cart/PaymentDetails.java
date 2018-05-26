package com.cart;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "payment_details")
public class PaymentDetails {

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@Column(name = "orderId", nullable = false)
	private String orderId;

	@NotNull
	@Column(name = "txid", nullable = false)
	private String txid;

	@Column(name = "gateWayCode", length = 2000, nullable = true)
	private String gateWayCode;

	@Column(name = "blagotCode", length = 2000, nullable = true)
	private String blagotCode;

	@Column(name = "status", columnDefinition = "int default 0", nullable = false)
	private int status;

	@Column(name = "visible", columnDefinition = "int default 0", nullable = false)
	private int visible;

	@Column(name = "datetime", nullable = true)
	private Date datetime;

	@Column(name = "trasaction_code", nullable = true, length = 40)
	private String trasaction_code;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: " + id + "  orderId: " + orderId + "  txid:- " + txid + " gateWayCode:- " + blagotCode + " status:"
				+ status + " visible:- " + visible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public String getGateWayCode() {
		return gateWayCode;
	}

	public void setGateWayCode(String gateWayCode) {
		this.gateWayCode = gateWayCode;
	}

	public String getBlagotCode() {
		return blagotCode;
	}

	public void setBlagotCode(String blagotCode) {
		this.blagotCode = blagotCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getTrasaction_code() {
		return trasaction_code;
	}

	public void setTrasaction_code(String trasaction_code) {
		this.trasaction_code = trasaction_code;
	}

}
