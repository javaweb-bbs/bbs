/**
 * Created by sjf on 5/30/17.
 */
window.onload = function () {
    var user = localStorage.getItem('user')
    var updateBtn = document.querySelector('#update-btn')
    var invitationInfo = {
        title: document.querySelector('#title'),
        type: document.querySelector('#sys_category'),
        content: document.querySelector('#message')
    }

    function getDetail(data) {
        data = JSON.parse(data)
        invitationInfo.title.value = data.title
        invitationInfo.type.value = data.type
        invitationInfo.content.value = data.content
    }

    function updateCb(data) {
        data = JSON.parse(data)
        if (data.status === 'update success') {
            alert('修改成功')
            location.href = 'invitationManage.jsp'
        } else {
            alert('修改失败')
        }
    }

    function renderType(data) {
        data = JSON.parse(data)
        var hasType = data.length
        if (hasType) {
            for (var i = 0; i < data.length; i++) {
                var option = document.createElement('option')
                option.setAttribute("value", data[i].name)
                option.innerHTML = data[i].name
                invitationInfo.type.appendChild(option)
            }
            ajax('GET', 'invitation?invitationId=' + location.search.split('=')[1], null, getDetail)
        } else {
            invitationInfo.type.innerHTML = '<option>暂无分类</option>'
        }
    }

    function init() {
        ajax('GET', 'type', null, renderType)
    }

    init()

    updateBtn.onclick = function () {
        var info = {
            title: invitationInfo.title.value,
            content: invitationInfo.content.value,
            type: invitationInfo.type.value,
            invitationId: location.search.split('=')[1],
            action: 'info'
        }
        if (info.content && info.title) {
            ajax("POST", 'invitation', JSON.stringify(info), updateCb)
        } else {
            alert("标题和内容为必填项")
        }
    }
}