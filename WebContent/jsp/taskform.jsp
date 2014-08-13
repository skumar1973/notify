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
			<td><s:actionerror /> <s:form action="task" method="post">
					<s:push value="task">
						<s:hidden name="id" />
						<s:textfield name="name" key="app.task.name" />
						<s:textfield name="desc" key="app.task.desc" />
						<s:select name="status" key="app.task.status"
							list="#{'Y':'Yes','N':'No' }" />
						<s:select name="freqID" list="tfList" listKey="id"
							listValue="Name" headerKey="0" headerValue="Select"
							key="app.task.freqID" />
						<s:hidden name="createdDt" />
						<s:hidden name="createdBy" />
						<s:hidden name="updatedDt" />
						<s:hidden name="updatedBy" />
						<s:submit value="Save/Update Task" onClick="return confirm('Do you want to continue?');" />
					</s:push>
				</s:form></td>
		</tr>
	</table>
	<s:if test="tsList.size()>0">
		<table width="100%" align="center" style="border: 1px solid black;">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Status</th>
				<th>Frequency</th>
				<th>Created Date</th>
				<th>Created By</th>
				<th>Updated Date</th>
				<th>Updated By</th>
				<th>Edit</th>
				<th>Delete</th>

			</tr>
			<s:iterator id="ls" value="tsList">
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="desc" /></td>
					<td><s:property value="status" /></td>
					<td><s:property value="freqName" /></td>
					<td><s:property value="createdDt" /></td>
					<td><s:property value="createdBy" /></td>
					<td><s:property value="updatedDt" /></td>
					<td><s:property value="updatedBy" /></td>
					<td><s:url id="editURL" action="editTask">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{editURL}"
							onClick="return confirm('Do you want to Edit these items?');">
							<img height="20" width="20"
								src="<s:url value="/img/edit_image.jpg"/>" />
						</s:a></td>
					<td><s:url id="deleteURL" action="deleteTask">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{deleteURL}"
							onClick="return confirm('Do you want to delete these items?');">
							<img height="20" width="20"
								src="<s:url value="/img/delete_image.jpg"/>" />
						</s:a></td>

				</tr>
			</s:iterator>
		</table>
	</s:if>
</body>
</html>