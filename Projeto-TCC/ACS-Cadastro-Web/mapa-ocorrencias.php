<?php include "template.php" ?>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_map").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-map-marker'></i>&emsp;Mapa de ocorrências");
        $("#submenu").append("<li>Mapa de ocorrências</li>");
    });
</script>

<div id="content" class="card">
    <div class="header">
        <h4 class="title">Striped Table with Hover</h4>
        <p class="category">Here is a subtitle for this table</p>
    </div>
</div>