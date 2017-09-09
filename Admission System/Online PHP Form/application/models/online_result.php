<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Online_Result extends CI_Model {
		
	function __construct()
	{
		// Call the Model constructor
		parent::__construct();
	}

	
	public function getWebTextData($key) {
       
		$sql = "SELECT * FROM webtextdata WHERE ID = $key";
		$query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
}
	
	 public function getEntryTestResult($seatNo,$program_type_id) {
         
        $sql = "SELECT * FROM BACH_2015 WHERE SEAT_MO = $seatNo AND PROGRAM_TYPE_ID=$program_type_id";
       $query=$this->db->query( $sql );
	   $result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
    }


	
	public   function getChallan($candidateId) {
         
        $sql = "SELECT * FROM CHALLAN WHERE CANDIDATE_ID = $candidateId";
      $query=$this->db->query( $sql );
	  $result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
	}
    public   function getCandidate($seatNo, $programType, $admissionListDetailId) {
         
        $sql = "SELECT * FROM CANDIDATE_RESULT WHERE SEAT_NO = $seatNo AND PROGRAM_TYPE = $programType AND ADMISSION_LIST_NO = $admissionListDetailId";
		$query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}  
		}
	
	public   function getFees($candidate_id) {
         
        $sql = "SELECT * FROM FEES_PAYMENT WHERE CANDIDATE_ID = $candidate_id";
		$query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
    }

    public   function getCandidateFromSession($seatNo, $programType, $admissionSession) {
         
        $sql = "SELECT * FROM CANDIDATE_RESULT WHERE SEAT_NO = $seatNo AND PROGRAM_TYPE = $programType AND ADMISSION_SESSION = $admissionSession AND ADMISSION_LIST_NO <> 0";
        $query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
    }


    public   function getChoice($candidateId, $campusId) {
         
        $sql = "SELECT * FROM CANDIDATE_CHOICE WHERE CANDIDATE_ID = $candidateId  ORDER BY SHIFT_NAME,SHIFT_ID, CHOICE_NO";
        $query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		} 
		}
	public   function getCategory($candidateId) {
         
        $sql = "SELECT * FROM CANDIDATE_CATGORY WHERE CANDIDATE_ID = $candidateId";
$query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
    }

    public   function getAdmissionSession($admissionListDetailId) {
         
        $sql = "SELECT * FROM CANDIDATE_RESULT WHERE ADMISSION_LIST_NO = $admissionListDetailId";
        $query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
    }

    public   function getDistinctCampus($admissionListDetailId){
         
        $sql = "SELECT DISTINCT(CAMPUS_ID),CAMPUS FROM CANDIDATE_RESULT WHERE ADMISSION_LIST_NO = $admissionListDetailId  AND CAMPUS <> '' ORDER BY CAMPUS";
		$query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
    }
    
    public   function getDistinctDiscipline($admissionListDetailId, $campusId,$programType){
         
        $sql = "SELECT DISTINCT(DISCIPLINE) FROM CANDIDATE_RESULT WHERE ADMISSION_LIST_NO = $admissionListDetailId AND CAMPUS_ID=$campusId AND PROGRAM_TYPE=$programType order by DISCIPLINE";
        $query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
    }
    
     public   function getDistinctCategories($admissionListDetailId, $campusId,$discipline){
         
        $sql = "SELECT DISTINCT(CATEGORY) FROM CANDIDATE_RESULT WHERE ADMISSION_LIST_NO = $admissionListDetailId AND CAMPUS_ID=$campusId AND DISCIPLINE = '$discipline'";
        $query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		} 
    }
    
     public   function getAllCandidates($admissionListDetailId, $campusId,$discipline,$category){
         
        $sql = "SELECT * FROM CANDIDATE_RESULT WHERE ADMISSION_LIST_NO = $admissionListDetailId AND CAMPUS_ID=$campusId AND DISCIPLINE = '$discipline' AND CATEGORY = '$category'";
	$query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			return false;
			//show_404();
		}
    }
    

}
	?>