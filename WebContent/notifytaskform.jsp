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
					<s:textfield name="periodDate" key="app.notifytask.period_date" />
					<s:checkboxlist list="empTaskNameList" name="empTaskIDArray" listKey="id"
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
						<s:iterator id="ls" value="etnList">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="empID" /></td>
								<td><s:property value="taskID" /></td>
								<td><s:property value="periodDate" /></td>
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