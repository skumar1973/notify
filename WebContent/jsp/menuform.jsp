<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<s:url value ="/css/Theme.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<table width="100%" align="center" cellpadding="0">
		<tr>
			<td><s:actionerror /> <s:form action="menu" method="post">
				<s:push value="menus">
					<s:hidden name="id"></s:hidden>
					<s:textfield name="name" key="app.menu.name" />
					<s:textfield name="parentID" key="app.menu.parent_id" />
					<s:textfield name="desc" key="app.menu.desc" />
					<s:select name="status" key="app.menu.status"
						list="#{'Y':'Yes','N':'No' }" />
					<s:textfield name="target" key="app.menu.target" />
					<s:hidden name="createdDt" />
					<s:hidden name="createdBy" />
					<s:hidden name="updatedDt" />
					<s:hidden name="updatedBy" />
					<s:submit value="Save/Update Menus" />
					</s:push>
				</s:form></td>
		</tr>
	</table>

	<s:if test="mnList.size()>0">
	<div class="CSSTableGenerator">
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
				<td>Edit</td>
				<td>Delete</td>
			</tr>
			<s:iterator id="ls" value="mnList">
				<tr>
					<td><s:property value="id" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="parentID" /></td>
					<td><s:property value="desc" /></td>
					<td><s:property value="status" /></td>
					<td><s:property value="target" /></td>
					<td><s:property value="createdDt" /></td>
					<td><s:property value="createdBy" /></td>
					<td><s:property value="updatedDt" /></td>
					<td><s:property value="updatedBy" /></td>
					<td><s:url id="editURL" action="editmenuform">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{editURL}">
							<img height="20" width="20"
								src="<s:url value="/img/edit_image.jpg"/>" />
						</s:a></td>
					<td><s:url id="deleteURL" action="deleteEmployee">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{deleteURL}"
							onClick="return confirm('Do you want to delete these items?');">
							<img height="20" width="20"
								src="<s:url value="/img/delete_image.jpg"/>" />
						</s:a></td>
				</tr>

			</s:iterator>
		</table>
		</div>
	</s:if>
</body>
</html>