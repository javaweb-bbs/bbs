/**
 * Created by sjf on 5/30/17.
 */
window.onload = function () {
    var addTypeBtn = document.querySelector('.add-type')
    addTypeBtn.onclick = function () {
        var categoryName = document.querySelector('#category_name')

        function cb (data) {
            data = JSON.parse(data)
            if (data.status === 'add success') {
                location.href = 'TypeManage.jsp'
            } else {
                alert('添加失败')
            }
        }

        if (categoryName.value) {
            ajax('PUT', 'type', JSON.stringify({name: categoryName.value}), cb)
        } else {
            alert('请填写需要添加的类名')
        }
    }
}