<?php
include "template.php";
include "views/CitizenView.php"
?>

<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_citizens").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-users'></i>&emsp;Cidadãos");
        $("#submenu").append("<li>Cidadãos</li>");
    });
</script>