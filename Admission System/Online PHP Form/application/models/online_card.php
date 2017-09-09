<?php
/**
 * Created by PhpStorm.
 * User: Jay
 * Date: 9/2/2015
 * Time: 1:44 PM
 */
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Online_Card extends CI_Model
{

    public function addOnlineCard($dec_username,$dec_password,$username,$password){
        $sql = "INSERT INTO `online_card_temp` (`user`, `pass`) VALUES (?, ?)  ";
        $query = $this->db->query($sql,array($dec_username,$dec_password) );
        if($query){
            $sql_two = "INSERT INTO `online_card` (
                  `username`,
                  `password`) VALUES (?,?) ";
            $query_two = $this->db->query($sql_two,array($username,$password) );
            return $query_two;
        }else{
            return $query;
        }
    }


}