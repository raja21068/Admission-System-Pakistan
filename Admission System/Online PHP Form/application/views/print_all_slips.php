<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
/**
 * Created by PhpStorm.
 * User: Jay
 * Date: 9/2/2015
 * Time: 9:26 PM
 */


class Print_all_slips
{

    var $result;

    public function __construct($result) {
      $this->result=$result;

    }



    public function printReport($result,  $group_name="")
    {

        $pd = new CellPDF('P', 'mm', array(165, 260));

        foreach($result as $bean) {
            //echo($re['candidate_id']."</br>");


            $candidateImage = (base_url("uploads/" . ($bean['candidate_id']) . ".jpg"));

            //$pd = new CellPDF();
           $pd->AddPage();
            $widthCell = $pd->w / 1.6;
            $lmargin = $pd->lMargin;
            $tmargin = $pd->tMargin;
            //$pd->RotatedImage('circle.png',85,60,40,16,45);
            //$pd->Rotate(90);

            $heightCell = 8;
            $left_margin = 48;
            $top_margin = 87;
            $picture_width = $pd->w / 5;


            $pd->SetFillColor(0, 0, 0);
            $pd->SetFont("Arial", 'B', 10);
            $pd->SetLeftMargin(16);
            $pd->SetY($top_margin - 7);
            //$pd->SetLeftMargin($left_margin);
            $pd->SetFont("Arial", 'B', 10);
            $pd->SetFont("Arial", 'BU', 10);

            $pd->SetFont("Arial", 'B', 10);
            $pd->SetFont("Arial", 'B', 8);

            if ($group_name != "") {
                $pd->Cell(70, $heightCell, $group_name, 0, 0);
                $pd->SetFont("Arial", 'B', 10);
                $pd->Cell(45, $heightCell, "Seat No:", 0, 0, 0);
                $pd->SetFont("Arial", 'BU', 14);
                $pd->SetTextColor(204, 51, 0);
                $pd->Cell(100, $heightCell, " " . $bean['seat_no'] . " ", 0, 1);

            } else {

                $pd->Cell(112, $heightCell, "Seat No:", 0, 0, 0);
                $pd->SetFont("Arial", 'BU', 14);
                $pd->SetTextColor(204, 51, 0);
                $pd->Cell(100, $heightCell, " " . $bean['seat_no'] . " ", 0, 1);

            }
            $pd->SetTextColor(0, 0, 0);
            $pd->SetY($top_margin);
            $pd->SetFont("Arial", '', 7);
            $pd->Cell(97, $heightCell, "Venue of Pre-Entry Test: ", 1, 1);
            $pd->SetFont("Arial", '', 10);
            $pd->Cell($widthCell, $heightCell, "Name of Applicant:       ", 1, 1);
            $pd->Cell($widthCell, $heightCell, "Father’s Name:           ", 1, 1);
            $pd->Cell($widthCell, $heightCell, "Surname:                 ", 1, 1);
            $pd->Cell($widthCell, $heightCell, "C N I C #:                   ", 1, 1);
            $pd->Cell(97, $heightCell, "District of Domicile:    ", 1, 1);
            $pd->Cell($widthCell + 27, $heightCell, "Postal Address:          ", 1, 1);
            // $pd->Cell( ($widthCell/2) + ($picture_width/2) ,$heightCell,"Mobile No.:",1,0);
            // $pd->Cell( ($widthCell/2) + ($picture_width/2) ,$heightCell,"Telephone No.:",1,1);
            // $this->Rotate(90,1,1);


            $pd->SetLeftMargin($left_margin);
            $pd->SetY($top_margin);
            $pd->SetFont("Times", 'B', 8);

            //$pd->Cell(0,$heightCell,"SINDH UNIVERSITY LAAR CAMPUS BADIN",0,1);

            $pd->Cell(0, $heightCell, $bean['campus_name'] . " " . $bean['location'], 0, 1);
            $pd->SetFont("Times", 'B', 10);
            $pd->Cell(0, $heightCell, $bean['name'], 0, 1);
            $pd->Cell(0, $heightCell, $bean['fathers_name'], 0, 1);
            $pd->Cell(0, $heightCell, $bean['surname'], 0, 1);
            $pd->Cell(0, $heightCell, $bean['cnic_no'], 0, 1);
            $pd->Cell(0, $heightCell, $bean['district_name'], 0, 1);
            //$pd->SetLeftMargin(10);
            $pd->SetFont("Times", '', 10);
            $current_y = $pd->GetY();
            $current_x = $pd->GetX();
            $pd->SetFont("Times", '', 7);
            $pd->Cell(86, $heightCell, $bean['present_postel_address'], 0, 1);

            //$current_x+=$cell_width;                           //calculate position for next cell
            //$pd->SetXY($current_x, $current_y);               //set position for next cell to print
            $pd->SetFont("Times", '', 10);


            $pd->Image($candidateImage, ($widthCell + $lmargin), ($top_margin + $tmargin - 10), $picture_width, 48);

            $pd->SetLeftMargin(($widthCell + $lmargin));
            $pd->SetY($top_margin);
            $pd->Cell($picture_width, 48, "", 1);
            $pd->SetFont("Times", '', 8);
            $pd->SetXY(15, 187);
            $pd->Cell(0, 0, "C.O.R No." . $bean['candidate_id'], 0, 0);
            $pd->SetXY(15, 190);

            $pd->Cell(0, 0, "Form No." . $bean['form_sno'], 0, 0);
            $pd->SetXY(15, 175);

            //$pd->Cell(0,0," "." ",0,0);
            //ob_clean();
      //      $dialog = true;
    //        $param = ($dialog ? 'true' : 'false');
  //          $script = "print($param);";
//            $pd->IncludeJS($script);


        }
        $pd->Output("ac.pdf", 'I');

    }


}
