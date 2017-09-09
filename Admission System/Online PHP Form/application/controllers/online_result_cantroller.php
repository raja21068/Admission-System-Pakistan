<?php
/**
 * Created by PhpStorm.
 * User: RAJA DELL LAPTOP
 * Date: 10/28/2015
 * Time: 1:20 AM
 */
class Online_Result_Cantroller extends CI_Controller{

    public function index()
    {

    }

    public function ajax_candidates_handler(){

        $admListDetailId = $this->input->post('admission_list_detail');
        $campusId = $this->input->post('campus_id');
        $discip = $this->input->post('discipline');
        $cat = $this->input->post('category');
            $this->load->model("Online_Result");
            $arr = $this->Online_Result->getAllCandidates($admListDetailId, $campusId, $discip, $cat);
            //echo json_encode($arr);
            $data = array();
            for ($index = 0; $index < count($arr); $index++) {
                $bean = $arr[$index];
                $data[$index] = array("id"=>$bean['SEAT_NO'] , "name"=>$bean['NAME'], "father_name"=>$bean['FATHER'],"area"=>$bean['AREA'],"district"=>$bean['DISTRICT'],"cpn"=>$bean['CPN']);
            }
            echo json_encode($data);
        }

    public function ajax_candidates_category_handler(){
        $programType = $this->input->post('program_type');
        $seatNo =$this->input->post('seatNo');
        $admListDetailId = $this->input->post('admission_list_detail_id');
        $this->load->model("Online_Result");
        $arr = $this->Online_Result->getCandidate($seatNo, $programType, $admListDetailId);
        //echo json_encode($arr);
        $candidateId = $arr['CANDIDATE_ID'];
        $arr = $this->Online_Result->getCategory($candidateId);
        $data = array();
        for ($index = 0; $index < count($arr); $index++) {
            $bean = $arr[$index];
            $data[$index] = array("category_name"=>$bean['CATEGORY_NAME']);
        }
        echo json_encode($data);
    }


	
	 public function ajax_choice_handler(){
        $programType = $this->input->post('program_type');
        $seatNo =$this->input->post('seatNo');
        $admListDetailId = $this->input->post('admission_list_detail_id');
        $this->load->model("Online_Result");
        $arr = $this->Online_Result->getCandidate($seatNo, $programType, $admListDetailId);
				$candidateId = $arr['CANDIDATE_ID'];
                $campusId = $arr['CAMPUS_ID'];
                $campus = $arr['CAMPUS'];
				$d = $arr['DISCIPLINE'];
				$choiceResult = $this->Online_Result->getChoice($candidateId, $campusId);
		       $data = array();
			for ($index = 0; $index < count($choiceResult); $index++) {
            $bean = $choiceResult[$index];
            $data[$index] = array("shift_id"=>$bean['SHIFT_ID'],"shift_name"=>$bean['SHIFT_NAME'],"discipline"=>$bean['DISCIPLINE'],"choice_no"=>$bean['CHOICE_NO']);
			}
        echo json_encode($data);
    }
	
		public function ajax_discipline_handler(){
        $programType = $this->input->post('program_type');
        $campusId =$this->input->post('campus_id');
        $admListDetailId = $this->input->post('admission_list_detail_id');
        $this->load->model("Online_Result");
        $arr = $this->Online_Result->getDistinctDiscipline($admListDetailId, $campusId,$programType);
			$data = array();
			for ($index = 0; $index < count($arr); $index++) {
            $bean = $arr[$index];
            $data[$index] = array("discipline"=>$bean['DISCIPLINE']);
			}
			echo json_encode($data);
			}



}
