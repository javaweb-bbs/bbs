/**
 * Created by sjf on 5/25/17.
 */
function ajax(type, url, params, cb) {
    var xhr = new XMLHttpRequest();
    xhr.open(type, "/javaWeb_BBS/" + url);
    xhr.setRequestHeader("content-type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            cb(xhr.responseText)
        }
    }
    xhr.send(params);
}

