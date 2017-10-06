<?php
include_once "models/AgentModel.class.php";
$agent = new AgentModel($_SESSION['NAME'], $_SESSION['NUM_SUS'], $_SESSION['AREA'], $_SESSION['EQUIP'], $_SESSION['ACCESS']);
?>

<script src="assets/js/script-general.js" type="text/javascript"></script>
<script>
    $(function () {
        //$(".nav li").removeClass("active");
        $("#title_menu").html("Perfil");
        $("#main_content").html($("#content"));
        $("#submenu").append("<li>Meus Dados</li>");
    });
</script>


<div id="content" class="card">

    <div class="row header" style="padding:40px 20px 20px 20px">
        <div class="col-md-8">
            <h4 class="title"><i class='fa fa-vcard-o'></i>&emsp;Meus Dados</h4>
        </div>
    </div>
    <div class="content table-responsive table-full-width" style="margin: 0;">

        <div class="row">
            <div class="col-md-8">
                <div class="form-group">
                    <label for="name">Nome</label>
                    <input type="text" id="name" name="name" class="form-control" disabled
                           value="<?php echo $agent->getName() ?>">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="access">Nível de acesso</label>
                    <input type="text" id="name" name="name" class="form-control" disabled
                           value="<?php echo $agent->getAccessAsText() ?>">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="numSus">Numero do SUS</label>
                    <input type="number" name="numSus" class="form-control" id="numSus" disabled
                           value="<?php echo $agent->getNumSus() ?>">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="area">Area</label>
                    <input type="number" name="area" class="form-control" id="area" disabled
                           value="<?php echo $agent->getArea() ?>">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="equip">Equipe</label>
                    <input type="number" name="equip" class="form-control" id="equip" disabled
                           value="<?php echo $agent->getEquip() ?>">
                </div>
            </div>
        </div>
        <div id="buttons" class="row" style="padding: 0 50px 0 50px">
            <div style="float: right; padding:40px 20px 20px 20px">
                <button name='cancel' class='btn btn-primary btn-fill' onclick='redirect("home");'>Ok</button>
            </div>
        </div>
    </div>
</div>