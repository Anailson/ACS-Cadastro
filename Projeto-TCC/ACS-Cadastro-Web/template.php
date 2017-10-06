<?php include_once "models/AgentModel.class.php" ?>

<body>
<div class="wrapper">

    <?php

    if ($_SESSION['ACCESS'] == AgentModel::ADMINISTRATOR[0]) {
        include_once "main-menu-adm.php";
    } else if ($_SESSION['ACCESS'] == AgentModel::AGENT[0]) {
        include_once "main-menu-agent.php";
    }
    ?>
    <div class="main-panel">

        <?php include_once "header.php"; ?>
        <div class="content">
            <div class="container-fluid">
                <ul id="submenu" class="card breadcrumb">

                    <li><a href="home">Home</a></li>
                </ul>
                <div class="row">
                    <div id="main_content" class="col-md-12">
                        <!-- All views will be inserted here via Javascript/JQuery -->
                    </div>
                </div>
            </div>
        </div>
        <?php include_once "footer.php" ?>
    </div>
</div>
</body>