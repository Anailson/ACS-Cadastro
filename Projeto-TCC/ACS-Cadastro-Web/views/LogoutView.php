<?php

include_once "controllers/LoginController.php";

$controller = new LoginController();
$controller->logout();
?>

<script>
    location.reload();
</script>
