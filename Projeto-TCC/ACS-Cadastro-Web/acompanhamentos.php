<?php include "template.php" ?>
<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_accompanies").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-eye'></i>&emsp;Acompanhamentos");
        $("#submenu").append("<li>Acompanhamentos</li>");
    });
</script>

<div id="content" class="card">
    <div class="header">
        <h4 class="title">Striped Table with Hover</h4>
        <p class="category">Here is a subtitle for this table</p>
    </div>
</div>