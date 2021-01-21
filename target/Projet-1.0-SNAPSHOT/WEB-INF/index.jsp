<%@ page import="Projet.Model.Etudiant" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="static jdk.internal.org.jline.utils.AttributedStringBuilder.append" %>
<%@ page import="static org.graalvm.compiler.debug.TTY.out" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jolan
  Date: 09/12/2020
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="etudiant" class="Projet.Model.Etudiant" scope="request"/>
<jsp:useBean id="content" class="java.lang.String" scope="request"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <!-- jQuery and JS bundle w/ Popper.js -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
            crossorigin="anonymous"></script>
</head>
<body>






<jsp:include page="<%= application.getInitParameter("header")%>"/>
<jsp:include page="<%=content%>"/>
<jsp:include page="<%= application.getInitParameter("footer")%>"/>
</body>
</html>


<%--
    <% List<Etudiant> listEtudiants = (List<Etudiant>) request.getAttribute("etudiants");
    for(Etudiant etu : listEtudiants){%>
<p>
    <%= etu.getNom()%>
</p>
<a href=""
<% } %>--%>