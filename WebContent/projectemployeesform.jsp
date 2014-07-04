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
			<td><s:actionerror /> <s:form action="projectemployees"
					method="post">
					
					<s:select name="proj_id" list="projlist" listKey="id"
						listValue="name" headerKey="0" headerValue="Select"
						key="app.projectemployees.proj_id" />
						
					<s:select name="emp_id" list="emplist" listKey="id"
						listValue="first_name" headerKey="0" headerValue="Select"
						key="app.projectemployees.emp_id" />
						
					<s:submit value="Add Project Employees" />
					<table width="100%" align="center" cellpadding="0"
						style="border: 1px solid black;">
						<tr>
							<td>Id</td>
							<td>Project Id</td>
							<td>Employee Id</td>
							<td>Created Date</td>
							<td>Created By</td>
							<td>Updated Date</td>
							<td>Updated By</td>
						</tr>
						<s:iterator id="ls" value="prjemplist">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="proj_id" /></td>
								<td><s:property value="emp_id" /></td>
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