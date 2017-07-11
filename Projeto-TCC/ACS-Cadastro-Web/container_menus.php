<!doctype html>
<html lang="pt-br">

<head>
    <meta charset="utf-8"/>

    <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Cadastro auxiliar para os Agentes Comunitários de Saúde</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/><!-- Bootstrap core CSS     -->

    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="assets/css/light-bootstrap-dashboard.css" rel="stylesheet"/>

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet"/>
    <link href="assets/css/themify-icons.css" rel="stylesheet"/>

</head>

<body>
<div class="wrapper">

    <?php include "main_menu.php"; ?>
    <div class="main-panel">

        <?php include "header.php" ?>
        <div class="content">
            <div class="container-fluid">

                <ul class="breadcrumb card">
                    <li><a href="home.php">Home</a></li>
                    <li><a id="sub-title" href=""></a></li>
                </ul>
                <div class="row">
                    <div id="main_content" class="col-md-12"></div>
                    <!-- All views will be inserted here via Javascript/JQuery -->
                </div>
            </div>
        </div>
        <?php include "footer.php" ?>
    </div>
</div>
</body>

<!--   Core JS Files   -->
<script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="assets/js/bootstrap-checkbox-radio-switch.js"></script>

<!--  Charts Plugin -->
<script src="assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="assets/js/light-bootstrap-dashboard.js"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project!
<script src="assets/js/demo.js"></script> -->