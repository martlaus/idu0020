<jsp:useBean id="learningObjects" scope="request" type="ttu.idu0200.model.LearningObject[]"/>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

    <script src="static/js/learningObject.js"></script>
</head>
<body bgcolor="white">
<a href='s'>servlet</a> | <a href='/log'>log.txt</a> <br>
Objektide nimekiri:
<br>


<%
    String id = "";
    String type = "";
    String seats = "";
    out.println("<table width=100% border=0 cellpadding=2 cellspacing=1 class='table table-striped'>");
    out.println("<tr bgcolor='#cccccc'><td><STRONG> kood&nbsp;</td><td><STRONG>mark&nbsp;</td><td><STRONG>pealkiri&nbsp;</td><td>......</td><td>......</td></tr><tr></tr>");

    try {
        for (int n = 0; n < learningObjects.length; n++) {
            id = Integer.toString(learningObjects[n].getId());
            type = learningObjects[n].getType();
            seats = learningObjects[n].getTitle();
            out.println("<TR BGCOLOR='#FFFFFF' ><TD  nowrap>");
            out.println("&nbsp;" + id + "&nbsp;</TD><TD>" + type + "&nbsp;</TD><TD>" + seats + "&nbsp;");
            out.println("</TD><TD align='top' nowrap><a HREF='javascript:get_object(" + id + ")' TARGET='_self'><b><u>kirjeldus</u><b></a></TD><TD align='top' nowrap><a HREF='s?id=" + id + "' TARGET='_self'><b><u>muuda</u><b></a></TD></TR>");
        }
    } catch (Exception ex) {
        out.println("Mingi viga" + ex.getMessage());

    }

    out.println("</table>");


%>

<br>
<br>

<div ID="ajax_response">
</div>
<div ID="description_form" style="visibility:hidden;">
    <form name=description_form>
        <TABLE BGCOLOR='#cccccc'>
            <TR BGCOLOR='#ffffff'>
                <TD BGCOLOR='#eeeeee' COLSPAN=2>Kirjeldus</TD>
            </tr>
            <TR BGCOLOR='#ffffff'>
                <TD BGCOLOR='#cccccc' nowrap>id</td>
                <td BGCOLOR='#cccccc'><input class="form-control" type=text name=plane_id size=4 disabled></TD>
            </tr>
            <TR BGCOLOR='#ffffff'>
                <TD BGCOLOR='#cccccc' nowrap>kirjeldus:</td>
                <td BGCOLOR='#cccccc'><textarea class="form-control" name=description cols=25 rows=5></textarea></TD>
            </tr>
            <TR BGCOLOR='#ffffff'>
                <TD BGCOLOR='#cccccc' nowrap COLSPAN=2><input type="button" value="KINNI" class="form-control"
                                                              onClick="hide_description_form()"></TD>
            </tr>
        </TABLE>
    </form>
</div>
</body>
</html>
