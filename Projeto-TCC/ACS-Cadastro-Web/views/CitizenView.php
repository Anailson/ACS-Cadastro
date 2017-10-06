<?php
include "controllers/CitizenController.class.php";
$controller = new CitizenController();
$buttons = $controller->crudButtons();
?>

<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-citizen.js" type="text/javascript"></script>

<div id="content" class="card">
    <div class="row header" style="padding:40px 20px 20px 20px">
        <div class="col-md-8">
            <h4 class="title"><i class='fa fa-users'></i>&emsp;Cidadãos cadastrados</h4>
        </div>
        <div class="col-md-4">
            <a style="float: right;">
                <?php echo $buttons[CitizenController::ADD]; ?>
            </a>
        </div>
    </div>

    <div class="content table-responsive table-full-width" style="margin: 0;">
        <table id="table" class="table table-hover table-bordered dataTable" cellspacing="0" width="100%">
            <thead>
            <th class="tdPers col-md-1">Nº do SUS</th>
            <th class="tdPers col-md-6">Nome</th>
            <th class="tdPers col-md-2">Telefone</th>
            <th class="tdPers col-md-1"></th>
            <th class="tdPers col-md-1"></th>
            <th class="tdPers col-md-1"></th>
            </thead>
            <tbody>
            <?php
            $infos = $controller->getAllParticularInfo();
            foreach ($infos as $info) {

                echo "<tr><td>" . $info[Particular::NUM_SUS] . "</td>"
                    . "<td> " . $info[Particular::NAME] . "</td>"
                    . "<td> " . $info[Contact::PHONE] . "</td>"
                    . "<td>" . $buttons[CitizenController::DETAILS] . "</td>"
                    . "<td>" . $buttons[CitizenController::EDIT] . "</td>"
                    . "<td>" . $buttons[CitizenController::DELETE] . "</td></tr>";
            }
            ?>
            </tbody>
        </table>


    </div>
</div>
