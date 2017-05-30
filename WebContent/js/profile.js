/**
 * Created by sjf on 5/30/17.
 */
window.onload = function () {
    var user = localStorage.getItem('user')
    if (user) {
        var updateInfoBtn = document.querySelector('.update-info')
        var updatePwdBtn = document.querySelector('.update-pwd')
        user = JSON.parse(user)
        var userInfo = {
            username: document.querySelector('#username'),
            email: document.querySelector('#email'),
            sex: document.querySelector('#gender'),
        }

        function init () {
            userInfo.username.value = user.userName
            userInfo.email.value = user.email
            userInfo.sex.value = user.sex ? '男' : '女'
        }

        function updateInfoCb(data) {
            data = JSON.parse(data)
            if (data.status === 'update success') {
                user.sex = userInfo.sex.value == '男' ? 1 : 0
                user.email = userInfo.email.value
                localStorage.removeItem('user')
                localStorage.setItem('user', JSON.stringify(user))
            } else {
                alert('修改信息失败')
            }
        }

        function updatePwdCb(data) {
            data = JSON.parse(data)
            if (data.status === 'update password success') {
                alert('修改密码成功，请重新登录')
                location.href = 'Login.jsp'
            } else {
                alert('更改密码失败')
            }
        }

        function updateInfo () {
            var info = {
                action: 'update',
                userId: user.userId,
                sex: userInfo.sex.value == '男' ? 1 : 0,
                email: userInfo.email.value
            }

            ajax('POST', 'user', JSON.stringify(info), updateInfoCb)
        }

        function updatePwd () {
            var pwdInfo = {
                password: document.querySelector('#new-pwd').value,
                userId: user.userId,
                action: 'pwd'
            }

            ajax('POST', 'user', JSON.stringify(pwdInfo), updatePwdCb)
        }

        updateInfoBtn.onclick = function () {
            updateInfo()
        }

        updatePwdBtn.onclick = function () {
            var oldPwd = document.querySelector('#old-pwd').value
            var newPwd = document.querySelector('#new-pwd').value
            var ensurePwd = document.querySelector('#ensure-pwd').value
            if (oldPwd && newPwd && oldPwd) {
                if (oldPwd == newPwd) {
                    alert('新旧密码不能相同')
                } else if (newPwd !== ensurePwd) {
                    alert('再次确认输入的密码和新密码不相同')
                } else {
                    updatePwd()
                }
            } else {
                alert('新旧密码，以及再次确认密码不能为空')
            }
        }

        init()
    } else {
        location.href = "Login.jsp"
    }
}
