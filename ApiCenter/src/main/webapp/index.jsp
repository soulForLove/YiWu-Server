<html>
<body>
<form action="<%=request.getContextPath()%>/upload"  enctype="multipart/form-data" method="post">
    <input type="file" required="required" name="file" id="file"/>
    <input type="submit" name="提交">
</form>
</body>
</html>
