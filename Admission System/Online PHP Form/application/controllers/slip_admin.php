<?php
/**
 * Created by PhpStorm.
 * User: Jay
 * Date: 05-Sep-15
 * Time: 1:10 PM
 */
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
require_once('app_form.php');

class Slip_Admin extends CI_Controller
{

    public function __construct(){
        parent::__construct();
        $this->load->helper('url');
        $this->load->model("online_slip_issuer");
    }

    public function index(){
        $this-> goto_admin_login();
    }

    private function goto_admin_login($message = ""){
        $bar_data = array("is_logged_in"=>false);
        $this->load->view("bar",$bar_data);
        $data = array();
        $data["title"] = "Admission Branch Login";
        $data[Variable::Message()] = $message;
        $data["request_submit"] = "slip_admin/show_admin_panel";
        $this->load->view("login",$data);
        $this->load->view('footerbar');
    }


    public function show_admin_panel($msg = ""){
        $is_logged_in = $this->authenticate();
        if($is_logged_in==0){
            $bar_data = array();
            $bar_data["is_logged_in"] = true;
            $bar_data["request_submit"] = "slip_form/submit_candidate_no";
            $data = array();
            $data["is_data"] = false;
            $this->load->view("bar",$bar_data);
            $this->load->view("tab_admin",$data);
            $this->load->view('footerbar');
        }

        if($is_logged_in==1){
            $bar_data = array();
            $bar_data["is_logged_in"] = true;
            $this->load->model("modelAdmission");

            $data = array();
            $data['year'] = $this->modelAdmission->getAdmissionYear();
            $data["is_data"] = false;
            $this->load->view("bar",$bar_data);
            $this->load->view("search_seat_no",$data);

        }
    }

    public function searchStudentBySeatNo(){
        $this->session->unset_userdata("candidateId");

        $is_logged_in = $this->authenticate();

        if($is_logged_in==1){
            $programType = $this->input->post(Variable::PROGRAM_TYPE());
            $seatNo = $this->input->post("seat_no");
            $radioButton = $this->input->post("radioSeat");
            $yearId = $this->input->post("admissionYear");
            $this->load->model("candidate");

            $candidateId=0;

            if($radioButton=="seatNo"){
                $candidateId = $this->candidate->searchCandidateBySeatNo($seatNo,$programType,$yearId);
            }else{
                $candidateId=$seatNo;
            }

            $isSuperUser="superUser";

            $appForm = new App_Form();
         //   $appForm->tab_personal($candidateId);

            $candidateId = array("candidateId" => $candidateId, "isSuperUser"=>$isSuperUser);
            $appForm->setCornNo($candidateId);

            $candidateId= $this->session->set_userdata($candidateId);

            $appForm->goto_personal_info_tab();
           // $s_username = $appForm->session->userdata(Variable::USERNAME());
           // $s_password = $this->session->userdata(Variable::PASSWORD());


            $appForm->is_logged_in();
        }
    }

