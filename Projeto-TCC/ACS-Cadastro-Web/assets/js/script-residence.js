const stepOne = 1;
const stepTwo = 2;
const stepThree = 3;
const nSteps = stepThree;

var currentStep = 0;
var btnCancel = {};
var btnSave = {};
var title = {};

$(function () {
    $(".nav li").removeClass("active");
    $("#menu_residences").addClass("active");
    $("#main_content").html($("#content"));
    $("#title_menu").html("Residencias");
    $("#submenu").append("<li>Residências</li>");
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
        case stepThree: save(); break;
        default: goToStepOne(); break;
    }
    setProgressBar(currentStep, nSteps);
    viewTop("nova-residencia#content");
}

function back() {
    switch (currentStep){
        case stepOne: confirmRedirect("Você realmente deseja cancelar esse cadastro?","residencias"); break;
        case stepTwo: goToStepOne(); break;
        case stepThree: goToStepTwo(); break;
        default: goToStepOne(); break;
    }
    setProgressBar(currentStep, nSteps);
    viewTop("nova-residencia#content");
}

function goToStepOne(){
    title.innerHTML = "Endereço";
    setTextButton(btnCancel, "Cancelar");
    setTextButton(btnSave, "Avançar");
    hide(document.getElementById("step-one"), false);
    hide(document.getElementById("step-two"), true);
    currentStep = 1;
}

function goToStepTwo() {
    title.innerHTML = "Condições de moradia";
    setTextButton(btnCancel, "Voltar");
    hide(document.getElementById("step-one"), true);
    hide(document.getElementById("step-two"), false);
    hide(document.getElementById("step-three"), true);
    currentStep = 2;
}

function goToStepThree() {

    title.innerHTML = "Famílias residentes";
    hide(document.getElementById("step-two"), true);
    hide(document.getElementById("step-three"), false);
    currentStep = 3;
}


function save() {
    alert("Os dados cadastrados foram salvo com sucesso");
    redirect("residencias");
}