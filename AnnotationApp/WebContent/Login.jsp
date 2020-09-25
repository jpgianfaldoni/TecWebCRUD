<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div style = 'display:flex; align-items:center; justify-content:center; '>
	<form action = 'Login' method ='post'>
		<div  style = 'display:block; align-items:center;'>
			Username: <input type = 'text' name = 'login' /><br/>
			Password: <input type = 'password' name = 'login' /><br/>
		</div>
		<div>
			<input type = 'submit' id = 'signup' name = 'login' value = 'Sign up' />
			<input type = 'submit' id = 'signin' name = 'login' value = 'Sign in' />
		</div>
	</form>
</div>
</body>
</html>