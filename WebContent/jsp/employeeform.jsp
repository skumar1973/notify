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
					<s:push value="employee">
						<s:hidden name="id"></s:hidden>
						<s:textfield name="firstName" key="app.employee.firstName" />
						<s:textfield name="lastName" key="app.employee.lastName" />
						<s:textfield name="email" key="app.employee.email" />
						<s:textfield name="designation" key="app.employee.designation" />
						<s:select name="status" key="app.employee.status"
							list="#{'Y':'Yes','N':'No' }" />
						<s:hidden name="createdDt" />
						<s:hidden name="createdBy" />
						<s:hidden name="updatedDt" />
						<s:hidden name="updatedBy" />
						<s:submit value="Save/Update Employee" />
					
					</s:push>
				</s:form></td>
		</tr>
	</table>

	<s:if test="empList.size()>0">
		<table width="100%" align="center" style="border: 1px solid black;">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Designation</th>
				<th>Status</th>
				<th>Created Date</th>
				<th>Created By</th>
				<th>Updated Date</th>
				<th>Updated By</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<s:iterator id="ls" value="empList">
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="firstName" /> 
					<s:property value="lastName" /></td>
					<td><s:property value="email" /></td>
					<td><s:property value="designation" /></td>
					<td><s:property value="status" /></td>
					<td><s:property value="createdDt" /></td>
					<td><s:property value="createdBy" /></td>
					<td><s:property value="updatedDt" /></td>
					<td><s:property value="updatedBy" /></td>
				<td><s:url id="editURL" action="editEmployee">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> <s:a href="%{editURL}"><img height="20" width="20" src="<s:url value="/img/edit_image.jpg"/>" /></s:a></td>
				<td><s:url id="deleteURL" action="deleteEmployee">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> <s:a href="%{deleteURL}" onClick="return confirm('Do you want to delete these items?');"><img height="20" width="20" src="<s:url value="/img/delete_image.jpg"/>" /></s:a></td>
			</tr>
				
			</s:iterator>
		</table>
	</s:if>
</body>
</html>