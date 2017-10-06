function confirmLogin(result) {

    result = parseInt(result);
    const error = {type: "", msg: ""};
    switch (result){
        case OK_RESULT:
            window.location.replace("home");
            break;
        case EMPTY_VALUES:
            error.type = "info";
            error.msg = "Por favor, preencha o campo referente ao Número do SUS";
            break;
        case INVALID_VALUES:
            error.type = "warning";
            error.msg = "O número do SUS informado é invalido.";
            break;
        case GENERAL_ERROR:
            error.type = "danger";
            error.msg = "Ocorreu um erro inesperado ao tentar realizar o login.";
            break;
    }
    showNotification(error.type, error.msg);
}
