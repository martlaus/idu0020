var req;
var mozillus = 0;
var appserver_url = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/";

function Initialize_dc() {
    try {
        req = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (e) {
        try {
            req = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (oc) {
            req = null;
        }
    }

    if (!req && typeof XMLHttpRequest != "undefined") {
        req = new XMLHttpRequest();
        mozillus = 1;
    }
}

function ShowDiv(divid) {
    if (document.layers) document.layers[divid].visibility = "show";
    else document.getElementById(divid).style.visibility = "visible";
}

function HideDiv(divid) {
    if (document.layers) document.layers[divid].visibility = "hide";
    else document.getElementById(divid).style.visibility = "hidden";
}

function show_description_form() {
    ShowDiv("description_form");
}

function evaluate_description_form(id, description) {
    document.forms['description_form'].plane_id.value = id;
    document.forms['description_form'].description.value = description;
}

function show_plane_description(id, description) {
    show_description_form();
    evaluate_description_form(id, description);
}

function hide_description_form() {
    HideDiv("description_form");
}

function get_object(object_id) {
    Initialize_dc();
    var url = appserver_url + "learningObjectService?id=" + object_id ;
    url = encodeURI(url);
    if (req != null) {
        req.onreadystatechange = Process_plane_request;
        req.open("GET", url, true);
        req.send(null);
    }
}

function Process_plane_request() {
    var x;

    if (req.readyState == 4) {


        if (req.status == 200) {
            if (req.responseText == "") {
                x = 1;
            }
            else {

                if (mozillus == 1) {
                    var learningObject = JSON.parse(req.responseText);
                }

                var id = learningObject.id;
                var description = learningObject.description;
                show_plane_description(id, description);
            }
        }

        else {
            document.getElementById("ajax_response").innerHTML =
                "Oli mingi probleem andmete saamisega:<br>" + req.statusText;
        }
    }
}
