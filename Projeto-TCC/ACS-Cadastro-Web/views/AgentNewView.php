<?php
include_once "controllers/AgentsController.php";
$controller = new AgentsController();

$result = null;
if (isset($_POST['save'])) {

    $name = $_POST["name"];
    $numSus = $_POST["numSus"];
    $area = $_POST["area"];
    $equip = $_POST["equip"];
    $access = $_POST["access"];
    $result = $controller->save($name, $numSus, $area, $equip, $access);
}
?>

<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-agent.js" type="text/javascript"></script>
<script>
    $(function () {
        const result = "<?php echo isset($result) ? $result : '-'; ?>";
        if(result != '-') {
            confirmSave(result);
        }
        initChosen();
    });
</script>

<div id="content" class="card">

    <div class="row header" style="padding:40px 20px 20px 20px">
        <div class="col-md-8">
            <h4 class="title"><i class='fa fa-vcard-o'></i>&emsp;Novo Agente de Sáude</h4>
        </div>
    </div>

    <div class="content table-responsive table-full-width" style="margin: 0;">
        <form method="post">
        <div class="row">
            <div class="col-md-8">
                <div class="form-group">
                    <label for="name">Nome</label>
                    <input type="text" id="name" name="name" class="form-control" value="">
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-group">
                    <label for="access">Nível de acesso</label>
                    <select name="access" id="access" required>
                        <option value="<?php echo AgentModel::AGENT[0]; ?>"><?php echo AgentModel::AGENT[1]; ?></option>
                        <option value="<?php echo AgentModel::ADMINISTRATOR[0]; ?>"><?php echo AgentModel::ADMINISTRATOR[1]; ?></option>
                    </select>
                </div>
            </div>
        </div>
        <form action="" method="post">
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="numSus">Numero do SUS</label>
                        <input type="number" name="numSus" class="form-control" value="" id="numSus">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="area">Area</label>
                        <input type="number" name="area" class="form-control" value="" id="area">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label for="equip">Equipe</label>
                        <input type="number" name="equip" class="form-control" value="" id="equip">
                    </div>
                </div>
            </div>
            <div id="buttons" class="row" style="padding: 0 50px 0 50px">
                <div style="float: right; padding:40px 20px 20px 20px">
                    <button name='save' class='btn btn-primary btn-fill'>Salvar</button>
                </div>
                <div style="float: left; padding:40px 20px 20px 20px">
                    <button name='cancel' class='btn btn-primary btn-fill' onclick="backAgents()">Cancelar</button>
                </div>
            </div>
        </form>
    </div>
</div>