<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
	<h1>Struts 2 Hello World Example</h1>
 
	<h4>
		Hello!!
		<s:property value="message" />
	</h4>
<div>

	<i><s:property value="user.userName"></s:property></i>
</div>
 
</body>
</html>