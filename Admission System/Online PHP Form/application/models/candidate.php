<?php

if ( ! defined('BASEPATH')) exit('No direct script access allowed');


class Candidate extends  CI_Model
{

    private $candidateId;
    private $seatNo;
    private $admissionYearId;
    private $programTypeId = "";
    private $name = "";
    private $fathersName = "";
    private $surname = "";
    private $gender=4;
    private $CnicOf;
    private $cnic = "";
    private $dateOfBirth;
    private $placeOfBirth;
    private $religion;
    private $guardianName;
    private $relationship;
    private $occupation;
    private $district;
    private $area=4;
    private $nationality;
    private $telephone;
    private $mobile;
    private $email;
    private $permenentAddr;
    private $postalAddr;
    private $guardianAddr;
    private $yearsDegree;
    private $challanNo;
    private $challanDate;
    private $formSNo;
    private $group_id;
    private $objection_remarks;
    private $is_objection;



    //joins
    private $countryName;
    private $districtName;
    private $religionName;
    private $provinceName;
    private $bankBranch;
    private $dateOfSumbitForm;
    private $familyMobile;

    public function addNewCandidate($admissionYearId,$programTypeId, $name, $fathersName, $surname, $gender,$cnincOf,$cnic,$dateOfBirth, $placeOfBirth, $religion, $gurdianName, $relationship, $occupation,$district,$area,$nationality,$telephone,$mobile,$email, $permenentAddr,$postalAddr,$guardianAddr,$challanNo = 0,$challanDate,$bankBranchId,$familyMobile,$formNo,$objectionRemarks,$is_objection){
        $dat = substr($dateOfBirth,0,2);
        $month = substr($dateOfBirth,3,2);
        $year = substr($dateOfBirth,6);
        $dateOfBirth = "$year-$month-$dat";

        $datCD = substr($challanDate,0,2);
        $monthCD = substr($challanDate,3,2);
        $yearCD = substr($challanDate,6);
        $challanDate = "$yearCD-$monthCD-$datCD";

//        $seatNo = $this->getMaxSeatNo($gender,$programTypeId);
        $seatNo = 0;

//        $cnincOf = "S";
        $data = array('district_id'=>$district,'country_id'=>$nationality,'religion_id'=>$religion,'program_type_id'=>$programTypeId,'admission_year_id'=>$admissionYearId,'seat_no'=>$seatNo
        ,'cnic_no'=>$cnic,'cnic_of'=>$cnincOf,'name'=>$name,'fathers_name'=>$fathersName,'surname'=>$surname,'gender'=>$gender,'area'=>$area,'date_of_birth'=>$dateOfBirth,'place_of_birth'=>$placeOfBirth
        ,'permanent_home_address'=>$permenentAddr,'present_postel_address'=>$postalAddr,'guardians_name'=>$gurdianName,'relationship'=>$relationship,'guardians_address'=>$guardianAddr
        ,'fathers_occupation'=>$occupation,'telephone'=>$telephone,'mobile'=>$mobile,'email'=>$email,'remarks'=>$challanNo,'chalan_date'=>$challanDate,'family_mobile'=>$familyMobile,'bank_branch_id'=>$bankBranchId,'form_sno'=>$formNo,'objection_remarks'=>$objectionRemarks,'is_objection'=>$is_objection);
        $query=$this->db->insert('candidate', $data);
        return $this->db->insert_id();
    }//end addNewCandidate()

    public  function searchCandidateBySeatNo($seatNo, $programType,$admissionYear){

        $sql = "SELECT candidate_id FROM candidate WHERE seat_no = ? and program_type_id = ? AND admission_year_id = ? ";
        $query = $this->db->query($sql, array($seatNo,$programType,$admissionYear));
        $result = $query->result_array();

        if ($result) {
            return $result[0]['candidate_id'];
        } else {
            return false;
        }

    }// end

    public function assignSeatNo($candidate_id,$gender,$programTypeId,$campus,$program_id=0){
        $seat_no = $this->getMaxSeatNo($gender,$programTypeId, $campus,$program_id);
        $rs = $this->updateSeatNo($candidate_id,$seat_no);
        if($rs){
            return $seat_no;
        }else{
           // show_404();

            return false;
        }
    }

