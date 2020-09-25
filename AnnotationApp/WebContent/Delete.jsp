<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<form action = 'Delete' method ='post'>
		<div style = 'display:flex; justify-content:space-around;'>
            <c:forEach var="note" items ="${notes}">
            <div style='background-color:rgb(243, 241, 107); color:rgb(3, 3, 12); display: block; justify-content: space-between; border-style:solid; border-color:black;  max-width: 300px; word-wrap: break-word;'>
                <h2 style = 'word-wrap: break-word; font-family: sans-serif;'>${note.notes}</h2>
                <p style = 'font-family: sans-serif;'>Tipo: ${note.type}</p>
                <p style = 'font-family: sans-serif;'>Criação: ${note.creation}</p>
                <p style = 'font-family: sans-serif;'>Deadline: ${note.deadline}</p>
                <input type = 'checkbox' name = 'checkbox' value ='${note.id}'/>
             </div>
            </c:forEach>
           </div>
     <div style = 'display:flex; justify-content:center;'>
		<input type = 'submit' value = 'Deletar' />
	</div>
	</form>
</body>
</html>




