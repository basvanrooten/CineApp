<?php
/**
 * Created by PhpStorm.
 * User: Wouter Kodde
 * Date: 12-10-2016
 * Time: 20:55
 */

namespace Database;

use PDO;

final class Database extends PDO
{

    /**
     * @var PDO
     */
    private $connection;
    /**
     * @var
     */
    private static $instance;

    public function __construct()
    {
        $this->init();
    }

    /**
     * Database constructor.
     */
    private function init()
    {
        try {
//            $this->connection = new PDO('mysql:host=localhost;dbname=pilsbase', "root", "");
            $this->connection = new PDO('mysql:host=gaikvanavondlam.nl;dbname=woutexw211_cineapp', "woutexw211_cineapp", "3bIaBuVY");
            $this->connection->setAttribute(Database::ATTR_ERRMODE, Database::ERRMODE_EXCEPTION);
        } catch (\Exception $e) {
            echo '<div class="alert alert-danger">
                    <strong>ERROR!</strong> <span>Website kan niet verbinden met de database. Neem contact op met uw beheerder!</span>
                  </div>';
        }
    }

    /**
     * @return PDO
     */
    public static function getConnection()
    {
        if (self::$instance == null) {
            self::$instance = new Database();
        }
        return self::$instance->connection;
    }


}
