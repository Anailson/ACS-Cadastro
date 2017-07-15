<?php
include "template.php";
include "views/AgentsView.php";
?>

<script src="assets/js/datatable-config.js"></script>

<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#menu_agents").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("<i class='fa fa-vcard-o'></i>&emsp;Agentes de Saúde");
        $("#submenu").append("<li>Agentes de Saúde</li>")
    });
</script>
