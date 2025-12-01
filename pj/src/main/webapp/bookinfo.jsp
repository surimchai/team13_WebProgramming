<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 정보</title>
</head>
<body>
   <h2>도서 목록</h2>
   <hr>

   <table border="1">
      <tr>
         <th>ID</th>
         <th>제목</th>
         <th>저자</th>
         <th>가격</th>
      </tr>

      <c:forEach items="${books}" var="b">
         <tr>
            <td>${b.id}</td>
            <td>${b.title}</td>
            <td>${b.author}</td>
            <td>${b.price}</td>
            <td><a href="/pj/bookControl?action=delete&id=${b.id}">판매</a></td>
  
         </tr>
      </c:forEach>
   </table>

   <hr>
   <h2>도서 등록</h2>
   <hr>

   <form method="post" action="/pj/bookControl?action=insert">
      <label>제목</label>
      <input type="text" name="title" required><br>

      <label>저자</label>
      <input type="text" name="author" required><br>

      <label>가격</label>
      <input type="number" name="price" required><br>

      <button type="submit">등록</button>
   </form>
</body>
</html>
