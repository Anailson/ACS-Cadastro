<?php
?>

<style>
    .border {
        border: solid 2px #dfdfe1;
        padding: 10px;
        margin: 0 0 10px 0;
    }
</style>

<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-visit.js" type="text/javascript"></script>
<script>
    window.onload = function () {
        $("#main_content").html($("#content"));
        initChosen();
        start();
    };
</script>

<div id="content" class="card">

    <div class="row header" style="padding:40px 20px 20px 20px">
        <div class="col-md-8">
            <h4 class="title"><i class='fa fa-clock-o'></i>&emsp;Novo Acompanhamento</h4>
        </div>
    </div>
    <div class="content" style="margin: 0;">

        <div class="header" style="padding: 10px 30px 30px 30px">
            <p class="category"><h5 id="title"></h5></p>
            <div class="progress">
                <div id="progressBar" class="progress-bar" role="progressbar" style="width: 0;"
                     aria-valuemax="100"></div>
            </div>
        </div>

        <div id="step-one" class="border">
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="record">Numero do prontuário</label>
                    <input type="number" id="record" name="record" class="form-control">
                </div>
                <div class="col-md-4">
                    <label for="shift">Turno</label>
                    <select name="shift" id="shift">
                        <option value=""></option>
                        <option value="Manhã">Manhã</option>
                        <option value="Tarde">Tarde</option>
                        <option value="Noite">Noite</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label for="isShared">
                        <input id="isShared" name="isShared" type="checkbox"> Visita compartilhada
                    </label>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-4">
                    <label for="numSus">Numero do SUS</label>
                    <input type="number" id="numSus" name="numSus" class="form-control">
                </div>
                <div class="col-md-4">
                    <label for="birthDate">Data de nascimento</label>
                    <div class="input-group">
                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                        <input type="date" id="birthDate" name="birthDate" class="form-control" required>
                    </div>
                </div>
                <div class="col-md-4">
                    <label for="sex">Sexo</label>
                    <select name="sex" id="sex">
                        <option value=""></option>
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                    </select>
                </div>
            </div>
        </div>

        <div id="step-two" class="border">
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="recordUpdate">
                        <input id="recordUpdate" name="recordUpdate" type="checkbox"> Cadastramento / Atualização
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="visitPeriodic">
                        <input id="visitPeriodic" name="visitPeriodic" type="checkbox"> Visita periódica
                    </label>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-12">
                    <label>Busca ativa</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-2">
                                <label for="appointment">
                                    <input id="appointment" name="appointment" type="checkbox"> Consulta
                                </label>
                            </div>
                            <div class="col-md-2">
                                <label for="exam">
                                    <input id="exam" name="exam" type="checkbox"> Exame
                                </label>
                            </div>
                            <div class="col-md-2">
                                <label for="vaccinate">
                                    <input id="vaccinate" name="vaccinate" type="checkbox"> Vacina
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="bolsaFamiliaConditions">
                                    <input id="bolsaFamiliaConditions" name="bolsaFamiliaConditions" type="checkbox"> Condicionalidade do Bolsa Família
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-12">
                    <label>Acompanhamento</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="pregnant">
                                    <input id="pregnant" name="pregnant" type="checkbox"> Gestante
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="puerpera">
                                    <input id="puerpera" name="puerpera" type="checkbox"> Puérpera
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="newborn">
                                    <input id="newborn" name="newborn" type="checkbox"> Recém-nascido
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="child">
                                    <input id="child" name="child" type="checkbox"> Criança
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="malnutrition">
                                    <input id="malnutrition" name="malnutrition" type="checkbox"> Pessoa com desnutrição
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="rehabilitation">
                                    <input id="rehabilitation" name="rehabilitation" type="checkbox"> Pessoa em
                                    reabilitação ou com deficiência
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="hypertension">
                                    <input id="hypertension" name="hypertension" type="checkbox"> Pessoa com hypertensão
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="diabetes">
                                    <input id="diabetes" name="diabetes" type="checkbox"> Pessoa com diabetes
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="asthma">
                                    <input id="asthma" name="asthma" type="checkbox"> Pessoa com Asma
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="dpoc">
                                    <input id="dpoc" name="dpoc" type="checkbox"> Pessoa com DPOC/Enfisema
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="cancer">
                                    <input id="cancer" name="cancer" type="checkbox"> Pessoa com Câncer
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="chronicDisease">
                                    <input id="chronicDisease" name="chronicDisease" type="checkbox"> Pessoa com outras Doenças Crônicas
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="leprosy">
                                    <input id="leprosy" name="leprosy" type="checkbox"> Pessoa com Hanseníase
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="tuberculosis">
                                    <input id="tuberculosis" name="tuberculosis" type="checkbox"> Pessoa com Tuberculose
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="respiratory">
                                    <input id="respiratory" name="respiratory" type="checkbox"> Sintomáticos Respiratórios
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="smoker">
                                    <input id="smoker" name="smoker" type="checkbox"> Tabagista
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="domiciled">
                                    <input id="domiciled" name="domiciled" type="checkbox"> Domiciliados / Acamados
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="vulnerability">
                                    <input id="vulnerability" name="vulnerability" type="checkbox"> Condições de Vulnerabilidade Social
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="bolsaFamilia">
                                    <input id="bolsaFamilia" name="bolsaFamilia" type="checkbox"> Condicionalidades do Bolsa Família
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="mentalHealth">
                                    <input id="mentalHealth" name="mentalHealth" type="checkbox"> Saúde Mental
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="alcohol">
                                    <input id="alcohol" name="alcohol" type="checkbox"> Usuário de álcool
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="drugs">
                                    <input id="drugs" name="drugs" type="checkbox"> Usuário de outras drogas
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="interment">
                        <input id="interment" name="interment" type="checkbox"> Egresso de Internação
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="controlEnvironment">
                        <input id="controlEnvironment" name="controlEnvironment" type="checkbox"> Controle de Ambientes / Vetores
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="collectiveActivities">
                        <input id="collectiveActivities" name="collectiveActivities" type="checkbox"> Convite Atividades Coletivas / Campanha de Saúde
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="guidance">
                        <input id="guidance" name="guidance" type="checkbox"> Orientação / Prevenção
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="another">
                        <input id="another" name="another" type="checkbox"> Outro
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label>Desfecho</label>
                    <select name="shift" id="shift">
                        <option value=""></option>
                        <option value="Visita Realizada">Visita Realizada</option>
                        <option value="Visita Recusada">Visita Recusada</option>
                        <option value="Ausente">Ausente</option>
                    </select>
                </div>
            </div>
        </div>

        <div id="buttons" class="row" style="padding: 0 50px 0 50px">
            <div style="float: right; margin:40px 20px 20px 20px">
                <button id='save' name='save' class='btn btn-primary btn-fill' onclick='next();'></button>
            </div>
            <div style="float: left; padding:40px 20px 20px 20px">
                <button id='cancel' name='cancel' class='btn btn-primary btn-fill' onclick='back();'></button>
            </div>
        </div>
    </div>
</div>