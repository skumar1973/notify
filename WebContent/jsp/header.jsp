<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<s:url value ="/css/Theme.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>

<!-- 	<table width="100%" height="100%" bgcolor="#A1EBF0"> -->

<table width="100%" height="100%">
		<tr>
		    <td> <img src="<s:url value="/img/c2p_logo.jpg"/>" />  </td>
			<td><s:date name="new java.util.Date()" format="dd MMMM yyyy" /></td>
			<td align="center">
				<h3>Notify TaskCompletion APP </h3>
			</td>
			<td align="left" ><s:property value="#session.loginID" />   <s:property value="#session.role"/> </td>
		</tr>
	</table>

</body>
</html>