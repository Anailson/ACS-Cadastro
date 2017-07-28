<?php
include "controllers/VisitController.class.php";
$controller = new VisitController();
$buttons = $controller->crudButtons();
?>

<div id="content" class="card">
    <div class="row header" style="padding:20px 20px 50px 20px">
        <div class="col-md-8">
            <h4 class="title">Residências cadastrados</h4>
        </div>
        <div class="col-md-4">
            <a href="#" style="float: right;">
                <?php
                echo $buttons[VisitController::ADD];
                ?>
            </a>
        </div>
    </div>

    <div class="content table-responsive table-full-width" style="margin: 0;">

        <table id="table" class="table table-hover table-bordered dataTable" cellspacing="0" width="100%">
            <thead>
                <th class="tdPers col-md-1">Prontuário</th>
                <th class="tdPers col-md-6">Nome</th>
                <th class="tdPers col-md-2">Nº do SUS</th>
                <th class="tdPers col-md-1"></th>
                <th class="tdPers col-md-1"></th>
                <th class="tdPers col-md-1"></th>
            </thead>
            <tbody>

            <?php
            $info = $controller->getAllSimpleVisitInfo();
            foreach ($info as $i) {
                echo "<tr><td>" . $i[RecordDetails::RECORD] . "</td>"
                    . "<td> " . $i[Particular::NAME] ."</td>"
                    . "<td> " . $i[Particular::NUM_SUS] ."</td>"
                    . "<td> " . $buttons[VisitController::DETAILS]  ."</td>"
                    . "<td> " . $buttons[VisitController::EDIT]  ."</td>"
                    . "<td> " . $buttons[VisitController::DELETE]  ."</td>";
            }
            ?>
            </tbody>
        </table>
    </div>
</div>
