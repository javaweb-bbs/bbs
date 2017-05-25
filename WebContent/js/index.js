/**
 * Created by sjf on 5/25/17.
 */
function renderList(data) {
    var list = document.querySelector('.list')
    data = JSON.parse(data)
    if (data.total > 0) {
        for (var i = 0; i < data.invitations.length; i++) {
            console.log(data.invitations[i].title)
            var listItem = document.createElement('div')
            var html = '<h3><a href="" target="_blank">' + data.invitations[i].title + '</a></h3>'
                + '<p><i class="glyphicon glyphicon-user"></i><a href="" target="_blank">'
                + data.invitations[i].author + '</a><i class="glyphicon glyphicon-calendar"></i>'
                + data.invitations[i].dateCreate + '</p><p>' + data.invitations[i].content + '</p><br>'
                + '<a class="btn btn-primary" href="">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>'
            listItem.innerHTML = html;
            list.appendChild(listItem);
        }
    }
}

function renderType(data) {
    var typeList = document.querySelector('.type-list')
    data = JSON.parse(data)
    var hasType = data.length
    if (hasType) {
        for (var i = 0; i < data.length; i++) {
            var li = document.createElement('li')
            var html = '<a href="#">' + data[i] + '</a>'
            li.innerHTML = html
            typeList.appendChild(li)
        }
    } else {
        typeList.innerHTML = '<li>暂无分类</li>'
    }
}

function init() {
    ajax("GET", "invitation", null, renderList);
    ajax("GET", "type", null, renderType);
}

init();