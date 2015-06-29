$(document).ready(function () {
    $("#login-btn").click(function () {

        var username = $("#username").val();
        //var email = $("#email").val();
        var passwd = $("#passwd").val();
        //var passwd_r = $("#passwd_r").val();

        if (username === "" || passwd === "")
        {
            alert("Empty fields!");
            validationStatus = 0;
        }
        else
        {
            $.ajax({
                url: 'AutoGrader/user/login',
                type: "POST",
                data: {name: username, passwd: passwd},
                dataType: "json",
                success: function (data) {

                    if (data.srvResponse === "ok") {
                        $.getScript("./scripts/js-cookie.js", function () {

                            console.log(data);
                            Cookies.set('AutoGraderUser', data.username, {expires: 7});
                            Cookies.set('Role', data.role);

                            window.location.href = './';
                        });
                    } else {
                        alert("Invalid username or password!");
                    }

                }
            });
        }
    });
});