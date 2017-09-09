<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class My_Controller extends CI_Controller{

    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
    }

    public function is_logged_in()
    {

        if ($this->session->userdata(Variable::USERNAME()) && $this->session->userdata(Variable::PASSWORD())) {
            $isSuperUser = $this->session->userdata("isSuperUser");

            if($isSuperUser=="superUser") {
                $candidate_id = $this->session->userdata("candidateId");
                //echo("cc".$candidate_id);
                return $candidate_id;
            }

            $this->load->model("users");
            $s_username = $this->session->userdata(Variable::USERNAME());
            $s_password = $this->session->userdata(Variable::PASSWORD());
            $candidate = $this->users->authenticateUser($s_username, $s_password);
           // echo("ssss".$candidate);
            return $candidate;


        }  else{

            return -1;
        }
    }

    public function check($id,$isSuperUser=""){
        if($id == -1) {
            return false;

        }else if($isSuperUser=="superUser"){
            return true;
            }else {
            $this->load->model("candidate");
            if($this->candidate->isLocked($id)){
                return false;
            }else{
                return true;
            }
        }

    }

    public function check_valid_id($id){
        if($id >0){
            return true;
        }
        else{
            return false;
        }
    }

    public function goto_login($candidateId = -1,$msg = ""){
        if($this->check_valid_id($candidateId)){
            $this->goto_show_info_page($candidateId,true);
        }else{
            $this->login_form($msg);
        }
    }

    protected function prepare_credentials($result){
        $crd_bean = new Credentials_Details;
        if($result){
            $crd_bean->setIssuerId($result[0]['issuer_id']);
            $crd_bean->setObtainMarks($result[0]['marks_obtained']);
            $crd_bean->setPassingYear($result[0]['passing_year']);
            $crd_bean->setProgramId($result[0]['program_id']);
            $crd_bean->setSeatNo($result[0]['seat_no']);
            $crd_bean->setTotalMarks($result[0]['total_marks']);
        }
        return $crd_bean;
    }

    public function login_form($message= ""){
        $bar_data = array("is_logged_in"=>false);
        $this->load->view("bar",$bar_data);
        $data = array();
        $data["title"] = "Candidate Admission Application Form";
        $data[Variable::Message()] = $message;
        $data["request_submit"] = "app_form/login_process";
        $this->load->view("login",$data);
        $this->load->view('footerbar');
    }

    protected function goto_show_info_page($candidateId, $isLocked = false){
        $data = array();
        $this->load->model("candidate");
        $this->load->model("credentials_details");

        $data["candidate_id"] = $candidateId;
        $data["isLocked"] = $isLocked;
        $data["admin"] = $this->session->userdata("isSuperUser");
        $data["is_slip_issued"] = ($this->candidate->isLocked($candidateId));
        $data["candidateBean"] = $this->candidate->getCandidateFullInfo($candidateId);
        $ssc_rs = $this->credentials_details->getCredentialDetailsByName($candidateId, Variable::SSC_LEVEL());
        $hsc_rs = $this->credentials_details->getCredentialDetailsByName($candidateId, Variable::HSC_LEVEL());
        $data['cred'] = $this->prepare_credentials_by_name($ssc_rs);
        $data['credHsc'] = $this->prepare_credentials_by_name($hsc_rs);

        if( $data["candidateBean"]->getProgramTypeId() == Variable::$MASTER_ID ){
            $grd_rs = $this->credentials_details->getCredentialDetailsByName($candidateId, Variable::GRD_LEVEL());
            $data['credGrd'] =  $this->prepare_credentials_by_name($grd_rs);

            $data['optionals'] = $this->candidate->getOptionalSubjectBeans($candidateId);
        }

        $data["campusList"] =  $this->candidate->getAppliedCampus($candidateId);
        $data["appliedCats"] =    $this->candidate->getAppliedCategory($candidateId);

        $data["morningChoices"] = $this->candidate->getCandidateProgramOfStudyByName($candidateId, Variable::$MORNING_SHIFT);
        $data["eveningChoices"] = $this->candidate->getCandidateProgramOfStudyByName($candidateId, Variable::$EVENING_SHIFT);

        $bar_data = array("is_logged_in"=>true);
        $this->load->view("bar",$bar_data);
        $this->load->view("show_all_info",$data);
        $this->load->view("footerbar");
    }

}