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
			<td><s:actionerror /> <s:form action="notifytask" method="post">
					<s:textfield name="period_date" key="app.notifytask.period_date" />
					<s:checkboxlist list="emptasklist" name="emptask" listKey="id"
						listValue="name" key="app.notifytask.emptask" />
					<s:submit key="app.notifytask.submit" />

					<table width="100%" align="center" style="border: 1px solid black;">
						<tr>
							<td>Id</td>
							<td>Emp Id</td>
							<td>Task Id</td>
							<td>Period Date</td>
							<td>Created By</td>
							<td>Created Date</td>
							<td>Updated By</td>
							<td>Updated Date</td>
						</tr>
						<s:iterator id="ls" value="etnlist">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="emp_id" /></td>
								<td><s:property value="task_id" /></td>
								<td><s:property value="period_date" /></td>
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