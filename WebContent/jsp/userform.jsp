<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<table width="100%" align="center">
		<tr>
			<td><s:actionerror /> 
			<s:form action="user" method="post">
					<s:push value="user">
					<s:hidden name="id"/>
					<s:textfield name="loginID" key="app.user.login_id" />
					<s:textfield name="password" type="password" key="app.user.password" />
					<s:select name="empID" list="empList" listKey="id"
						listValue="firstName" headerKey="0" headerValue="Select"
						key="app.user.emp_id" />
					<s:submit value="Add/Update Users" onClick="return confirm('Do you want to continue?')" />
					</s:push>
	<s:if test="userList.size()>0">
					<table width="100%" align="center" style="border: 1px solid black;">
						<tr>
							<th>Id</th>
							<th>login_id</th>
							<th>emp_name</th>
							<th>Created Date</th>
							<th>Created By</th>
							<th>Updated Date</th>
							<th>Updated By</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<s:iterator id="ls" value="userList">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="loginID" /></td>
								<td><s:property value="empName" /></td>
								<td><s:property value="createdDt" /></td>
								<td><s:property value="createdBy" /></td>
								<td><s:property value="updatedDt" /></td>
								<td><s:property value="updatedBy" /></td>
								<td><s:url id="editURL" action="editUser">
										<s:param name="id" value="%{id}"></s:param>
									</s:url> <s:a href="%{editURL}">
										<img height="20" width="20"
											src="<s:url value="/img/edit_image.jpg"/>" />
									</s:a></td>
								<td><s:url id="deleteURL" action="deleteUser">
										<s:param name="id" value="%{id}"></s:param>
									</s:url> <s:a href="%{deleteURL}" onClick="return confirm('Do you want to continue?')">
										<img height="20" width="20"
											src="<s:url value="/img/delete_image.jpg"/>" />
									</s:a></td>
							</tr>
						</s:iterator>
					</table>
		</s:if>
				</s:form></td>
		</tr>
	</table>
</body>
</html>