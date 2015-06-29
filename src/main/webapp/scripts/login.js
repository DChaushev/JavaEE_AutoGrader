$(document).ready(function() {
    $("#login-btn").click(function() {
       
        var username = $("#username").val();
        //var email = $("#email").val();
        var passwd = $("#passwd").val();
        //var passwd_r = $("#passwd_r").val();
        
        var validationStatus = 1;
        
        if(username === "" || passwd === "")
        {
            alert("Empty fields!");
            validationStatus = 0;
        }
        
        if(Boolean(validationStatus))
        {
          
          $.post('AutoGrader/user/login', {name: username, passwd: passwd}, function(data) {  
                  $("#server-response").text(data);
                  
                  $.getScript("./scripts/js-cookie.js", function(){
                    Cookies.set('AutoGraderUser', data.username, { expires: 7 });
                    Cookies.set('Role',data.role);
                    //console.log(data);
                    
                    // Use anything defined in the loaded script...

                    window.location.href='./';
                  });
                  
            });
        }
    });
});