    public function updateSeatNo($candidateId,$seatNo){
        $sql = "UPDATE candidate
                        SET seat_no = ?
                        WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($seatNo,$candidateId));
        return $query;
    }

    public function updateImage($candidateId,$imagePath){
        $sql = "UPDATE candidate
                        SET image_path = ?
                        WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($imagePath,$candidateId));
        return $query;
    }

    public function updateDegreeYears($candidateId,$degreeYears){
        $sql = "UPDATE candidate
                        SET years_degree = ?
                        WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($degreeYears,$candidateId));
        return $query;
    }

    public function updateDeductionMarks($candidateId,$deductionMarks){
        $sql = "UPDATE candidate
                        SET deduction_marks = ?
                        WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($deductionMarks,$candidateId));
        return $query;
    }

    public function updateFormNo($candidateId,$formNo){
        $sql = "UPDATE candidate
                        SET form_sno = ?
                        WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($formNo,$candidateId));
        return $query;
    }

	public function getMaxSeatNoForMaster($gender, $campus, $program_id,$optional_subject = "" ){ /*  $optional_subject = "1,3,4"   */
		$sql = "SELECT MAX(c.seat_no) FROM candidate as c 
				INNER JOIN credential_details as cd on cd.candidate_id = c.candidate_id and cd.detail_of = 2
				INNER JOIN optional_subject as ops on ops
				";
	}

    public function getOptionalSubjectAndProgramName($candidateId){
        $sql="SELECT os.subject_id,ds.`discipline_id`,ds.name AS discipline_name ,p.name AS program_name ,p.`program_id`,fac.`faculty_id`,fac.`name` FROM optional_subject os
                   INNER JOIN credential_details  AS cd ON  cd.credential_details_id = os.credential_details_id
                   INNER JOIN program AS p ON p.program_id = cd.program_id
                   INNER JOIN subject  AS sb ON sb.subject_id = os.subject_id
                   INNER JOIN discipline AS ds ON ds.discipline_id = sb.discipline_id
                   INNER JOIN `department` AS dep ON dep.`department_id`=ds.`department_id`
                   INNER JOIN `faculty` AS fac ON dep.`faculty_id`=fac.`faculty_id`
                   WHERE candidate_id = ? AND detail_of = ?";
                    $query = $this->db->query($sql,array($candidateId,  Variable::$GRD_LEVEL));
                    $result = $query->result_array();
                    if($result){
                    return $result;
                    }else{
                       return false;
                        //show_404();
                    }

    }

        public function getMasterGroup($gender){
            $sql = "SELECT id, name FROM  master_group  where gender= $gender order by name";
      //  $query = $this->db->query($sql);
            $query=$this->db->query( $sql );
            $result = $query->result_array();
            return $result;

        }

    public function getMaxMasterGroup($group_id){
        $sql="SELECT MAX( seat_no ) as seat_no  FROM  `master_seat_no`  WHERE group_id =$group_id";
        $query = $this->db->query($sql);
         $result = $query->result_array();
        $seatNo=$result[0]['seat_no'];
            return $seatNo;


    }
    public function getMasterGroupById($group_id){
        $sql=" SELECT name FROM  `master_group`  WHERE id =$group_id";
        $query = $this->db->query($sql);
        $result = $query->result_array();
        $name=$result[0]['name'];
        return $name;


    }
    public function addMasterSeatNo($group_id,$seatNo,$remarks=""){
        $sql="INSERT INTO `master_seat_no` (`group_id`, `seat_no`,remarks) values(?,?,?) ";
        $query = $this->db->query($sql,array($group_id,$seatNo,$remarks));
        return $query;
    }



    public function getMaxMasteGroupSeatNo($gender){
        $sql="SELECT * FROM   master_group  where gender=?";
        $query = $this->db->query($sql,array($gender));
        return $result = $query->result_array();
    }

    public function getMaxSeatNo($gender,$programTypeId, $campus,$program_id){
        $sql = "SELECT max(c.seat_no) as seat_no FROM candidate c inner join applied_campus ac on ac.candidate_id = c.candidate_id and ac.campus_id = $campus
				where gender = ? and admission_year_id = ? and program_type_id = ?";
        $query = $this->db->query($sql,array($gender,$this->variable->ADMISSION_YEAR_ID(),$programTypeId) );
        $result = $query->result_array();
        if($result[0]['seat_no'] != null && $result[0]['seat_no'] != 0){
            return $result[0]['seat_no']+1;
        }else{
            if($programTypeId == Variable::BACHELOR_ID()){
                if($campus == Variable::JAMSHORO_CAMPUS()){
//                        0001 to 3500         3501 to 16000
                    if($gender == Variable::$MALE){
                        return 3501;
                    }else if($gender == Variable::$FEMALE){
                        return 1;
                    }
                }else if($campus == Variable::BADIN_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 16501;
                    }else if($gender == Variable::$FEMALE){
                        return 16001;
                    }
                }else if($campus == Variable::BHITSHAH_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 20501;
                    }else if($gender == Variable::$FEMALE){
                        return 20001;
                    }
                }else if($campus == Variable::DADU_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 18501;
                    }else if($gender == Variable::$FEMALE){
                        return 18001;
                    }
                }else if($campus == Variable::LARKANA_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 19501;
                    }else if($gender == Variable::$FEMALE){
                        return 19001;
                    }
                }else if($campus == Variable::MIRPURKHAS_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 17501;
                    }else if($gender == Variable::$FEMALE){
                        return 17001;
                    }
                }else if($campus == Variable::THATTA_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 21501;
                    }else if($gender == Variable::$FEMALE){
                        return 21001;
                    }
                }
            }else{//Master


                if($campus == Variable::JAMSHORO_CAMPUS()){
//                        0001 to 3500         3501 to 16000

                /*    if($gender == Variable::$MALE){
                        return 201;
                    }else if($gender == Variable::$FEMALE){
                        return 1;
                    } */

                }else if($campus == Variable::BADIN_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 9001;
                    }else if($gender == Variable::$FEMALE){
                        return 9151;
                    }
                }else if($campus == Variable::BHITSHAH_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 9201;
                    }else if($gender == Variable::$FEMALE){
                        return 9351;
                    }
                }else if($campus == Variable::DADU_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 9401;
                    }else if($gender == Variable::$FEMALE){
                        return 9551;
                    }
                }else if($campus == Variable::LARKANA_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 9601;
                    }else if($gender == Variable::$FEMALE){
                        return 9751;
                    }
                }else if($campus == Variable::MIRPURKHAS_CAMPUS()){
                    if($gender == Variable::$MALE){
                     //   return 17501;
                    }else if($gender == Variable::$FEMALE){
                       // return 17001;
                    }
                }else if($campus == Variable::THATTA_CAMPUS()){
                    if($gender == Variable::$MALE){
                        return 9801;
                    }else if($gender == Variable::$FEMALE){
                        return 9951;
                    }
                }

            }
            return 1;
        }
    }


    public function updateCard($username, $password,$candidateId){
        $sql = "update online_card set candidate_id = ? where username=? and password = ?";
        $query = $this->db->query($sql,array($candidateId,$username,$password));
        return $query;
    }//end updateCard()


    public function getCandidateInfo($candidateId) {
        $sql = "SELECT candidate_id,district_id,country_id,religion_id,program_type_id,admission_year_id,seat_no,form_sno
                    ,cnic_no,cnic_of,name,fathers_name,surname,gender,area,date_of_birth,place_of_birth
                    ,permanent_home_address,present_postel_address,guardians_name,relationship,guardians_address
                    ,fathers_occupation,telephone,mobile,email,years_degree,remarks,chalan_date,family_mobile,bank_branch_id,print_form_date_time,objection_remarks,is_objection
                    FROM candidate
                    WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($candidateId));
        $result = $query->result_array();

        if( $result ){
            //return $result[0];
            $bean = new Candidate();
            $bean->setCandidateId($result[0]['candidate_id']);
            $bean->setChallanNo($result[0]['remarks']);
            $bean->setChallanDate($result[0]['chalan_date']);
            $bean->setDistrict($result[0]['district_id']);
            $bean->setNationality($result[0]['country_id']);
            $bean->setReligion($result[0]['religion_id']);
            $bean->setProgramTypeId($result[0]['program_type_id']);
            $bean->setAdmissionYearId($result[0]['admission_year_id']);
            $bean->setCnic($result[0]['cnic_no']);
            $bean->setCnicOf($result[0]['cnic_of']);
            $bean->setName($result[0]['name']);
            $bean->setFathersName($result[0]['fathers_name']);
            $bean->setSurname($result[0]['surname']);
            $bean->setGender($result[0]['gender']);
            $bean->setArea($result[0]['area']);
            $bean->setDateOfBirth($result[0]['date_of_birth']);
            $bean->setPlaceOfBirth($result[0]['place_of_birth']);
            $bean->setPermenentAddr($result[0]['permanent_home_address']);
            $bean->setPostalAddr($result[0]['present_postel_address']);
            $bean->setGurdianName($result[0]['guardians_name']);
            $bean->setRelationship($result[0]['relationship']);
            $bean->setGuardianAddr($result[0]['guardians_address']);
            $bean->setOccupation($result[0]['fathers_occupation']);
            $bean->setTelephone($result[0]['telephone']);
            $bean->setMobile($result[0]['mobile']);
            $bean->setEmail($result[0]['email']);
            $bean->setFamilyMobile($result[0]['family_mobile']);
            $bean->setBankBranch($result[0]['bank_branch_id']);
            $bean->setDateOfSumbitForm($result[0]['print_form_date_time']);
            $bean->setYearsDegree($result[0]['years_degree']);
            $bean->setFormSNo($result[0]['form_sno']);
            $bean->setSeatNo($result[0]['seat_no']);
            $bean->setObjectionRemarks(($result[0]['objection_remarks']));
            $bean->setIsObjection(($result[0]['is_objection']));

            return $bean;
        }else{
            //show_404();
           return false;
        }
    }//end getCandidateInfo()


    public function getProvinceId($districtId) {
        $sql = "SELECT dv.province_id FROM district dst
                    inner join division dv on dv.division_id = dst.division_id
                    WHERE dst.district_id = ? ";
        $query = $this->db->query($sql,array($districtId));
        $result = $query->result_array();

        if( $result ){
            return $result[0]['province_id'];
        }else{
            return 0;
        }
    }//end getCandidateInfo()



    public function getCandidateFullInfo($candidateId) {
        $sql = "SELECT c.candidate_id, con.name as country_name, d.name as district_name, p.name as province_name, rl.name as religion_name, c.candidate_id,c.district_id,c.country_id,c.religion_id,c.program_type_id,c.admission_year_id,seat_no
                    ,cnic_no,cnic_of,c.name,fathers_name,surname,gender,area,date_of_birth,place_of_birth,c.form_sno
                    ,permanent_home_address,present_postel_address,guardians_name,relationship,guardians_address
                    ,fathers_occupation,telephone,mobile,email,years_degree,family_mobile,bank_branch_id,print_form_date_time,c.remarks,c.chalan_date,c.objection_remarks,c.is_objection FROM candidate c
                     INNER JOIN district d on d.district_id = c.district_id
                     INNER JOIN division dv on dv.division_id = d.division_id
                     INNER JOIN province p on p.province_id = dv.province_id
                     INNER JOIN country con on con.country_id = c.country_id
                     INNER JOIN religion rl on rl.religion_id = c.religion_id
                    WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($candidateId));
        $result = $query->result_array();

        if($result){
//            return $result;
            $bean = new Candidate();
            $bean->setCandidateId($result[0]['candidate_id']);
            $bean->setChallanNo($result[0]['remarks']);
            $bean->setChallanDate($result[0]['chalan_date']);
            $bean->setDistrictName($result[0]['district_name']);
            $bean->setProvinceName($result[0]['province_name']);
            $bean->setCountryName($result[0]['country_name']);
            $bean->setReligionName($result[0]['religion_name']);
            $bean->setProgramTypeId($result[0]['program_type_id']);
            $bean->setAdmissionYearId($result[0]['admission_year_id']);
            $bean->setCnic($result[0]['cnic_no']);
            $bean->setCnicOf($result[0]['cnic_of']);
            $bean->setName($result[0]['name']);
            $bean->setFathersName($result[0]['fathers_name']);
            $bean->setSurname($result[0]['surname']);
            $bean->setGender($result[0]['gender']);
            $bean->setArea($result[0]['area']);
            $bean->setDateOfBirth($result[0]['date_of_birth']);
            $bean->setPlaceOfBirth($result[0]['place_of_birth']);
            $bean->setPermenentAddr($result[0]['permanent_home_address']);
            $bean->setPostalAddr($result[0]['present_postel_address']);
            $bean->setGurdianName($result[0]['guardians_name']);
            $bean->setRelationship($result[0]['relationship']);
            $bean->setGuardianAddr($result[0]['guardians_address']);
            $bean->setOccupation($result[0]['fathers_occupation']);
            $bean->setTelephone($result[0]['telephone']);
            $bean->setMobile($result[0]['mobile']);
            $bean->setEmail($result[0]['email']);
            $bean->setFamilyMobile($result[0]['family_mobile']);
            $bean->setBankBranch($result[0]['bank_branch_id']);
            $bean->setDateOfSumbitForm($result[0]['print_form_date_time']);
            $bean->setYearsDegree($result[0]['years_degree']);
            $bean->setSeatNo($result[0]['seat_no']);
            $bean->setFormSNo($result[0]['form_sno']);
            $bean->setObjectionRemarks(($result[0]['objection_remarks']));
            $bean->setIsObjection(($result[0]['is_objection']));
            return $bean;
        }else{
            return false;
           // show_404();
        }
    }//end getCandidateInfo()


    public function getPrintAllSlips($programTypeId,$seatNoTo,$seatNoFrom,$admissionYear) {
        $sql = "SELECT c.candidate_id, con.name AS country_name, d.name AS district_name, p.name AS province_name, rl.name AS religion_name, c.candidate_id,c.district_id,c.country_id,c.religion_id,c.program_type_id,c.admission_year_id,seat_no, `campus`.`name` as campus_name , `campus`.`location`
                    ,cnic_no,cnic_of,c.name,fathers_name,surname,gender,AREA,date_of_birth,place_of_birth,c.form_sno
                    ,permanent_home_address,present_postel_address,guardians_name,relationship,guardians_address
                    ,fathers_occupation,telephone,mobile,email,years_degree,family_mobile,bank_branch_id,print_form_date_time,c.remarks,c.chalan_date,c.objection_remarks,c.is_objection FROM candidate c
                     INNER JOIN district d ON d.district_id = c.district_id
                     INNER JOIN division dv ON dv.division_id = d.division_id
                     INNER JOIN province p ON p.province_id = dv.province_id
                     INNER JOIN country con ON con.country_id = c.country_id
                     INNER JOIN religion rl ON rl.religion_id = c.religion_id
                     INNER JOIN applied_campus AS ac ON ac.`candidate_id`=c.`candidate_id`
                     INNER JOIN `campus`  ON `campus`.`campus_id`=ac.`campus_id`
                      WHERE c.program_type_id=?  and admission_year_id= $admissionYear AND seat_no BETWEEN $seatNoTo AND $seatNoFrom   ORDER BY seat_no";
        $query = $this->db->query($sql,array($programTypeId));
        $result = $query->result_array();

            return $result;

    }//end getCandidateInfo()
