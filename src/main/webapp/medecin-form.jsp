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
      <c:if test="${medecin != null}">
        <form action="${pageContext.request.contextPath}/medecin/update" method="post">
      </c:if>
      
      <c:if test="${medecin == null}">
        <form action="${pageContext.request.contextPath}/medecin/insert" method="post">
      </c:if>
      
      <table border="1">
        <h2>
            <c:if test="${medecin != null}">Edit Medecin</c:if>
            <c:if test="${medecin == null}">Add New Medecin</c:if>
        </h2>
        
        <c:if test="${medecin != null}">
            <input type="hidden" name="codemed" value="<c:out value='${medecin.codemed}'/>"/>
        </c:if>
        
        <tr>
            <th>Medecin Name</th>
            <td>
                <input type="text" name="nom" size="45" value="<c:out value='${medecin.nom}'/>"/>
            </td>
        </tr>
        
        <tr>
            <th>Medecin Prenom</th>
            <td>
                <input type="text" name="prenom" size="45" value="<c:out value='${medecin.prenom}'/>"/>
            </td>
        </tr>
        
        <tr>
            <th>Medecin Grade</th>
            <td>
                <input type="text" name="grade" size="45" value="<c:out value='${medecin.grade}'/>"/>
            </td>
        </tr>
        
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Save"/>
            </td>
        </tr>
      </table>
      </form> <!-- Fermer la balise form -->
    </div>
</body>
</html>