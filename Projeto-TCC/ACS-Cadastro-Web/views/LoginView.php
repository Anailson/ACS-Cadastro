<?php

include_once "models/AgentModel.class.php";
include_once "controllers/LoginController.php";

$controller = new LoginController();
$result = null;

if (isset($_POST['btnLogin'])) {
    $numSus = $_POST['edtNumSus'];
    $remember = isset($_POST['chbRemember']);
    $result = $controller->login($numSus, $remember);
}
?>

<script src="assets/js/script-general.js" type="text/javascript"></script>
<script src="assets/js/script-login.js" type="text/javascript"></script>
<script>
    $(function () {

        const result = "<?php echo isset($result) ? $result : '-' ?>";
        if(result != '-') {
            confirmLogin(result);
        }
    });
</script>
<style>
    .container {
        width: 25%;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        padding-top: 20px;
        padding-bottom: 10px;
        margin-top: 20px;
    }

    .resize-img {
        padding: 30px 0 0 0px;
        width: 15%;
        height: auto;
    }

    .containers {
        width: 100%;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
    }

    body {
        background-color: #f9f3f6;
    }
</style>
<body style="border: ">

<div class="containers">
    <img src='assets/img/ic_launch_web.png' class='resize-img'>
</div>
<div id="content" class="container card">
    <form method="post">
        <div class="row">

            <div class="col-md-12" style="padding: 0 20px 0 20px">
                <label for="edtNumSus">Número do SUS</label>
                <input type="number" id="edtNumSus" name="edtNumSus" class="form-control"/><br>
            </div>
            <div class="col-md-12" style="padding: 0 20px 0 20px">
                <label for="chbRemember">
                    <input type="checkbox" id="chbRemember" name="chbRemember" /> Lembrar dados
                </label>
            </div>
            <div class="col-md-12" style="padding: 0 20px 15px 20px">
                <button name="btnLogin" class="btn btn-block btn-primary btn-fill">Acessar o sistema</button>
            </div>
        </div>
    </form>
</div>

</body>