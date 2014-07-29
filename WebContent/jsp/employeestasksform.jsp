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
			<td><s:actionerror /> <s:form action="employeestasks"
					method="post">

					<s:select name="empID" list="empList" listKey="id"
						listValue="firstName" headerKey="0" headerValue="Select"
						key="app.employeestasks.emp_id" />

					<s:select name="taskID" list="taskList" listKey="id"
						listValue="name" headerKey="0" headerValue="Select"
						key="app.employeestasks.task_id" />

					<s:submit value="Add Employee Tasks " />

					<table width="100%" align="center" cellpadding="0"
						style="border: 1px solid black;">
						<tr>
							<td>Id</td>
							<td>Employee Id</td>
							<td>Task Id</td>
							<td>Created Date</td>
							<td>Created By</td>
							<td>Updated Date</td>
							<td>Updated By</td>
						</tr>
						<s:iterator id="ls" value="empTaskList">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="empID" /></td>
								<td><s:property value="taskID" /></td>
								<td><s:property value="createdDt" /></td>
								<td><s:property value="createdBy" /></td>
								<td><s:property value="updatedDt" /></td>
								<td><s:property value="updatedBy" /></td>

							</tr>
						</s:iterator>
					</table>
				</s:form></td>
		</tr>
	</table>

</body>
</html>