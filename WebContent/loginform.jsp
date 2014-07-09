<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<table width="100%" align="center" cellpadding="10">
		<tr>
			<td><s:actionerror /> <s:form action="login" method="post">
					<s:textfield name="loginID" key="app.login.loginid" />
					<s:password name="password" key="app.login.password" />
					<s:submit value="Login" />
				</s:form></td>
		</tr>
	</table>
</body>
</html>