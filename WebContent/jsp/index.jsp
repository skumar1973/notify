<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<tiles:insertTemplate template="/jsp/layout.jsp">
		<tiles:putAttribute name="title" value="Tiles integration with Struts"
			type="string" />
		<tiles:putAttribute name="header" value="/jsp/header.jsp" />
		<tiles:putAttribute name="menu" value="/jsp/mainmenu.jsp" />
		<tiles:putAttribute name="body" value="/jsp/mainbody.jsp" />
		<tiles:putAttribute name="footer" value="/jsp/footer.jsp" />
	</tiles:insertTemplate>
</body>
</html>