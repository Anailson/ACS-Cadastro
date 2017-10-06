<?php
echo "NewAgentView<br><br>";
include "controllers/AgentsController.php";
?>

<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_agents").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-newspaper-o'></i>&emsp;NewAgentView");
        $("#submenu").append("<li>NewAgentView</li>");
    });
</script>

<div id="content" class="card">
    <div class="header">
        <h4 class="title">NewAgentView</h4>
    </div>
</div>