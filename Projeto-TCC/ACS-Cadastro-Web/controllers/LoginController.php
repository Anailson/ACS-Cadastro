<?php
include_once "controllers/Controller.php";
include_once "persistences/AgentsPersistence.php";

class LoginController
{
    public function isLogged()
    {
        return isset($_SESSION['REMEMBER']);// && $_SESSION['REMEMBER'];
    }

    public function login($numSus, $remember)
    {
        if($numSus == ""){
            return Controller::EMPTY_VALUES;
        }
        $values = AgentsPersistence::get($numSus);
        if($values){

            $_SESSION["ID"] = $values["ID"];
            $_SESSION[AgentModel::NUM_SUS] = $values[AgentModel::NUM_SUS];
            $_SESSION[AgentModel::NAME] = $values[AgentModel::NAME];
            $_SESSION[AgentModel::AREA] = $values[AgentModel::AREA];
            $_SESSION[AgentModel::EQUIP] = $values[AgentModel::EQUIP];
            $_SESSION[AgentModel::ACCESS] = $values[AgentModel::ACCESS];
            $_SESSION[AgentModel::REMEMBER] = $remember;
            return Controller::OK_RESULT;
        }
        return Controller::INVALID_VALUES;
    }

    public function logout()
    {
        $_SESSION = array();
        session_destroy();
        @header("Location: login.php");
        header("Refresh: 0; url=login");
        exit;
    }
}