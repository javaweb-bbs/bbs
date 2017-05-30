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
                var html = '<td>' + data[i].name + '</td><td>' + data[i].count + '</td><td><a class="delete">' +
                    '<i name="' + data[i].name + '" class="glyphicon glyphicon-remove"></i></a></td>'
                listItem.innerHTML = html
                typeList.appendChild(listItem)
                listItem.querySelector('.delete').onclick = function (event) {
                    if (event.target.classList.contains('glyphicon-remove')) {
                        console.log('123')
                        var name = event.target.getAttribute('name')
                        deleteType(name)
                    }
                }
            }
        }
    }

    function deleteCb(data) {
        data = JSON.parse(data)
        if (data.status === 'delete success') {
            ajax("GET", "type", null, getList)
        } else {
            alert('delete fail')
        }
    }

    function deleteType (name) {
        ajax('DELETE', 'type', JSON.stringify({name: name}), deleteCb)
    }

    function init() {
        ajax("GET", "type", null, getList)
    }

    init()
}