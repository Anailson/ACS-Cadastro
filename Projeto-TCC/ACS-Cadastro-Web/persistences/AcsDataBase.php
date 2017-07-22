<?php

class AcsDataBase
{
    const GET = "GET";
    const PUT = "PUT";
    const DELETE = "DELETE";
    const POST = "POST";

    const DB_NAME = "acs_bd";

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

    public function insert($query, array $params)
    {
        $this->openConnection();
        try {
            $stmt = $this->connection->prepare($query);

            echo "<b>Query </b> -> "; var_dump($query); echo "<br>";
            echo "<b>Params </b> -> "; var_dump($params);echo "<br>";

            if ($stmt) {

                $this->connection->beginTransaction();
                $stmt->execute($params);
                $id = $this->connection->lastInsertId();
                $this->connection->commit();
                $this->closeConnection();

                echo "<b>ID: </b> -> ";var_dump($id); echo "<br><br>";
                return $id;
            }

        } catch (PDOException $e) {
            echo "<b>" . $e->getMessage() . "</b><br><br>";
        }
        $this->closeConnection();
        return false;
    }
}