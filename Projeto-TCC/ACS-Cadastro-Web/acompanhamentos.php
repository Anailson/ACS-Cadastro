<?php
include "template.php" ;
include "views/AccompanyView.php"?>

<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_accompanies").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-eye'></i>&emsp;Acompanhamentos");
        $("#submenu").append("<li>Acompanhamentos</li>");
    });
</script>