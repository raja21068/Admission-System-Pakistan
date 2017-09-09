<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

require_once("my_controller.php");

class App_Form extends My_Controller
{

    public function index()
    {
        $isCandidateId = $this->is_logged_in();


        if($this->check($isCandidateId)){
            $this->tab_personal($isCandidateId);
        }else{
            $this->goto_login($isCandidateId);
        }
    }


    public function login_process(){

        $candidate_id = $this->is_logged_in();
        if($this->check($candidate_id)) {
//            $this->tab_personal($candidate_id);
            redirect("app_form/goto_personal_info_tab");
        }else{
            $this->recaptcha->recaptcha_check_answer();
            if($this->recaptcha->getIsValid()){
                $username = $this->input->post(Variable::USERNAME());
                $password = $this->input->post(Variable::PASSWORD());
                $c_username = My_Cryptor::myEncryption($username);
                $c_password = My_Cryptor::myEncryption($password);
//                echo $c_username;
                $this->load->model("users");
                $candidateId = $this->users->authenticateUser($c_username,$c_password);
                $loginData = array(
                    Variable::USERNAME()  => $c_username,
                    Variable::PASSWORD()  => $c_password,
                    Variable::USER_TYPE() => Variable::USER_CANDIDATE(),
                    "isSuperUser"=>""
                );
                $this->session->set_userdata($loginData);
                if( $this->check(($candidateId))){
                    //goto tab personal

                    redirect("app_form/goto_personal_info_tab");
                }else{
                    $this->goto_login($candidateId,"Username or Password in invalid");
                }
            }else{
                $this->goto_login($candidate_id,"Please verify you are not robot");
            }
        }
    }

    public function logout(){
        $this->session->unset_userdata(array(Variable::USERNAME()=>'',Variable::PASSWORD()));
        $this->login_form();

    }


    public function goto_personal_info_tab($candidate_id = -2){

        if($candidate_id == -2){
            $candidate_id = $this->is_logged_in();
        }

        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
            $candidate_id = $this->session->userdata("candidateId");
            //$candidate_id=$this->getCornNo();

            //echo($candidate_id);
        }


