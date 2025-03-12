<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Application</title>
</head>
<body>
    <div>
        <h1>Patient Management</h1>
        <h2>
            <a href="new">Add New Patient</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Patient</a>
        </h2>
    </div>

    <div align="center">
        <table border="1">
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Sexe</th>
                <th>Adresse</th>
                <th>Actions</th>
            </tr>
            <c:if test="${empty listPatients}">
                <p>Aucun patient trouvé.</p>
            </c:if>
            <c:forEach var="patient" items="${listPatients}">
                <tr>
                    <td><c:out value="${patient.nom}"/></td>
                    <td><c:out value="${patient.prenom}"/></td>
                    <td><c:out value="${patient.sexe}"/></td>
                     <td><c:out value="${patient.adresse}"/></td>
                    <td>
                        <a href="edit?codepal=<c:out value='${patient.codepal}'/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="delete?codepal=<c:out value='${patient.codepal}'/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
