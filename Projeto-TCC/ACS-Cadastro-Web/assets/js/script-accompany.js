const stepOne = 1;
const stepTwo = 2;
const stepThree = 3;
const stepFour = 4;
const nSteps = stepFour;

var currentStep = 0;
var btnCancel = {};
var btnSave = {};
var title = {};

$(function () {
    $(".nav li").removeClass("active");
    $("#menu_accompanies").addClass("active");
    $("#main_content").html($("#content"));
    $("#title_menu").html("Acompanhamentos");
    $("#submenu").append("<li>Acompanhamentos</li>");
});


function start() {
    currentStep = 0;

    title = document.getElementById("title");
    btnCancel = document.getElementById("cancel");
    btnSave = document.getElementById("save");
    next();
}

function next() {
    switch (currentStep){
        case stepOne: goToStepTwo(); break;
        case stepTwo: goToStepThree(); break;
        case stepThree: goToStepFour(); break;
        case stepFour: save();  break;
        default: goToStepOne(); break;
    }
    setProgressBar(currentStep, nSteps);
    viewTop("novo-acompanhamento#content");
}

function back(){
    switch (currentStep){
        case stepOne: confirmRedirect("Você realmente deseja cancelar esse cadastro?","acompanhamentos"); break;
        case stepTwo: goToStepOne(); break;
        case stepThree: goToStepTwo(); break;
        case stepFour: goToStepThree(); break;
        default: goToStepOne(); break;
    }
    setProgressBar(currentStep, nSteps);
    viewTop("novo-acompanhamento#content");
}

function goToStepOne(){
    title.innerHTML = "Dados do acompanhamento";
    setTextButton(btnCancel, "Cancelar");
    setTextButton(btnSave, "Avançar");
    hide(document.getElementById("step-one"), false);
    hide(document.getElementById("step-two"), true);
    currentStep = 1;
}

function goToStepTwo() {
    title.innerHTML = "Problema / Condição avaliada";
    setTextButton(btnCancel, "Voltar");
    hide(document.getElementById("step-one"), true);
    hide(document.getElementById("step-two"), false);
    hide(document.getElementById("step-three"), true);
    currentStep = 2;
}

function goToStepThree() {

    title.innerHTML = "Exames solicitados e avaliados";
    hide(document.getElementById("step-two"), true);
    hide(document.getElementById("step-three"), false);
    hide(document.getElementById("step-four"), true);
    currentStep = 3;
}

function goToStepFour() {
    title.innerHTML = "NASF e Conduta";
    setTextButton(btnSave, "Salvar");
    hide(document.getElementById("step-three"), true);
    hide(document.getElementById("step-four"), false);
    currentStep = 4;
}


function save() {
    alert("Os dados cadastrados foram salvo com sucesso");
    redirect("acompanhamentos");
}