$(document).ready(function () {

    $.get("AutoGrader/task/all", function (data) {

        $.each(data, function (index, value) {
            $(".entry-content").append("<div> <a href='./task.html?id=" + value.task_id + "'>" + value.task_name + "</a></div>");
        });
    });
    console.log(Cookies.get("AutoGraderUserId"));
});