<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<h2 style = 'display: flex; justify-content: space-around;'> Atenção: só é possível modificar as notas do usuário logado</h2>
	<form action = 'Modify' method ='post'>
			<div style = 'display: flex; justify-content: space-around;'>
				<c:forEach var="note" items ="${notes}">
		            <div style='background-color:rgb(243, 241, 107); color:rgb(3, 3, 12); display: block; justify-content: space-between; border-style:solid; border-color:black;  max-width: 300px; word-wrap: break-word;'>
					<textarea style = 'font-family: sans-serif; background-color:rgb(243, 241, 107); border: 1px solid; overflow: auto;' id = '${note.id}' name = 'note'>${note.notes}</textarea>
					<textarea style = 'font-family: sans-serif; background-color:rgb(243, 241, 107); border: 1px solid; overflow: auto;' id = '${note.id}' name = 'type'>${note.type}</textarea>
					<p>${note.creation}</p>
					<textarea style = 'font-family: sans-serif; background-color:rgb(243, 241, 107);' id = '${note.id}' name = 'deadline'>${note.deadline}</textarea>
					<p>${note.userid}</p>
					<textarea style = 'display:none; font-family: sans-serif; background-color:rgb(243, 241, 107); border: 1px solid; overflow: auto;' name = 'id'>${note.id}</textarea>
					
				</div>
				</c:forEach>
			</div>
			<div style = 'display:flex; align-items:center; justify-content:center;' >
				<input type = 'submit' value = 'Submit' />
			</div>
	</form>
</body>
</html>

