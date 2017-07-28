<?php
include "controllers/ResidenceController.class.php";
$controller = new ResidenceController();
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
                echo $buttons[ResidenceController::ADD];
                ?>
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
            <tbody>
            <td></td>
            <td></td>
            <td></td>
            <td> <?php echo $buttons[ResidenceController::DETAILS] ?> </td>
            <td> <?php echo $buttons[ResidenceController::EDIT] ?> </td>
            <td> <?php echo $buttons[ResidenceController::DELETE] ?> </td>
            </tbody>
        </table>
    </div>
</div>

