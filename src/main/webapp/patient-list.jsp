<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Patients</title>
</head>
<body>
    <h1>Liste des Patients</h1>

    <!-- Formulaire de recherche -->
    <form action="search" method="GET">
        <input type="text" name="keyword" value="${keyword}" placeholder="Rechercher par nom ou code">
        <button type="submit">Rechercher</button>
    </form>
    <br>

    <table border="1">
        <tr>
            <th>Code Patient</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Sexe</th>
            <th>Adresse</th>
            <th>Actions</th>
        </tr>
        <c:if test="${empty listPatients}">
            <tr>
                <td colspan="6">Aucun patient trouvé.</td>
            </tr>
        </c:if>
        <c:forEach var="patient" items="${listPatients}">
            <tr>
                <td><c:out value="${patient.codepal}"/></td>
                <td><c:out value="${patient.nom}"/></td>
                <td><c:out value="${patient.prenom}"/></td>
                <td><c:out value="${patient.sexe}"/></td>
                <td><c:out value="${patient.adresse}"/></td>
                <td>
                    <a href="edit?codepal=${patient.codepal}">Éditer</a> |
                    <a href="delete?codepal=${patient.codepal}" onclick="return confirm('Confirmer la suppression ?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="new">Ajouter un nouveau patient</a>
</body>
</html>
