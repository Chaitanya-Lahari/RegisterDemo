<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
background-color:#ffffff;
font-family:Arial,sans-serif;
}
fieldset{

width:350px;
margin:100px auto;
padding:25px;
background-color:#FFF5F7;
border-radius:10px;
}
.center-btn{
text-align:center;
margin-top:20px;

}
.center-bt{
text-align:center;
margin-top:20px
}
<input[type="text"],
<input[type="password"],
select{
  
    width: 100%;
    padding: 8px;
    margin: 8px 0;
    border-radius: 10px;
    border: 1px solid #ccc;
    box-sizing: border-box;
}
input[type="submit"]:hover{
    background-color: #555;
    color:white;
}
<input[type="submit"],
select{
   background-color:white;
   color:grey;
    width: 120px;
    padding: 8px;
    border-radius: 10px;
    border: 1px solid #ccc;
    display: block;
    margin: 15px auto;
    cursor: pointer;

}
label{
  font-weight: bold;
}
.checkbox-group{
margin: 8px ;
}
.form-title{
text-align:center;
marging-bottom:20px;
color:#1E3A8A;
}
</style>
</head>
<body>
<fieldset>
<h2 class="form-title">Registration Form</h2>
<form action="regForm" method="post">
<label>Name: </label>
<input type="text" name="name" placeholder="Enter your name"><br><br>
<label>Email:</label>
<input type="text" name="email" placeholder="Enter your email id"><br><br>
<label>Password:</label>
<input type="password" name="pass" placeholder="Enter password"><br><br>
<label>Gender:</label>
<input type="radio" name="gender" value="male"> Male 
<input type="radio" name="gender" value="female"> Female <br><br>
<label>City: </label>
<select name="city">
<option value=""><--select city--></option>
<option value="Kadapa">Kadapa</option>
<option value="Kurnool">Kurnool</option>
<option value="Hyderabad">Hyderabad</option>
<option value="Chennai">Chennai</option>
</select><br><br>
<label>Intrested Subjects:</label><br>
<input type="checkbox" name="subjects" value="Java">Java<br>
<input type="checkbox" name="subjects" value="JDBC">JDBC<br>
<input type="checkbox" name="subjects" value="Servlet">Servlet<br>
<input type="checkbox" name="subjects" value="JSP">JSP<br>
<input type="checkbox" name="subjects" value="Python">Python<br><br>
<label>Hobies: </label>
<input type="text" name="hobies" placeholder="Enter your hobies"><br><br>
<label>Address:</label>
<textarea name="address" rows="4" cols="30"></textarea><br>
<div class="center-btn">
<input type="submit" value="register"><br>
</form>
<form action="view" method="get">
<input type="submit" value="view">
</form>
</div>
</fieldset>
</body>
</html>