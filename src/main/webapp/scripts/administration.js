
function refreshUsers() {
    $("#users").find("tr:gt(0)").remove();
    $.get('AutoGrader/user/all/0/0',  function(data) {  
    //console.log(data);
    u = $("#users");
    
    for (var i = 0; i < data.length; i++) { 
        t = u.append("<tr>"+
                "<td>"+data[i].username+"</td>"+
                "<td>"+data[i].role+"</td>"+
                "<td>"+"<button id=\"btn_"+data[i].username+"\"type=\"button\" onclick=\"changeUser('"+data[i].username+"' )\">Change Role</button></td>"+
                "</tr>");
    }
   
    
    }); 
    

}
refreshUsers();
function changeUser(uname){
   $.post('AutoGrader/user/role', {username: uname}, function(data) {
        if(data === "OK"){
            refreshUsers();
        }
   });
   
   

}
