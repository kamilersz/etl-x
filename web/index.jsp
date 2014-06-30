<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
        <title>ETL-X</title>
  <link rel="stylesheet" href="dist/themes/default/style.min.css" />
  <link rel="stylesheet" href="dist/style.css" />
  <link href="dist/skin/ui.dynatree.css" rel="stylesheet" type="text/css">
</head>
<body>
Upload File
<form id="upload_file" action="upload.jsp" method="post" enctype="multipart/form-data">
<div class="field">
<label>File</label>
<input name="avatar" id="avatar" type="file">
<div class="buttons">
<button type="submit" id="avatarbutton" >Upload</button>
</div><br>&nbsp;
</div>
</form>

File List:<br>
<%
File folder = new File(config.getServletContext().getRealPath("/")+"files/");
File[] listOfFiles = folder.listFiles();

for (File file : listOfFiles) {
    if (file.isFile()) {
%>
		<%= file.getName() %> <br>
<%
    }
}
%>
<br>Menu:<br>
<a href="editor.jsp">Editor</a>
<br><a href="#" id="dbschematoonto">Convert Schema to Ontology</a>
<div id="dbschematoontomenu" style="display:none">
<br>Input:<br>
<button id="dbschematoontoconvert" >Convert</button>
<br>Output:<br>
<textarea id="dbschematoontooutput"></textarea>
</div>
<br><a href="#" id="generatemappingscript">Generate Mapping Script</a>
<div id="generatemappingscriptmenu" style="display:none">
<br>Input:<br>
<textarea id="generatemappingscriptinput"></textarea>
<br><button id="generatemappingscriptgenerate" >Generate</button>
<br>Output:<br>
<textarea id="generatemappingscriptoutput"></textarea>
</div>
	<script src="dist/libs/jquery.js" type="text/javascript"></script>
	<script src="dist/libs/jquery-ui.custom.js" type="text/javascript"></script>
	<script src="dist/libs/jquery.cookie.js" type="text/javascript"></script>
  <script src="dist/jquery.dynatree.min.js"></script>
<script>
$("#dbschematoonto").on("click",function() {
	$("#dbschematoontomenu").toggle();
});
$("#generatemappingscript").on("click",function() {
	$("#generatemappingscriptmenu").toggle();
	$("#generatemappingscriptinput").val('{"source" : {"db_type" : "Oracle","url" : "jdbc:oracle:thin:@localhost:1521:xe","username" : "a","password" : "b"},"destination" : {"db_type" : "Oracle","url" : "jdbc:oracle:thin:@localhost:1521:xe","username" : "c","password" : "d" },"mapping" : ' + localStorage.getItem("mapping") + '}');
});
$("#dbschematoontoconvert").on("click",function() {
	var db_fn = prompt("Masukkan nama file DDL script", "");
	$.post('dbschematoonto', {db: db_fn}, function(response) {
		$("#dbschematoontooutput").val(response);
	});
});
$("#generatemappingscriptgenerate").on("click",function() {
	var input = $("#generatemappingscriptinput").val();
	$.post('generatemappingscript', {mapping: input}, function(response) {
		$("#generatemappingscriptoutput").val(response);
		
	});
});
</script>
</body>
</html>
