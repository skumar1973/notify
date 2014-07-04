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
			<td><s:actionerror /> <s:form action="menu" method="post">
					<s:textfield name="name" key="app.menu.name" />
					<s:textfield name="parent_id" key="app.menu.parent_id" />
					<s:textfield name="desc" key="app.menu.desc" />
					<s:textfield name="status" key="app.menu.status" />
					<s:textfield name="target" key="app.menu.target" />
					<s:submit value="Add Menus" />
					<table width="100%" align="center" style="border: 1px solid black;">
						<tr>
							<td>Id</td>
							<td>Name</td>
							<td>Parent_id</td>
							<td>Description</td>
							<td>Status</td>
							<td>Target</td>
							<td>Created Date</td>
							<td>Created By</td>
							<td>Updated Date</td>
							<td>Updated By</td>
						</tr>
						<s:iterator id="ls" value="menulist">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="parent_id" /></td>
								<td><s:property value="desc" /></td>
								<td><s:property value="status" /></td>
								<td><s:property value="target" /></td>
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