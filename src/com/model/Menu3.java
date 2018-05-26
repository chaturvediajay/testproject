package com.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu3")
public class Menu3{
	public Menu3() {
	}
	@Id
	@GeneratedValue
	@Column(name = "m3id")
    private int m3id;
	
	@Column(name = "m2id")
    private int m2id;
    
    @Column(name = "submenu")
    private String submenu;
    
    @Column(name = "specification")
    private String specification;
    
    @Column(name = "status")
    private boolean status;
    

    public int getM3id() {
		return m3id;
	}

	public void setM3id(int m3id) {
		this.m3id = m3id;
	}

	public int getM2id() {
		return m2id;
	}

	public void setM2id(int m2id) {
		this.m2id = m2id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

    public String getSubmenu() {
        return this.submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public String getSpecification() {
        return this.specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
