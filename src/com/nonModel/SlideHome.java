package com.nonModel;

public class SlideHome {
	private String url;
	private String title;
	private String pkey;
	private String mrp;
	private String smrp;
	private String size;
	private String description;
	private String color;
	private int visible;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	
	public String getSmrp() {
		return smrp;
	}
	public void setSmrp(String smrp) {
		this.smrp = smrp;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "mrp: "+mrp+" title:-"+title+"  pkey:-"+pkey+"  url"+url;
	}

}

