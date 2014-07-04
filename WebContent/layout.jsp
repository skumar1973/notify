<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body topmargin="0">
	<table width="1000" cellspacing="0" align="center" border="0">
		<tr>
			<td colspan="2" height="20"><tiles:insertAttribute name="header" />
			</td>
		</tr>
		<tr>
			<td width="200" valign="top"><tiles:insertAttribute name="menu" />
			</td>
			<td width="800" valign="top"><tiles:insertAttribute name="body" />
			</td>
		</tr>
		<tr>
			<td colspan="2" height="20"><tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>
</body>
</html>
