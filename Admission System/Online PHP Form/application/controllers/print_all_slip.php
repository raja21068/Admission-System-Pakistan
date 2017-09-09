<?php
/**
 * Created by PhpStorm.
 * User: Jay
 * Date: 05-Sep-15
 * Time: 1:10 PM
 */
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
require_once('app_form.php');

class Print_All_Slip extends CI_Controller
{

    public function __construct(){
        parent::__construct();
        $this->load->helper('url');

    }

    public function index(){
       $this->showAllSlipPanel();
    }

    public function showAllSlipPanel(){

        $bar_data = array();
        $bar_data["is_logged_in"] = true;


        $data = array();
        $this->load->model("modelAdmission");
        $data['year'] = $this->modelAdmission->getAdmissionYear();
        //$data["is_data"] = false;
        $this->load->view("bar",$bar_data);
        $this->load->view("print-slips",$data);
    }

    /**
     *
     */
    public function print_candidate(){
            $this->load->model("candidate");
            $this->load->model("credentials_details");
            $this->load->library("fpdf/cellpdf");
            // $this->load->library("fpdf/rotate");
            // $this->load->library("fpdf/PDF_Javascript");

            $this->load->view("Print_all_slips");


            $programType = $this->input->post(Variable::PROGRAM_TYPE());
            $seatNoTo = $this->input->post("seat_no_to");
            $seatNoFrom= $this->input->post("seat_no_from");
            $yearId = $this->input->post("admissionYear");



            $result = $this->candidate->getPrintAllSlips($programType,$seatNoTo,$seatNoFrom,$yearId);
          //  foreach($result as $re){
                //echo($re['candidate_id']."</br>");
          //  }


         //   $group_name = "";


            $rep = new Print_all_slips($result);
            $rep->printReport($result);
    }




}
?>