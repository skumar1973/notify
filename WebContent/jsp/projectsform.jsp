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
			<td><s:actionerror /> <s:form action="projects" method="post">
					<s:textfield name="name" key="app.projects.name" />
					<s:textfield name="desc" key="app.projects.desc" />
					<s:select name="mgrID" list="empList" listKey="id"
						listValue="firstName" headerKey="0" headerValue="Select" key="app.projects.mgr_id" />
					<s:textfield name="status" key="app.projects.status" />
					<s:submit value="Add Projects" />


					<table width="100%" align="center" style="border: 1px solid black;">
						<tr>
							<td>Id</td>
							<td>Name</td>
							<td>Description</td>
							<td>Manager ID</td>
							<td>Status</td>
							<td>Created By</td>
						</tr>
						<s:iterator id="projects" value="projList">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="desc" /></td>
								<td><s:property value="mgrID" /></td>
								<td><s:property value="status" /></td>
								<td><s:property value="createdBy" /></td>
							</tr>
						</s:iterator>
					</table>
				</s:form></td>
		</tr>
	</table>
</body>
</html>