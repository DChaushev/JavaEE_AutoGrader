$(document).ready(function() {
    $("#login-btn").click(function() {
        var username = $("#username").val();
        //var email = $("#email").val();
        var passwd = $("#passwd").val();
        //var passwd_r = $("#passwd_r").val();
        
        var validationStatus = 1;
        
       /* if(username === "" || email === "" || passwd === "" || passwd_r == "")
        {
            alert("Please don't leave any empy fileds!");
            validationStatus = 0;
        }
        
        if(passwd !== passwd_r)
        {
            alert("The two passwords doesn't match!");
        } */
        
        if(Boolean(validationStatus))
        {
          
          $.post('AutoGrader/user/login', {name: username, passwd: passwd}, function(data) {  
                  $("#server-response").text(data);
                  
                  $.getScript("js-cookie.js", function(){

                    Cookies.set('AutoGraderUser', username, { expires: 7 });
                    // Use anything defined in the loaded script...
                    window.location.href='/AutoGrader';
                  });
                  
            });
        }
    });
});