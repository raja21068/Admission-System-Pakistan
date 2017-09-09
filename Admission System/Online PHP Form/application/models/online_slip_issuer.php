<?php
/**
 * Created by PhpStorm.
 * User: Jay
 * Date: 06-Sep-15
 * Time: 6:35 PM
 */

//namespace application\models;


class Online_Slip_Issuer extends CI_Model
{

    private $username;
    private $password;
    private $is_super;

    public function authenticateUser($username,$password){
        $sql = "SELECT si.is_super FROM online_slip_issuer AS si where si.username = ? and si.password = ? ";
        $query = $this->db->query($sql,array($username,$password));
        $rs = $query->result_array();
        if($rs){
            return $rs;
        }else{
            return false;
           // show_404();
        }
    }

    public function addSlipIssuer($username, $password){
        $sql = "INSERT INTO online_slip_issuer (username, password ) VALUES (? , ?)";
        $query = $this->db->query($sql,array($username,$password));
        return $query;
    }

    public function updateSlipIssuer($username, $password){
        $sql = "UPDATE online_slip_issuer SET  password = ? WHERE username = ? ";
        $query = $this->db->query($sql,array($password,$username));
        return $query;
    }

    public function addSuperUser($username, $password){
        $sql = "INSERT INTO online_slip_issuer (username, password, is_super ) VALUES (? , ? , 1)";
        $query = $this->db->query($sql,array($username,$password));
        return $query;
    }

}