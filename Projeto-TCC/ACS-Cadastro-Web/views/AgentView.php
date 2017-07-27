<?php
include "controllers/AgentsController.php";
$controller = new AgentsController();
$buttons = $controller->crudButtons("");
?>

<div id="content" class="card">
    <div class="row header" style="padding:20px 20px 50px 20px">
        <div class="col-md-8">
            <h4 class="title">Agentes comunitários cadastrados</h4>
        </div>
        <div class="col-md-4">
            <a href="#" style="float: right;">
                <?php echo $buttons[AgentsController::ADD]; ?>
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