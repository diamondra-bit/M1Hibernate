<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Visiter Application</title>
</head>
<body>
    <div>
        <h1>Visiter Management</h1>
        <h2>
           <a href="${pageContext.request.contextPath}/visiter/new">Add New visiter</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/visiter/list">List All visiter</a>
        </h2>
    </div>

    <div align="center">
      <c:if test="${visiter != null}">
        <form action="${pageContext.request.contextPath}/visiter/update" method="post">
      </c:if>
      
      <c:if test="${visiter == null}">
        <form action="${pageContext.request.contextPath}/visiter/insert" method="post">
      </c:if>
      
      <table border="1">
        <h2>
            <c:if test="${visiter != null}">Edit visiter</c:if>
            <c:if test="${visiter == null}">Add New visiter</c:if>
        </h2>
        
        <c:if test="${visiter != null}">
            <input type="hidden" name="codevisiter" value="<c:out value='${visiter.codevisiter}'/>"/>
        </c:if>
        
        <tr>
            <th> Code medecin</th>
            <td>
                <input type="text" name="codemed" size="45" value="<c:out value='${visiter.codemed}'/>"/>
            </td>
        </tr>
        
        <tr>
            <th> Code patient</th>
            <td>
                <input type="text" name="codepal" size="45" value="<c:out value='${visiter.codepal}'/>"/>
            </td>
        </tr>
        
        <tr>
            <th>Date</th>
            <td>
                <input type="date" name="date" size="45" value="<c:out value='${visiter.date}'/>"/>
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