<?php

class AcsDataBase
{
    const GET = "GET";
    const PUT = "PUT";
    const DELETE = "DELETE";
    const POST = "POST";

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
            $options = array(
                PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES UTF8'
            );
            $this->connection = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->dbName, "root", "", $options);
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

        if ($prepare) {

            $params ? $prepare->execute($params) : $prepare->execute();

            $agents = $prepare->fetchAll();
            $this->closeConnection();
            return $agents;

        } else {
            $this->closeConnection();
            return false;
        }
    }

    public function insert($query, $params)
    {
        $this->openConnection();
        $stmt = $this->connection->prepare($query);
        if ($stmt) {

            $stmt->execute($params);
            $id = $this->connection->lastInsertId();
            $this->closeConnection();
            return $id;
        } else {
            $this->closeConnection();
            return false;
        }
    }
}