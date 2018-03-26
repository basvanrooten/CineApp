<?php

/**
 * Created by PhpStorm.
 * User: Wouter Kodde
 * Date: 12-10-2016
 * Time: 19:57
 */

namespace Services;

use Database\Database;
use Session\SessionUtil;
use User\User;

class UserService
{

    public static function loginUserByUserName($username, $password)
    {
        $statement = Database::getConnection()->prepare("SELECT * FROM `user` WHERE username = ?;");
        $statement->bindValue(1, $username, Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return false;
        }
        
        $result = $statement->fetch();
        $id = $result['user_id'];
        $username = $result['username'];
        $email = $result['email'];
        $password_hash = $result['password_hash'];
        $salt = $result['salt'];
        $name = $result['name'];
        $is_email_verified = $result['is_email_verified'];
        $pilsbaas = $result['pilsbaas'];
        $admin = $result['admin'];

        if(empty($password) || !password_verify($password.$salt, $password_hash))
        {
            return ServiceConst::PASSWORD_INCORRECT;
        }

        if(!$is_email_verified)
        {
            return ServiceConst::NOT_EMAIL_VERIFIED;
        }


        $newSalt = self::generateSalt();
        $newPassword_hash = password_hash($password.$newSalt, PASSWORD_DEFAULT);

        $user = new User($id, $email, $username, $newPassword_hash, $newSalt , $name, $is_email_verified, date('Y-m-d H:i:s'), PilsBaasService::getPilsBaasById($pilsbaas), $admin);

        self::saveUser($user);

        SessionUtil::login($user);

        return ServiceConst::SUCCESS;


    }

    public static function getUserById($id)
    {
        $statement = Database::getConnection()->prepare("SELECT * FROM `user` WHERE user_id = ?;");
        $statement->bindValue(1, $id, Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return false;
        }

        $result = $statement->fetch();
        $id = $result['user_id'];
        $username = $result['username'];
        $email = $result['email'];
        $password_hash = $result['password_hash'];
        $salt = $result['salt'];
        $name = $result['name'];
        $is_email_verified = $result['is_email_verified'];
        $pilsbaas = $result['pilsbaas'];

        $user = new User($id, $email, $username, $password_hash, $salt , $name, $is_email_verified, date('Y-m-d H:i:s'), PilsBaasService::getPilsBaasById($pilsbaas));

        return $user;

    }

    /**
     * @param $email string
     * @param $password string
     * @return bool|User
     */
    public static function loginUserByEmail($email, $password)
    {
        $statement = Database::getConnection()->prepare("SELECT * FROM `user` WHERE email = ?;");
        $statement->bindValue(1, $email, Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return false;
        }

        $result = $statement->fetch();
        $id = $result['user_id'];
        $username = $result['username'];
        $email = $result['email'];
        $password_hash = $result['password_hash'];
        $salt = $result['salt'];
        $name = $result['name'];
        $is_email_verified = $result['is_email_verified'];
        $pilsbaas = $result['pilsbaas'];

        if(empty($password) || !password_verify($password.$salt, $password_hash))
        {
            return false;
        }

        $newSalt = self::generateSalt();
        $newPassword_hash = password_hash($password.$newSalt, PASSWORD_DEFAULT);

        $user = new User($id, $email, $username, $newPassword_hash, $newSalt , $name, $is_email_verified, date('Y-m-d H:i:s'), $pilsbaas);

        self::saveUser($user);

        return $user;

    }

    /**
     * @param $user User
     * @param $oldPassword string
     * @param $newPassword string
     * @return bool|User
     */
    public static function changePassword($user, $oldPassword, $newPassword)
    {
        $statement = Database::getConnection()->prepare("SELECT * FROM `user` WHERE user_id = ?;");
        $statement->bindValue(1, $user->getId(), Database::PARAM_STR);


        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return false;
        }

        $result = $statement->fetch();
        $id = $result['user_id'];
        $username = $result['username'];
        $email = $result['email'];
        $password_hash = $result['password_hash'];
        $salt = $result['salt'];
        $name = $result['name'];
        $is_email_verified = $result['is_email_verified'];
        $pilsbaas = $result['pilsbaas'];



        if(empty($oldPassword) || !password_verify($oldPassword.$salt, $password_hash))
        {
            return false;
        }


        $newSalt = self::generateSalt();
        $newPassword_hash = password_hash($newPassword.$newSalt, PASSWORD_DEFAULT);

        $user = new User($id, $email, $username, $newPassword_hash, $newSalt , $name, $is_email_verified, date('Y-m-d H:i:s'), PilsBaasService::getPilsBaasById($pilsbaas));



        self::saveUser($user);

