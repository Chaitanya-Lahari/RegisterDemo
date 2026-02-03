<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<%ResultSet rs=(ResultSet) request.getAttribute("rs"); 
String gender = "";
String subjects = "";

if (rs != null) {
    gender = rs.getString("Gender");
    subjects = rs.getString("Subjects");
}%>
<head>
<meta charset="UTF-8">
<title>Edit form</title>
</head>
<body>
<h2 style='color:blue ; text-align:center;'>Edit Registration Form</h2>
<form action="update" method="post">
<input type="hidden" name="id" value="<%=rs.getInt("S_No") %>"><br><br>
Name:
<input type="text" name="name" value="<%=rs.getString("Name") %>"><br><br>
Email:
<input type="text" name="email" value="<%=rs.getString("Email") %>"><br><br>
Password:
<input type="password" name="pass" value="<%=rs.getString("Password")%>"><br><br>
Gender:
<input type="radio" name="gender" value="male"
  <%= "male".equalsIgnoreCase(gender) ? "checked" : "" %> > Male
   <input type="radio" name="gender" value="female"
   <%="female".equalsIgnoreCase(gender) ? "checked" :"" %> >Female 
<br>
City:
<select name="city">
    <option value="">-- Select City --</option>

    <option value="Kadapa"
        <%= "Kadapa".equalsIgnoreCase(rs.getString("City")) ? "selected" : "" %>>
        Kadapa
    </option>

    <option value="Kurnool"
        <%= "Kurnool".equalsIgnoreCase(rs.getString("City")) ? "selected" : "" %>>
        Kurnool
    </option>

    <option value="Hyderabad"
        <%= "Hyderabad".equalsIgnoreCase(rs.getString("City")) ? "selected" : "" %>>
        Hyderabad
    </option>

    <option value="Chennai"
        <%= "Chennai".equalsIgnoreCase(rs.getString("City")) ? "selected" : "" %>>
        Chennai
    </option>
</select>
<br><br>

Interested Subjects:<br>

<input type="checkbox" name="subjects" value="Java"
<%= subjects != null && subjects.contains("Java") ? "checked" : "" %>> Java<br>

<input type="checkbox" name="subjects" value="JDBC"
<%= subjects != null && subjects.contains("JDBC") ? "checked" : "" %>> JDBC<br>

<input type="checkbox" name="subjects" value="Servlet"
<%= subjects != null && subjects.contains("Servlet") ? "checked" : "" %>> Servlet<br>

<input type="checkbox" name="subjects" value="JSP"
<%= subjects != null && subjects.contains("JSP") ? "checked" : "" %>> JSP<br>

<input type="checkbox" name="subjects" value="Python"
<%= subjects != null && subjects.contains("Python") ? "checked" : "" %>> Python<br><br>

Hobies:
<input type="text" name="hobies" value="<%=rs.getString("Hobies")%>"> <br><br>

Address:
<textarea name="address" rows="4" cols="30"><%= rs.getString("Address") %></textarea>
<br><br>
<input type="submit" value="Update">
</form>
</body>
</html>