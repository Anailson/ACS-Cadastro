const OK_RESULT = 0;
const EMPTY_VALUES = -1;
const INVALID_VALUES = -2;
const GENERAL_ERROR = -3;

function initChosen(){
    $("select").chosen({
        width: "100%",
        no_results_text: "Nenhum resulado encontrado para: ",
        placeholder_text_single: "Selecione uma das opções"
    });
}

function confirmRedirect(msg, url){
    if(confirm(msg)){
        redirect(url);
    }
}

function redirect(url) {
    if (url) {
        window.location.replace(url);
    }
}

function setProgressBar(current, steps){
    const value = Math.trunc(current / steps * 100) + "%";
    const el = document.getElementById("progressBar");
    el.style.width = value;
    el.innerHTML = value;
}


function hide(element, flag){
    if(flag){
        if(!element.classList.contains("hidden")){
            element.classList.add("hidden")
        }
    } else {
        if(element.classList.contains("hidden")){
            element.classList.remove("hidden")
        }
    }
}

function setTextButton(element, text){
    element.textContent = text;
}
function viewTop(tag) {
    window.location.replace(tag);
}

function showNotification(type, msg){

    $.notify({
        icon: "fa fa-info",
        message: "<br>" + msg + "<br><br>"
    },{
        type: type,
        timer: 4000,
        placement: {
            from: 'top',
            align: 'right'
        }
    });
}