package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="slide_control")
public class slide_control {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "field")
    private String field;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id- "+id+" name- "+name+"  field-"+field;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
