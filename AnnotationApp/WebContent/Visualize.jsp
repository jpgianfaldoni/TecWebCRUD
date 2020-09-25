<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
		<form action = 'Visualize' method = 'get'>
		<div style = 'display:flex; align-items:center; justify-content:center;'>
			Filtrar: <input type = 'text' name = 'filter' /><br/>
		</div>
		<div style = 'display:flex; align-items:center; justify-content:center;'>			
				<p>Order by:    </p>
				<input type = 'submit' name = 'ordenacao' value = 'notes'/>
				<input type = 'submit' name = 'ordenacao' value = 'type'/>
				<input type = 'submit' name = 'ordenacao' value = 'creation'/>
				<input type = 'submit' name = 'ordenacao' value = 'deadline'/>
				</form>
        </div>
        <div style = 'display: flex; justify-content: space-around;'>
            <c:forEach var="note" items ="${notes}">
            <div style='background-color:rgb(243, 241, 107); color:rgb(3, 3, 12); display: block; justify-content: space-between; border-style:solid; border-color:black;  max-width: 300px; word-wrap: break-word;'>
                <h2 style = 'word-wrap: break-word; font-family: sans-serif;'>${note.notes}</h2>
                <p style = 'font-family: sans-serif;'>Tipo: ${note.type}</p>
                <p style = 'font-family: sans-serif;'>Criação: ${note.creation}</p>
                <p style = 'font-family: sans-serif;'>Deadline: ${note.deadline}</p>
                <p style = 'font-family: sans-serif;'>Usuário: ${note.userid}</p>
            </div>
            </c:forEach>
        </div>
		<div style = 'display:flex; align-items:center; justify-content:center;'>
			<p>Navegar para:    </p>
			<form action = 'Create' method ='get'>
				<input type = 'submit' value = 'Create'  />
				 <input type="hidden" id="username" name="username" value="${user.firstname}">
			</form>
			<form action = 'Delete' method ='get'>
				<input type = 'submit' value = 'Delete'/>
			</form>
			<form action = 'Modify' method ='get'>
				<input type = 'submit' value = 'Modify'/>
			</form>
		</div>
</body>
</html>