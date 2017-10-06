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
<script src="assets/js/script-residence.js" type="text/javascript"></script>

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
            <h4 class="title"><i class='fa fa-home'></i>&emsp;Nova Residência</h4>
        </div>
    </div>
    <div id="top" class="content" style="margin: 0;"><div class="content" style="margin: 0;">

            <div class="header" style="padding: 10px 30px 30px 30px">
                <p class="category"><h5 id="title"></h5></p>
                <div class="progress">
                    <div id="progressBar" class="progress-bar" role="progressbar" style="width: 0;"
                         aria-valuemax="100"></div>
                </div>
            </div>
            <div id="step-one" class="border hidden">
                <div class="row form-group">
                    <div class="col-md-3">
                        <label for="placeType">Tipo do logradouro</label>
                        <select name="placeType" id="placeType">
                            <option value=""></option>
                            <option value="Avenida (Avn.:)">Avenida (Avn.:)</option>
                            <option value="Fazenda (Fzn.:)">Fazenda (Fzn.:)</option>
                            <option value="Largo (Lrg.:)">Largo (Lrg.:)</option>
                            <option value="Loteamento (Ltm.:)">Loteamento (Ltm.:)</option>
                            <option value="Parque (Prq.:)">Parque (Prq.:)</option>
                            <option value="Praça (Prc.:)">Praça (Prc.:)</option>
                            <option value="Rua (Rua.:)">Rua (Rua.:)</option>
                            <option value="Travessa (Trv.:)">Travessa (Trv.:)</option>
                        </select>
                    </div>
                    <div class="col-md-7">
                        <label for="streetName">Nome do logradouro</label>
                        <input type="text" id="streetName" name="streetName" class="form-control">
                    </div>

                    <div class="col-md-2">
                        <label for="number">Numero</label>
                        <input type="number" id="number" name="number" class="form-control" >
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-7">
                        <label for="complement">Complemento</label>
                        <input type="text" id="complement" name="complement" class="form-control">
                    </div>

                    <div class="col-md-5">
                        <label for="neighborhood">Bairro</label>
                        <input type="text" id="neighborhood" name="neighborhood" class="form-control" >
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <label for="cep">Cep</label>
                        <input type="number" id="cep" name="cep" class="form-control" >
                    </div>
                    <div class="col-md-4">
                        <label for="uf">UF</label>
                        <select name="uf" id="uf">
                            <option value=""></option>
                            <option value="SE">Sergipe</option>
                            <option value="AL">Alagoas</option>
                            <option value="BH">Bahia</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="city">Municipio</label>
                        <select name="city" id="city">
                            <option value=""></option>
                            <option value="Maceió">Maceió</option>
                            <option value="Arapiraca">Arapiraca</option>
                            <option value="Palmeira dos Índios">Palmeira dos Índios</option>
                            <option value="Salvador">Salvador</option>
                            <option value="Feira de Santana">Feira de Santana</option>
                            <option value="Porto Segura">Porto Segura</option>
                            <option value="Aracaju">Aracaju</option>
                            <option value="Itabaiana">Itabaiana</option>
                            <option value="Areia Branca">Areia Branca</option>
                        </select>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <label for="homePhone">Telefone residencial</label>
                        <input type="number" id="homePhone" name="homePhone" class="form-control">
                    </div>
                    <div class="col-md-4">
                        <label for="referencePhone">Telefone de referência</label>
                        <input type="number" id="referencePhone" name="referencePhone" class="form-control">
                    </div>
                </div>
            </div>

            <div id="step-two" class="border hidden">
                <div class="row form-group">
                    <div class="col-md-6">
                        <label for="housingSituation">Situação de moradia / Posse da terra</label>
                        <select name="housingSituation" id="housingSituation">
                            <option value=""></option>
                            <option value="Próprio">Próprio</option>
                            <option value="Financiado">Financiado</option>
                            <option value="Alugado">Alugado</option>
                            <option value="Arrendado">Arrendado</option>
                            <option value="Ocupação">Ocupação</option>
                            <option value="Situação de rua">Situação de rua</option>
                            <option value="Outro">Outro</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="residenceType">Tipo do domicilio</label>
                        <select name="residenceType" id="residenceType">
                            <option value=""></option>
                            <option value="Casa">Casa</option>
                            <option value="Apartamento">Apartamento</option>
                            <option value="Cômodo">Cômodo</option>
                            <option value="Outro">Outro</option>
                        </select>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <label for="location">Localização</label>
                        <select name="location" id="location">
                            <option value=""></option>
                            <option value="Rural">Rural</option>
                            <option value="Urbana">Urbana</option>
                        </select>
                    </div>
                    <div class="col-md-8">
                        <label for="ownership">Em caso de produção rural, condições de posse da terra</label>
                        <select name="ownership" id="ownership">
                            <option value=""></option>
                            <option value="Proprietário">Proprietário</option>
                            <option value="Parceiro/Meeiro">Parceiro/Meeiro</option>
                            <option value="Assentado">Assentado</option>
                            <option value="Posseiro">Posseiro</option>
                            <option value="Arrendatário">Arrendatário</option>
                            <option value="Comodatário">Comodatário</option>
                            <option value="Beneficitário do Banco da Terra">Beneficitário do Banco da Terra</option>
                            <option value="Nao se aplica">Nao se aplica</option>
                        </select>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-md-3">
                        <label for="nResidents">Total de moradores</label>
                        <input type="number" id="nResidents" name="nResidents" class="form-control">
                    </div>
                    <div class="col-md-3">
                        <label for="nRooms">Total de cômodos</label>
                        <input type="number" id="nRooms" name="nRooms" class="form-control">
                    </div>
                    <div class="col-md-6">
                        <label for="residenceAccess">Tipo de acesso ao domicilio</label>
                        <select name="residenceAccess" id="residenceAccess">
                            <option value=""></option>
                            <option value="Pavimento">Pavimento</option>
                            <option value="Parceiro/Meeiro">Parceiro/Meeiro</option>
                            <option value="Chão batido">Chão batido</option>
                            <option value="Fluvial">Fluvial</option>
                            <option value="Outro">Outro</option>
                        </select>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <label for="responsible">
                            <input id="responsible" name="responsible" type="checkbox"> Disponibilidade de energia eletrica
                        </label>
                    </div>
                    <div class="col-md-4">
                        <label for="residenceConstruction">Material predominante na construção das paredes externas do domicilio</label>
                        <select name="residenceAccess" id="residenceAccess">
                            <option value=""></option>
                            <option value="Alvenaria/Tijolo">Alvenaria/Tijolo</option>
                            <option value="Taipa">Taipa</option>
                            <option value="Madeira aparelhada">Madeira aparelhada</option>
                            <option value="Material aproveitado">Material aproveitado</option>
                            <option value="Palha">Palha</option>
                            <option value="Outro">Outro</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="residenceCoating">Revestimento</label>
                        <select name="residenceCoating" id="residenceCoating">
                            <option value=""></option>
                            <option value="Com revestimento">Com revestimento</option>
                            <option value="Sem revestimento">Sem revestimento</option>
                        </select>
                    </div>
                </div>
                <div class="row form-group">

                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <label for="waterSupply">Tipo de abastemcimento de água</label>
                        <select name="waterSupply" id="waterSupply">
                            <option value=""></option>
                            <option value="Rede encanada até o domicílio">Rede encanada até o domicílio</option>
                            <option value="Poço/Nascimento no domcilio">Poço/Nascimento no domcilio</option>
                            <option value="Cisterna">Cisterna</option>
                            <option value="Carro pipa">Carro pipa</option>
                            <option value="Outro">Outro</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="waterTreatment">Tratamento de água no domicilio</label>
                        <select name="waterTreatment" id="waterTreatment">
                            <option value=""></option>
                            <option value="Filtração">Filtração</option>
                            <option value="Fervura">Fervura</option>
                            <option value="Cloração">Cloração</option>
                            <option value="Sem tratamento">Sem tratamento</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="bathroom">Forma de escoamento do banheiro</label>
                        <select name="bathroom" id="bathroom">
                            <option value=""></option>
                            <option value="Rede coletora de esgoto ou pluvial">Rede coletora de esgoto ou pluvial</option>
                            <option value="Fossa séptica">Fossa séptica</option>
                            <option value="Fossa rudimentar">Fossa rudimentar</option>
                            <option value="Direto para o rio, lago ou mar">Direto para o rio, lago ou mar</option>
                            <option value="Céu aberto">Céu aberto</option>
                            <option value="Outro">Outro</option>
                        </select>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="animals">
                            <input id="animals" name="animals" type="checkbox">Possui animais no domicilio?
                        </label>
                        <div class="border">
                            <div class="row form-group">
                                <div class="col-md-4">
                                    <label for="cat">
                                        <input id="cat" name="cat" type="checkbox"> Gato
                                    </label>
                                </div>
                                <div class="col-md-4">
                                    <label for="dog">
                                        <input id="dog" name="dog" type="checkbox"> Cachorro
                                    </label>
                                </div>
                                <div class="col-md-4">
                                    <label for="bird">
                                        <input id="bird" name="bird" type="checkbox"> Passaro
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-4">
                                    <label for="creation">
                                        <input id="creation" name="creation" type="checkbox"> Criação (galinha, porco, etc)
                                    </label>
                                </div>
                                <div class="col-md-4">
                                    <label for="anotherAnimal">
                                        <input id="anotherAnimal" name="anotherAnimal" type="checkbox"> Outro
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-3">
                                    <label for="nAnimals">Quantos
                                        <input type="number" id="nAnimals" name="nAnimals" >
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div id="step-three" class="border hidden">

                <label>Responsável familiar #1</label>
                <div class="border">
                    <div class="row form-group">
                    <div class="col-md-4">
                        <label for="record_1">Numero do prontuário</label>
                        <input id="record_1" name="record_1" type="number" class="form-control">
                    </div>
                    <div class="col-md-4">
                        <label for="respNumSus_1">Numero do SUS</label>
                        <input id="respNumSus_1" name="respNumSus_1" type="number" class="form-control">
                    </div>
                    <div class="col-md-4">
                        <label for="respBirthDate_1">Data de nascimento</label>
                        <div class="input-group">
                            <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                            <input type="date" id="respBirthDate_1" name="respBirthDate_1" class="form-control" required>
                        </div>
                    </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-3">
                            <label for="income_1">Renda familiar</label>
                            <select name="income_1" id="income_1">
                                <option value=""></option>
                                <option value="Menor que 1/4 salário mínimo">Menor que 1/4 salário mínimo</option>
                                <option value="Entre 1/4 e 1/2 salário mínimos">Entre 1/4 e 1/2 salários mínimos</option>
                                <option value="1 salário mínimo">1 salário mínimo</option>
                                <option value="2 salários mínimos">2 salários mínimos</option>
                                <option value="3 salários mínimos">3 salários mínimos</option>
                                <option value="4 salários mínimos">4 salários mínimos</option>
                                <option value="Acima de 4 salários mínimos">Acima de 4 salários mínimos</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label for="livesSince_1">Reside desde</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                <input type="date" id="livesSince_1" name="livesSince_1"  class="form-control" >
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="record_1">Numero de membros</label>
                            <input id="record_1" name="record_1" type="number" class="form-control">
                        </div>
                        <div class="col-md-3">
                            <label for="moved_1">
                                <input id="moved_1" name="moved_1" type="checkbox"> Mudou-se
                            </label>
                        </div>
                    </div>
                </div>

                <label>Responsável familiar #2</label>
                <div class="border">
                    <div class="row form-group">
                        <div class="col-md-4">
                            <label for="record_2">Numero do prontuário</label>
                            <input id="record_2" name="record_2" type="number" class="form-control">
                        </div>
                        <div class="col-md-4">
                            <label for="respNumSus_2">Numero do SUS</label>
                            <input id="respNumSus_2" name="respNumSus_2" type="number" class="form-control">
                        </div>
                        <div class="col-md-4">
                            <label for="respBirthDate_2">Data de nascimento</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                <input type="date" id="respBirthDate_2" name="respBirthDate_2" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-3">
                            <label for="income_2">Renda familiar</label>
                            <select name="income_2" id="income_2">
                                <option value=""></option>
                                <option value="Menor que 1/4 salário mínimo">Menor que 1/4 salário mínimo</option>
                                <option value="Entre 1/4 e 1/2 salário mínimos">Entre 1/4 e 1/2 salários mínimos</option>
                                <option value="1 salário mínimo">1 salário mínimo</option>
                                <option value="2 salários mínimos">2 salários mínimos</option>
                                <option value="3 salários mínimos">3 salários mínimos</option>
                                <option value="4 salários mínimos">4 salários mínimos</option>
                                <option value="Acima de 4 salários mínimos">Acima de 4 salários mínimos</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label for="livesSince_2">Reside desde</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                <input type="date" id="livesSince_2" name="livesSince_2"  class="form-control" >
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="record_2">Numero de membros da familia</label>
                            <input id="record_2" name="record_2" type="number" class="form-control">
                        </div>
                        <div class="col-md-3">
                            <label for="moved_2">
                                <input id="moved_2" name="moved_2" type="checkbox"> Mudou-se
                            </label>
                        </div>
                    </div>
                </div>

                <label>Responsável familiar #3</label>
                <div class="border">
                    <div class="row form-group">
                        <div class="col-md-4">
                            <label for="record">Numero do prontuário</label>
                            <input id="record" name="record" type="number" class="form-control">
                        </div>
                        <div class="col-md-4">
                            <label for="respNumSus">Numero do SUS</label>
                            <input id="respNumSus" name="respNumSus" type="number" class="form-control">
                        </div>
                        <div class="col-md-4">
                            <label for="respBirthDate">Data de nascimento</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                <input type="date" id="respBirthDate" name="respBirthDate" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-3">
                            <label for="income">Renda familiar</label>
                            <select name="income" id="income">
                                <option value=""></option>
                                <option value="Menor que 1/4 salário mínimo">Menor que 1/4 salário mínimo</option>
                                <option value="Entre 1/4 e 1/2 salário mínimos">Entre 1/4 e 1/2 salários mínimos</option>
                                <option value="1 salário mínimo">1 salário mínimo</option>
                                <option value="2 salários mínimos">2 salários mínimos</option>
                                <option value="3 salários mínimos">3 salários mínimos</option>
                                <option value="4 salários mínimos">4 salários mínimos</option>
                                <option value="Acima de 4 salários mínimos">Acima de 4 salários mínimos</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label for="livesSince">Reside desde</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                <input type="date" id="livesSince" name="livesSince"  class="form-control" >
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="record">Numero de membros da familia</label>
                            <input id="record" name="record" type="number" class="form-control">
                        </div>
                        <div class="col-md-3">
                            <label for="moved">
                                <input id="moved" name="moved" type="checkbox"> Mudou-se
                            </label>
                        </div>
                    </div>
                </div>
                <label>Responsável familiar #4</label>
                <div class="border">
                    <div class="row form-group">
                        <div class="col-md-4">
                            <label for="record">Numero do prontuário</label>
                            <input id="record" name="record" type="number" class="form-control">
                        </div>
                        <div class="col-md-4">
                            <label for="respNumSus">Numero do SUS</label>
                            <input id="respNumSus" name="respNumSus" type="number" class="form-control">
                        </div>
                        <div class="col-md-4">
                            <label for="respBirthDate">Data de nascimento</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                <input type="date" id="respBirthDate" name="respBirthDate" class="form-control" required>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-3">
                            <label for="income">Renda familiar</label>
                            <select name="income" id="income">
                                <option value=""></option>
                                <option value="Menor que 1/4 salário mínimo">Menor que 1/4 salário mínimo</option>
                                <option value="Entre 1/4 e 1/2 salário mínimos">Entre 1/4 e 1/2 salários mínimos</option>
                                <option value="1 salário mínimo">1 salário mínimo</option>
                                <option value="2 salários mínimos">2 salários mínimos</option>
                                <option value="3 salários mínimos">3 salários mínimos</option>
                                <option value="4 salários mínimos">4 salários mínimos</option>
                                <option value="Acima de 4 salários mínimos">Acima de 4 salários mínimos</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label for="livesSince">Reside desde</label>
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                <input type="date" id="livesSince" name="livesSince"  class="form-control" >
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="record">Numero de membros da familia</label>
                            <input id="record" name="record" type="number" class="form-control">
                        </div>
                        <div class="col-md-3">
                            <label for="moved">
                                <input id="moved" name="moved" type="checkbox"> Mudou-se
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <div id="buttons" class="row">
                <div style="float: right; margin:40px 20px 20px 20px">
                    <button id='save' name='save' class='btn btn-primary btn-fill' onclick='next();'></button>
                </div>
                <div style="float: left; padding:40px 20px 20px 20px">
                    <button id='cancel' name='cancel' class='btn btn-primary btn-fill' onclick='back();'></button>
                </div>
            </div>
        </div>
    </div>

</div>

