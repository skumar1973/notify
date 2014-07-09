<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<table width="100%">
<!-- 		<tr> -->
<%-- 			<td align="left" style="border-bottom: 2px solid green;"><s:property --%>
<%-- 					value="#session.loginID" /></td> --%>
<%-- 			<td align="left"> <s:property value="#session.role"/> </td> --%>
<!-- 		</tr> -->
		<tr>
			<td align="left"><a href="notifytaskform.action">Notify Tasks</a></td>
		</tr>
		<tr>
			<td align="left"><a href="logoff.action">Logoff</a></td>
		</tr>
	</table>

</body>
</html>