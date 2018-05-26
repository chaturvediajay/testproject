package com.model;


	

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="order_details")
public class OrderDetails {
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Column(name = "orderId",nullable=false)
	private String orderId;
	
	@NotNull
	@Column(name = "status",nullable=false)
	private int status;

	@NotNull
	@Column(name = "uid",nullable=false)
	private int uid;
	
	@NotNull
	@Column(name = "confirm",nullable=false)
	private int confirm;
	
	
	@NotNull
	@Column(name = "visible",nullable=false)
	private int visible;
	
	
	@Column( name = "cdate", columnDefinition = "TIMESTAMP default Current_TIMESTAMP", nullable = false ) 
    @Temporal( value = TemporalType.TIMESTAMP ) 
    @org.hibernate.annotations.Generated(value=GenerationTime.INSERT) 
    private Date cdate;
	
	@NotNull
	@Column(name = "total",nullable=false)
	private double total;
	
	@NotNull
	@Column(name = "other",nullable=false)
	private String other;
	
	@Column(name = "taxAndCharge",length=200, nullable = true)
	private String taxAndCharge;
	
	
	
	public  OrderDetails()
	{
		
	}

	public  OrderDetails(String orderId,int status,int uid,double total,String other,String taxAndCharge)
	{
		this.orderId=orderId;
		this.status=status;
		this.uid=uid;
		this.total=total;
		this.other=other;
		this.taxAndCharge=taxAndCharge;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
	
	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	public String getTaxAndCharge() {
		return taxAndCharge;
	}

	public void setTaxAndCharge(String taxAndCharge) {
		this.taxAndCharge = taxAndCharge;
	}

	public static void main(String args[]){
		 Session session=com.model.HibernateUtil.getSessionFactory().openSession();
	        Transaction ts=null;
	        Integer itemID=0;
	        try
	        {
	            ts=session.beginTransaction();
	         //   com.model.OrderDetails info=new com.model.OrderDetails("245654446464", 0, 11);
	         //   itemID=(Integer)session.save(info);
	          //  ts.commit();
	        }
	        catch(Exception e)
	        {
	            if(ts!=null)
	                System.err.println(e.toString());
	                ts.rollback();
	        }
	        finally
	        {
	            session.close();
	        }
	        if(itemID>0)
	        	System.out.println("Success");
	}

}
