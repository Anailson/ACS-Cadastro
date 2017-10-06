const stepOne = 1;
const stepTwo = 2;
const nSteps = stepTwo;

var currentStep = 0;
var btnCancel = {};
var btnSave = {};
var title = {};

$(function () {
    $(".nav li").removeClass("active");
    $("#menu_visits").addClass("active");
    $("#main_content").html($("#content"));
    $("#title_menu").html("Visitas");
    $("#submenu").append("<li>Visitas</li>");
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
        case stepTwo: save(); break;
        default: goToStepOne(); break;
    }
    setProgressBar(currentStep, nSteps);
    viewTop("nova-visita#content");
}


function back() {
    switch (currentStep){
        case stepOne: confirmRedirect("Você realmente deseja cancelar esse cadastro?","visitas"); break;
        case stepTwo: goToStepOne(); break;
        default: goToStepOne(); break;
    }
    setProgressBar(currentStep, nSteps);
    viewTop("nova-visita#content");
}

function goToStepOne(){
    title.innerHTML = "Dados da visita";
    setTextButton(btnCancel, "Cancelar");
    setTextButton(btnSave, "Avançar");
    hide(document.getElementById("step-one"), false);
    hide(document.getElementById("step-two"), true);
    currentStep = 1;
}

function goToStepTwo() {
    title.innerHTML = "Problema / Condição avaliada";
    setTextButton(btnCancel, "Voltar");
    setTextButton(btnSave, "Salvar");
    hide(document.getElementById("step-one"), true);
    hide(document.getElementById("step-two"), false);
    currentStep = 2;
}


function save() {
    alert("Os dados cadastrados foram salvo com sucesso");
    redirect("visitas");
}