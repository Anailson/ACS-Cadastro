<?php

class AcsDataBase
{

    const DB_NAME = "tcc_bd";

    private $host;
    private $dbName;
    private $connection;

    function __construct($dbName)
    {
        $this->host = "localhost";
        $this->dbName = $dbName;
    }

    private function openConnection()
    {
        try {

            $this->connection = new PDO("mysql:host=" . $this->host . ";dbname=". $this->dbName, "root", "");
            $this->connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
            $this->connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        } catch (PDOException $e) {
            echo "Falha: " . $e->getMessage() . "\n";
            die();
        }
    }

    private function closeConnection()
    {
        $this->connection = null;
    }

    public function select($query, $params = false)
    {
        $this->openConnection();
        $prepare = $this->connection->prepare($query);

        if($prepare) {

            $params ? $prepare->execute($params) : $prepare->execute();

            $agents = $prepare->fetchAll();
            $this->closeConnection();
            return $agents;

        }else{
            $this->closeConnection();
            return false;
        }
    }
}