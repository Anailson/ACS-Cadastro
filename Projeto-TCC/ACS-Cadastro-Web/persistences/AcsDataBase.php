<?php

class AcsDataBase
{
    const GET = "GET";
    const PUT = "PUT";
    const DELETE = "DELETE";
    const POST = "POST";

    const DB_NAME = "acs_bd_backup";

    private $host;
    private $dbName;
    //private $connection;

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

            //mysql:host=localhost;dbname=acs_bd_backup, "root", "", $options
            $connection = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->dbName, "root", "", $options);
            $connection->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
            $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        } catch (PDOException $e) {
            echo "Falha: " . $e->getMessage() . "\n";
            die();
        }
        return $connection;
    }

    public function select($query, $params = false)
    {
        $connection = $this->openConnection();

        $prepare = $connection->prepare($query);
        if ($prepare) {
            $params ? $prepare->execute($params) : $prepare->execute();

            $rows = $prepare->fetchAll();
            return $rows;

        }
        return false;
    }

    public function insert($query, array $params)
    {
        $connection = $this->openConnection();
        try {
            $stmt = $connection->prepare($query);

            //echo "<b>Query </b> -> "; var_dump($query); echo "<br>";
            //echo "<b>Params </b> -> "; var_dump($params);echo "<br>";

            if ($stmt) {

                $connection->beginTransaction();
                $stmt->execute($params);
                $id = $connection->lastInsertId();
                $connection->commit();

                //echo "<b>ID: </b> -> ";var_dump($id); echo "<br><br>";
                return $id;
            }

        } catch (PDOException $e) {
            echo "<br><br><b>PDOException: " . $e->getMessage() . "<br><br></b>";
        }
        return false;
    }

    public function update($query)
    {
        $connection = $this->openConnection();
        try {
            $stmt = $connection->prepare($query);

            if ($stmt) {

                $connection->beginTransaction();
                $stmt->execute();
                $connection->commit();

                //echo "<b>ID: </b> -> ";var_dump($id); echo "<br><br>";
                return  $stmt->rowCount();
            }

        }catch (PDOException $e){
            echo "<br><br><b>PDOException: " . $e->getMessage() . "</b><br><br>";
        }
        return false;
    }

    public function delete($query)
    {
        $connection = $this->openConnection();
        try {
            $stmt = $connection->prepare($query);
            if ($stmt) {

                $connection->beginTransaction();
                $stmt->execute();
                $connection->commit();
                return  $stmt->rowCount();
            }
        } catch (PDOException $e){
            echo "<br><br><b>PDOException: " . $e->getMessage() . "<br><br></b>";
        }
        return false;
    }
}