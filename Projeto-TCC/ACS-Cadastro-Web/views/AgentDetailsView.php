<?php
include_once "controllers/AgentsController.php";
$controller = new AgentsController();

$result = 1;
$numSus = -1;
$agent = null;
if (isset($_GET['p']) && !empty($_GET['p'])) {

    $numSus = $_GET['p'];
    $result = $controller->get($numSus);

    if($result != Controller::EMPTY_VALUES && $result != Controller::GENERAL_ERROR){

        $name = $result[AgentModel::NAME];
        $area = $result[AgentModel::AREA];
        $equip = $result[AgentModel::EQUIP];
        $access = $result[AgentModel::ACCESS];
        $agent = new AgentModel($name, $numSus, $area, $equip, $access);
    }
}
?>
<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-agent.js" type="text/javascript"></script>
<script>
    $(function () {
        confirmDetails("<?php echo $result; ?>");
    })
</script>

<div id="content" class="card">

    <div class="row header" style="padding:40px 20px 20px 20px">
        <div class="col-md-8">
            <h4 class="title"><i class='fa fa-vcard-o'></i>&emsp;Detalhes do Agente de Saúde</h4>
        </div>
    </div>
    <div class="content table-responsive table-full-width" style="margin: 0;">

<?php if(!$agent){ ?>
        <div class="row" >
            <div class="col-md-12" style="padding: 40px 0 150px 50px;">
                <h4><i class='fa fa-close'></i>&emsp;Não foi encontrado nenhum registro para os dados informados.</h4>
                <h5><i class='fa '></i>&emsp;Número do SUS
                    <?php echo $numSus == -1 ? " não informado" : " inválido";?>.
                </h5>
            </div>
        </div>
<?php } else {?>
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
                <button name='cancel' class='btn btn-primary btn-fill' onclick='redirect("agentes");'>Ok</button>
            </div>
        </div>
    </div>
</div>

<?php } ?>