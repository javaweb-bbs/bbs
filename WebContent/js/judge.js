/**
 * Created by sjf on 5/28/17.
 */
var user = localStorage.getItem("user")
var loginSuccess = document.querySelector('.login-success')
var noLogin = document.querySelector('.no-login')
var manageInvitation = document.querySelector('.manage-invitation')
var username = document.querySelector('.username')
var loginOut = document.querySelector('.login-out')

if (user) {
    console.log('login success')
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

loginOut.onclick = function () {
    localStorage.removeItem("user")
    location.reload()
}
