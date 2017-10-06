<?php

if (!@include_once "controllers/OccurrencesMapController.class.php") {
    include_once "../controllers/OccurrencesMapController.class.php";
};
$controller = new OccurrencesMapController();

if (isset($_POST['filter']) and $_POST['filter']) {
    $controller->setOccurrencesOnMap($_POST['filter'], $_POST['parameter']);
}
?>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA0rr4Oo1hl224JfqbecfMtzlgc4T3quBc"></script>
<script src="assets/js/script-map.js" type="text/javascript"></script>
<script src="assets/js/script-general.js" type="text/javascript"></script>

<style>
    .vertical-gap {
        padding-top: 20px;
    }
</style>

<script>
    $(function () {
        initChosen();
        initMap();
        $(".nav li").removeClass("active");
        $("#menu_map").addClass("active");
        $("#main_content").html($("#content"));
        $("#title_menu").html("Mapa de ocorrências");
        $("#submenu").append("<li>Mapa de ocorrências</li>");
    });
</script>

<div id="content">
    <div class="row">
        <div class="col-md-3 ">
            <div class="card" style="height:600px">

                <div class="header">
                    <h4 class="title">Filtrar ocorrências</h4>
                    <p class="category"></p>
                </div>
                <div style="padding: 15px">
                    <div class="vertical-gap">
                        <label for="city">Cidade</label>
                        <select name="city" id="city" disabled>
                            <option value="ALL">TODAS</option>
                            <option value="ITABAIANA">ITABAIANA</option>
                            <option value="ARACAJU">ARACAJU</option>
                        </select>
                    </div>
                    <div class="vertical-gap">
                        <label for="filter">Filtra por:</label>
                        <select onchange="changeOccurrence();" name="filter" id="filter">
                            <option value="ALL"></option>
                            <optgroup label="CIDADÃOS">
                                <option value="SOCIAL_DEMOGRAPHIC">INFORMAÇÕES SÓCIO-DEMOGRAFICAS</option>
                                <option value="HEALTH_CONDITIONS">CONDIÇÕES / SITUAÇÃO DE SAÚDE GERAL</option>
                                <option value="STREET_SITUATION">SITUAÇÃO DE RUA</option>
                            </optgroup>
                            <optgroup label="ACOMPANHAMENTOS">
                                <option value="CONDITIONS">CONDIÇÃO AVALIADA</option>
                                <option value="EXAMS">EXAMES SOLICITADOS / AVALIADOS</option>
                            </optgroup>
                            <optgroup label="RESIDÊNCIAS">
                                <option value="HOUSING_CONDITIONS">CONDIÇÕES DE MORADIA</option>
                            </optgroup>
                        </select>
                    </div>
                    <div class="vertical-gap">
                        <label for="parameter">Parâmetro</label>
                        <select name="parameter" id="parameter" data-placeholder="Selecione uma parâmetro">
                            <option value="ALL"></option>
                        </select>
                    </div>
                    <div class="vertical-gap align-items-end">
                        <button onclick="getMarkers();" id="filter" name="filter" class='btn btn-primary btn-fill btn-block'>
                            Filtrar
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-9 ">
            <div class="card">
                <div id="map" style="height: 600px; "></div>
            </div>
        </div>
    </div>
</div>