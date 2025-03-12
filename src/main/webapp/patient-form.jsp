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
      <c:if test="${patient != null }">
        <form action="update" method="post">
      </c:if>
      
      <c:if test="${patient == null }">
        <form action="insert" method="post">
      </c:if>
      
      <table border="1">
        <h2>
            <c:if test="${patient != null }">Edit Patient</c:if>
            <c:if test="${patient == null }">Add New Patient</c:if>
        </h2>
        
        <c:if test="${patient != null }">
            <input type="hidden" name="codepal" value="<c:out value='${patient.codepal}'/>"/>
        </c:if>
        
        <tr>
            <th> Name</th>
            <td>
                <input type="text" name="nom" size="45" value="<c:out value='${patient.nom}'/>"/>
            </td>
        </tr>
        
        <tr>
            <th> Prenom</th>
            <td>
                <input type="text" name="prenom" size="45" value="<c:out value='${patient.prenom}'/>"/>
            </td>
        </tr>
        
        <tr>
            <th> sexe</th>
            <td>
                <input type="text" name="sexe" size="45" value="<c:out value='${patient.sexe}'/>"/>
            </td>
        </tr>
        
        <tr>
            <th> adresse</th>
            <td>
                <input type="text" name="adresse" size="45" value="<c:out value='${patient.adresse}'/>"/>
            </td>
        </tr>
        
        
        <tr>
        <td colspan="2" align="center">
            <input type="submit" value="Save"/>
        </td>
        </tr>
        
      </table>
      
    </div>
</body>
</html>
