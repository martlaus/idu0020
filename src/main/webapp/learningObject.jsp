<%--
  Created by IntelliJ IDEA.
  User: mart
  Date: 23.03.16
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap"  %>
<jsp:useBean id="learningObject" scope="request" type="ttu.idu0200.model.LearningObject"/>
<jsp:useBean id="learningObjectErrors" scope="request" class="java.util.HashMap" />

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</head>
<body>

<a href='s'>servlet</a> | <a href='/log'>log.txt</a> <br>


<form action='s?action=save' method=POST>

    <input type='hidden' name='id' value='<%=learningObject.getId() %>'>


    <%

        int id;
        String type = "";
        String title = "";
        String description = "";


        id = learningObject.getId();
        type = learningObject.getType();
        title = learningObject.getTitle();
        description = learningObject.getDescription();

        String titleError = "";
        String typeError = "";


        if (learningObjectErrors.get("typeError") != null)
        {
            typeError = (String) learningObjectErrors.get("typeError");
        }


        if (learningObjectErrors.get("titleError") != null)
        {
            titleError = (String) learningObjectErrors.get("titleError");
        }


        if (learningObjectErrors.size() > 0)
        {
            out.println("<table class='table table-striped'><tr><td><b><font face=arial color=red>Prooviti salvestada - aga ei onnestunud!</b></td></tr></table>");
        }


        out.println("<table width=100% border=0 cellpadding=2 cellspacing=1 class='table table-striped'>");
        out.println("<TR ><TD nowrap>id:</td><td>&nbsp;" + id + "&nbsp;</TD></tr>");
        out.println("<TR ><TD nowrap>tyyp:</td><td>&nbsp;<b><font color='#0000ff'><input type='text' value='" + type + "' name='type'></font></b>" + typeError + "</TD></tr>");
        out.println("<TR ><TD nowrap>Pealkiri:</td><td>&nbsp;<b><font color='#0000ff'><input type='text' value='" + title + "' name='title'></font></b>" + titleError + "</TD></tr>");
        out.println("<TR ><TD nowrap>kirjeldus:</td><td>&nbsp;<b><font color='#0000ff'><textarea name=\"description\" cols=25 rows=6>" + description + "</textarea></font></b></TD></tr>");
        out.println("</table>");
    %>

    <input class="form-control" type="submit" value="salvesta">


</form>


</body>
</html>
