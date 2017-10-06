<?php
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

<div id='content' class='card'>
    <div class='text-left'>
        <img src='assets/img/ic_main.png' class='resize-img'>
    </div>
</div>
