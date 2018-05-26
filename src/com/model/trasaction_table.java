package com.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@NamedNativeQueries({
		@NamedNativeQuery(name = "callAddTrasactionTable", 
				query = "CALL insert_trasaction_table(:uid,:codeValue,:message,:ttid)", 
				resultClass = trasaction_table.class) })
@Entity
@Table(name = "trasaction_table")
public class trasaction_table implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "uid")
	private int uid;

	@Column(name = "tid")
	private String tid;

	@Column(name = "code")
	private int code;

	@Column(name = "message")
	private String message;

	@Column(name = "visible", columnDefinition = "boolean default false")
	private boolean visible;

	@Column(name = "cdate", columnDefinition = "TIMESTAMP default Current_TIMESTAMP")
	private Date cdate;

	@Column(name = "ttid")
	private int ttid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public int getTtid() {
		return ttid;
	}

	public void setTtid(int ttid) {
		this.ttid = ttid;
	}

	public static void main(String args[]) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tras = session.beginTransaction();
		Query query = (Query) session.getNamedQuery("callAddTrasactionTable").setParameter("uid", 52)
				.setParameter("codeValue", 849).setParameter("message", "mhes");
		List result = ((org.hibernate.Query) query).list();
		for (int i = 0; i < result.size(); i++) {
			trasaction_table stock = (trasaction_table) result.get(i);
			System.out.println(stock.getCode() + " get(i): " + result.get(i));
		}
		// Transaction tras=session.beginTransaction();
		// String hql = "FROM blagotco_ajay.trasaction_table;";
		// List<trasaction_table> documents = null;
		// try {
		// documents = (List<trasaction_table>)session.createQuery("from
		// trasaction_table").list();
		// for(trasaction_table tt:documents){
		// System.out.println(tt.getTid());
		// }
		//
		// } catch (HibernateException e) {
		// e.printStackTrace();
		// }
		session.getTransaction().commit();
		// System.out.print(i);
		// Query query = session.createSQLQuery(
		// "CALL insert_trasaction_table(:uid,:codeValue,:message)")
		// //"CALL GetStocks(:stockCode)")
		// .addEntity(TrasactionTable.class)
		// .setParameter("uid", "1")
		// .setParameter("codeValue", "502")
		// .setParameter("message", "no message");
		//
		// List result = query.list();
		// for(int i=0; i<result.size(); i++){
		// TrasactionTable stock = (TrasactionTable)result.get(i);
		// System.out.println(stock.getUid());
		// }

		// Query query = session.getNamedQuery("callStockStoreProcedure")
		// {

		// .setParameter("codeValue", "502")
		// .setParameter("message", "no message");
		// List result = query.list();
		// for(int i=0; i<result.size(); i++){
		// TrasactionTable stock = (TrasactionTable)result.get(i);
		// System.out.println(stock.getId());
		// }

		// session.getNamedQuery("call
		// insert_trasaction_table").setInteger("uid",
		// 1).setInteger("codeValue", 201).setString("message", "no
		// message").executeUpdate();
		// System.out.println("Now trying to call the Stored
		// Procedure*****************");
		// Query exQuery = session.createSQLQuery("callCustomerStoreProcedure")
		// .setParameter("uid", "1")
		// .setParameter("codeValue", "502")
		// .setParameter("message", "no message");
		// int exRows = exQuery.executeUpdate();
		// System.out.println("Executed Rows from Stored
		// Procedure****************"+exRows);

		session.close();
	}
}
