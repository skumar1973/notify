<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table width="100%" align="center" cellpadding="0">
		<tr>
			<td><s:actionerror /> <s:form action="projects" method="post">
					<s:push value="project">
					<s:hidden name="id" />
						<s:textfield name="name" key="app.projects.name" />
						<s:textfield name="desc" key="app.projects.desc" />
						<s:select name="mgrID" list="empList" listKey="id"
							listValue="firstName" headerKey="0" headerValue="Select"
							key="app.projects.mgr_id" />
							<s:select name="status" key="app.projects.status"
							list="#{'Y':'Yes','N':'No' }" />
							<s:hidden name="createdDt" />
							<s:hidden name="createdBy" />
							<%-- <s:hidden name="updatedDt" />
							<s:hidden name="updatedBy" /> --%>
						<%-- <s:textfield name="status" key="app.projects.status" /> --%>
						<s:submit value="Add Projects/Update Projects"
							onClick="return confirm('Do you want to continue?')" />
					</s:push>

	<s:if test="projList.size()>0">
					<table width="100%" align="center" style="border: 1px solid black;">
						<tr>
							<th>Id</th>
							<th>Project Name</th>
							<th>Description</th>
							<th>Manager Name</th>
							<th>Status</th>
							<th>Created Date</th>
							<th>Created By</th>
							<th>Updated Date</th>
							<th>Updated By</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<s:iterator id="projects" value="projList">
							<tr>
								<td><s:property value="id" /></td>
								<td><s:property value="name" /></td>
								<td><s:property value="desc" /></td>
								<td><s:property value="full_name" /></td>
								<%-- <td><s:property value="mgrID" /></td> --%>
								<td><s:property value="status" /></td>
								<td><s:property value="createdDt" /></td>
								<td><s:property value="createdBy" /></td>
								<td><s:property value="updatedDt" /></td>
								<td><s:property value="updatedBy" /></td>
								<td><s:url id="editURL" action="editProject">
										<s:param name="id" value="%{id}"></s:param>
									</s:url> <s:a href="%{editURL}">
										<img height="20" width="20"
											src="<s:url value="/img/edit_image.jpg"/>" />
									</s:a></td>
								<td><s:url id="deleteURL" action="deleteProject">
										<s:param name="id" value="%{id}"></s:param>
									</s:url> <s:a href="%{deleteURL}">
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