<?php include "template.php" ?>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_report").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-newspaper-o'></i>&emsp;Relatorios");
        $("#submenu").append("<li>Relatórios</li>");
    });
</script>

<div id="content" class="card">
    <div class="header">
        <h4 class="title">Striped Table with Hover</h4>
        <p class="category">Here is a subtitle for this table</p>
    </div>
</div>