/*
    public function updateCandidate($candidateId,$admissionYearId,$programTypeId, $name, $fathersName, $surname, $gender,$cnicOf, $cnic,$dateOfBirth, $placeOfBirth, $religion, $gurdianName, $relationship, $occupation,$district,$area,$nationality,$telephone,$mobile,$email, $permenentAddr,$postalAddr,$guardianAddr,$challanNo,$challanDate,$bankBranch,$familyNumber){
        $dat = substr($dateOfBirth,0,2);
        $month = substr($dateOfBirth,3,2);
        $year = substr($dateOfBirth,6);
        $dateOfBirth = "$year-$month-$dat";

        $datCD = substr($challanDate,0,2);
        $monthCD = substr($challanDate,3,2);
        $yearCD = substr($challanDate,6);
        $challanDate = "$yearCD-$monthCD-$datCD";

        $sql = "UPDATE candidate
                        SET district_id = ?, country_id = ?, religion_id = ?, program_type_id = ?,
                        admission_year_id = ?, cnic_no = ?, name = ? , fathers_name = ?, surname = ?,
                        gender = ?, area = ?, date_of_birth = ?, place_of_birth = ?, permanent_home_address = ?,
                        present_postel_address = ?, guardians_name = ?, relationship = ?, guardians_address = ?,
                        fathers_occupation = ?, telephone = ?, mobile = ?, email = ?,remarks = ? ,chalan_date = ? , cnic_of = ? , bank_branch_id = ? , family_mobile = ?
                        WHERE candidate_id = ? ";

        $query = $this->db->query($sql,array($district,$nationality,$religion,$programTypeId
        ,$admissionYearId ,$cnic,$name,$fathersName,$surname
        ,$gender,$area,$dateOfBirth,$placeOfBirth,$permenentAddr
        ,$postalAddr,$gurdianName, $relationship,$guardianAddr
        ,$occupation,$telephone,$mobile,$email,$challanNo,$challanDate,$cnicOf,$candidateId,$bankBranch,$familyNumber));

        return $query;

    }//end addNewCandidate()

*/
    public function updateCandidate($candidateId,$admissionYearId,$programTypeId, $name, $fathersName, $surname, $gender,$cnicOf, $cnic,$dateOfBirth, $placeOfBirth, $religion, $gurdianName, $relationship, $occupation,$district,$area,$nationality,$telephone,$mobile,$email, $permenentAddr,$postalAddr,$guardianAddr,$challanNo,$challanDate,$bankBranch,$familyNumber,$form_no,$objectionRemarks,$is_objection){
        $dat = substr($dateOfBirth,0,2);
        $month = substr($dateOfBirth,3,2);
        $year = substr($dateOfBirth,6);
        $dateOfBirth = "$year-$month-$dat";

        $datCD = substr($challanDate,0,2);
        $monthCD = substr($challanDate,3,2);
        $yearCD = substr($challanDate,6);
        $challanDate = "$yearCD-$monthCD-$datCD";

        $sql = "UPDATE candidate
                        SET district_id = ?, country_id = ?, religion_id = ?, program_type_id = ?,
                        admission_year_id = ?, cnic_no = ?, name = ? , fathers_name = ?, surname = ?,
                        gender = ?, area = ?, date_of_birth = ?, place_of_birth = ?, permanent_home_address = ?,
                        present_postel_address = ?, guardians_name = ?, relationship = ?, guardians_address = ?,
                        fathers_occupation = ?, telephone = ?, mobile = ?, email = ?,remarks = ? ,chalan_date = ? , cnic_of = ? , bank_branch_id = ?, family_mobile = ? ,form_sno= ?,objection_remarks=?,is_objection=?
                        WHERE candidate_id = ? ";

        $query = $this->db->query($sql,array($district,$nationality,$religion,$programTypeId
        ,$admissionYearId ,$cnic,$name,$fathersName,$surname
        ,$gender,$area,$dateOfBirth,$placeOfBirth,$permenentAddr
        ,$postalAddr,$gurdianName, $relationship,$guardianAddr
        ,$occupation,$telephone,$mobile,$email,$challanNo,$challanDate,$cnicOf,$bankBranch,$familyNumber,$form_no,$objectionRemarks,$is_objection,$candidateId));

        return $query;

    }//end addNewCandidate()

    public function updateCandidatePrintFormDate($candidateId){

        // Change the line below to your timezone!
        date_default_timezone_set('Asia/Karachi');
        $print_form_date_time = date('Y-m-d H:i:s', time());

        $sql = "UPDATE candidate
                        SET print_form_date_time = ?  WHERE candidate_id = ? ";

        $query = $this->db->query($sql,array($print_form_date_time,$candidateId));

        return $query;

    }//end updatePrintFormDate()

    public function updateCandidateDegreeYears($candidateId,$degreeYears){
        $sql = "UPDATE candidate  SET years_degree = ? WHERE candidate_id = ? ";

        $query = $this->db->query($sql,array($degreeYears, $candidateId));

        return $query;
    }//end updateCandidate()

    public function saveCredential($level,$candidateId,$issuerId,$totalMarks,$obtainMarks,$seatNo,$passingYear,$programId){
        $sql = "SELECT * from credential_details WHERE detail_of = ? and candidate_id = ?";
        $query = $this->db->query($sql,array($level,$candidateId));
        $rows = $query->num_rows;
        if ($rows > 0) {
            $sqlUpdate = "UPDATE credential_details
                        SET issuer_id = ?, total_marks = ?, marks_obtained = ?,
                        group_ = ?, seat_no = ?, passing_year = ?, program_id = ?
                        WHERE candidate_id = ? and detail_of = ? ";
            $updateQuery = $this->db->query($sqlUpdate,array($issuerId,$totalMarks,$obtainMarks,$programId,$seatNo,$passingYear,$programId,$candidateId,$level));
            return $updateQuery;
        }else{
            $sqlInsert = "INSERT INTO credential_details ( candidate_id, issuer_id, total_marks, marks_obtained, group_, seat_no, passing_year,detail_of,program_id)
                    VALUES
                    (?, ?, ?, ?, ?, ?, ?,?,? );";
            $sqlQuery = $this->db->query($sqlInsert,array($candidateId,$issuerId,$totalMarks,$obtainMarks,$programId,$seatNo,$passingYear,$level,$programId));
            return $sqlQuery;
        }
    }


    public function deleteOptionalSubject($candidateId){
        $sql = "delete os from optional_subject os
                  inner join credential_details cr on os.credential_details_id = cr.credential_details_id
                  where cr.candidate_id = ? ";

        $query = $this->db->query($sql, array($candidateId) );
       // echo("$query");
        return $query;
    }


    public function addOptionalSubject($candidateId, $subjectId){
        $sql = "INSERT INTO optional_subject (credential_details_id,subject_id) values (
                (select credential_details_id from credential_details where candidate_id = ? and detail_of = ? )
               , ?
               )";
        $query = $this->db->query($sql,array($candidateId,  Variable::$GRD_LEVEL,$subjectId));
        return $query;
    }

    public function getOptionalSubjectIds($candidateId){
        $sql = "SELECT subject_id FROM optional_subject os
                   inner join credential_details cd on  cd.credential_details_id = os.credential_details_id
                   where candidate_id = ? and detail_of = ? ";
        $query = $this->db->query($sql,array($candidateId,  Variable::$GRD_LEVEL));
        $result = $query->result_array();
        if($result){
            return $result;
        }else{
            return false;
            //show_404();
        }
    }

    public function getOptionalSubjectBeans($candidateId){
        $sql = "SELECT os.subject_id,ds.name FROM optional_subject os
                   inner join credential_details cd on  cd.credential_details_id = os.credential_details_id
                   inner join subject sb on sb.subject_id = os.subject_id
                   INNER JOIN discipline ds on ds.discipline_id = sb.discipline_id
                   where candidate_id = ? and detail_of = ? ";

        $query = $this->db->query($sql,array($candidateId,  Variable::$GRD_LEVEL));
        $result = $query->result_array();
        return $result;
    }


    public function deleteAppliedCategory($candidateId){
        $sql = "DELETE FROM applied_category WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($candidateId));
        return $query;
    }

    public function deleteAppliedCampus($candidateId){
        $sql = "DELETE FROM applied_campus WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($candidateId));
        return $sql;
    }

    public function addAppliedCampus($candidateId, $campusId, $optional = false){
        $sql = "";
        if($optional){
            $sql = "INSERT INTO applied_campus (candidate_id, campus_id, remarks ) VALUES (?, ? , 'optional')";
        }else{
            $sql = "INSERT INTO applied_campus (candidate_id, campus_id) VALUES (?, ?)";
        }
        $query = $this->db->query($sql,array($candidateId,$campusId));
        return $this->db->insert_id();
    }

    public function addAppliedCategory($candidateId,$catCode,$remarks,$noCode){
        $sql = "INSERT INTO applied_category
                (candidate_id, category_code, remarks, code)
                  VALUES
                (?, ?, ?, ? );
                ";
        $query = $this->db->query($sql,array($candidateId,$catCode,$remarks,$noCode));
        return $this->db->insert_id();
    }

    public function getAppliedCategory($candidateId){
        $sql = "SELECT code FROM applied_category where candidate_id = ? ";
        $query = $this->db->query($sql,array($candidateId));
        return $query->result_array();
    }

    public function getAppliedCampus($candidateId , $optional = false){
        $sql = "SELECT ac.campus_id,cm.name,cm.location FROM applied_campus as ac
                INNER JOIN campus cm on cm.campus_id= ac.campus_id
                where candidate_id = ? ";
        if($optional){
            $sql.= " AND ac.remarks IS NOT NULL";
        }else{
            $sql.= " AND ac.remarks IS NULL";
        }
        $query = $this->db->query($sql,array($candidateId));
        return $query->result_array();
    }

    public function deleteCandidateProgramOfStudy($candidateId , $filter = 0){
        $sql = "DELETE cnpos FROM candidate_program_of_study AS cnpos
                WHERE
                cnpos.candidate_id = ? ";
        if($filter !=0){
            $sql.= " AND cnpos.campus_program_of_study_id NOT IN ($filter) ";
        }

        $data = array($candidateId);
        $query = $this->db->query($sql,$data);
    //   echo($sql);
        return $query;
    }


    public function deleteCandidateProgramOfStudyByFilter($candidateId , $programTypeId ,$programId = 0,$subjectsIds = ""){
        $where_clause = "";
        $where_clause = " where ps.program_id = $programId ";
        if($subjectsIds != ""){
            $where_clause.=" and ps.subject_id in ($subjectsIds) ";
        }
        $subQuery = (" select pr.program_of_study_id from prerequisite pr
                    inner join program_subject ps on ps.program_subject_id = pr.program_subject_id
                    ".$where_clause);

        $campusFilter = "";
//        if($campusId != 0){
            $campusFilter = " ca.campus_id in ( select camp.campus_id from applied_campus as camp where camp.candidate_id = $candidateId)";
//        }


        $sql = " SELECT  cpos.campus_program_of_study_id
                FROM campus_program_of_study AS cpos
                INNER JOIN program_of_study AS pos on pos.program_of_study_id = cpos.program_of_study_id
                INNER JOIN program AS p on p.program_id = pos.program_id
                INNER JOIN shift AS s on s.shift_id = cpos.shift_id
                INNER JOIN campus AS ca on ca.campus_id =  cpos.campus_id
                WHERE " .$campusFilter.
            "   AND p.program_type_id = $programTypeId
            and pos.program_of_study_id in
                (  $subQuery
                 )  ";
        $new_sql = "DELETE cnpos FROM candidate_program_of_study AS cnpos
                WHERE
                cnpos.candidate_id = ? AND cnpos.campus_program_of_study_id NOT IN ($sql)";
     // echo "$new_sql";
        $data = array($candidateId);
        $query = $this->db->query($sql,$data);
        return $query;
    }


    public function addCandidateProgramOfStudy($candidateId,$cposId,$remarks,$choiceNo){
        $sql = "INSERT INTO candidate_program_of_study
                  (candidate_id, campus_program_of_study_id, remarks, choice_no)
                  VALUES
                  ( ?, ?, ?, ?)
                ";
        $query = $this->db->query($sql,array( $candidateId,$cposId,$remarks,$choiceNo));
        return $this->db->insert_id();
    }

    public function getCandidateProgramOfStudy($candidateId, $shiftId){
        $sql = "select cnpos.campus_program_of_study_id as campus_program_of_study_id from candidate_program_of_study cnpos
                inner join campus_program_of_study cpos on cpos.campus_program_of_study_id = cnpos.campus_program_of_study_id
                where shift_id = ? and candidate_id = ?
                order by choice_no ";
        $query = $this->db->query($sql,array($shiftId, $candidateId));
        return $query->result_array();
    }

    public function getCandidateProgramOfStudyByName($candidateId, $shiftId){
        $sql = "select
                  CASE cpos.campus_id
						WHEN 2 THEN CONCAT(pos.name,' ',cm.name)
						ELSE concat(pos.name,' (',p.name,')') END as name,
                 cnpos.campus_program_of_study_id as campus_program_of_study_id from candidate_program_of_study cnpos
                inner join campus_program_of_study cpos on cpos.campus_program_of_study_id = cnpos.campus_program_of_study_id
                INNER JOIN program_of_study pos ON pos.program_of_study_id = cpos.program_of_study_id
                INNER JOIN program p ON p.program_id = pos.program_id
                INNER JOIN campus cm ON cm.campus_id = cpos.campus_id
                where shift_id = ? and candidate_id = ?
                order by choice_no ";
        $query = $this->db->query($sql,array( $shiftId, $candidateId));
        return $query->result_array();
    }

    public function addPicture($candidateId,&$data, $contentType){
        $sql = "INSERT INTO online_candidate_picture
                    (candidate_id,pic,content_type) VALUES ( ?, '{$data}'  ,? );
                    ";
        $query = $this->db->query($sql,array( $candidateId,$contentType));
        return $this->db->insert_id();
    }
    public function deletePicture($candidateId){
        $sql = "DELETE FROM online_candidate_picture
                    WHERE candidate_id = ? ;
                    ";
        $query = $this->db->query($sql,array($candidateId));
        return $query;
    }

    public function isLocked($candidateId){
        $sql = "SELECT c.lock FROM candidate as c WHERE candidate_id = ? ";
        $query = $this->db->query($sql,array($candidateId));
        $result = $query->result_array();
        $locked = false;
        if($result){
            if($result[0]['lock'] != 0){
                $locked = true;
            }
        }
        return $locked;

    }// end isLocked()


    public  function deleteCredentialDetails($candidateId, $level){
        $sql = "DELETE FROM credential_details WHERE candidate_id = ? and detail_of = ?";
        $query = $this->db->query($sql, array($candidateId,$level));
        //$result = $query->result_array();
//echo("</br>".$sql);
        if ($query) {
            return $query;
        } else {
           return false;
           // show_404();
        }
    }



    public function lockCandidate($candidateId){
        $sql = "update candidate set `lock` = ? where candidate_id = ?";
        $l = 1;
        $query = $this->db->query($sql,array($l, $candidateId));
        return $query;

    }//end updateCard()

    public function unLockCandidate($candidateId){
        $sql = "update candidate set `lock` = ? , seat_no=''  where candidate_id = ?";
        $l = 0;
        $query = $this->db->query($sql,array($l, $candidateId));
        return $query;

    }//end updateCard()


    public function getImage($candidateId){
        $sql = "SELECT pic, content_type, size FROM  online_candidate_picture WHERE candidate_id = ? ;";
        $query = $this->db->query($sql,array($candidateId));
        $result = $query->result_array();

        if ($result) {
            return $result[0];
        } else {
            return false;
            //show_404();
        }
    }

    /**
     * @return mixed
     */


    public function getObjectionRemarks()
    {
        return $this->objection_remarks;
    }

    /**
     * @param mixed $objection_remarks
     */
    public function setObjectionRemarks($objection_remarks)
    {
        $this->objection_remarks = $objection_remarks;
    }

    public function getCandidateId()
    {
        return $this->candidateId;
    }

    /**
     * @param mixed $candidateId
     */
    public function setCandidateId($candidateId)
    {
        $this->candidateId = $candidateId;
    }



    public function getAdmissionYearId() {
        return $this->admissionYearId;
    }

    public function setAdmissionYearId($admissionYearId) {
        $this->admissionYearId = $admissionYearId;
    }

    public function getProgramTypeId() {
        return $this->programTypeId;
    }

    public function setProgramTypeId($programTypeId) {
        $this->programTypeId = $programTypeId;
    }

    public function getName() {
        return $this->name;
    }

    public function setName($name) {
        $this->name = $name;
    }

    public function getFathersName() {
        return $this->fathersName;
    }

    public function setFathersName($fathersName) {
        $this->fathersName = $fathersName;
    }

    public function getSurname() {
        return $this->surname;
    }

    public function setSurname($surname) {
        $this->surname = $surname;
    }

    public function getGender() {
        return $this->gender;
    }

    public function setGender($gender) {
        $this->gender = $gender;
    }

    public function getCnic() {
        return $this->cnic;
    }

    public function setCnic($cnic) {
        $this->cnic = $cnic;
    }


    public function getFormSNo() {
        return $this->formSNo;
    }

    public function setFormSNo($formSNo) {
        $this->formSNo = $formSNo;
    }

    public function getDateOfBirth() {
        if($this->dateOfBirth){
            $dob = $this->dateOfBirth;
            $year = substr($dob,0,4);
            $month = substr($dob,5,2);
            $dat = substr($dob,8);
            return "$dat-$month-$year";
        }else{
            return $this->dateOfBirth;
        }
    }

    public function setDateOfBirth($dateOfBirth) {
        $this->dateOfBirth = $dateOfBirth;
    }

    public function getPlaceOfBirth() {
        return $this->placeOfBirth;
    }

    public function setPlaceOfBirth($placeOfBirth) {
        $this->placeOfBirth = $placeOfBirth;
    }

    public function getReligion() {
        return $this->religion;
    }

    public function setReligion($religion) {
        $this->religion = $religion;
    }

    public function getGurdianName() {
        return $this->guardianName;
    }

    public function setGurdianName($gurdianName) {
        $this->guardianName = $gurdianName;
    }

    public function getRelationship() {
        return $this->relationship;
    }

    public function setRelationship($relationship) {
        $this->relationship = $relationship;
    }

    public function getOccupation() {
        return $this->occupation;
    }

    public function setOccupation($occupation) {
        $this->occupation = $occupation;
    }

    public function getDistrict() {
        return $this->district;
    }

    public function setDistrict($district) {
        $this->district = $district;
    }

    public function getArea() {
        return $this->area;
    }

    public function getAreaCode() {
        if($this->area == 0){
            return "U";
        }
        else if($this->area == 1){
            return "R";
        }
        else{
            return "N";
        }
    }

    public function setArea($area) {
        $this->area = $area;
    }

    public function getNationality() {
        return $this->nationality;
    }

    public function setNationality($nationality) {
        $this->nationality = $nationality;
    }

    public function getTelephone() {
        return $this->telephone;
    }

    public function setTelephone($telephone) {
        $this->telephone = $telephone;
    }

    public function getMobile() {
        return $this->mobile;
    }

    public function setMobile($mobile) {
        $this->mobile = $mobile;
    }

    public function getEmail() {
        return $this->email;
    }

    public function setEmail($email) {
        $this->email = $email;
    }

    public function getPermenentAddr() {
        return $this->permenentAddr;
    }

    public function setPermenentAddr($permenentAddr) {
        $this->permenentAddr = $permenentAddr;
    }

    public function getPostalAddr() {
        return $this->postalAddr;
    }

    public function setPostalAddr($postalAddr) {
        $this->postalAddr = $postalAddr;
    }

    public function getGuardianAddr() {
        return $this->guardianAddr;
    }

    public function setGuardianAddr($guardianAddr) {
        $this->guardianAddr = $guardianAddr;
    }
    public function getYearsDegree() {
        return $this->yearsDegree;
    }

    public function setYearsDegree($yearsDegree) {
        $this->yearsDegree = $yearsDegree;
    }

    public function getCountryName() {
        return $this->countryName;
    }

    public function setCountryName($countryName) {
        $this->countryName = $countryName;
    }

    public function getDistrictName() {
        return $this->districtName;
    }

    public function setDistrictName($districtName) {
        $this->districtName = $districtName;
    }

    public function getReligionName() {
        return $this->religionName;
    }

    public function setReligionName($religionName) {
        $this->religionName = $religionName;
    }

    public function getProvinceName() {
        return $this->provinceName;
    }

    public function setProvinceName($provinceName) {
        $this->provinceName = $provinceName;
    }


    public function getChallanNo() {
        return $this->challanNo;
    }

    public function setChallanNo($challanNo) {
        $this->challanNo = $challanNo;
    }


    public function getBankBranch() {
        return $this->bankBranch;
    }

    public function setBankBranch($bankBranch) {
        $this->bankBranch = $bankBranch;
    }

    public function getDateOfSumbitForm() {
        return $this->dateOfSumbitForm;
    }

    public function setDateOfSumbitForm($dateOfSumbitForm) {
        $this->dateOfSumbitForm = $dateOfSumbitForm;
    }

    public function getFamilyMobile() {
        return $this->familyMobile;
    }

    public function setFamilyMobile($familyMobile) {
        $this->familyMobile = $familyMobile;
    }

    /**
     * @return mixed
     */
    public function getIsObjection()
    {
        return $this->is_objection;
    }

    /**
     * @param mixed $is_objection
     */
    public function setIsObjection($is_objection)
    {
        $this->is_objection = $is_objection;
    }






    public function getChallanDate() {
        if($this->challanDate){
            $cd = $this->challanDate;
            $year = substr($cd,0,4);
            $month = substr($cd,5,2);
            $dat = substr($cd,8);
            return "$dat-$month-$year";
        }else{
            return $this->challanDate;
        }

    }

    public function setChallanDate($challanDate) {
        $this->challanDate = $challanDate;
    }

    /**
     * @return mixed
     */
    public function getSeatNo()
    {
        return $this->seatNo;
    }

    /**
     * @param mixed $seatNo
     */
    public function setSeatNo($seatNo)
    {
        $this->seatNo = $seatNo;
    }

    /**
     * @return mixed
     */
    public function getGuardianName()
    {
        return $this->guardianName;
    }

    /**
     * @param mixed $guardianName
     */
    public function setGuardianName($guardianName)
    {
        $this->guardianName = $guardianName;
    }

    /**
     * @return mixed
     */
    public function getCnicOf()
    {
        return $this->CnicOf;
    }

    /**
     * @param mixed $CnicOf
     */
    public function setCnicOf($CnicOf)
    {
        $this->CnicOf = $CnicOf;
    }



   // for seat number distrubation
    public function getGroupId()
    {
        return $this->group_id;
    }


    public function setGroupId($group_id)
    {
        $this->group_id = $group_id;
    }



}