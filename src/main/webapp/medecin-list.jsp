<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <a href="${pageContext.request.contextPath}/medecin/new">Add New Medecin</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/medecin/list">List All Medecin</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <tr>
                <th>Code médecin</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Grade</th>
                <th>Actions</th>
            </tr>
            <c:if test="${empty listMedecins}">
                <tr>
                    <td colspan="5">Aucun médecin trouvé.</td>
                </tr>
            </c:if>
            <c:forEach var="medecin" items="${listMedecins}">
                <tr>
                    <td><c:out value="${medecin.codemed}"/></td>
                    <td><c:out value="${medecin.nom}"/></td>
                    <td><c:out value="${medecin.prenom}"/></td>
                    <td><c:out value="${medecin.grade}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/medecin/edit?codemed=<c:out value='${medecin.codemed}'/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/medecin/delete?codemed=<c:out value='${medecin.codemed}'/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
