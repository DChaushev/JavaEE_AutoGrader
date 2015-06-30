$(document).ready(function () {
    $.getScript("./scripts/js-cookie.js", function () {

        $(".entry-title").text(Cookies.get('AutoGraderUser'));

//        $.post('AutoGrader/user/me', {name: Cookies.get('AutoGraderUser')}, function (data) {
//
//            alert(JSON.stringify(data));
//
//        });
    });
});