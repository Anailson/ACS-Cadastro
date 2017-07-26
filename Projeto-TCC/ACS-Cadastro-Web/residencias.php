<?php
include "template.php";
include "views/ResidenceView.php";
?>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_residences").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-home'></i>&emsp;Residencias");
        $("#submenu").append("<li>Residências</li>");
    });
</script>