        if($this->check($candidate_id,$isSuperUser)){
            $this->tab_personal($candidate_id);
        }else{
            $this->goto_login($candidate_id);
        }
    }

    public function goto_educational_info_tab(){
        $candidate_id = $this->is_logged_in();
        if($this->check_valid_id($candidate_id)){
            $this->tab_education($candidate_id);
        }else{
            $this->goto_personal_info_tab($candidate_id);
        }
    }

    public function goto_apply_for_tab(){
        $candidate_id = $this->is_logged_in();
        if ($this->check_valid_id($candidate_id)) {
            $this->tab_apply_for($candidate_id);
        } else {
            $this->goto_personal_info_tab($candidate_id);
        }
    }


    public function tab_personal($candidateId,$msg = ""){
        $this->load->model("candidate");
        $candidateBean = new Candidate;

        $data['province_id'] = -1;

        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
            $candidateId = $this->session->userdata("candidateId");

        }


        if($this->check($candidateId,$isSuperUser)){
            $candidateInfo = $this->candidate->getCandidateInfo($candidateId);
            if($candidateInfo ){
                $candidateBean = $candidateInfo;
                $data['province_id'] = $this->candidate->getProvinceId($candidateInfo->getDistrict());
            }
        }

        $this->load->model("modelAdmission");

        $data['message'] = $msg;
        $data['admin'] = $isSuperUser;
        $data['country'] = $this->modelAdmission->getCountry();
        $data['religion'] = $this->modelAdmission->getReligion();
        $data['bankbranch'] = $this->modelAdmission->getBankBranchName();
        $data['candidate_bean'] = $candidateBean;


        if($candidateId == null){
            $data['img_src'] = '';
            $data['img_input'] = 'required';
        }else{
            $data['img_input'] = '';
            $data['img_src'] = "src='".base_url("uploads/".($candidateId)).".jpg?" . time() ."'";
        }
        $bar_data = array("is_logged_in"=>true);
        $this->load->view("bar",$bar_data);
        $this->load->view("tab_personal",$data);
        $this->load->view("footerbar");

    }

    public function personal_information_save(){
        //echo($candidate_id);

        $candidate_id = $this->is_logged_in();

        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
           $candidate_id = $this->session->userdata("candidateId");
           // $candidate_id=$this->getCornNo();
        }


        if($this->check($candidate_id,$isSuperUser)){
            $this->load->model("candidate");
            $admissionYearId=Variable::ADMISSION_YEAR_ID();
            $programTypeId=$this->input->post(Variable::PROGRAM_TYPE());
            $name=$this->input->post(Variable::STUDENT_NAME());
            $name = strtoupper($name);
            $fathersName=$this->input->post(Variable::FATHER_NAME());
            $fathersName = strtoupper($fathersName);
            $surname=$this->input->post(Variable::SURNAME());
            $surname = strtoupper($surname);
            $gender=$this->input->post(Variable::GENDER());
            $cnic=$this->input->post(Variable::CNIC());
            $dateOfBirth=$this->input->post("birth-date");
            $placeOfBirth="";
//            $placeOfBirth=$this->input->post(Variable::BIRTH_PLACE());
            $religion=$this->input->post(Variable::RELIGION());
            $gurdianName=$this->input->post(Variable::GUARDIAN_NAME());
            $gurdianName = strtoupper($gurdianName);
            $relationship=$this->input->post(Variable::GUARDIAN_RELATIONSHIP());
            $occupation=$this->input->post(Variable::GUARDIAN_OCCUPATION());
            $district=$this->input->post(Variable::DISTRICT());
            $area=$this->input->post(Variable::AREA());
            $nationality=$this->input->post(Variable::COUNTRY());
            $telephone=$this->input->post(Variable::TELEPHONE());
            $mobile=$this->input->post(Variable::MOBILE());
            $email=$this->input->post(Variable::EMAIL());
            $permenentAddr=$this->input->post(Variable::PERMENENT_ADDRESS());
            $permenentAddr=strtoupper($permenentAddr);
            $postalAddr=$this->input->post(Variable::POSTAL_ADDRESS());
            $postalAddr=strtoupper($postalAddr);
            $guardianAddr=$this->input->post(Variable::GUARDIAN_ADDRESS());
            $guardianAddr=strtoupper($guardianAddr);
            $challanNo =$this->input->post(Variable::CHALLAN_NO());
            $cnicOf = $this->input->post("cninc_of");
            $challanDate=$this->input->post("challan-date");
            $form_no = $this->input->post("formNo");
            $familyNumber=$this->input->post(Variable::FAMILY_MOBIE());
            $bankBranch=$this->input->post(Variable::BANK_BRANCH());
            $objectionRemarks=$this->input->post(Variable::OBJECTION_REMARKS());
            $is_objection=$this->input->post(Variable::IS_OBJECTION());


            if($candidate_id == null || $candidate_id == ""){
                $candidate_id = $this->candidate->addNewCandidate($admissionYearId,$programTypeId, $name, $fathersName, $surname, $gender,$cnicOf ,$cnic,$dateOfBirth, $placeOfBirth, $religion, $gurdianName, $relationship, $occupation,$district,$area,$nationality,$telephone,$mobile,$email, $permenentAddr,$postalAddr,$guardianAddr,$challanNo ,$challanDate,$bankBranch,$familyNumber,$form_no,$objectionRemarks,$is_objection);
                $this->candidate->updateImage($candidate_id,"$candidate_id.jpg");
                $this->candidate->updateCandidatePrintFormDate($candidate_id);
                $this->candidate-> updateCard($this->session->userdata(Variable::USERNAME()), $this->session->userdata(Variable::PASSWORD()),$candidate_id);
            }else{

                // delete choices when change degree or program_type_id
                $this->load->model("candidate");
                $candidate_bean = $this->candidate->getCandidateInfo($candidate_id);
                if($candidate_bean->getProgramTypeId() != $programTypeId){
                        $this->candidate->deleteCandidateProgramOfStudy($candidate_id);
                        $this->candidate->deleteAppliedCategory($candidate_id);
                        $this->candidate->deleteAppliedCampus($candidate_id);

                    }

                //update candidate
                $this->candidate->updateCandidate($candidate_id, $admissionYearId, $programTypeId, $name, $fathersName, $surname, $gender,$cnicOf,$cnic, $dateOfBirth, $placeOfBirth , $religion, $gurdianName, $relationship, $occupation, $district, $area, $nationality, $telephone, $mobile, $email, $permenentAddr, $postalAddr, $guardianAddr,$challanNo,$challanDate,$bankBranch,$familyNumber,$form_no,$objectionRemarks,$is_objection);
                $this->candidate->updateCandidatePrintFormDate($candidate_id);


            }
            $config['upload_path'] = './uploads/';
            $config['allowed_types'] = 'jpg|png';
            $config['overwrite'] = TRUE;
            $config['detect_mime'] = TRUE;
            $config['file_name'] =($candidate_id);
            $this->load->library('upload', $config);
            if ( $this->upload->do_upload(Variable::STUDENT_PICTURE()))
            {
                $gd['image_library'] = 'gd2';
                $gd['source_image']	= './uploads/'.$this->upload->file_name;
                $gd['maintain_ratio'] = TRUE;
                $gd['create_thumb'] = TRUE;
                $gd['thumb_marker'] = '';
                $gd['overwrite'] = TRUE;
                $gd['width']	= 400;
                $gd['height']	= 400;
                $this->load->library('image_lib', $gd);
                $this->image_lib->resize();

            }
            $this->tab_education($candidate_id);

        }else{
            $this->goto_login($candidate_id);
        }

    }

    private function tab_education($candidate_id,$msg = "",$ssc_bean = null,$hsc_bean=null,$grd_bean=null,$candidate_bean=null,$optionalOne = null,$optionalTwo = null,$optionalThree = null){
        $this->load->model("modelAdmission");
        $this->load->model("credentials_details");
        $this->load->model("candidate");

        $data['message'] = $msg;
        $data['boards'] = $this->modelAdmission->getBoards();
		$data['university'] = $this->modelAdmission->getUniversity();
		$data['sscgroup'] = $this->modelAdmission->getSSCGroup();
		$data['hscgroup'] = $this->modelAdmission->getHSCGroup();
        $data['candidate_bean'] = $this->candidate->getCandidateInfo($candidate_id);
       // $candidate_bean = new Candidate;

        if($candidate_bean ==null){


            $candidate_bean = $this->candidate->getCandidateInfo($candidate_id);
        }


        if($ssc_bean == null){
            $sscCand =$this->credentials_details->getCredentialDetails($candidate_id, Variable::$SSC_LEVEL);
            $ssc_bean = $this->prepare_credentials($sscCand);
        }
        if($hsc_bean == null){
            $hscCand =$this->credentials_details->getCredentialDetails($candidate_id, Variable::$HSC_LEVEL);
            $hsc_bean = $this->prepare_credentials($hscCand);
        }


        $data['ssc_bean'] = $ssc_bean;
        $data['hsc_bean'] = $hsc_bean;

        $data['is_master'] = ($candidate_bean->getProgramTypeId() == Variable::MASTER_ID());
        if($candidate_bean->getProgramTypeId() == Variable::MASTER_ID()){

            $data['bachlorProgram'] = $this->modelAdmission->getBachlorProgram();
            if($grd_bean == null){
                $grdCand = $this->credentials_details->getCredentialDetails($candidate_id, Variable::$GRD_LEVEL);
                $grd_bean = $this->prepare_credentials($grdCand);
            }
            $data['grd_bean'] = $grd_bean;
            $data['candidate_optional_sub_ids'] = array();
            $result = $this->candidate->getOptionalSubjectIds($candidate_id);
            if($result){
                $data['candidate_optional_sub_ids'] = $result;
            }
        }

        $data['optional_subjects'] = null;

        if($grd_bean!=null && $grd_bean->getProgramId()){
            $data['optional_subjects'] = $this->modelAdmission->getOptionalSubject($grd_bean->getProgramId());
        }

        $bar_data = array("is_logged_in"=>true);
        $this->load->view("bar",$bar_data);
        $this->load->view("tab_educational",$data);
        $this->load->view("footerbar");

    }

    public function sendMail($from,$senderName,$to,$cc,$subject,$message="",$filePath)
    {
        $this->load->library('email'); // load email library
        $this->email->from($from, $senderName);
        $this->email->to($to);
        $this->email->cc($cc);
        $this->email->subject($subject);
        $this->email->message($message);
        $this->email->attach($filePath); // attach file
       // $this->email->attach('/path/to/file2.pdf');
        if ($this->email->send())
            echo "Mail Sent!";
        else
            echo "There is error in sending mail!";
    }

