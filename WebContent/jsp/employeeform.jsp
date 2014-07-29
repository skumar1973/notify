<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<table width="100%" align="center" cellpadding="0">
		<tr>
			<td><s:actionerror /> <s:form action="employee" method="post">
					<s:textfield name="firstName" key="app.employee.firstName" />
					<s:textfield name="lastName" key="app.employee.lastName" />
					<s:textfield name="email" key="app.employee.email" />
					<s:textfield name="designation" key="app.employee.designation" />
					<s:textfield name="status" key="app.employee.status" />
					<s:submit value="Add Employee" />
					<table width="100%" align="center" style="border: 1px solid black;">
						<tr>
							<td>Id</td>
							<td>Name</td>
							<td>Email</td>
							<td>Designation</td>
							<td>Status</td>
							<td>Created Date</td>
							<td>Created By</td>
							<td>Updated Date</td>
							<td>Updated By</td>
<!-- 							<td>Edit</td> -->
						</tr>
						<s:iterator id="ls" value="empList">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="firstName" /> <s:property
										value="lastName" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="designation" /></td>
								<td><s:property value="status" /></td>
								<td><s:property value="createdDt" /></td>
								<td><s:property value="createdBy" /></td>
								<td><s:property value="updatedDt" /></td>
								<td><s:property value="updatedBy" /></td>
<%-- 								<td><s:url id="editURL" action="editEmployee"> --%>
<%-- 										<s:param name="id" value="%{id}"></s:param> --%>
<%-- 									</s:url> <s:a href="%{editURL}">Edit</s:a></td> --%>
							</tr>
						</s:iterator>
					</table>
				</s:form></td>
		</tr>
	</table>
</body>
</html>