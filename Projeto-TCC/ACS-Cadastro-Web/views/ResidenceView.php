<?php
include "controllers/ResidenceController.class.php";
$controller = new ResidenceController();
$buttons = $controller->crudButtons();
?>

<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-residence.js" type="text/javascript"></script>


<div id="content" class="card">
    <div class="row header" style="padding:40px 20px 20px 20px">
        <div class="col-md-8">
            <h4 class="title"><i class='fa fa-home'></i>&emsp;Residências cadastrados</h4>
        </div>
        <div class="col-md-4">
            <a style="float: right;">
                <?php echo $buttons[ResidenceController::ADD]; ?>
            </a>
        </div>
    </div>

    <div class="content table-responsive table-full-width" style="margin: 0;">

        <table id="table" class="table table-hover table-bordered dataTable" cellspacing="0" width="100%">
            <thead>
            <th class="tdPers col-md-6">Endereço</th>
            <th class="tdPers col-md-1">CEP</th>
            <th class="tdPers col-md-2">Telefone</th>
            <th class="tdPers col-md-1"></th>
            <th class="tdPers col-md-1"></th>
            <th class="tdPers col-md-1"></th>
            </thead>
            <?php
            $records = $controller->getAllSimpleInfoResidence();
            foreach ($records as $record) {
                echo "<tr><td>" . $record[AddressDataModel::ADDRESS_DATA] . "</td>"
                    . "<td>" . $record[CityLocation::CEP] . "</td>"
                    . "<td>" . $record[Phones::PHONES] . "</td>"
                    . "<td>" . $buttons[ResidenceController::DETAILS] . "</td>"
                    . "<td>" . $buttons[ResidenceController::EDIT] . "</td>"
                    . "<td>" . $buttons[ResidenceController::DELETE] . "</td></tr>";
            }
            ?>
            </tbody>
        </table>
    </div>
</div>