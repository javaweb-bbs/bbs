/**
 * Created by sjf on 5/26/17.
 */
window.onload = function () {
    var totalPage = 0
    var currentPage = 1
    var nextBtn = document.querySelector('.next')
    var prevBtn = document.querySelector('.previous')
    var invitationList = document.querySelector('.invitation-list')
    var user = JSON.parse(localStorage.getItem('user'))
    function getList(data) {
        data = JSON.parse(data)
        invitationList.innerHTML = ''
        totalPage = Math.ceil(data.total / 10)
        if (data.total > 0) {
            var invitations = data.invitations
            for (var i = 0; i < invitations.length; i++) {
                var listItem = document.createElement('tr')
                var html = '<td><a href="Post.jsp?invitationId=' + invitations[i].invitationId + '" target="_blank">' +invitations[i].title
                    + '</a></td><td>' + invitations[i].type + '</td>'
                    + '<td>' + invitations[i].content + '</td>'
                    + '<td>' + invitations[i].dateCreate + '</td>'
                    + '<td><a href="UpdateInvitation.jsp?invitationId=' + invitations[i].invitationId + '">'
                    + '<i class="glyphicon glyphicon-pencil"></i></a>'
                    + '<a class="delete" id="' + invitations[i].invitationId +'"><i class="glyphicon glyphicon-remove" id="' + invitations[i].invitationId +'"></i></a></td>'
                listItem.innerHTML = html
                invitationList.appendChild(listItem)
                listItem.querySelector('.delete').onclick = function (event) {
                    if (event.target.classList.contains('delete') || event.target.classList.contains('glyphicon-remove')) {
                        var id = +event.target.getAttribute('id')
                        deleteInvitation(id)
                    }
                }
            }
        }
    }

    function init() {
        ajax("GET", "invitation?user_id=" + user.userId, null, getList)
    }

    function nextPage() {
        if (currentPage == totalPage) {
            alert("当前已是最后一页")
        } else {
            currentPage += 1
            ajax("GET", "invitation?user_id=" + user.userId + "&pageNum=" + currentPage, null, getList)
        }
    }

    function prevPage() {
        if (currentPage == 1) {
            alert("当前已经是第一页")
        } else {
            console.log(currentPage)
            currentPage -= 1
            ajax("GET", "invitation?user_id=" + user.userId + "&pageNum=" + currentPage, null, getList)
        }
    }

    function deleteCb(data) {
        data = JSON.parse(data)
        if (data.status === 'delete success') {
            ajax("GET", "invitation?user_id=" + user.userId, null, getList)
        } else {
            alert('删除失败')
        }
    }

    function deleteInvitation(invitationId) {
        console.log('delete')
        ajax('DELETE', 'invitation', JSON.stringify({invitationId: invitationId}), deleteCb)
    }

    nextBtn.onclick = function () {
        nextPage()
    }

    prevBtn.onclick = function () {
        prevPage()
    }

    init()
}