<?php
include "template.php";
include "views/HomeView.php";
?>

<style>
    .resize-img {
        padding: 50px 0 0 50px;
        width: 60%;
        height: auto;
    }
</style>

<script>


    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_home").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-globe'></i> Home");
    });
</script>