/**
 * Created by sjf on 5/26/17.
 */
window.onload = function () {
    var contactBtn = document.querySelector('#contact-submit')
    var subject = document.querySelector('#subject')
    var invitationMes = {
        title: '',
        content: '',
        type: 'skill',
        author: '',
        isEssence: false
    }
    function renderType(data) {
        data = JSON.parse(data)
        var hasType = data.length
        if (hasType) {
            for (var i = 0; i < data.length; i++) {
                var option = document.createElement('option')
                option.setAttribute("value", data[i])
                option.innerHTML = data[i]
                subject.appendChild(option)
            }
            var option = document.createElement('option')
            option.setAttribute("value", 'test')
            option.innerHTML = 'test'
            subject.appendChild(option)
        } else {
            subject.innerHTML = '<option>暂无分类</option>'
        }
    }

    function addCb(data) {
        data = JSON.parse(data)
        if (data.status == 'OK') {
            location.href = 'invitationManage.jsp'
        } else {
            alert("添加帖子失败")
        }
    }

    function init() {
        ajax("GET", "type", null, renderType)
    }

    subject.onchange = function () {
        invitationMes.type = subject.value
    }

    contactBtn.onclick = function () {
        var content = document.querySelector('#message').value
        var title = document.querySelector('#name').value
        var userId = document.querySelector('#user-id').value
        console.log(userId)
        if (content && title) {
            invitationMes.content = content
            invitationMes.title = title
            invitationMes.author = +userId
            ajax("PUT", "invitation", JSON.stringify(invitationMes), addCb)
        } else {
            alert("帖子内容和标题为必填")
        }
    }

    init()
}