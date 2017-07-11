<?php include "container_menus.php" ?>

<style>
    .resize-img {
        padding: 70px  0 0 50px;
        width: 55%;
        height: auto;
    }
</style>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_home").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").text("Home");
    });
</script>

<div id="content" class="card">

    <div class="text-left">
        <img src="assets/img/ic_main.png" class="resize-img">
    </div>
</div>

