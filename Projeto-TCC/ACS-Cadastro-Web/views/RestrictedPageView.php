<?php ?>

<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("Área restrita");
    });
</script>

<div id="content" class="card">
    <div class="header">
        <h4 class="title"><i class="fa fa-times-circle-o "></i>&emsp;Área restrita</h4>
    </div>
    <div class="content table-responsive table-full-width" style="margin: 0;">

        <div class="row">
            <div class="col-md-12" style="padding: 40px 0 150px 50px;">
                <h4>Você não tem acesso para acessar essa página.</h4>
            </div>
        </div>
    </div>
</div>

