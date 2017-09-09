<?php
/**
 * Created by PhpStorm.
 * User: Raja
 * Date: 8/16/2015
 * Time: 8:49 AM
 */
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Credentials_Details extends CI_Model
{

    private $issuerId;
    private $totalMarks;
    private $obtainMarks;
    private $seatNo;
    private $passingYear;
    private $programId;
    private $detailOf;

    //
    private $issuerName;
    private $groupName;



    public function getCredentialDetails($candidateId, $level){

        $sql = "SELECT credential_details_id, candidate_id,issuer_id,total_marks, marks_obtained, group_,seat_no, passing_year, detail_of,program_id
                  FROM credential_details WHERE candidate_id = ? and detail_of = ?";
        $query = $this->db->query($sql, array($candidateId,$level));
        $result = $query->result_array();

        if ($result) {
            return $result;
        } else {
            return false;
            //show_404();
        }

    }


    public  function getCredentialDetailsByName($candidateId, $level)
    {
        $sql = "SELECT cd.total_marks, cd.marks_obtained,cd.seat_no, cd.passing_year, iss.location AS issuer_name
                 ,p.name  AS group_name ,p.program_id
                FROM credential_details AS cd
                INNER JOIN `issuer` iss ON cd.issuer_id = iss.issuer_id
                INNER JOIN program p ON p.program_id = cd.program_id WHERE cd.candidate_id = ? and cd.detail_of = ?";
        $query = $this->db->query($sql, array($candidateId, $level));
        $result = $query->result_array();

        if ($result) {
            return $result;
        } else {
            return false;
           // show_404();
        }
    }


    public function getIssuerId() {
        return $this->issuerId;
    }

    public function setIssuerId($issuerId) {
        $this->issuerId = $issuerId;
    }

    public function getTotalMarks() {
        return $this->totalMarks;
    }

    public function setTotalMarks($totalMarks=1100) {
        $this->totalMarks = $totalMarks;
    }

    public function getObtainMarks() {
        return $this->obtainMarks;
    }

    public function setObtainMarks($obtainMarks) {
        $this->obtainMarks = $obtainMarks;
    }

    public function getSeatNo() {
        return $this->seatNo;
    }

    public function setSeatNo($seatNo) {
        $this->seatNo = $seatNo;
    }

    public function getPassingYear() {
        return $this->passingYear;
    }

    public function setPassingYear($passingYear) {
        $this->passingYear = $passingYear;
    }

    public function getProgramId() {
        return $this->programId;
    }

    public function setProgramId($programId) {
        $this->programId = $programId;
    }

    public function getDetailOf() {
        return $this->detailOf;
    }

    public function setDetailOf($detailOf) {
        $this->detailOf = $detailOf;
    }

    public function getIssuerName() {
        return $this->issuerName;
    }

    public function setIssuerName($issuerName) {
        $this->issuerName = $issuerName;
    }

    public function getGroupName() {
        return $this->groupName;
    }

    public function setGroupName($groupName) {
        $this->groupName = $groupName;
    }

}