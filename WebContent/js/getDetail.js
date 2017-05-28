window.onload = function () {
    /**
     * Created by sjf on 5/26/17.
     */
    var invitationId = location.search.split('=')[1]
    var addComment = document.querySelector('#add-comment')
    var commentMes = {
        commentUser: 0,
        answerUser: 0,
        content: '',
        invitation: 0
    }
    function getDetail(data) {
        var invitationDetail = document.querySelector('.invitation-detail')
        var getType = document.querySelector('.get-type')
        data = JSON.parse(data)
        getType.innerHTML = data.type
        invitationDetail.innerHTML = '<h2>' + data.title + '</h2><p> <i class="icon-user"></i>' +
                                     '<a href="#">' + data.authorName + '</a> | <i class="icon-calendar"></i>' + data.dateCreate + '</p><p>' + data.content + '</p>'
    }

    function getComent(data) {
        var commentList = document.querySelector('.comment-list')
        commentList.innerHTML = ''
        data = JSON.parse(data)
        for (var i = 0; i < data.length; i++) {
            var listItem = document.createElement('div')
            listItem.innerHTML = '<p><i class="icon-user"></i><a href="" target="_blank">' + data[i].authorName +
                '</a> | <i class="icon-calendar"></i>2017-05-26</p><p>' + data[i].content + '</p>'
            commentList.appendChild(listItem)
        }
    }

    function addCb(data) {
        data = JSON.parse(data)
        if (data.status == 'add success') {
            ajax('GET', 'comment?invitationId=' + invitationId, null, getComent)
            document.querySelector('.comment-content').value = ''
        } else {
            alert("评论失败")
        }
    }

    function getRelativeList() {

    }

    addComment.onclick = function () {
        var user = localStorage.getItem('user')
        if (user) {
            var userId = JSON.parse(user).userId
            var content = document.querySelector('.comment-content').value
            if (content) {
                commentMes.content = content
                commentMes.answerUser = +userId
                commentMes.invitation = +invitationId
                ajax('PUT', 'comment', JSON.stringify(commentMes), addCb)
            } else {
                alert("评论内容必填")
            }
        } else {
            alert('请先登录在进行评论')
        }
    }

    function init() {
        if (invitationId) {
            ajax('GET', 'invitation?invitationId=' + invitationId, null, getDetail)
            ajax('GET', 'comment?invitationId=' + invitationId, null, getComent)
        }
    }

    init()
}
