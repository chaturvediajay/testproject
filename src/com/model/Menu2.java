package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="menu2")
public class Menu2{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "m2id: "+m2id+"  submenu:- "+submenu+"  m1id:- "+m1id+" status:-"+status;
	}
    
    @Id
	@GeneratedValue
	@Column(name = "m2id")
    private int m2id;
    
    @Column(name = "submenu")
    private String submenu;
    
    @Column(name = "m1id")
    private int m1id;
    
    
    
    @Column(name = "status",columnDefinition = "TINYINT")
    private boolean status;
    
    public int getM2id() {
		return m2id;
	}


	public void setM2id(int m2id) {
		this.m2id = m2id;
	}

	public int getM1id() {
		return m1id;
	}

	public void setM1id(int m1id) {
		this.m1id = m1id;
	}

	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
    

   
    public String getSubmenu() {
        return this.submenu.trim().toLowerCase().trim();
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

   

}
