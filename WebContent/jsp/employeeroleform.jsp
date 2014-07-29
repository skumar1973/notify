<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<table width="100%" align="center" >
		<tr>
			<td><s:actionerror /> <s:form action="employeerole" method="post">
					<s:select name="empID" list="empIDList" listKey="id"
						listValue="firstName" headerKey="0" headerValue="Select"
						key="app.employeeRole.empID" />
					<s:select name="roleID" list="roleIDList" listKey="id"
						listValue="name" headerKey="0" headerValue="Select"
						key="app.employeeRole.roleID" />
					<s:submit value="Add Employee Role" />
				<table width="100%" align="center" style="border:1px solid black;">
						<tr >
							<td >Id</td>
							<td >Emp ID</td>
							<td >Role ID</td>
							<td >Created Date</td>
							<td >Created By</td>
							<td >Updated Date</td>
							<td >Updated By</td>							
						</tr>
						<s:iterator id="ls" value="empRoleList">
							<tr >
								<td ><s:property value="id" /></td>
								<td ><s:property value="empID" /></td>
								<td ><s:property value="roleID" /></td>
								<td ><s:property value="createdDt" /></td>
								<td ><s:property value="createdBy" /></td>
								<td ><s:property value="updatedDt" /></td>
								<td ><s:property value="updatedBy" /></td>
							</tr>
						</s:iterator>
					</table>
				</s:form></td>
		</tr>
	</table>
</body>
</html>