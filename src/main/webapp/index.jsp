<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database</title>
<link rel="stylesheet" href="main.css">
</head>
<body>
	<table border="1">
		<caption>Адреса пользователей</caption>
   		<tr>
    		<th>ID</th>
    		<th>Фамилия</th>
    		<th>Имя</th>
    		<th>Адрес</th>
    		<th>Телефон</th>
    		<th colspan="2">Действия</th>
   		</tr>
   		${table}   		
   	</table>
	<button class="add">Добавить</button>
	
	<div id="row_modal" class="modal">
		<div class="modal-content">
			<span class="close">&times;</span>
    		<form id="user_form">
    			<input type="hidden" name="action">
    			<input type="hidden" name="id">
    			<label>
    				Фамилия:
    				<input type="text" name="second_name" required>
    			</label>
    			<label>
    				Имя:
    				<input type="text" name="first_name" required>
    			</label>
    			<label>
    				Адрес:
    				<input type="text" name="address" required>
    			</label>
    			<label>
    				Телефон:
    				<input type="tel" name="phone" required>
    			</label>
    			<button type="submit">Сохранить</button>
    		</form>
  		</div>
  	</div>
	<script src="main.js"></script>
</body>
</html>