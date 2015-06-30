$(document).ready(function () {

    function getUrlVar(key)
    {
        var result = new RegExp(key + "=([^&]*)", "i").exec(window.location.search);
        return result && unescape(result[1]) || "";
    }

    $.get("AutoGrader/task/" + getUrlVar("id"), function (data) {

        $(".entry-title").text(data.task_name);
        $("#task_file").html("<div> <a href='http://" + data.task_file + "'>" + data.task_name + "</a></div>");


        $.each(data.results, function (index, value) {
            $("#task-results").append("<tr>\n\
            <td>n/a</td>\n\
            <td>" + value.status + "</td>\n\
            <td>" + value.score + "</td>\n\
            <td>n/a</td>\n\
            <td>view source</td>\n\
            </tr>");
        });


    });
});