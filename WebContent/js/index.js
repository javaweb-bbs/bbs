window.onload = function () {
    /**
     * Created by sjf on 5/25/17.
     */
    var totalPage = 0
    var currentPage = 1
    var nextBtn = document.querySelector('.next')
    var prevBtn = document.querySelector('.previous')
    var loginSuccess = document.querySelector('.login-success')
    var noLogin = document.querySelector('.no-login')
    var username = document.querySelector('.username')
    var loginOut = document.querySelector('.login-out')
    var manageInvitation = document.querySelector('.manage-invitation')
    function renderList(data) {
        var list = document.querySelector('.list')
        list.innerHTML = ''
        data = JSON.parse(data)
        totalPage = Math.ceil(data.total / 10)
        if (data.total > 0) {
            var invitations = data.invitations
            for (var i = 0; i < invitations.length; i++) {
                var listItem = document.createElement('div')
                var html = '<h3><a href="Post.jsp?invitationId=' + invitations[i].invitationId +  '" target="_blank">' +invitations[i].title + '</a></h3>'
                    + '<p><i class="glyphicon glyphicon-user"></i><a href="" target="_blank">'
                    + invitations[i].authorName + '</a><i class="glyphicon glyphicon-calendar"></i>'
                    + invitations[i].dateCreate + '</p><p>' + invitations[i].content + '</p><br>'
                    + '<a class="btn btn-primary" href="Post.jsp?invitationId=' + invitations[i].invitationId + '">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>'
                listItem.innerHTML = html
                list.appendChild(listItem)
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

    function nextPage() {
        if (currentPage == totalPage) {
            alert("当前已是最后一页")
        } else {
            currentPage += 1
            ajax("GET", "invitation?pageNum=" + currentPage, null, renderList)
        }
    }

    function prevPage() {
        if (currentPage == 1) {
            alert("当前已经是第一页")
        } else {
            currentPage -= 1
            ajax("GET", "invitation?pageNum=" + currentPage, null, renderList)
        }
    }

    nextBtn.onclick = function () {
        nextPage()
    }

    prevBtn.onclick = function () {
        prevPage()
    }

    loginOut.onclick = function () {
        localStorage.removeItem("user")
        location.reload()
    }

    function init() {
        ajax("GET", "invitation", null, renderList);
        ajax("GET", "type", null, renderType);
        var user = localStorage.getItem("user")
        if (user) {
            user = JSON.parse(user)
            noLogin.style.display = 'none'
            loginSuccess.style.display = 'block'
            manageInvitation.style.display = 'block'
            username.innerHTML = '欢迎,' + user.userName + '<b class="caret"></b>'
        } else {
            noLogin.style.display = 'block'
            loginSuccess.style.display = 'none'
            manageInvitation.style.display = 'none'
        }
    }

    init();
}
