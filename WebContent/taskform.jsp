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
			<td><s:actionerror /> <s:form action="task" method="post">
					<s:textfield name="name" key="app.task.name" />
					<s:textfield name="desc" key="app.task.desc" />
					<s:select name="status" key="app.task.status"
						list="#{'Y':'Yes','N':'No' }" />
					<s:select name="freq_id" list="tflist" listKey="id"
						listValue="Name" headerKey="0" headerValue="Select"
						key="app.task.freq_id" />
					<s:submit value="Add Task" />
				<table width="100%" align="center" style="border:1px solid black;">
						<tr >
							<td >Id</td>
							<td >Name</td>
							<td >Description</td>
							<td >Status</td>
							<td >Frequency Id</td>							
							<td >Created Date</td>
							<td >Created By</td>
							<td >Updated Date</td>
							<td >Updated By</td>							
						</tr>
						<s:iterator id="ls" value="tslist">
							<tr >
								<td ><s:property value="id" /></td>
								<td ><s:property value="name" /></td>
								<td ><s:property value="desc" /></td>
								<td ><s:property value="status" /></td>
								<td ><s:property value="freq_id" /></td>
								<td ><s:property value="created_dt" /></td>
								<td ><s:property value="created_by" /></td>
								<td ><s:property value="updated_dt" /></td>
								<td ><s:property value="updated_by" /></td>
							</tr>
						</s:iterator>
					</table>
				</s:form></td>
		</tr>
	</table>
</body>
</html>