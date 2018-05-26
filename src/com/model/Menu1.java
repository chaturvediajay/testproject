package com.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@Entity
@Table(name="menu1")
public class Menu1 {
	@Id
	@GeneratedValue
	private int m1id;
	
	@Column(name = "menu")
    private String menu;
	
	@Column(name = "status")
    private boolean status;
    
 
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

	public Menu1() {
    	 
	}

    public Menu1(String menu2) {
    	this.menu=menu2;
	}


    public String getMenu() {
        return this.menu.toLowerCase().trim();
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        AnnotationConfiguration config = new AnnotationConfiguration();
        config.addAnnotatedClass((Class)Menu1.class);
        config.configure("hibernate.cfg.xml");
        new SchemaExport((Configuration)config).create(true, true);
    }
}