function testmail()
{
    $this->load->library('email'); // load email library
    $this->email->from('rajakumarlohano@gmail.com', 'Admission University of Sindh');
    $this->email->to('rajakumarlohano@gmail.com');
    //$this->email->cc('test2@gmail.com');
    $this->email->subject('Admission Form');
    $this->email->message('Your Message');
    //$this->email->attach('/path/to/file1.png'); // attach file
    //$this->email->attach('/path/to/file2.pdf');

    if ($this->email->send())

        echo "Mail Sent!";
    else
        echo "There is error in sending mail!";
}


    public function educational_information_save(){
        $candidate_id = $this->is_logged_in();

        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
            $candidate_id = $this->session->userdata("candidateId");
           // $candidate_id=$this->getCornNo();
        }



        if($this->check($candidate_id,$isSuperUser)) {
            $ssc_issuer = $this->input->post(Variable::SSC_BOARD());
            $ssc_total = $this->input->post(Variable::SSC_TOTAL());
            $ssc_seat_no = $this->input->post(Variable::SSC_SEATNO());
            $ssc_obtain = $this->input->post(Variable::SSC_OBTAIN());
            $ssc_group = $this->input->post(Variable::SSC_GROUP());
            $ssc_year = $this->input->post(Variable::SSC_YEAR());
            $ssc_bean  = $this->prepare_credentials_bean($ssc_issuer,$ssc_obtain,$ssc_year,$ssc_group,$ssc_seat_no,$ssc_total);
            $hsc_issuer = $this->input->post(Variable::HSC_BOARD());
            $hsc_seat_no = $this->input->post(Variable::HSC_SEATNO());
            $hsc_total = $this->input->post(Variable::HSC_TOTAL());
            $hsc_obtain = $this->input->post(Variable::HSC_OBTAIN());
            $hsc_group = $this->input->post(Variable::HSC_GROUP());
            $hsc_year = $this->input->post(Variable::HSC_YEAR());
            $hsc_bean  = $this->prepare_credentials_bean($hsc_issuer,$hsc_obtain,$hsc_year,$hsc_group,$hsc_seat_no,$hsc_total);

            $this->load->model("candidate");
            $this->load->model("credentials_details");

            $candidate_bean = $this->candidate->getCandidateInfo($candidate_id);
            $this->candidate->updateCandidatePrintFormDate($candidate_id);

            $credential_rs = $this->credentials_details->getCredentialDetails($candidate_id, Variable::$HSC_LEVEL);
            $group= $credential_rs[0]['group_'];

            //delete bachlor program of study
            if($candidate_bean->getProgramTypeId() == Variable::BACHELOR_ID()) {
                $credential_rs = $this->credentials_details->getCredentialDetails($candidate_id, Variable::$HSC_LEVEL);
                $group = $credential_rs[0]['group_'];
                if ($hsc_group != $group) {
                    $this->candidate->deleteCandidateProgramOfStudy($candidate_id);
                    $this->candidate->deleteAppliedCategory($candidate_id);
                    $this->candidate->deleteAppliedCampus($candidate_id);
                }
            }
            //-------------------------end delete bachlor--------------------------//


            $grd_bean = null;
            $grd_sub_one = null;
            $grd_sub_two = null;
            $grd_sub_three = null;
            if($candidate_bean->getProgramTypeId() == Variable::MASTER_ID()){
                $grd_issuer = $this->input->post(Variable::GRD_UNIVERSITY());
                $grd_seat_no = $this->input->post(Variable::GRD_SEAT_NO());
                $grd_total = $this->input->post(Variable::GRD_TOTAL());
                $grd_obtain = $this->input->post(Variable::GRD_OBTAIN());
                $grd_group = $this->input->post(Variable::GRD_GROUP());
                $grd_year = $this->input->post(Variable::GRD_YEAR());
                $grd_bean  = $this->prepare_credentials_bean($grd_issuer,$grd_obtain,$grd_year,$grd_group,$grd_seat_no,$grd_total);
                if($this->input->post(Variable::GRD_SUB_ONE())){
                    $grd_sub_one = $this->input->post(Variable::GRD_SUB_ONE());
                }
                if($this->input->post(Variable::GRD_SUB_TWO())){
                    $grd_sub_two = $this->input->post(Variable::GRD_SUB_TWO());
                }
                if($this->input->post(Variable::GRD_SUB_THREE())){
                    $grd_sub_three = $this->input->post(Variable::GRD_SUB_THREE());
                }
            }


            //delete Master program of study
            if($candidate_bean->getProgramTypeId() == Variable::MASTER_ID()){
                $credential_rs = $this->credentials_details->getCredentialDetails($candidate_id, Variable::$GRD_LEVEL);
                $group= $credential_rs[0]['group_'];
                if($grd_group != $group){
                    $this->candidate->deleteCandidateProgramOfStudy($candidate_id);
                    $this->candidate->deleteAppliedCategory($candidate_id);
                    $this->candidate->deleteAppliedCampus($candidate_id);

                }
            }
                //-------------------end delete master -----------------------//


            if($ssc_obtain >= $ssc_total) {
                $this->tab_education($candidate_id, "SSC/Matriculation Obtained Marks should be lesser than Total Marks", $ssc_bean, $hsc_bean, $grd_bean, $candidate_bean, $grd_sub_one, $grd_sub_two, $grd_sub_three);
           } else if($hsc_year-$ssc_year < 2){
                    $this->tab_education($candidate_id,"HSC/Intermediate Year Invalid",$ssc_bean,$hsc_bean,$grd_bean,$candidate_bean,$grd_sub_one,$grd_sub_two,$grd_sub_three);
            }else if($hsc_obtain >= $hsc_total){
                $this->tab_education($candidate_id,"HSC/Intermediate Obtained Marks should be lesser than the Total Marks",$ssc_bean,$hsc_bean,$grd_bean,$candidate_bean,$grd_sub_one,$grd_sub_two,$grd_sub_three);
            }else if(  ($candidate_bean->getProgramTypeId() == Variable::MASTER_ID() && (  ($grd_bean->getObtainMarks()/$grd_bean->getTotalMarks())*100) < 45 )  ){
                $this->tab_education($candidate_id,"You are not eligible for admission due to 3rd class in your graduation degree",$ssc_bean,$hsc_bean,$grd_bean,$candidate_bean,$grd_sub_one,$grd_sub_two,$grd_sub_three);
            }else if(  ($candidate_bean->getProgramTypeId() != Variable::MASTER_ID() && (  ($hsc_bean->getObtainMarks()/$hsc_bean->getTotalMarks())*100) < 45 )  ){
                $this->tab_education($candidate_id,"Only those candidate with <b>45% or higher </b> marks  in HSC/Intermediate are eligiblie for apply for the admission. ",$ssc_bean,$hsc_bean,$grd_bean,$candidate_bean,$grd_sub_one,$grd_sub_two,$grd_sub_three);
            }else if(  ($candidate_bean->getProgramTypeId() == Variable::MASTER_ID() && (  $grd_bean->getObtainMarks()>=$grd_bean->getTotalMarks()  )  )){
                $this->tab_education($candidate_id,"Bachelor/Graduation <b>Obtain Marks</b> cannot be equal to or greater than Total marks",$ssc_bean,$hsc_bean,$grd_bean,$candidate_bean,$grd_sub_one,$grd_sub_two,$grd_sub_three);
            }else{
                $this->candidate->deleteOptionalSubject($candidate_id);

                $this->candidate->deleteCredentialDetails($candidate_id, Variable::SSC_LEVEL());
                $this->candidate->deleteCredentialDetails($candidate_id, Variable::HSC_LEVEL());
                $this->candidate->deleteCredentialDetails($candidate_id, Variable::GRD_LEVEL());


                $this->candidate->saveCredential(Variable::SSC_LEVEL(),$candidate_id,$ssc_issuer,$ssc_total,$ssc_obtain,$ssc_seat_no,$ssc_year,$ssc_group);
                $this->candidate->saveCredential(Variable::HSC_LEVEL(),$candidate_id,$hsc_issuer,$hsc_total,$hsc_obtain,$hsc_seat_no,$hsc_year,$hsc_group);
                // update Bachelor Deduction Marks
                if($candidate_bean->getProgramTypeId() == Variable::BACHELOR_ID()) {
                    //  $deductionMarks= DeductionCalculation::forBachalor(Variable::$CURRENT_YEAR,$ssc_year);
                   // $this->candidate->updateDeductionMarks($candidate_id,$deductionMarks);

                }
                    if($candidate_bean->getProgramTypeId() == Variable::MASTER_ID()){
                    $this->candidate->saveCredential(Variable::GRD_LEVEL(),$candidate_id,$grd_issuer,$grd_total,$grd_obtain,$grd_seat_no,$grd_year,$grd_group);
                    $this->candidate->updateDegreeYears($candidate_id,$this->input->post(Variable::$GRD_DEGREE_YEARS));

                  //  $deductionMarks= DeductionCalculation::forMasterUSindh(Variable::$CURRENT_YEAR,$ssc_year,$this->input->post(Variable::$GRD_DEGREE_YEARS));
                  //  $this->candidate->updateDeductionMarks($candidate_id,$deductionMarks);

                    if($this->input->post(Variable::GRD_SUB_ONE())){
                        $subject_id_one = $this->input->post(Variable::GRD_SUB_ONE());
                        $this->candidate->addOptionalSubject($candidate_id,$subject_id_one);

                        $subject_id_two = $this->input->post(Variable::GRD_SUB_TWO());
                        $this->candidate->addOptionalSubject($candidate_id,$subject_id_two);

                        $subject_id_three = $this->input->post(Variable::GRD_SUB_THREE());
                        $this->candidate->addOptionalSubject($candidate_id,$subject_id_three);

                        //do check and delete choices if pre-requisite is not matched
                     //  $this->deleteChoicesAccordingToEducationalRecord($candidate_id, Variable::$MASTER_ID , 0 , $grd_group, ("$subject_id_one,$subject_id_two,$subject_id_three") );
                        $this->candidate->deleteCandidateProgramOfStudyByFilter($candidate_id, Variable::$MASTER_ID , $grd_group, ("$subject_id_one,$subject_id_two,$subject_id_three") );

                    }else{
                        // no optional subjects available for selected degree

                        //do check and delete choices if pre-requisite is not matched
//                        $this->deleteChoicesAccordingToEducationalRecord($candidate_id, Variable::$MASTER_ID , 0 , $grd_group );
                       // $this->candidate->deleteCandidateProgramOfStudyByFilter($candidate_id, Variable::$MASTER_ID , $grd_group );
                    }

                }else{
                    //do check and delete choices if pre-requisite is not matched
//                    $this->deleteChoicesAccordingToEducationalRecord($candidate_id, Variable::$BACHELOR_ID , 0 , $hsc_group );

                //    $this->candidate->deleteCandidateProgramOfStudy($candidate_id);

                    //if($morning_choice_array){
                   //     for($i=0;$i<count($morning_choice_array);$i++) {
                        //    $this->candidate->addCandidateProgramOfStudy($candidate_id, $morning_choice_array[$i], "", ($i+1) );
                       // }
                    //}
                   // $this->candidate->deleteCandidateProgramOfStudyByFilter($candidate_id, Variable::$BACHELOR_ID , $hsc_group );
                  //print_r($a);
                }


                $this->tab_apply_for($candidate_id, $candidate_bean);
            }

        }else{
            $this->goto_login($candidate_id);
        }

    }



    protected function prepare_credentials_by_name($result){
        $crd_bean = new Credentials_Details;
        if($result){
            $crd_bean->setIssuerName($result[0]['issuer_name']);
            $crd_bean->setObtainMarks($result[0]['marks_obtained']);
            $crd_bean->setPassingYear($result[0]['passing_year']);
            $crd_bean->setGroupName($result[0]['group_name']);
            $crd_bean->setSeatNo($result[0]['seat_no']);
            $crd_bean->setTotalMarks($result[0]['total_marks']);
        }
        return $crd_bean;
    }

    protected function prepare_credentials_bean($issuer,$obtain,$passing_year,$program,$seat_no,$total){
        $this->load->model("credentials_details");
        $crd_bean = new Credentials_Details;
            $crd_bean->setIssuerId($issuer);
            $crd_bean->setObtainMarks($obtain);
            $crd_bean->setPassingYear($passing_year);
            $crd_bean->setProgramId($program);
            $crd_bean->setSeatNo($seat_no);
            $crd_bean->setTotalMarks($total);
        return $crd_bean;
    }


    public function ajax_optional_subject_handler(){
        if($this->input->post('programId')){
            $programId = $this->input->post('programId');
            $this->load->model("modelAdmission");
            $arr = $this->modelAdmission->getOptionalSubject($programId);
            //echo json_encode($arr);
            $data = array();
            for ($index = 0; $index < count($arr); $index++) {
                $bean = $arr[$index];
                $data[$index] = array("id"=>$bean['subject_id'] , "name"=>$bean['name']);
            }
            echo json_encode($data);
        }
    }


    public function ajax_District_handler(){
        if($this->input->post('provanceName')){
            $provanceName = $this->input->post('provanceName');
            $this->load->model("modelAdmission");
            $arr = $this->modelAdmission->getDistrictByProvince($provanceName);
            //echo json_encode($arr);

            $data = array();
            for ($index = 0; $index < count($arr); $index++) {
                $bean = $arr[$index];
                $data[$index] = array("id"=>$bean['district_id'] , "name"=>$bean['name']);

            }
            echo json_encode($data);
        }
    }



    public function get_image(){
        $candidateId = 19;
        $this->load->model("candidate");
        $result = $this->candidate->getImage($candidateId);
        $this->output->set_header("Content-type:".$result['content_type']);
        echo $result['pic'];
    }

    private function tab_apply_for($candidateId,$candidateBean = null){
        $this->load->model("modelAdmission");
        $this->load->model("candidate");
        if($candidateBean == null){
            $candidateBean =  $this->candidate->getCandidateInfo($candidateId);
        }
        $data = array();
        $data['message'] = "";
        $data['candidate_id'] = $candidateId;
        $data['campus'] = $this->modelAdmission->getCampus();

        $catIds = array();
        $cats = $this->candidate->getAppliedCategory($candidateId);
        for($i=0;$i<count($cats);$i++){
            $catIds[$i] = $cats[$i]['code'];
            //$catIds[$i] = $cats[$i]['code'];
        }
        $data['applied_category'] = $catIds;

        $applied_campus = array();
        $applied_campus_result_set = $this->candidate->getAppliedCampus($candidateId);

        for($i=0;$i<count($applied_campus_result_set);$i++){
            $applied_campus[$i] = $applied_campus_result_set[$i]['campus_id'];
        }
        $data['applied_campus'] = $applied_campus;

        $applied_optional_campus_result_set = $this->candidate->getAppliedCampus($candidateId,true);
        if(count($applied_optional_campus_result_set)>0){
            $data['applied_optional_campus'] = $applied_optional_campus_result_set[0]['campus_id'];
        }else{
            $data['applied_optional_campus'] = 0;
        }


        if(count($applied_campus)>0){
            //getting MORNING selected choices of candidate
            $morning_cnpos_ids = array();
            $morning_cnpos_result_set = $this->candidate->getCandidateProgramOfStudyByName($candidateId,Variable::MORNING_SHIFT());
            for($i=0;$i<count($morning_cnpos_result_set);$i++){
                $morning_cnpos_ids[$i] = $morning_cnpos_result_set[$i]['campus_program_of_study_id'];
            }
            $data['morning_cnpos_ids'] = $morning_cnpos_ids;
	        $data['morning_cnpos_rs'] = $morning_cnpos_result_set;

            $test_var = "hello";

            //getting EVENING selected choices of candidate
            $evening_cnpos_ids = array();
            $evening_cnpos_result_set = $this->candidate->getCandidateProgramOfStudyByName($candidateId,Variable::EVENING_SHIFT());
            for($i=0;$i<count($evening_cnpos_result_set);$i++){
                $evening_cnpos_ids[$i] = $evening_cnpos_result_set[$i]['campus_program_of_study_id'];
            }
            $data['evening_cnpos_ids'] = $evening_cnpos_ids;
	    $data['evening_cnpos_rs'] = $evening_cnpos_result_set;

            $data['campus_progs_morning'] = $this->modelAdmission->getCampusProgramOfStudy($candidateBean->getProgramTypeId() , Variable::MORNING_SHIFT(), $applied_campus[0]);
            $data['campus_progs_evening'] = $this->modelAdmission->getCampusProgramOfStudy($candidateBean->getProgramTypeId() , Variable::EVENING_SHIFT(), $applied_campus[0]);

            $this->load->model("credentials_details");
            $last_academic_program_id = 0;
            if($candidateBean->getProgramTypeId() == Variable::MASTER_ID()){
                $bean = $this->credentials_details->getCredentialDetails($candidateId, Variable::$GRD_LEVEL);
                $last_academic_program_id = $bean[0]['program_id'];
            }else{
                $bean = $this->credentials_details->getCredentialDetails($candidateId, Variable::$HSC_LEVEL);
                $last_academic_program_id = $bean[0]['program_id'];
            }

            $optional_subject_rs = $this->candidate->getOptionalSubjectIds($candidateId);

            $cmpos_morning_filteration_ids = array();
            $cmpos_evening_filteration_ids = array();
            $cmpos_with_program_filteration_morning = array();
            $cmpos_with_program_filteration_evening = array();
            $hyd_cmpos_with_program_filteration_morning = array();
            $hyd_cmpos_with_program_filteration_evening = array();
            if(count($optional_subject_rs) > 0){
                $os_array = array();
                for($loop = 0 ;$loop < count($optional_subject_rs);$loop++){
                    $os_array[$loop] = $optional_subject_rs[$loop]['subject_id'];
                }
                $filter = implode(",",$os_array);

                $cmpos_with_program_filteration_morning = $this->modelAdmission->getCampusProgramOfStudy($candidateBean->getProgramTypeId() , Variable::MORNING_SHIFT(), $applied_campus[0],$last_academic_program_id);
                $cmpos_with_program_filteration_evening = $this->modelAdmission->getCampusProgramOfStudy($candidateBean->getProgramTypeId() , Variable::EVENING_SHIFT(), $applied_campus[0],$last_academic_program_id);
            }else{
                $cmpos_with_program_filteration_morning = $this->modelAdmission->getCampusProgramOfStudy($candidateBean->getProgramTypeId() , Variable::MORNING_SHIFT(), $applied_campus[0],$last_academic_program_id);
                $cmpos_with_program_filteration_evening = $this->modelAdmission->getCampusProgramOfStudy($candidateBean->getProgramTypeId() , Variable::EVENING_SHIFT(), $applied_campus[0],$last_academic_program_id);
            }
            if(count($hyd_cmpos_with_program_filteration_morning)>0){
                $cmpos_with_program_filteration_morning = array_merge($cmpos_with_program_filteration_morning,$hyd_cmpos_with_program_filteration_morning);
            }
            if(count($hyd_cmpos_with_program_filteration_evening)>0){
                $cmpos_with_program_filteration_evening = array_merge($cmpos_with_program_filteration_evening,$hyd_cmpos_with_program_filteration_evening);
            }
            for($i=0;$i<count($cmpos_with_program_filteration_morning);$i++){
                $cmpos_morning_filteration_ids[$i] = $cmpos_with_program_filteration_morning[$i]['campus_program_of_study_id'];
            }
            for($i=0;$i<count($cmpos_with_program_filteration_evening);$i++){
                $cmpos_evening_filteration_ids[$i] = $cmpos_with_program_filteration_evening[$i]['campus_program_of_study_id'];
            }

            $data['cmpos_morning_filteration_ids'] = $cmpos_morning_filteration_ids;
            $data['cmpos_evening_filteration_ids'] = $cmpos_evening_filteration_ids;

        }
        $data['candidate_bean'] = $candidateBean;




        $bar_data = array("is_logged_in"=>true);
        $this->load->view("bar",$bar_data);
        $this->load->view("tab_apply_for",$data);
        $this->load->view("footerbar");
    }

    public function ajax_choices_handler(){
        $candidate_id = $this->is_logged_in();

        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
            $candidate_id = $this->session->userdata("candidateId");
          //  $candidate_id=$this->getCornNo();
        }

        if($this->check($candidate_id,$isSuperUser)){
            if($this->input->post('campusId') &&  $this->input->post('shiftId')){
                $this->load->model("candidate");
                $bean = $this->candidate->getCandidateInfo($candidate_id);
                $campus_id = $this->input->post('campusId');
                $shift_id = $this->input->post('shiftId');
                $this->load->model("modelAdmission");
                $this->load->model("credentials_details");
                $data = array();
                $allProgs = array();
                $filterProgs = array();
                $result_set_all_programs = array();
                $result_set_filter = array();
                if($bean->getProgramTypeId() == Variable::MASTER_ID()){
                    $os_ids = $this->candidate->getOptionalSubjectIds($candidate_id);
                    $rs = $this->credentials_details->getCredentialDetails($candidate_id, Variable::GRD_LEVEL());
                    if(count($os_ids)>0){
                        $custom_array = array();
                        for($i=0;$i<count($os_ids);$i++){
                            $custom_array[$i] = $os_ids[$i]['subject_id'];
                        }
                        $filter = implode(",",$custom_array);
                        $result_set_filter = $this->modelAdmission->getCampusProgramOfStudy($bean->getProgramTypeId() , $shift_id, $campus_id,$rs[0]['program_id']);
                    }else{
                        $result_set_filter = $this->modelAdmission->getCampusProgramOfStudy($bean->getProgramTypeId() , $shift_id, $campus_id,$rs[0]['program_id']);
                    }
                }else{
                    $rs = $this->credentials_details->getCredentialDetails($candidate_id, Variable::HSC_LEVEL());
                    $result_set_filter = $this->modelAdmission->getCampusProgramOfStudy($bean->getProgramTypeId() , $shift_id, $campus_id,$rs[0]['program_id']);
                }

                $result_set_all_programs = $this->modelAdmission->getCampusProgramOfStudy($bean->getProgramTypeId() , $shift_id, $campus_id);

                for ($index = 0; $index < count($result_set_filter); $index++) {
                    $bean = $result_set_filter[$index];
//                    $filterProgs[$index] = array("id"=>$bean['campus_program_of_study_id'] , "name"=> ($bean['Name']." (".$bean['programName'].")") );
                    $filterProgs[$index] = $bean['campus_program_of_study_id'];
                }

                for ($index = 0; $index < count($result_set_all_programs); $index++) {
                    $bean = $result_set_all_programs[$index];
                    if( in_array( $bean['campus_program_of_study_id'], $filterProgs ) ){
                        $allProgs[$index] = array("id"=>$bean['campus_program_of_study_id'] , "name"=> ($bean['Name']." (".$bean['programName'].")") , "is_okay"=>true );
                    }else{
                        $allProgs[$index] = array("id"=>$bean['campus_program_of_study_id'] , "name"=> ($bean['Name']." (".$bean['programName'].")") , "is_okay"=>false);
                    }

                }

//                $data['filter_progs'] = $filterProgs;
                $data['all_progs'] = $allProgs;
                echo json_encode($data);
            }
        }

    }

    public function deleteCandidateProgramOfStudy(){

        $candidate_id = $this->input->post("candidate_id");
        $this->candidate->deleteAppliedCampus($candidate_id);
        $this->candidate->deleteCandidateProgramOfStudy($candidate_id);
        $this->candidate->deleteAppliedCategory($candidate_id);
    }


    public function tab_apply_for_save(){
        $candidate_id = $this->is_logged_in();
        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
            $candidate_id = $this->session->userdata("candidateId");
           // $candidate_id=$this->getCornNo();
        }

        if($this->check($candidate_id,$isSuperUser)){
            if( $this->input->post(Variable::CAMPUS()) ){
                $campus_id = $this->input->post(Variable::CAMPUS());
                $morning_choice_array = $this->input->post(Variable::$MORNING_CHOICE_NAME);
                $evening_choice_array = $this->input->post(Variable::$EVENING_CHOICE_NAME);

                $this->load->model("candidate");

                // adding the applied campus of student
                $this->candidate->deleteAppliedCampus($candidate_id);
                $this->candidate->addAppliedCampus($candidate_id, $campus_id);
                $this->candidate->updateCandidatePrintFormDate($candidate_id);

                if($this->input->post(Variable::CAMPUS_OPTIONAL())){
                    $optional_campus_id = $this->input->post(Variable::CAMPUS_OPTIONAL());
                    $this->candidate->addAppliedCampus($candidate_id, $optional_campus_id,true);
                }

                //adding / updating the applied categories
                $this->candidate->deleteAppliedCategory($candidate_id);
                $this->candidate->addAppliedCategory($candidate_id,Variable::$CategoryEnumCode[0],"",0);
                if($this->input->post(Variable::$CATEGORY_NAME)){
                    $cats_array = $this->input->post(Variable::$CATEGORY_NAME);
                    for($i=0;$i<count($cats_array);$i++){
                        $this->candidate->addAppliedCategory($candidate_id,Variable::$CategoryEnumCode[$cats_array[$i]],"",$cats_array[$i]);
                    }
                }

                //deleting and adding new selected choices
                $this->candidate->deleteCandidateProgramOfStudy($candidate_id);
                if($morning_choice_array){
                    for($i=0;$i<count($morning_choice_array);$i++) {
                        $this->candidate->addCandidateProgramOfStudy($candidate_id, $morning_choice_array[$i], "", ($i+1) );
                    }
                }

                if($evening_choice_array){
                    for($i=0;$i<count($evening_choice_array);$i++) {
                      $this->candidate->addCandidateProgramOfStudy($candidate_id, $evening_choice_array[$i], "", ($i+1) );
                    }
                }
                $this->show_all_info();
            }
        }else{
            $this->goto_login($candidate_id);
        }
    }

    public function show_all_info(){
        $candidate_id = $this->is_logged_in();
        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
            $candidate_id = $this->session->userdata("candidateId");
           // $candidate_id=$this->getCornNo();
        }

        if($this->check($candidate_id,$isSuperUser)) {
            $this->goto_show_info_page($candidate_id);
        }else{
            $this->goto_login($candidate_id);
        }
    }


    public function submit_form(){
        $candidate_id = $this->is_logged_in();
        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
            $candidate_id = $this->session->userdata("candidateId");
           // $candidate_id=$this->getCornNo();
        }

        if($this->check($candidate_id,$isSuperUser)) {
            $this->load->model("candidate");
            $this->candidate->lockCandidate($candidate_id);
            $this->goto_show_info_page($candidate_id,true);
        }else{
            $this->goto_login($candidate_id);
        }
    }

    public function print_form(){
        $candidate_id = $this->is_logged_in();
        $isSuperUser = $this->session->userdata("isSuperUser");
        if($isSuperUser=="superUser") {
           $candidate_id = $this->session->userdata("candidateId");
          //  $candidate_id=$this->getCornNo();
        }

        if($this->check($candidate_id,$isSuperUser)) {
            $data =array();
            $this->load->model("candidate");
            $this->load->model("credentials_details");
           // $this->candidate->updateCandidatePrintFormDate($candidate_id);

            $this->load->model("modeladmission");


            $data['candidateId'] = $candidate_id;
            $data['candidateImage'] = base_url("uploads/".($candidate_id)).".jpg";
            $data['bean'] =           $this->candidate->getCandidateFullInfo($candidate_id);
            $data['campusList'] =     $this->candidate->getAppliedCampus($candidate_id);
            $data['appliedCats'] =    $this->candidate->getAppliedCategory($candidate_id);
            $data['sscInfo'] =        $this->credentials_details->getCredentialDetailsByName($candidate_id, Variable::SSC_LEVEL());
            $data['hscInfo'] =        $this->credentials_details->getCredentialDetailsByName($candidate_id, Variable::HSC_LEVEL());
            $data['morningChoices'] = $this->candidate->getCandidateProgramOfStudyByName($candidate_id, Variable::MORNING_SHIFT());
            $data['eveningChoices'] = $this->candidate->getCandidateProgramOfStudyByName($candidate_id, Variable::EVENING_SHIFT());

            $data['bankName']=  $this->modeladmission->getBankBranchNameById($data['bean']->getBankBranch());
            $data['grdInfo'] = null;
            $data['optionals'] = array();
            if($data['bean']->getProgramTypeId() == Variable::$MASTER_ID){
                $data['grdInfo'] =        $this->credentials_details->getCredentialDetailsByName($candidate_id, Variable::GRD_LEVEL());
                $data['optionals'] = $this->candidate->getOptionalSubjectBeans($candidate_id);
            }



            $this->load->library("fpdf/cellpdf");
            $this->load->view("app_form_report");
            $this->load->view("print_form",$data);

            if($isSuperUser!="superUser") {
                $this->logout();
            }

        }else{
            $this->goto_login($candidate_id);
        }

    }

    private  $cornNo;
    public  function  setCornNo($cornNo){
        $this->cornNo=$cornNo;
    }
    public function getCornNo(){
        return $this->cornNo;

    }


}

