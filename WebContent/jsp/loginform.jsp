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
	<div class="myLogin">
		<table>
			<tr>
				<td><s:actionerror /> <s:form action="login" method="post">
					<s:textfield name="loginID" placeholder="Enter User Name"
							cssclass="textbox login" key="app.login.loginid" />
						<s:password name="password" placeholder="Enter Password"
							cssclass="textbox" key="app.login.password" />
						<s:submit value="Login" id="orange" cssClass="myButton orange" />
					</s:form></td>
			</tr>
		</table>
	</div>
</body>
</html>