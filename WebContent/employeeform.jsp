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
					<s:textfield name="firstname" key="app.employee.firstname" />
					<s:textfield name="lastname" key="app.employee.lastname" />
					<s:textfield name="email" key="app.employee.email" />
					<s:textfield name="design" key="app.employee.designation" />
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
						</tr>
						<s:iterator id="ls" value="emplist">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="first_name" /> <s:property
										value="last_name" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="designation" /></td>
								<td><s:property value="status" /></td>
								<td><s:property value="created_dt" /></td>
								<td><s:property value="created_by" /></td>
								<td><s:property value="updated_dt" /></td>
								<td><s:property value="updated_by" /></td>
							</tr>
						</s:iterator>
					</table>
				</s:form></td>
		</tr>
	</table>
</body>
</html>