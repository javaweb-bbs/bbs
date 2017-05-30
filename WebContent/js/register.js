/**
 * Created by sjf on 5/30/17.
 */
/**
 * Created by sjf on 5/27/17.
 */
window.onload = function () {
    var submit = document.querySelector('.submit')

    function registerCb(data) {
        data = JSON.parse(data)
        if (data.hasOwnProperty("status")) {
            alert("注册失败")
        } else {
            localStorage.setItem("user", JSON.stringify(data))
            location.href = "index.jsp"
        }
    }

    submit.onclick = function () {
        var username = document.querySelector('.username').value
        var password = document.querySelector('.password').value
        var ensurePwd = document.querySelector('.ensure-pwd').value
        var email = document.querySelector('.email').value
        if (ensurePwd !== password) {
            alert('再次确认输入的密码和密码不一致')
        }
        if (username && password && email) {
            var registerMes = {
                username: username,
                password: password,
                email: email
            }
            ajax("PUT", "user", JSON.stringify(registerMes), registerCb)
        } else {
            alert("用户名和密码为必填")
        }
    }
}