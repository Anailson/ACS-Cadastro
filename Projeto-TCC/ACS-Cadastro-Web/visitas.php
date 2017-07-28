<?php
include "template.php";
include "views/VisitView.php"; ?>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_visits").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-clock-o'></i>&emsp;Visitas");
        $("#submenu").append("<li>Visitas</li>");
    });
</script>