        return true;
    }

    /**
     * @param $user User
     * @return bool|User
     */
    public static function saveUser(User $user)
    {
        $statement = Database::getConnection()->prepare("UPDATE `user` SET username = ?, email = ?, password_hash = ?, salt = ?, name = ?, is_email_verified = ?, last_login = ?, pilsbaas = ? where user_id = ?;");
        $statement->bindValue(1, $user->getUsername(), Database::PARAM_STR);
        $statement->bindValue(2, $user->getEmail(), Database::PARAM_STR);
        $statement->bindValue(3, $user->getPassword(), Database::PARAM_STR);
        $statement->bindValue(4, $user->getSalt(), Database::PARAM_STR);
        $statement->bindValue(5, $user->getName(), Database::PARAM_STR);
        $statement->bindValue(6, $user->isIsEmailVerified(), Database::PARAM_BOOL);
        $statement->bindValue(7, $user->getLastLogin(), Database::PARAM_STR);
        $statement->bindValue(8, $user->getPilsbaas()->getId(), Database::PARAM_STR);
        $statement->bindValue(9, $user->getId(), Database::PARAM_INT);

        try {
            $statement->execute();
        } catch (\Exception $e) {
            return false;
        }

        return true;

    }

    /**
     * @param $id integer
     * @return bool
     */
    public static function deleteUserById($id)
    {
        $statement = Database::getConnection()->prepare("DELETE FROM `user` WHERE user_id = ? AND is_email_verified = 0 AND admin = 0;");
        $statement->bindValue(1, $id, Database::PARAM_STR);

        try {
            $statement->execute();
        } catch (\Exception $e) {
            print_r($e);
            return false;
        }

        return true;

    }

    /**
     * @param $user User
     * @return bool
     */
    public static function insertUser($user)
    {

        $newSalt = self::generateSalt();
        $newPassword_hash = password_hash($user->getPassword().$newSalt, PASSWORD_DEFAULT);

        $user->setSalt($newSalt);
        $user->setPassword($newPassword_hash);

        $statement = Database::getConnection()->prepare("INSERT INTO `user`(username, email, password_hash, salt, `name`, is_email_verified, pilsbaas) VALUES(?, ?, ?, ?, ?, 0, ?)");
        $statement->bindValue(1, $user->getUsername(), Database::PARAM_STR);
        $statement->bindValue(2, $user->getEmail(), Database::PARAM_STR);
        $statement->bindValue(3, $user->getPassword(), Database::PARAM_STR);
        $statement->bindValue(4, $user->getSalt(), Database::PARAM_STR);
        $statement->bindValue(5, $user->getName(), Database::PARAM_STR);
        $statement->bindValue(6, $user->getPilsbaas(), Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return false;
        }

        $user->setId(Database::getConnection()->lastInsertId());

        return true;

    }

    /**
     * @param $username string
     * @return string
     */

    public static function setPasswordForgot($username)
    {

        $statement = Database::getConnection()->prepare("SELECT user_id, email FROM `user` WHERE username = ?");
        $statement->bindValue(1, $username, Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return ServiceConst::ERROR;
        }

        $result = $statement->fetch();

        if(empty($result) || empty($result['user_id'] || empty($result['email']))){
            return ServiceConst::NO_USER_FOUND;
        }

        $email = $result['email'];
        $key = self::getRandomKey();

        $statement = Database::getConnection()->prepare("INSERT INTO `passwordforgot`(user_user_id, forgotKey) VALUES (?, ?);");
        $statement->bindValue(1, $result['user_id'], Database::PARAM_STR);
        $statement->bindValue(2, $key, Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            if($e->getCode() == 23000){
                return self::setPasswordForgot($username);
            }
            return ServiceConst::ERROR;
        }

        return MailService::sendPasswordForgotEmail($email, $key);
    }

    /**
     * @param $key string
     * @param $password string
     * @param $passwordConfirmed string
     * @return string
     */
    public static function resetPassword($key, $password, $passwordConfirmed)
    {

        if($password != $passwordConfirmed){
            return ServiceConst::PASSWORD_INCORRECT;
        }

        $statement = Database::getConnection()->prepare("SELECT user_user_id FROM `passwordforgot` WHERE forgotKey = ?");
        $statement->bindValue(1, $key, Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            die($e->getMessage());
            return ServiceConst::ERROR;
        }

        $result = $statement->fetch();

        if(empty($result) || empty($result['user_user_id'])){
            return ServiceConst::KEY_NOT_EXIST;
        }

        $newSalt = self::generateSalt();

        $newPassword_hash = password_hash($password.$newSalt, PASSWORD_DEFAULT);

        $statement = Database::getConnection()->prepare("UPDATE `user` SET password_hash = ?, salt = ? WHERE user_id = ?");
        $statement->bindValue(1, $newPassword_hash, Database::PARAM_STR);
        $statement->bindValue(2, $newSalt, Database::PARAM_STR);
        $statement->bindValue(3, $result['user_user_id'], Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            die($e->getMessage());
            return ServiceConst::ERROR;
        }

        $statement = Database::getConnection()->prepare("DELETE FROM `passwordforgot` WHERE forgotKey = ?");
        $statement->bindValue(1, $key, Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            die($e->getMessage());
            return ServiceConst::ERROR;
        }

        return ServiceConst::SUCCESS;
    }

    /**
     * @param $username string
     * @return string
     */
    public static function checkUsernameExist($username)
    {

        $statement = Database::getConnection()->prepare("SELECT username FROM `user` WHERE username = ?;");
        $statement->bindValue(1, $username, Database::PARAM_STR);

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return ServiceConst::ERROR;
        }

        $result = $statement->fetch();

        if(empty($result) || empty($result['username'])){
            return ServiceConst::NO_USER_FOUND;
        }

        return ServiceConst::SUCCESS;
    }


    public static function getRandomKey()
    {
        $length = 10;
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $charactersLength = strlen($characters);
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[rand(0, $charactersLength - 1)];
        }
        return hash('sha256', $randomString);
    }

    private static function generateSalt() {
        $length = 10;
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $charactersLength = strlen($characters);
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[rand(0, $charactersLength - 1)];
        }
        return hash('sha256', $randomString);
    }

    /**
     * @return User[]|bool
     */
    public static function getAllNotVerifiedUsers()
    {
        $statement = Database::getConnection()->prepare("SELECT * FROM `user` WHERE is_email_verified = 0;");

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return false;
        }

        $results = $statement->fetchAll();
        $users = array();
        foreach($results as $result){
            $id = $result['user_id'];
            $username = $result['username'];
            $email = $result['email'];
            $password_hash = $result['password_hash'];
            $salt = $result['salt'];
            $name = $result['name'];
            $is_email_verified = $result['is_email_verified'];
            $admin = $result['admin'];
            $users[] = new User($id, $email, $username, $password_hash, $salt, $name, $is_email_verified, date('Y-m-d H:i:s'), null, $admin);
        }

        return $users;

    }

    /**
     * @return array
     */
    public static function getAllEmails()
    {
        $statement = Database::getConnection()->prepare("SELECT email FROM `user`;");

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return array();
        }

        $results = $statement->fetchAll();
        $emails = array();

        foreach ($results as $result){
            $emails[] = $result['email'];
        }

        return $emails;

    }

    /**
     * @return array
     */
    public static function getWeeklyDrunkEachUser(){

        $statement = Database::getConnection()->prepare("
        SELECT user.email, COALESCE(Weekly.WeeklyDrunk, 0) as 'WeeklyDrunk', CreditTable.BeerCredit
        FROM `user`
        JOIN(
                SELECT SUM(Amount) as 'WeeklyDrunk', Drinker FROM `drink`
                WHERE Date(DrinkDate) BETWEEN (ADDDATE(CURDATE(), -7)) AND CURDATE()
                GROUP BY Drinker) as Weekly
        ON user.pilsbaas = Weekly.Drinker
        LEFT JOIN (
                  SELECT pilsbaas.Id, pilsbaas.Name, COALESCE(drunk.TotalDrunk, 0) as 'BeersDrunk', COALESCE((creditTable.BeerCredit - COALESCE(drunk.TotalDrunk, 0)), 0) as 'BeerCredit'
                FROM `pilsbaas`
                LEFT JOIN (
                        SELECT COUNT(drink.Drinker) as 'TotalDrunk', drink.Drinker
                        FROM `drink`
                        GROUP BY drink.Drinker
                    ) as drunk
                ON drunk.Drinker = pilsbaas.Id
                LEFT JOIN (
                        SELECT SUM(credit.CreditAmount) as 'BeerCredit', credit.CreditReciever
                        FROM `credit`
                        GROUP BY credit.CreditReciever
                        ) as creditTable
                ON creditTable.CreditReciever = pilsbaas.Id) as CreditTable
        ON user.pilsbaas = CreditTable.Id
		");

        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            return array();
        }

        $results = $statement->fetchAll();

        $weekly = array();
        foreach ($results as $result){
            $temp = array('email' => $result['email'], 'weeklyDrunk' => $result['WeeklyDrunk'], 'beerCredit' => $result['BeerCredit']);
            $weekly[] = $temp;
        }

        return $weekly;
    }



}