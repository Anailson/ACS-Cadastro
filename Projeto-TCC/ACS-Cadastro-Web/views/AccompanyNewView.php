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
<script src="assets/js/script-accompany.js" type="text/javascript"></script>
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
            <h4 class="title"><i class='fa fa-eye'></i>&emsp;Novo Acompanhamento</h4>
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

        <div id="step-one" class="hidden">
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
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="placeCare">Local do atendimento</label>
                    <select name="placeCare" id="placeCare">
                        <option value=""></option>
                        <option value="UBS">UBS</option>
                        <option value="Unidade Móvel">Unidade Móvel</option>
                        <option value="Rua">Rua</option>
                        <option value="Domicílio">Domicílio</option>
                        <option value="Escola / Creche">Escola / Creche</option>
                        <option value="Polo (Academia da saúde)">Polo (Academia da saúde)</option>
                        <option value="Instituição / Abrigo">Instituição / Abrigo</option>
                        <option value="Unidade prisional ou congêneres">Unidade prisional ou congêneres</option>
                        <option value="Unidade socioeducativa">Unidade socioeducativa</option>
                        <option value="Outro">Outro</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="typeCare">Tipo do atendimento</label>
                    <select name="typeCare" id="typeCare">
                        <option value=""></option>
                        <option value="Consulta agendada programada / Cuidado continuado">Consulta agendada programada /
                            Cuidado continuado
                        </option>
                        <option value="Consulta agendada">Consulta agendada</option>
                        <option value="Escuta inicial / Orientação">Escuta inicial / Orientação</option>
                        <option value="Consulta no dia">Consulta no dia</option>
                        <option value="Atendimento de urgência">Atendimento de urgência</option>
                    </select>
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

            <div class="row form-group">
                <div class="col-md-8">
                    <label>Avaliação antropometrca</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="height">Altura (cm)</label>
                                <input type="number" id="height" name="height" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label for="weight">Peso (Kg)</label>
                                <input type="number" id="weight" name="weight" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label for="vaccinates">
                                    <input id="vaccinates" name="vaccinates" type="checkbox"> Vacinas em dia
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <label>Criança</label>
                    <div class="border" style="padding-bottom: 25px">
                        <div class="row form-group">
                            <div class="col-md-12">
                                <label for="feedingBreast">Aleitamento materno</label>
                                <select name="feedingBreast" id="feedingBreast">
                                    <option value=""></option>
                                    <option value="Exclusivo">Exclusivo</option>
                                    <option value="Predominante">Predominante</option>
                                    <option value="Complementado">Complementado</option>
                                    <option value="Inexistente">Inexistente</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <label>Gestantes</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="dum">DUM</label>
                                <input type="number" id="dum" name="dum" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label for="weeks">Idade gestacional(semanas)</label>
                                <input type="number" id="weeks" name="weeks" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label for="plannedPregnancy">
                                    <input id="plannedPregnancy" name="plannedPregnancy" type="checkbox"> Gravidez
                                    planejada
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="previousPregnancy">Gestações previas</label>
                                <input type="number" id="previousPregnancy" name="previousPregnancy"
                                       class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label for="childBirth">Partos</label>
                                <input type="number" id="childBirth" name="childBirth" class="form-control">
                            </div>
                            <div class="col-md-4">
                                <label for="homeCare">Atenção domiciliar</label>
                                <select name="homeCare" id="homeCare">
                                    <option value=""></option>
                                    <option value="Modalidade AD1">Modalidade AD1</option>
                                    <option value="Modalidade AD2">Modalidade AD2</option>
                                    <option value="Modalidade AD3">Modalidade AD3</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="step-two" class="hidden">
            <div class="row form-group">
                <div class="col-md-3">
                    <label for="asthma">
                        <input id="asthma" name="asthma" type="checkbox"> Asma
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="malnutrition">
                        <input id="malnutrition" name="malnutrition" type="checkbox"> Desnutrição
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="diabetes">
                        <input id="diabetes" name="diabetes" type="checkbox"> Diabetes
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="dpoc">
                        <input id="dpoc" name="dpoc" type="checkbox"> DPOC
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-3">
                    <label for="hypertension">
                        <input id="hypertension" name="hypertension" type="checkbox"> Hypertensão arterial
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="obesity">
                        <input id="obesity" name="obesity" type="checkbox"> Obesidade
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="prenatal">
                        <input id="prenatal" name="prenatal" type="checkbox"> Pré-natal
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="childCare">
                        <input id="childCare" name="childCare" type="checkbox"> Puericultura
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-3">
                    <label for="puerperium">
                        <input id="puerperium" name="puerperium" type="checkbox"> Puerpério (até 42 dias)
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="sexualHealth">
                        <input id="sexualHealth" name="sexualHealth" type="checkbox"> Saúde sexual e reprodutica
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="smoking">
                        <input id="smoking" name="smoking" type="checkbox"> Tabagismo
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="alcohol">
                        <input id="alcohol" name="alcohol" type="checkbox"> Usuário de alcool
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-3">
                    <label for="drugs">
                        <input id="drugs" name="drugs" type="checkbox"> Usuário de outras drogas
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="mentalHealth">
                        <input id="mentalHealth" name="mentalHealth" type="checkbox"> Saúde mental
                    </label>
                </div>
                <div class="col-md-3">
                    <label for="rehabilitation">
                        <input id="rehabilitation" name="rehabilitation" type="checkbox"> Reabilitação
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <label>Doenças transmissíveis</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="tuberculosis">
                                    <input id="tuberculosis" name="tuberculosis" type="checkbox"> Tuberculose
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="leprosy">
                                    <input id="leprosy" name="leprosy" type="checkbox"> Hanseníase
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="dengue">
                                    <input id="dengue" name="dengue" type="checkbox"> Dengue
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="dst">
                                    <input id="dst" name="dst" type="checkbox"> DST
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-12">
                    <label>Rastreamento</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="cervicalCancer">
                                    <input id="cervicalCancer" name="cervicalCancer" type="checkbox"> Cancer de colo do
                                    útero
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="breastCancer">
                                    <input id="breastCancer" name="breastCancer" type="checkbox"> Cancer de mama
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="cardiovascular">
                                    <input id="cardiovascular" name="cardiovascular" type="checkbox"> Risco
                                    cardiovascular
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="cidCiap">Outros - código CID/CIAP (separe os códigos por vírgula)</label>
                    <input type="text" id="cidCiap" name="cidCiap" class="form-control">
                </div>
            </div>
        </div>

        <div id="step-three" class="hidden">
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="homeCare">Usou algum PIC? Se sim, indique qual</label>
                    <select name="homeCare" id="homeCare">
                        <option value=""></option>
                        <option value="Medicina Tradicional Chinesa">Medicina Tradicional Chinesa</option>
                        <option value="Antroposofia aplicada à saúde">Antroposofia aplicada à saúde</option>
                        <option value="Homeopatia">Homeopatia</option>
                        <option value="Fitoterapia">Fitoterapia</option>
                        <option value="Termalismo / Crenoterapia">Termalismo / Crenoterapia</option>
                        <option value="Práticas corporais e mentais em PICs">Práticas corporais e mentais em PICs
                        </option>
                        <option value="Técnicas manuais em PICs">Técnicas manuais em PICs</option>
                        <option value="Outro">Outro</option>
                    </select>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label>Solicitado</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="cholesterolR">
                                    <input id="cholesterolR" name="cholesterolR" type="checkbox"> Colesterol total
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="creatinineR">
                                    <input id="creatinineR" name="creatinineR" type="checkbox"> Creatinina
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="easEquR">
                                    <input id="easEquR" name="easEquR" type="checkbox"> EAS/EQU
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="electrocardiogramR">
                                    <input id="electrocardiogramR" name="electrocardiogramR" type="checkbox">
                                    Eletrocardiograma
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="hemoglobinR">
                                    <input id="hemoglobinR" name="hemoglobinR" type="checkbox"> Eletroforese de
                                    Hemoglobina
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="spirometryR">
                                    <input id="spirometryR" name="spirometryR" type="checkbox"> Espirometria
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="sputumR">
                                    <input id="sputumR" name="sputumR" type="checkbox"> Exame de escarro
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="glycemiaR">
                                    <input id="glycemiaR" name="glycemiaR" type="checkbox"> Glicemia
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="hdlR">
                                    <input id="hdlR" name="hdlR" type="checkbox"> HDL
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="hemoglobinGlycemicR">
                                    <input id="hemoglobinGlycemicR" name="hemoglobinGlycemicR" type="checkbox">
                                    Hemoglobina Glicada
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="bloodCountR">
                                    <input id="bloodCountR" name="bloodCountR" type="checkbox"> Hemograma
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="ldlR">
                                    <input id="ldlR" name="ldlR" type="checkbox"> LDL
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="eyesR">
                                    <input id="eyesR" name="eyesR" type="checkbox"> Retinografia / Fundo de Olho com
                                    oftalmologista
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="syphilisR">
                                    <input id="syphilisR" name="syphilisR" type="checkbox"> Sorologia de Sífilis (VDRL)
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="dengueR">
                                    <input id="dengueR" name="dengueR" type="checkbox"> Sorologia para Dengue
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="hivR">
                                    <input id="hivR" name="hivR" type="checkbox"> Sorologia para HIV
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="humanAntiglobulinR">
                                    <input id="humanAntiglobulinR" name="humanAntiglobulinR" type="checkbox"> Teste
                                    indireto de antiglobulina humana (tia)
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="earTestR">
                                    <input id="earTestR" name="earTestR" type="checkbox"> Teste da orelhinha
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="pregnancyTestR">
                                    <input id="pregnancyTestR" name="pregnancyTestR" type="checkbox"> Teste de Gravidez
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="eyeTestR">
                                    <input id="eyeTestR" name="eyeTestR" type="checkbox"> Teste do olhinho
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="footTestR">
                                    <input id="footTestR" name="footTestR" type="checkbox"> Teste do pezinho
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="ultrasonographyR">
                                    <input id="ultrasonographyR" name="ultrasonographyR" type="checkbox">
                                    Ultrassonografia obstétrica
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="urocultureR">
                                    <input id="urocultureR" name="urocultureR" type="checkbox"> Urocultura
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <label>Avaliado</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="cholesterolS">
                                    <input id="cholesterolS" name="cholesterolS" type="checkbox"> Colesterol total
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="creatinineS">
                                    <input id="creatinineS" name="creatinineS" type="checkbox"> Creatinina
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="easEquS">
                                    <input id="easEquS" name="easEquS" type="checkbox"> EAS/EQU
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="electrocardiogramS">
                                    <input id="electrocardiogramS" name="electrocardiogramS" type="checkbox">
                                    Eletrocardiograma
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="hemoglobinS">
                                    <input id="hemoglobinS" name="hemoglobinS" type="checkbox"> Eletroforese de
                                    Hemoglobina
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="spirometryS">
                                    <input id="spirometryS" name="spirometryS" type="checkbox"> Espirometria
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="sputumS">
                                    <input id="sputumS" name="sputumS" type="checkbox"> Exame de escarro
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="glycemiaS">
                                    <input id="glycemiaS" name="glycemiaS" type="checkbox"> Glicemia
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="hdlS">
                                    <input id="hdlS" name="hdlS" type="checkbox"> HDL
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="hemoglobinGlycemicS">
                                    <input id="hemoglobinGlycemicS" name="hemoglobinGlycemicS" type="checkbox">
                                    Hemoglobina Glicada
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="bloodCountS">
                                    <input id="bloodCountS" name="bloodCountS" type="checkbox"> Hemograma
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="ldlS">
                                    <input id="ldlS" name="ldlS" type="checkbox"> LDL
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="eyesS">
                                    <input id="eyesS" name="eyesS" type="checkbox"> Retinografia / Fundo de Olho com
                                    oftalmologista
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="syphilisS">
                                    <input id="syphilisS" name="syphilisS" type="checkbox"> Sorologia de Sífilis (VDRL)
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="dengueS">
                                    <input id="dengueS" name="dengueS" type="checkbox"> Sorologia para Dengue
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="hivS">
                                    <input id="hivS" name="hivS" type="checkbox"> Sorologia para HIV
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="humanAntiglobulinS">
                                    <input id="humanAntiglobulinS" name="humanAntiglobulinS" type="checkbox"> Teste
                                    indireto de antiglobulina humana (tia)
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="earTestS">
                                    <input id="earTestS" name="earTestS" type="checkbox"> Teste da orelhinha
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="pregnancyTestS">
                                    <input id="pregnancyTestS" name="pregnancyTestS" type="checkbox"> Teste de Gravidez
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="eyeTestS">
                                    <input id="eyeTestS" name="eyeTestS" type="checkbox"> Teste do olhinho
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="footTestS">
                                    <input id="footTestS" name="footTestS" type="checkbox"> Teste do pezinho
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="ultrasonographyS">
                                    <input id="ultrasonographyS" name="ultrasonographyS" type="checkbox">
                                    Ultrassonografia obstétrica
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6">
                                <label for="urocultureS">
                                    <input id="urocultureS" name="urocultureS" type="checkbox"> Urocultura
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="step-four" class="hidden">
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="observation">
                        <input id="observation" name="observation" type="checkbox"> Ficou em observação
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <label>NASF /Polo</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="evaluation">
                                    <input id="evaluation" name="evaluation" type="checkbox"> Avaliação / Diagnóstico
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="procedure">
                                    <input id="procedure" name="procedure" type="checkbox"> Procedimentos clínicos /
                                    terapéuticos
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="prescription">
                                    <input id="prescription" name="prescription" type="checkbox"> Prescrição terapeutica
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <label>Conduta</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="scheduledAppointment">
                                    <input id="scheduledAppointment" name="scheduledAppointment" type="checkbox">
                                    Retorno para consulta agendada
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="scheduledCare">
                                    <input id="scheduledCare" name="scheduledCare" type="checkbox"> Retorno para cuidado
                                    continuado / programado
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="groupScheduled">
                                    <input id="groupScheduled" name="groupScheduled" type="checkbox"> Agendamento para
                                    grupos
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="nasfScheduled">
                                    <input id="nasfScheduled" name="nasfScheduled" type="checkbox"> Agendamento para
                                    NASF
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="release">
                                    <input id="release" name="release" type="checkbox"> Alta do episódio
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <label>Encaminhamentos</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="inDay">
                                    <input id="inDay" name="inDay" type="checkbox"> Encaminhamento interno no dia
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="caps">
                                    <input id="caps" name="caps" type="checkbox"> Encaminhamento para CAPS
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="specialized">
                                    <input id="specialized" name="specialized" type="checkbox"> Encaminhamento para
                                    serviço especializado
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">

                            <div class="col-md-4">
                                <label for="hospitalization">
                                    <input id="hospitalization" name="hospitalization" type="checkbox"> Retorno para
                                    cuidado continuado/programado
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="urgency">
                                    <input id="urgency" name="urgency" type="checkbox"> Encaminhamento para urgencia
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="homeCareService">
                                    <input id="homeCareService" name="homeCareService" type="checkbox"> Encaminhamento
                                    para Serviço de Atenção Domiciliar
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="intersectorial">
                                    <input id="intersectorial" name="intersectorial" type="checkbox"> Encaminhamento
                                    Intersetorial
                                </label>
                            </div>
                        </div>
                    </div>
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