    public function search_candidate(){
        $is_logged_in = $this->authenticate();
        if($is_logged_in==0){
            if($this->input->post("corn")){
            $candidate_id = $this->input->post("corn");
            $this->load->model("candidate");
                $this->load->model("credentials_details");

                $candidate_bean = $this->candidate->getCandidateInfo($candidate_id);
               if($candidate_bean->getProgramTypeId()==2) {
                   $data["master_group"] = $this->candidate->getMasterGroup($candidate_bean->getGender());
               }

                // ----------Formula for assign master seat no by group wise-------------------------//
                $subject_program = $this->candidate->getOptionalSubjectAndProgramName($candidate_id);
                $countOptionalSubject = count($subject_program);


                if($countOptionalSubject>0) {
                    $faculty_id=$subject_program[0]['faculty_id'];

                //    echo($faculty_id);

                    // for commerece  group
                    if($faculty_id==2){
                        if($candidate_bean->getGender() == Variable::$MALE){$this->candidate->setGroupId(10);}
                        if($candidate_bean->getGender() == Variable::$FEMALE){$this->candidate->setGroupId(9);}
                    }

                //    echo($this->candidate->getGroupId());
                    // for arts group
                    if($faculty_id==1  ||$faculty_id==3 || $faculty_id==4  || $faculty_id==5 || $faculty_id==8  || $faculty_id==9){
                        if($candidate_bean->getGender() == Variable::$MALE){$this->candidate->setGroupId(2);}
                        if($candidate_bean->getGender() == Variable::$FEMALE){$this->candidate->setGroupId(1);}

                    }

                    // NATURAL SCIENCE
                    if($faculty_id==6){

                    $credential_rs = $this->credentials_details->getCredentialDetails($candidate_id, Variable::$HSC_LEVEL);
                    $group= $credential_rs[0]['group_'];



                        //Medical Group group
                        if($group==52){

                            if($candidate_bean->getGender() == Variable::$MALE){$this->candidate->setGroupId(4);}
                            if($candidate_bean->getGender() == Variable::$FEMALE){$this->candidate->setGroupId(3);}


                        }
                        //Engineering Group

                        if($group==53){
                            if($candidate_bean->getGender() == Variable::$MALE){$this->candidate->setGroupId(6);}
                            if($candidate_bean->getGender() == Variable::$FEMALE){$this->candidate->setGroupId(5);}

                        }



                        $discipline_id=$subject_program[0]['discipline_id'];

                        if($discipline_id==12){
                            $discipline_id=$subject_program[1]['discipline_id'];
                        }

                        //  computer science group
                        if($discipline_id==1 || $discipline_id==3 || $discipline_id==4 ){

                            if($candidate_bean->getGender() == Variable::$MALE){$this->candidate->setGroupId(8);}
                            if($candidate_bean->getGender() == Variable::$FEMALE){$this->candidate->setGroupId(7);}

                        }




                    }

                    if($faculty_id==7){
                        if($candidate_bean->getGender() == Variable::$MALE){$this->candidate->setGroupId(4);}
                        if($candidate_bean->getGender() == Variable::$FEMALE){$this->candidate->setGroupId(3);}

                    }

                    // ----------End Formula for assign master seat no by group wise-------------------------//


                }





                if($candidate_bean){
                $data["is_data"] = true;
                $data["candidate"] = $candidate_bean;
                $data["corn"] = $candidate_id;
            }else{
                $data["is_data"] = false;
            }
            $bar_data["is_logged_in"] = true;
            $bar_data["request_submit"] = "slip_form/submit_candidate_no";
            $this->load->view("bar",$bar_data);
            $this->load->view("tab_admin",$data);
            $this->load->view('footerbar');
            }else {
                //$this->goto_admin_login("you are not  login");
            }
        }
    }

