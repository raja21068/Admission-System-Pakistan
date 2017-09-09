<?php
/**
 * Created by PhpStorm.
 * User: Raja Kumar
 * Date: 8/13/2015
 * Time: 9:19 PM
 */

//namespace application\models;

if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Users extends  CI_Model
{

    public  function authenticateUser($username, $password){

        $sql = "SELECT candidate_id FROM online_card WHERE username = ? and password = ? ";
        $query = $this->db->query($sql, array($username,$password));
        $result = $query->result_array();

        if ($result) {
            return $result[0]['candidate_id'];
        } else {
            return -1;
        }

    }// end authenticateUser()

}