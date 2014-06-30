<%@page import="java.util.HashMap"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@ page import="java.io.*" %>
<%
    try {
        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                
            } else {
                File savedFile = new File(config.getServletContext().getRealPath("/")+"files/" + FilenameUtils.getName(item.getName()));
                item.write(savedFile);
            }
        }
    } catch (FileUploadException e) {
        throw new ServletException("Cannot parse multipart request.", e);
    }
    response.sendRedirect("index.jsp");
%>