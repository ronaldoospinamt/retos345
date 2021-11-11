var logout = function () {
    $.post("/logout", function () {
        $("#user").html('');
        $(".unauthenticated").show();
        $(".authenticated").hide();
    });
    window.location.href = '#';
    return true;
}

$.get("/user", function (data) {
            console.log("get");
            console.log(data);
            $("#user").html(data.name);
            $(".unauthenticated").hide();
            $(".authenticated").show();
});