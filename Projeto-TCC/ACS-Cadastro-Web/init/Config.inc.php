<?php
spl_autoload_register(function ($class) {

    $folders = array("controllers", "models", "persistences", "subModels", "views", "webService");

    foreach ($folders as $folder) {
        $suffix = "php";
        if (file_exists("{$folder}/{$class}.{$suffix}")) {

            require_once "{$folder}/{$class}.{$suffix}";
            return;
        }
        $suffix = "class.php";
        if (file_exists("{$folder}/{$class}.{$suffix}")) {

            require_once "{$folder}/{$class}.{$suffix}";
            return;
        }
    }

    echo "<b>Erro ao incluir " . "{$folder}/{$class}.{$suffix}</b>";
});