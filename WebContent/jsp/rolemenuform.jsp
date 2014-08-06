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
			<td><s:actionerror /> <s:form action="rolemenu" method="post">
					<s:push value="roleMenu">
						<s:hidden name="id"></s:hidden>
						<s:select name="roleID" list="roleIDList" listKey="id"
							listValue="name" headerKey="0" headerValue="Select"
							key="app.roleMenu.roleID" />
						<s:select name="menuID" list="menuIDList" listKey="id"
							listValue="name" headerKey="0" headerValue="Select"
							key="app.roleMenu.menuID" />
						<s:hidden name="createdDt" />
						<s:hidden name="createdBy" />
						<s:hidden name="updatedDt" />
						<s:hidden name="updatedBy" />
						<s:submit value="Save/Update RoleMenu" />
					</s:push>
				</s:form></td>
		</tr>
	</table>
	<s:if test="roleMenuList.size()>0">
		<table width="100%" align="center" style="border: 1px solid black;">
			<tr>
				<th>Id</th>
				<th>RoleID</th>
				<th>MenuID</th>

				<th>Created Date</th>
				<th>Created By</th>
				<th>Updated Date</th>
				<th>Updated By</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<s:iterator id="ls" value="roleMenuList">
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="roleID" /></td>
					<td><s:property value="menuID" /></td>

					<td><s:property value="createdDt" /></td>
					<td><s:property value="createdBy" /></td>
					<td><s:property value="updatedDt" /></td>
					<td><s:property value="updatedBy" /></td>
					<td><s:url id="editURL" action="editrolemenu">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{editURL}" onclick=" return false">
							<img height="20" width="20"
								src="<s:url value="/img/edit_image.jpg"/>" />
						</s:a></td>
					<td><s:url id="deleteURL" action="deleterolemenu">
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