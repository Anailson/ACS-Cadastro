<?php ?>

<script>
    $(function () {
        $(".nav li").removeClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("Página não encontrada");
    });
</script>

<div id="content" class="card">
    <div class="header">
        <h4 class="title"><i class="fa fa-bug"></i>&emsp;Página não encontrada</h4>
    </div>
    <div class="content table-responsive table-full-width" style="margin: 0;">

        <div class="row">
            <div class="col-md-12" style="padding: 40px 50px 150px 50px;">

                <p><h4>A página que você está tentando acessar aparentemente não existe.
                        Por favor, verifique o endereço digitado e tente novamente.
                        Se o erro persistir, informe um administrador.</h4></p>
            </div>
        </div>
    </div>
</div>

