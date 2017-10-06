$(function () {
    $(".nav li").removeClass("active");
    $("#menu_agents").addClass("active");
    $("#title_menu").html("Agentes");
    $("#main_content").html($("#content"));
    $("#submenu").append("<li>Agentes de Saúde</li>");
});

function confirmSave(result) {

    result = parseInt(result);

    const error = {type: "", msg: ""};
    switch (result){
        case OK_RESULT:
            redirect("agentes");
            alert("Os dados do novo agente foram salvo com sucesso!");
            break;
        case EMPTY_VALUES:
            error.type = "info";
            error.msg = "Por favor, preencha os campos do novo agente.";
            break;
        case INVALID_VALUES:
            error.type = "warning";
            error.msg = "Warning.";
            break;
        case GENERAL_ERROR:
            error.type = "danger";
            error.msg = "Ocorreu um erro inesperado ao salvar os dados do agente.";
            break;
    }

    showNotification(error.type, error.msg);
}

function confirmDetails(result) {

    result = parseInt(result);

    const error = {type: "", msg: ""};
    switch (result){
        case OK_RESULT:
            //error.type = "success";
            //error.msg = "Os dados do novo agente foram salvo com sucesso!";
            break;
        case EMPTY_VALUES:
            error.type = "info";
            error.msg = "Número do SUS não informado.";
            break;
        case INVALID_VALUES:
            error.type = "warning";
            error.msg = "Não foi encontado nenhum registro para os dados informados.";
            break;
        case GENERAL_ERROR:
            error.type = "danger";
            error.msg = "Ocorreu um erro desconhecido.";
            break;
    }

    showNotification(error.type, error.msg);
}

function confirmEdit(result){

    result = parseInt(result);
    const error = {type: "", msg: ""};
    switch (result){
        case OK_RESULT:
            redirect("agentes");
            alert("Os dados do agente foram alterados com sucesso!");
            break;
        case EMPTY_VALUES:
            error.type = "info";
            error.msg = "Os campos devem ser preenchidos.";
            break;
        case INVALID_VALUES:
            error.type = "warning";
            error.msg = "Nenhum numerdo do SUS foi encontrado.";
            break;
        case GENERAL_ERROR:
            error.type = "danger";
            error.msg = "Não foi possivel encontrar nenhum registro com os dados informados.";
            break;
    }
    showNotification(error.type, error.msg);
}

function confirmDelete(result){

    result = parseInt(result);
    const error = {type: "", msg: ""};
    switch (result){
        case OK_RESULT:
            window.location.replace("agentes");
            error.type = "success";
            error.msg = "Os dados do agente foram alterados com sucesso!";
            break;
        case EMPTY_VALUES:
            error.type = "info";
            error.msg = "Os campos devem ser preenchidos.";
            break;
        case INVALID_VALUES:
            error.type = "warning";
            error.msg = "WARNING";
            break;
        case GENERAL_ERROR:
            alert("Não foi possivel remover o agente. Por favor, tente mais tarde.");
            redirect("agentes");
            break;
    }
    showNotification(error.type, error.msg);
}

function details(value) {
    if(value){
        redirect("detalhes-agente?p=" + value)
    }
}

function edit(value) {
    if(value){
        redirect("alterar-agente?p=" + value)
    }
}

function deleteAgent(value){
    if(value){
        confirmRedirect("Você realmente deseja remover esse cadastro?", "remover-agente?p=" + value);
    }
}

function backAgents() {
    confirmRedirect("Você realmente deseja cancelar esse cadastro?", "agentes");
}