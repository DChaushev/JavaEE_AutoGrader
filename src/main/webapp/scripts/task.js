$(document).ready(function () {

    function getUrlVar(key)
    {
        var result = new RegExp(key + "=([^&]*)", "i").exec(window.location.search);
        return result && unescape(result[1]) || "";
    }

    $("#submit-btn").click(function () {

        if ($(".form-control").val() === "")
        {
            alert("Please provide a source code!");
        }
        else
        {
            $.post('AutoGrader/task/submit', {user: Cookies.get('AutoGraderUser'), task_id: parseInt(getUrlVar("id")), src_code: $(".form-control").val()}, function (data) {

                if (data.hasOwnProperty("err"))
                    alert(data.err);
                else
                {
                    window.location.href = './task.html?id=' + getUrlVar("id");
                }
            });
        }
    });



    $.get("AutoGrader/task/" + getUrlVar("id"), function (data) {

        $(".entry-title").text(data.task_name);
        $("#task_file").html("<div> <a href='http://" + data.task_file + "'>" + data.task_name + "</a></div>");

        var i = 0;

        $.each(data.results, function (index, value) {



            $("#srcs").append("<div id='src" + i + "' style='display: none;'>" + value.source + "</div>");

            $("#task-results").append("<tr>\n\
            <td>n/a</td>\n\
            <td>" + value.status + "</td>\n\
            <td>" + value.score + "</td>\n\
            <td>n/a</td>\n\
            <td><a href='#' class='showsrc' id='src" + i + "' > view source </a></td>\n\
            </tr>");

            i = i + 1;
        });

        $(".showsrc").click(function () {
            //$('#' + $(this).data('id')).toggle();
            $("#srcs").children().not('#' + $(this).attr('id')).hide();

            $('#' + $(this).attr('id')).toggle();
        });

    });
});