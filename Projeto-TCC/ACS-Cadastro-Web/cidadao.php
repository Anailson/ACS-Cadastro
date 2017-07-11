<?php include "container_menus.php" ?>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_citizens").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").text("Cidadão");
        $("#sub-title").text("Cidadão").attr("href", "cidadao.php");
    });
</script>

<div id="content" class="card">
    <div class="header">
        <h4 class="title">Striped Table with Hover</h4>
        <p class="category">Here is a subtitle for this table</p>
    </div>
</div>