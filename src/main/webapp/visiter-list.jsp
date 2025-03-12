<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Medecin Application</title>
</head>
<body>
    <div>
        <h1>Medecin Management</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/visiter/new">Add New visiter</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/visiter/list">List All visiter</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <tr>
                <th>Code medecin</th>
                <th>Code patient</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            <c:if test="${empty listVisiters}">
                <tr>
                    <td colspan="4">Aucune visite trouvée.</td>
                </tr>
            </c:if>
            <c:forEach var="visiter" items="${listVisiters}">
                <tr>
                    <td><c:out value="${visiter.codemed}"/></td>
                    <td><c:out value="${visiter.codepal}"/></td>
                    <td>
                        <fmt:formatDate value="${visiter.date}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/visiter/edit?codevisiter=<c:out value='${visiter.codevisiter}'/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/visiter/delete?codevisiter=<c:out value='${visiter.codevisiter}'/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
