<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:bundle basename="trasaction">
       <fmt:message var="charge_free" key="charge.free"/>
       <fmt:message var="charge_corrier" key="charge.corrier"/>
       <fmt:message var="currency_dollar" key="currency.dollar"/>
       
        <fmt:message var="company_introduction" key="company.introduction"/>
       <fmt:message var="company_name" key="company.name"/>
       <fmt:message var="company_director_name" key="company.director.name"/>
       <fmt:message var="company_director_visible" key="company.director.visible"/>
       <fmt:message var="company_address" key="company.address"/>
       <fmt:message var="company_contact" key="company.contact"/>
       <fmt:message var="company_email" key="company.email"/>
        <fmt:message var="company_url" key="company.url"/>
       
      
 </fmt:bundle>
<head>
<%response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache, must-revalidate");
response.setDateHeader("Expires", -1);%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="google-site-verification" content="9t_JoUYdXM3lpzgXgp3QR7QcPZGthGROACtpvfN0qpA" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Expires" content="-1">
<meta http-equiv="expires" content="Tue, 11 Jan 2016 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />
<c:set var="rand"><%= java.lang.Math.round(java.lang.Math.random() * 1000) %></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>chouhanrugs</title>
<link rel="shortcut icon" type="image/x-icon" href="images/logo.png" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css?randId=${rand}" rel="stylesheet" type="text/css" media="all">
<link href="${pageContext.request.contextPath}/css/style.css?randId=${rand}" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="blagot infotech Responsive designs, salwar suit , textiles , LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,700' rel='stylesheet' type='text/css'>
<!--//fonts-->
<!-- start menu -->
<link href="${pageContext.request.contextPath}/css/megamenu.css?randId=${rand}" rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/etalage.css?randId=${rand}" rel="stylesheet">










</head>