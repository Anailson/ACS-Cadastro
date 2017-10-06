<?php
    include_once "controllers/AgentsController.php";
    $controller = new AgentsController();
    $buttons = $controller->crudButtons("");
?>
<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-agent.js" type="text/javascript"></script>
<div id="content" class="card">

    <div class="row header" style="padding:40px 20px 20px 20px">
        <div class="col-md-8">
            <h4 class="title"><i class='fa fa-vcard-o'></i>&emsp;Agentes comunitários cadastrados</h4>
        </div>
        <div class="col-md-4">
            <a style="float: right;">
                <?php echo $controller->crudButtons("")[AgentsController::ADD] ?>
            </a>
        </div>
    </div>

    <div class="content table-responsive table-full-width" style="margin: 0;">
        <table id="table" class="table table-hover table-bordered dataTable" cellspacing="0" width="100%">
            <thead>
            <th class="tdPers col-md-2">Nº do SUS</th>
            <th class="tdPers col-md-5">Nome</th>
            <th class="tdPers col-md-1">Área</th>
            <th class="tdPers col-md-1">Equipe</th>
            <th class="tdPers col-md-1"></th>
            <th class="tdPers col-md-1"></th>
            <th class="tdPers col-md-1"></th>
            </thead>
            <tbody>
            <?php
            $agents = $controller->getAll();
            foreach ($agents as $agent) {

                $buttons = $controller->crudButtons($agent->getNumSus());
                echo "<tr><td>" . $agent->getNumSus() . "</td>"
                    . "<td>" . $agent->getName() . "</td>"
                    . "<td>" . $agent->getArea() . "</td>"
                    . "<td>" . $agent->getEquip() . "</td>"
                    . "<td>" . $buttons[AgentsController::DETAILS] . "</td>"
                    . "<td>" . $buttons[AgentsController::EDIT] . "</td>"
                    . "<td>" . $buttons[AgentsController::DELETE] . "</td></tr>";
            }
            ?>
            </tbody>
        </table>
    </div>
</div>