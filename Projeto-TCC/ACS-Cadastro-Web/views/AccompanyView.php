<?php
include "controllers/AccompanyController.class.php";
$controller = new AccompanyController();
$buttons = $controller->crudButtons();
?>

<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-accompany.js" type="text/javascript"></script>

<div id="content" class="card">
    <div class="row header" style="padding:40px 20px 20px 20px">
        <div class="col-md-8">
            <h4 class="title"><i class='fa fa-eye'></i>&emsp;Acompanhamentos cadastrados</h4>
        </div>
        <div class="col-md-4">
            <a style="float: right;">
                <?php echo $buttons[AccompanyController::ADD]; ?>
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
                $recordInfo = $controller->getAllSimpleInfoAccompany();
                foreach ($recordInfo as $info) {
                    echo "<tr><td>" . $info[RecordDetails::RECORD] . "</td>"
                        . "<td>" . $info[Particular::NAME] . "</td>"
                        . "<td>" . $info[Particular::NUM_SUS] . "</td>"
                        . "<td>" . $buttons[AccompanyController::DETAILS] . "</td>"
                        . "<td>" . $buttons[AccompanyController::EDIT] . "</td>"
                        . "<td>" . $buttons[AccompanyController::DELETE] . "</td></tr>";
                }
                ?>
            </tbody>
        </table>
    </div>
</div>
