<?php include "template.php" ?>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_residences").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-home'></i>&emsp;Residencias");
        $("#submenu").append("<li>Residências</li>");
    });
</script>

<div id="content" class="card">
    <div class="header">
        <h4 class="title">Striped Table with Hover</h4>
        <p class="category">Here is a subtitle for this table</p>
    </div>
</div>