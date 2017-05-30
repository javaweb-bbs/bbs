/**
 * Created by sjf on 5/30/17.
 */
window.onload = function () {
    var typeList = document.querySelector('.type-list')
    function getList(data) {
        data = JSON.parse(data)
        typeList.innerHTML = ''
        if (data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                var listItem = document.createElement('tr')
                var html = '<td>' + data[i].name + '</td><td>' + data[i].count + '</td><td><a><i class="glyphicon glyphicon-remove"></i></a></td>'
                listItem.innerHTML = html
                typeList.appendChild(listItem)
            }
        }
    }

    function init() {
        ajax("GET", "type", null, getList)
    }

    init()
}