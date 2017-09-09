<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class ModelAdmission extends CI_Model {
		
	function __construct()
	{
		// Call the Model constructor
		parent::__construct();
	}

	
	
	public function getCountry() {
		
		$sql = "SELECT country_id,name FROM country order by name";
		
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
	
	
	public function getCampus() {
		
		$sql = "SELECT campus_id,name,location FROM campus where campus_id != 2 order by display_order";
		
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


	public function getDistrictByProvince($provanceName) {
		$sql="";
		if($provanceName=="SINDH") {

			$sql = "SELECT district_id,`district`.`name` FROM district
 INNER JOIN `division` ON `district`.`division_id`=`division`.`division_id`
 INNER JOIN `province` ON `division`.`province_id`=`province`.`province_id` AND `province`.`name`='$provanceName'
  ORDER BY NAME ";
		}else{
			$sql = "SELECT district_id,`district`.`name` FROM district
 INNER JOIN `division` ON `district`.`division_id`=`division`.`division_id`
 INNER JOIN `province` ON `division`.`province_id`=`province`.`province_id` AND `province`.`name`<>'SINDH'
  ORDER BY NAME ";
		}

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

	public function getDistrict() {
		
		//$sql = "SELECT district_id,name FROM district order by name";
		$sql = "SELECT district_id,`district`.`name` FROM district
 INNER JOIN `division` ON `district`.`division_id`=`division`.`division_id`
 INNER JOIN `province` ON `division`.`province_id`=`province`.`province_id` AND `province`.`name`='SINDH'
  ORDER BY NAME ";
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

	public function getReligion() {
		
		$sql = "SELECT religion_id,name FROM religion order by name";
		
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

	public function getBoards() {
		
		$sql = "SELECT  issuer_id ,name,location FROM  issuer where is_board = true";
		
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

	public function getBankBranchName() {

	$sql = "SELECT  * FROM  bank_branch order by name";

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

	public function getAdmissionYear(){
		$sql="SELECT * FROM `admission_year` ORDER BY `admission_year`.`year` DESC ";
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




	public function getBankBranchNameById($id) {

		$sql = "SELECT  name FROM  bank_branch  where bank_branch_id= ? order by name ";

		$query=$this->db->query( $sql, array($id) );
		$result = $query->result_array();

		if( $result ){
			return $result;
		}else{
			return false;
			//show_404();
		}
	}

	public function getUniversity() {
		
		$sql = "SELECT  issuer_id ,name FROM  issuer where is_board = false";
		
		$query=$this->db->query( $sql );
		$result = $query->result_array();
		
		if( $result )
		{
			return $result;
		}
		else
		{
			//return false;
			show_404();
		}
		
	}

	public function getSSCGroup() {
        return $this->getProgram(0);
    }

    public  function getHSCGroup() {
        return $this->getProgram(1);
    }

    public function getBachlorProgram() {
        return $this->getProgram(2);
    }
	
	
	public function getProgram($level) {
	
		$sql = "SELECT program_id, name FROM program  where level= ? order by name ";
		
		$query=$this->db->query( $sql, array($level) );
		$result = $query->result_array();
		
		if( $result ){
			return $result;
		}else{
			return false;
			//show_404();
		}
	}
	
	public function getOptionalSubject($programId)
	{

		$sql = "SELECT s.subject_id,d.name FROM program_subject ps  INNER JOIN subject as s on s.subject_id = ps.subject_id INNER JOIN discipline d ON d.discipline_id = s.discipline_id WHERE ps.program_id = $programId order by d.name";

		$query = $this->db->query($sql, array($programId));
		$result = $query->result_array();

		if ($result) {
			return $result;
		} else {
			return false;
			//show_404();
		}
	}

	public  function getCampusProgramOfStudy($programTypeId , $shiftId, $campusId = 0 ,$programId = 0,$subjectsIds = ""){
		$where_clause = "";
		if($programId != 0){
			$where_clause = "where ps.program_id = $programId ";
			if($subjectsIds != ""){
				$where_clause.=" and ps.subject_id in ($subjectsIds) ";
			}
		}
		$subQuery = (" select pr.program_of_study_id from prerequisite pr
                    inner join program_subject ps on ps.program_subject_id = pr.program_subject_id
                    ".$where_clause);

		$campusFilter = "";
		if($campusId == 1){
			$campusFilter = " AND  ca.campus_id IN (1,2) ";
		}
		else if($campusId != 0){
			$campusFilter = " AND  ca.campus_id =  $campusId ";
		}


		$sql = " SELECT
 						CASE cpos.campus_id
						WHEN 2 THEN CONCAT(pos.name,' ',cm.name)
						ELSE pos.name END AS Name,
						p.name as programName, cpos.campus_program_of_study_id
            FROM campus_program_of_study AS cpos
            INNER JOIN campus AS cm ON cm.campus_id = cpos.campus_id
            INNER JOIN program_of_study AS pos on pos.program_of_study_id = cpos.program_of_study_id
            INNER JOIN program AS p on p.program_id = pos.program_id
            INNER JOIN shift AS s on s.shift_id = cpos.shift_id
            INNER JOIN campus AS ca on ca.campus_id =  cpos.campus_id
            WHERE s.shift_id = ? " .$campusFilter.
			"AND p.program_type_id = ?
            and pos.program_of_study_id in
                (  $subQuery
                 ) ORDER BY pos.name ";

		$query = $this->db->query($sql, array($shiftId,$programTypeId));

		$result = $query->result_array();
		return $result;

	}


}
	?>