    /**
     *
     */
    public function print_candidate(){
    $is_logged_in = $this->authenticate();
    if($is_logged_in==0){
        //lock candidate
        $this->load->model("candidate");
        $this->load->model("credentials_details");
        $this->load->library("fpdf/cellpdf");
       // $this->load->library("fpdf/rotate");
       // $this->load->library("fpdf/PDF_Javascript");

        $this->load->view("slip_report");
        $candidate_id = $this->input->post("corn");


        $candidate_bean = $this->candidate->getCandidateFullInfo($candidate_id);

        if($candidate_bean->getFormSNo()==0){
            $form_no = $this->input->post("formNo");
            $this->candidate->updateFormNo($candidate_id,$form_no);
        }


        //$credential_rs = $this->credentials_details->getCredentialDetails($candidate_id, Variable::$GRD_LEVEL);
        //$program_id= $credential_rs[0]['group_'];

        $campus_rs = $this->candidate->getAppliedCampus($candidate_id);
        $group_name="";

        if(!$this->candidate->isLocked($candidate_id)){
            $seat_no="";


            if($candidate_bean->getProgramTypeId()==2 && $campus_rs[0]['campus_id'] == Variable::JAMSHORO_CAMPUS()){


                $group = $this->input->post("group");

                //$seat_no = $this->input->post("seatNo");
                $max_sno=$this->candidate->getMaxMasterGroup($group);
                //echo($max_sno);
                $seat_no=$max_sno+1;
                $this->candidate->addMasterSeatNo($group,$seat_no,$candidate_id);

                $this->candidate->updateSeatNo($candidate_id,$seat_no);



            }

            // MASTER OTHER CAMPUSES ROLL NUMBER
            if($candidate_bean->getProgramTypeId()==2 && $campus_rs[0]['campus_id'] != Variable::JAMSHORO_CAMPUS()){

                $seat_no = $this->candidate->assignSeatNo($candidate_id, $candidate_bean->getGender(), $candidate_bean->getProgramTypeId(), $campus_rs[0]['campus_id']);

            }

            if($candidate_bean->getProgramTypeId()==1) {

                $seat_no = $this->candidate->assignSeatNo($candidate_id, $candidate_bean->getGender(), $candidate_bean->getProgramTypeId(), $campus_rs[0]['campus_id']);

            }
            if(!$seat_no){
                echo "Some Error Occured Please Retry";
            }else{
                $candidate_bean->setSeatNo($seat_no);
                $this->candidate->lockCandidate($candidate_id);
            }
        }
        if($candidate_bean->getProgramTypeId()==2 && $campus_rs[0]['campus_id'] == Variable::JAMSHORO_CAMPUS()){
            $group_name=$this->candidate->getMasterGroupById($this->input->post("group"));
            //$subject_program=array();


        }


        $rep = new Slip_Report($candidate_id,(base_url("uploads/".($candidate_id)).".jpg"));
        $rep->printReport($candidate_bean,$campus_rs,$group_name);
    }
}






    public function unlock_candidate_form(){
        $is_logged_in = $this->authenticate();
        if($is_logged_in==0){
            $this->load->model("candidate");
            $candidate_id = $this->input->post("corn");
            $this->candidate->unLockCandidate($candidate_id);
            $data =array();
            $data['msg']="sucessfuly unlocked";


        }
    }

    public function print_candidate_form(){
        $is_logged_in = $this->authenticate();
        if($is_logged_in==0){
            //lock candidate
            $this->load->model("candidate");
            $this->load->model("credentials_details");
            $this->load->library("fpdf/cellpdf");

            $this->load->view("slip_report");
            $candidate_id = $this->input->post("corn");

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

        }
    }

    private function authenticate(){
        if($this->session->userdata(Variable::USERNAME()) &&  $this->session->userdata(Variable::PASSWORD())){
            $s_username = $this->session->userdata(Variable::USERNAME());
            $s_password = $this->session->userdata(Variable::PASSWORD());
            $candidate = $this->online_slip_issuer->authenticateUser($s_username , $s_password);
            if($candidate){
                //return true;
                return $candidate[0]['is_super'];
            }else{
                return false;
            }
        }else if( $this->input->post(Variable::USERNAME()) && $this->input->post(Variable::PASSWORD()) ){
            $this->recaptcha->recaptcha_check_answer();
            if($this->recaptcha->getIsValid()){
                $d_u = $this->input->post(Variable::USERNAME());
                $d_p = $this->input->post(Variable::PASSWORD());
                $this->load->library("my_cryptor");

                $en_username = My_Cryptor::myEncryption($d_u);
                $en_password = My_Cryptor::myEncryption($d_p);
                $candidate = $this->online_slip_issuer->authenticateUser($en_username , $en_password);
                if($candidate){
                    $loginData = array(
                        Variable::USERNAME()  => $en_username,
                        Variable::PASSWORD()  => $en_password,
                        Variable::USER_TYPE() => Variable::USER_SLIP_ISSUER()
                    );
                    $this->session->set_userdata($loginData);
                    //return true;
                    return $candidate[0]['is_super'];
                }else{
                    $this->goto_admin_login("Username or Password is Invalid");
                    return false;
                }
            }else{
                $this->goto_admin_login("Please Verify You are not robot");
            }
        }
        else{
            $this->goto_admin_login();
            return false;
        }
    }
    public function logout(){
        $this->session->unset_userdata(array(Variable::USERNAME()=>'',Variable::PASSWORD(),"isSuperUser","candidateId"));
        $this->goto_admin_login();
    }

}
?>