package com.html;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.derby.tools.sysinfo;

import com.logic.ProductLogic;
import com.nonModel.SlideHome;

public class ProductPagingHome {
	
	public static String getProductShow(List<SlideHome> lsh,HttpServletRequest request){
		String str="";
		int i=0;
		System.out.println(" LIst Of Paging Product= "+lsh);
		for(SlideHome sh:lsh)
		{
			i++;
			System.out.println(" Title= "+sh.getTitle());
			String [] arrOfStr = sh.getUrl().split(",");
	        sh.getColor();
	        if(i==1)
	        	str+="<div class=\"pink\">";
			
			str+="<div class=\"col-md-3 box-in-at\">";
			str+="<div class=\"grid_box portfolio-wrapper\">";	
			str+="<a href=\""+request.getContextPath()+"/single?pkey="+sh.getPkey()+"\"> <img src=\""+request.getContextPath()+"/temp/img/"+arrOfStr[0]+"\" class=\"img-responsive\" alt=\"\" style=\"height: 300px; width: 250px;\">";
			str+="<div class=\"zoom-icon\">";
			str+="<ul class=\"in-by\">";
			str+="<li><h5>sizes:</h5></li>";
			str+="<li><span>X</span></li>";
			str+="<li><span>XS</span></li>";
			str+="<li><span>M</span></li>";
			str+="<li><span> L</span></li>";
			str+="<li><span>XL</span></li>";
			str+="<li><span> XXL</span></li>";
			str+="</ul>";
			str+="<ul class=\"in-by-color\">";
			str+="<li><h5>colors:</h5></li>";                   
			str+="<li><span> </span></li>";
			str+="<li><span class=\"color\"> </span></li>";
			str+="<li><span class=\"color1\"> </span></li>";
			str+="<li><span class=\"color2\"> </span></li>";
			str+="<li><span class=\"color3\"> </span></li>";
			str+="</ul>";
			str+="</div> </a> "	;
			str+= "</div>";
			str+="<!---->";
			str+="<div class=\"grid_1 simpleCart_shelfItem\">";
			str+="<a href=\"#\" class=\"cup item_add\"><span class=\"item_price\"><label>&#x24;</label>"+sh.getSmrp()+"<i> </i> </span></a>";				
			str+="</div>";
			str+="<!---->";
			str+="</div>";
			if(i==4){
				str+="<div class=\"clearfix\"> </div></div>";
				i=0;
			}
				
		}
		return str;
	}
	
	public static void main(String args[])
	{
		
	}

}
