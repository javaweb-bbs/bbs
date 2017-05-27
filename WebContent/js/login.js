/**
 * Created by sjf on 5/27/17.
 */
window.onload = function () {
    var submit = document.querySelector('.submit')

    function loginCb(data) {
        data = JSON.parse(data)
        if (data.hasOwnProperty("status")) {
            alert("该用户不存在，请先进行注册")
        } else {
            location.href = "index.jsp"
            localStorage.setItem("user", JSON.stringify(data))
        }
    }

    submit.onclick = function () {
        var username = document.querySelector('.username').value
        var password = document.querySelector('.password').value

        if (username && password) {
            var loginMes = {
                action: "login",
                username: username,
                password: password
            }
            ajax("POST", "user", JSON.stringify(loginMes), loginCb)
        } else {
            alert("用户名和密码为必填")
        }
    }
}