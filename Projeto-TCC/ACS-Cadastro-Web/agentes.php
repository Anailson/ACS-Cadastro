<?php
include "template.php";
include "views/AgentView.php";
?>

<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_agents").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-vcard-o'></i>&emsp;Agentes de Saúde");
        $("#submenu").append("<li>Agentes de Saúde</li>")
    });
</script>
