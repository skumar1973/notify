<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table width="100%" align="center" cellpadding="0">
		<tr>
			<td><s:actionerror /> <s:form action="editProject" method="post">
					<s:textfield name="name" key="app.projects.name" />
					<s:textfield name="desc" key="app.projects.desc" />
					<s:select name="mgrID" list="empList" listKey="id"
						listValue="firstName" headerKey="0" headerValue="Select" key="app.projects.mgr_id" />
					<s:textfield name="status" key="app.projects.status" />
					<s:submit value="Edit Projects" />
					
				</s:form></td>
		</tr>
	</table>
</body>
</html>