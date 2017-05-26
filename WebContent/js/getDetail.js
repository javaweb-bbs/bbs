/**
 * Created by sjf on 5/26/17.
 */
window.onload = function () {
    function getDetail(data) {

    }

    function init() {
        ajax('GET', 'detail', null, getDetail)
    }
}
