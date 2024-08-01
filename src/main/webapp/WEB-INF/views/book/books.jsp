<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 7/31/2024
  Time: 8:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Category</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<a href="/book?action=add" class="btn btn-primary">Add</a>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Category</th>
            <th>Name</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Total Pages</th>
            <th>Year Created</th>
            <th>Author</th>
            <th>Image</th>
            <th>Status</th>
            <th colspan="3">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${book}" var="b">
            <tr>
                <td>${b.id}</td>
                <td>${b.categoryId}</td>
                <td>${b.name}</td>
                <td>${b.price}</td>
                <td>${b.stock}</td>
                <td>${b.totalPages}</td>
                <td>${b.yearCreated}</td>
                <td>${b.author}</td>
                <td><img src="${b.image}" alt=""></td>
                <td>${b.status?"Hoạt Động":"Không Hoạt Động"}</td>
                <td><a href="/category?action=edit&id=${b.id}" class="btn btn-warning">Edit</a></td>
                <td><a href="/category?action=detail&id=${b.id}" class="btn btn-success">Detail</a></td>
                <td><a href="/category?action=delete&id=${b.id}" class="btn btn-danger">Detele</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
