<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registered Students</title>

<style>
table {
    border-collapse: collapse;
    width: 95%;
    margin: auto;
}
th, td {
    border: 1px solid #555;
    padding: 12px;
    text-align: center;
    vertical-align: middle;
}
.action-btn {
    margin: 6px 0;
    display: block;
}
.file-btn {
    margin: 4px 0;
}
</style>
</head>

<body>

<h2 style="text-align:center;color:blue;">Registered Students</h2>

<table>
<tr>
    <th>S_No</th>
    <th>Name</th>
    <th>Email</th>
    <th>Gender</th>
    <th>City</th>
    <th>Subjects</th>
    <th>Hobies</th>
    <th>Address</th>
    <th>Edit</th>
    <th>Files</th>
</tr>

<%
ResultSet rs = (ResultSet) request.getAttribute("Result");

/* 
 fileMap structure:
 studentId -> List of file names
*/
Map<Integer, List<String>> fileMap =
    (Map<Integer, List<String>>) request.getAttribute("fileMap");

if (rs != null) {
    while (rs.next()) {
        int sid = rs.getInt("S_No");
%>

<tr>
    <td><%= sid %></td>
    <td><%= rs.getString("Name") %></td>
    <td><%= rs.getString("Email") %></td>
    <td><%= rs.getString("Gender") %></td>
    <td><%= rs.getString("City") %></td>
    <td><%=rs.getString("Subjects") %></td>
    <td><%=rs.getString("Hobies")%></td>
    <td><%= rs.getString("Address") %></td>
    
    
    
    <!-- EDIT + DELETE -->
    <td>
        <form action="edit" method="post" class="action-btn">
            <input type="hidden" name="id" value="<%= sid %>">
            <input type="submit" value="Edit">
        </form>

        <form action="delete" method="post"
              onsubmit="return confirm('Delete this record?');"
              class="action-btn">
            <input type="hidden" name="id" value="<%= sid %>">
            <input type="submit" value="Delete">
        </form>
    </td>

    <!-- FILE UPLOAD + VIEW -->
    <td>

        <!-- Upload multiple files -->
        <form action="upload" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%= sid %>">
            <input type="file" name="file" multiple>
            <input type="submit" value="Upload">
        </form>

      <% if (fileMap != null && fileMap.get(sid) != null) {
    for (String f : fileMap.get(sid)) { %>

        <a href="<%= request.getContextPath() %>/files/<%= f %>"
           target="_blank">
            <button type="button" class="file-btn">
                View <%= f %>
            </button>
        </a>

<%  }
} %>


    </td>
</tr>

<%
    }
}
%>

</table>
</body>
</html>
