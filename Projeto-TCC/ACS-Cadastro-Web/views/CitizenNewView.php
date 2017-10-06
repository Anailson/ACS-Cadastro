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
<script src="assets/js/script-citizen.js" type="text/javascript"></script>
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
            <h4 class="title"><i class='fa fa-users'></i>&emsp;Novo Cidadão</h4>
        </div>
    </div>
    <div class="content" style="margin: 0;">

        <div class="header" style="padding: 10px 30px 30px 30px">
            <p class="category"><h5 id="title">sdfsdf</h5></p>
            <div class="progress">
                <div id="progressBar" class="progress-bar" role="progressbar" style="width: 0;"
                     aria-valuemax="100"></div>
            </div>
        </div>
        <div id="step-one" class="hidden">
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="numSus">Numero do SUS</label>
                    <input type="number" id="numSus" name="numSus" class="form-control">
                </div>
                <div class="col-md-4">
                    <label for="numNis">Numero do NIS (Pis / PASE) </label>
                    <input type="number" id="numNis" name="numNis" class="form-control">
                </div>

                <div class="col-md-4">
                    <label for="birthDate">Data de nascimento</label>
                    <div class="input-group">
                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                        <input type="date" id="birthDate" name="birthDate" class="form-control" required>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="name">Nome completo</label>
                    <input type="text" id="name" name="name" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label for="socialName">Nome social</label>
                    <input type="text" id="socialName" name="socialName" class="form-control">
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <div class="col-md-6"><label for="motherName">Nome completo da mãe</label></div>
                    <label for="motherDontKown">
                        <input id="motherDontKown" name="motherDontKown" type="checkbox"> Não sabe
                    </label>
                    <input type="text" id="motherName" name="motherName" class="form-control" required>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="phone">Telefone</label>
                    <input type="number" id="phone" name="phone" class="form-control">
                </div>
                <div class="col-md-4">
                    <label for="email">E-mail</label>
                    <input type="email" id="email" name="email" class="form-control">
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-4">
                    <label for="responsible">
                        <input id="responsible" name="responsible" type="checkbox"> Responsável familiar
                    </label>
                </div>
            </div>
            <div class="row form-group border">
                <div class="col-md-4">
                    <label for="socialName">Numero do SUS do responsável</label>
                    <input type="number" title="socialName" name="socialName" class="form-control">
                </div>

                <div class="col-md-4">
                    <label for="numSusResp">Data de nascimento do responsável</label>
                    <div class="input-group ">
                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                        <input type="date" class="form-control" id="numSusResp">
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="sex">Sexo</label>
                    <select name="sex" id="sex">
                        <option value=""></option>
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="genderRace">Raça/Cor</label>
                    <select name="genderRace" id="genderRace">
                        <option value=""></option>
                        <option value="Branca">Branca</option>
                        <option value="Preta">Preta</option>
                        <option value="Parda">Parda</option>
                        <option value="Amarela">Amarela</option>
                        <option value="Indígena">Indígena</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="nationality">Nacionalidade</label>
                    <select name="nationality" id="nationality">
                        <option value=""></option>
                        <option value="Brasileiro">Brasileiro</option>
                        <option value="Naturalizado">Naturalizado</option>
                        <option value="Estrangeiro">Estrangeiro</option>
                    </select>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <div class="col-md-7"><label for="nationBirth">País de nascimento</label></div>
                    <label for="nationBrazil">
                        <input id="nationBrazil" name="nationBrazil" type="checkbox"> Brasil
                    </label>
                    <input type="text" id="nationBirth" name="nationBirth" class="form-control">
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

        </div>
        <div id="step-two" class="hidden">
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="uf">Relação de parentesco com o responsável familiar</label>
                    <select name="uf" id="uf">
                        <option value=""></option>
                        <option value="Cônjuje / Companheiro">Cônjuje / Companheiro</option>
                        <option value="Filho (a)">Filho (a)</option>
                        <option value="Enteado (a)">Enteado (a)</option>
                        <option value="Neto (a) / Bisneto (a)">Neto (a) / Bisneto (a)</option>
                        <option value="Pai / Mãe">Pai / Mãe</option>
                        <option value="Irmã / Irmão">Irmã / Irmão</option>
                        <option value="Genro / Nora">Genro / Nora</option>
                        <option value="Outro">Outro</option>
                        <option value="Não parente">Não parente</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="occupation">Ocupação</label>
                    <input type="text" id="occupation" name="occupation" class="form-control">
                </div>
                <div class="col-md-4">
                    <label for="school">
                        <input id="school" name="school" type="checkbox"> Frequenta creche ou escola?
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="education">Qual o curso mais elevado que frequentou?</label>
                    <select name="education" id="education">
                        <option value=""></option>
                        <option value="Creche">Creche</option>
                        <option value="Pré-escola (exceto CA)">Pré-escola (exceto CA)</option>
                        <option value="Classe Alfabetizada - CA">Classe Alfabetizada - CA</option>
                        <option value="Ensino Fundamental 1ª a 4ª séries">Ensino Fundamental 1ª a 4ª séries</option>
                        <option value="Ensino Fundamental 5ª a 8ª séries">Ensino Fundamental 5ª a 8ª séries</option>
                        <option value="Ensino Fundamental Completo">Ensino Fundamental Completo</option>
                        <option value="Ensino Fundamental Especial">Ensino Fundamental Especial</option>
                        <option value="Ensino Fundamental EJA - séries iniciais(Supletivo 1ª a 4ª)">Ensino Fundamental
                            EJA - séries iniciais(Supletivo 1ª a 4ª)
                        </option>
                        <option value="Ensino Fundamental EJA - séries finais (Supletivo 5ª a 9ª)">Ensino Fundamental
                            EJA - séries finais (Supletivo 5ª a 9ª)
                        </option>
                        <option value="Ensino Médio, Médio 2º Ciclo (Científico, Técnico e etc)">Ensino Médio, Médio 2º
                            Ciclo (Científico, Técnico e etc)
                        </option>
                        <option value="Ensino Médio Especial">Ensino Médio Especial</option>
                        <option value="Ensino Médio EJA (Supletivo)">Ensino Médio EJA (Supletivo)</option>
                        <option value="Superior, Aperfeiçoamento, Especialização, Mestrado, Doutorado">Superior,
                            Aperfeiçoamento, Especialização, Mestrado, Doutorado
                        </option>
                        <option value="Alfabetização para Adultos (Mobral, etc) ">Alfabetização para Adultos (Mobral,
                            etc)
                        </option>
                        <option value="Nenhum">Nenhum</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="employment">Situação no mercado de trabalho</label>
                    <select name="employment" id="employment">
                        <option value=""></option>
                        <option value="Empregador">Empregador</option>
                        <option value="Assalariado com carteira de trabalho">Assalariado com carteira de trabalho
                        </option>
                        <option value="Assalariado sem carteira de trabalho">Assalariado sem carteira de trabalho
                        </option>
                        <option value="Autônomo com previdência social">Autônomo com previdência social</option>
                        <option value="Autônomo sem previdência social">Autônomo sem previdência social</option>
                        <option value="Aposentado/Pensionista">Aposentado/Pensionista</option>
                        <option value="Desempregado">Desempregado</option>
                        <option value="Não trabalha">Não trabalha</option>
                        <option value="Outro">Outro</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="kids_09">Crianças de 0 a 9 anos, com quem ficam?</label>
                    <select name="kids_09" id="kids_09">
                        <option value=""></option>
                        <option value="Adulto Responsável">Adulto Responsável</option>
                        <option value="Outra(s) Criança(s)">Outra(s) Criança(s)</option>
                        <option value="Adolescente">Adolescente</option>
                        <option value="Sozinha">Sozinha</option>
                        <option value="Creche">Creche</option>
                        <option value="Outro">Outro</option>
                    </select>
                </div>
            </div>
            <div class="row form-group">

                <div class="col-md-4">
                    <label for="caregiver">
                        <input id="caregiver" name="caregiver" type="checkbox"> Frequenta cuidador tradicional?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="communityGroup">
                        <input id="communityGroup" name="communityGroup" type="checkbox"> Participa de grupo
                        comunitário?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="healthPlan">
                        <input id="healthPlan" name="healthPlan" type="checkbox"> Possui plano de saúde particular?
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="communityTraditional">
                        <input id="communityTraditional" name="communityTraditional" type="checkbox"> É membro de povo
                        ou comunidade tradicional? Cite.
                    </label>
                    <input type="text" id="communityTraditionalText" name="communityTraditionalText"
                           class="form-control">
                </div>
                <div class="col-md-6">
                    <label for="sexualOrientation">
                        <input id="sexualOrientation" name="sexualOrientation" type="checkbox"> Deseja informar sua
                        orientação sexual? Cite.
                    </label>
                    <input type="text" id="sexualOrientationText" name="sexualOrientationText" class="form-control">
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-12">
                    <label for="deficiency">
                        <input id="deficiency" name="deficiency" type="checkbox"> Possui alguma deficiência?
                    </label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="visual">
                                    <input id="visual" name="visual" type="checkbox"> Visual
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="hearing">
                                    <input id="hearing" name="hearing" type="checkbox"> Auditiva
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="intellectual">
                                    <input id="intellectual" name="intellectual" type="checkbox"> Intelectual/Cognitiva
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="physical">
                                    <input id="physical" name="physical" type="checkbox"> Física
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="anotherDeficiency">
                                    <input id="anotherDeficiency" name="anotherDeficiency" type="checkbox"> Outra
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="step-three" class="hidden">
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="pregnant">
                        <input id="pregnant" name="pregnant" type="checkbox"> Está gestante? Cite sua maternidade de
                        preferência
                    </label>
                    <input type="text" id="pregnantText" name="pregnantText" class="form-control">
                </div>
                <div class="col-md-4">

                    <label for="weight">Sobre seu peso, você se considera?</label>
                    <select name="weight" id="weight">
                        <option value=""></option>
                        <option value="Abaixo do peso">Abaixo do peso</option>
                        <option value="Peso adequado">Peso adequado</option>
                        <option value="Acima do peso">Acima do peso</option>
                    </select>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="smoker">
                        <input id="smoker" name="smoker" type="checkbox"> Está fumante?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="alcohol">
                        <input id="alcohol" name="alcohol" type="checkbox"> Faz consumo de alcool?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="drugs">
                        <input id="drugs" name="drugs" type="checkbox"> Faz consumo de outras dogras?
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="hypertension">
                        <input id="hypertension" name="hypertension" type="checkbox"> Tem hipertensão arterial?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="diabetes">
                        <input id="diabetes" name="diabetes" type="checkbox"> Tem diabetes?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="avc">
                        <input id="avc" name="avc" type="checkbox"> Teve AVC/Derrame?
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="heartAttack">
                        <input id="heartAttack" name="heartAttack" type="checkbox"> Teve infarto?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="leprosy">
                        <input id="leprosy" name="leprosy" type="checkbox"> Está com hanseniase?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="tuberculosis">
                        <input id="tuberculosis" name="tuberculosis" type="checkbox"> Está com tuberculose?
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <label for="cancer">
                        <input id="cancer" name="cancer" type="checkbox"> Tem ou teve câncer?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="inBed">
                        <input id="inBed" name="inBed" type="checkbox"> Está acamado?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="domiciled">
                        <input id="domiciled" name="domiciled" type="checkbox"> Está domiciliado?
                    </label>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="otherPractices">
                        <input id="otherPractices" name="otherPractices" type="checkbox"> Fez ou faz tratamento com
                        psiquiatra ou teve internação por problema mental?
                    </label>
                </div>
                <div class="col-md-6">
                    <label for="mentalHealth">
                        <input id="mentalHealth" name="mentalHealth" type="checkbox"> Usa outras práticas integrativas e
                        complementares?
                    </label>
                </div>
            </div>
            <div class="row form-group">

                <div class="col-md-12">
                    <label for="heartDisease">
                        <input id="heartDisease" name="heartDisease" type="checkbox"> Tem doença cardíaca? Cite quais.
                    </label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="heartInsufficiency">
                                    <input id="heartInsufficiency" name="heartInsufficiency" type="checkbox">
                                    Insuficiência cardíaca
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="heartAnother">
                                    <input id="heartAnother" name="heartAnother" type="checkbox"> Outra
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="heartDontKnow">
                                    <input id="heartDontKnow" name="heartDontKnow" type="checkbox"> Não sabe
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <label for="kidneyDisease">
                        <input id="kidneyDisease" name="kidneyDisease" type="checkbox"> Tem ou teve probelas nos rins?
                        Cite quais.
                    </label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="kidneyInsufficiency">
                                    <input id="kidneyInsufficiency" name="kidneyInsufficiency" type="checkbox">
                                    Insuficiência renal
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="kidneyAnother">
                                    <input id="kidneyAnother" name="kidneyAnother" type="checkbox"> Outra
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="kidneyDontKnow">
                                    <input id="kidneyDontKnow" name="kidneyDontKnow" type="checkbox"> Não sabe
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12">
                    <label for="respiratoryDisease">
                        <input id="respiratoryDisease" name="respiratoryDisease" type="checkbox"> Tem doença
                        respiratória? Cite quais.
                    </label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="asthma">
                                    <input id="asthma" name="asthma" type="checkbox"> Asma
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="emphysema">
                                    <input id="emphysema" name="emphysema" type="checkbox"> DPOC/Empisema
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="respiratoryAnother">
                                    <input id="respiratoryAnother" name="respiratoryAnother" type="checkbox"> Outra
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="respiratoryDontKnow">
                                    <input id="respiratoryDontKnow" name="respiratoryDontKnow" type="checkbox"> Não sabe
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="interment">
                        <input id="interment" name="interment" type="checkbox"> Teve alguma internação nos últimos 12
                        meses? Se sim, por qual motivo?
                    </label>
                    <input type="text" id="intermentText" name="intermentText" class="form-control">
                </div>
                <div class="col-md-6">
                    <label for="plant">
                        <input id="plant" name="plant" type="checkbox"> Faz uso de plantas medicinais? Se sim, cite
                        quais?
                    </label>
                    <input type="text" id="plantText" name="plantText" class="form-control">
                </div>
            </div>
        </div>

        <div id="step-four" class="hidden">
            <div class="row form-group">
                <div class="col-md-6">
                    <label for="streetTime">
                        <input id="streetTime" name="streetTime" type="checkbox"> Está em situação de rua? Se sim, por
                        quanto tempo?
                    </label>
                    <select name="sex" id="sex" required>
                        <option value=""></option>
                        <option value="Menos de 6 meses">Menos de 6 meses</option>
                        <option value="Entre 6 e 12 meses">Entre 6 e 12 meses</option>
                        <option value="Entre 1 e 5 anos">Entre 1 e 5 anos</option>
                        <option value="Mais de 5 anos">Mais de 5 anos</option>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="foodPerDay">Quantas vezes se alimenta por dia?</label>
                    <select name="foodPerDay" id="foodPerDay">
                        <option value=""></option>
                        <option value="1 vez">1 vez</option>
                        <option value="2 ou 3 vezes">2 ou 3 vezes</option>
                        <option value="Mais de 3 vezes">Mais de 3 vezes</option>
                    </select>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-4">
                    <label for="benefit">
                        <input id="benefit" name="benefit" type="checkbox"> Recebe algum benefício?
                    </label>
                </div>
                <div class="col-md-4">
                    <label for="family">
                        <input id="family" name="family" type="checkbox"> Possui referência familiar?
                    </label>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-12">
                    <label for="benefit"> Qual a origem da alimentação?</label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="restaurant">
                                    <input id="restaurant" name="restaurant" type="checkbox"> Restaurante popular
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="restaurantDonation">
                                    <input id="restaurantDonation" name="restaurantDonation" type="checkbox"> Doação de
                                    restaurante?
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="religiousDonation">
                                    <input id="religiousDonation" name="religiousDonation" type="checkbox"> Doação de
                                    grupo religioso?
                                </label>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-4">
                                <label for="popularDonation">
                                    <input id="popularDonation" name="popularDonation" type="checkbox"> Doação popular
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label for="foodAnother">
                                    <input id="foodAnother" name="foodAnother" type="checkbox"> Outro
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-6">
                    <label for="anotherInstitution">
                        <input id="anotherInstitution" name="anotherInstitution" type="checkbox"> É acompanhado por
                        outra instituição? Se sim, cite qual(ais)
                    </label>
                    <input type="text" id="anotherInstitutionText" name="anotherInstitutionText" class="form-control">
                </div>
                <div class="col-md-6">
                    <label for="familyVisit">
                        <input id="familyVisit" name="familyVisit" type="checkbox"> Visita algum familiar com
                        frequência? Se sim, qual grau de parentesco?
                    </label>
                    <input type="text" id="familyVisitText" name="familyVisitText" class="form-control">
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-12">
                    <label for="hygiene">
                        <input id="hygiene" name="hygiene" type="checkbox"> Tem acesso a higiene pessoal? Se sim, de que
                        tipo?
                    </label>
                    <div class="border">
                        <div class="row form-group">
                            <div class="col-md-3">
                                <label for="bathroom">
                                    <input id="bathroom" name="bathroom" type="checkbox"> Banho
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="sanitary">
                                    <input id="sanitary" name="sanitary" type="checkbox"> Acesso a sanitário
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="oral">
                                    <input id="oral" name="oral" type="checkbox"> Higiene bucal
                                </label>
                            </div>
                            <div class="col-md-3">
                                <label for="hygieneAnother">
                                    <input id="hygieneAnother" name="hygieneAnother" type="checkbox"> Outro
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
