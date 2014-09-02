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

	<table width="100%" align="center">
		<tr>
			<td><s:actionerror /> <s:form action="employeerole"
					method="post">
					<s:push value="empRole">
						<s:hidden name="id"></s:hidden>
						<s:select name="empID" list="empIDList" listKey="id"
							listValue="firstName" headerKey="0" headerValue="Select"
							key="app.employeeRole.empID" />
						<s:select name="roleID" list="roleIDList" listKey="id"
							listValue="name" headerKey="0" headerValue="Select"
							key="app.employeeRole.roleID" />
						<s:hidden name="createdDt" />
						<s:hidden name="createdBy" />
						<s:hidden name="updatedDt" />
						<s:hidden name="updatedBy" />

						<s:submit value="Save/Update Employee Role" />
					</s:push>
				</s:form></td>
		</tr>
	</table>
	<s:if test="empRoleList.size()>0">
	<div class="CSSTableGenerator">
		<table>
			<tr>
				<td>Id</td>
				<td>Emp ID</td>
				<td>Role ID</td>
				<td>Created Date</td>
				<td>Created By</td>
				<td>Updated Date</td>
				<td>Updated By</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
			<s:iterator id="ls" value="empRoleList">
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="empID" /></td>
					<td><s:property value="roleID" /></td>
					<td><s:property value="createdDt" /></td>
					<td><s:property value="createdBy" /></td>
					<td><s:property value="updatedDt" /></td>
					<td><s:property value="updatedBy" /></td>
					<td><s:url id="editURL" action="editEmployeeRole">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{editURL}">
							<img height="20" width="20"
								src="<s:url value="/img/edit_image.jpg"/>" />
						</s:a></td>
					<td><s:url id="deleteURL" action="deleteEmployeeRole">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{deleteURL}"
							onClick="return confirm('Do you want to delete these items?');">
							<img height="20" width="20"
								src="<s:url value="/img/delete_image.jpg"/>" />
						</s:a></td>

				</tr>
			</s:iterator>
		</table>
		</div>
	</s:if>
</body>
</html>