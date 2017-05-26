/**
 * Created by sjf on 5/26/17.
 */
window.onload = function () {
    var totalPage = 0
    var currentPage = 1
    var nextBtn = document.querySelector('.next')
    var prevBtn = document.querySelector('.previous')
    var invitationList = document.querySelector('.invitation-list')
    function getList(data) {
        data = JSON.parse(data)
        invitationList.innerHTML = ''
        totalPage = Math.ceil(data.total / 10)
        if (data.total > 0) {
            var invitations = data.invitations
            for (var i = 0; i < invitations.length; i++) {
                var listItem = document.createElement('tr')
                var html = '<td><a href="" target="_blank">' +invitations[i].title + '</a></td>'
                    + '<td>' + invitations[i].type + '</td>'
                    + '<td>' + invitations[i].content + '</td>'
                    + '<td>' + invitations[i].dateCreate + '</td>'
                    + '<td><a><i class="glyphicon glyphicon-pencil"></i></a>'
                    + '<a><i class="glyphicon glyphicon-remove"></i></a></td>'
                listItem.innerHTML = html
                invitationList.appendChild(listItem)
            }
        }
    }

    function init() {
        ajax("GET", "/invitation", null, getList)
    }

    function nextPage() {
        if (currentPage == totalPage) {
            alert("当前已是最后一页")
        } else {
            currentPage += 1
            ajax("GET", "invitation?pageNum=" + currentPage, null, getList)
        }
    }

    function prevPage() {
        if (currentPage == 1) {
            alert("当前已经是第一页")
        } else {
            console.log(currentPage)
            currentPage -= 1
            ajax("GET", "invitation?pageNum=" + currentPage, null, getList)
        }
    }

    nextBtn.onclick = function () {
        nextPage()
    }

    prevBtn.onclick = function () {
        prevPage()
    }

    init()
}