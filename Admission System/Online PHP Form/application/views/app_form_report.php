<?php
/**
 * Description of AppFormReport
 *
 * @author Jay Kumarr
 */
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class AppFormReport{
    var $candidateId;
    var $candidateImage;
    var $degreeProgram;
    var $pd;
    var $columnWidth = 60;
    var $columnHeight = 5;
    var $isMaster;
    var $bean;
    public function __construct($candidateId,$candidateImage) {
        $this->candidateId = $candidateId;
        $this->candidateImage = $candidateImage;

        $this->degreeProgram = "Bachelor Degree Program";
    }
    
    
    public function printReport($bean,$campusList,$appliedCats,$sscInfo,$hscInfo,$morningChoices,$eveningChoices,$grdInfo=null,$optionals = array() ,$bankName ){

        if($bean->getProgramTypeId() == Variable::$MASTER_ID){
            $this->degreeProgram = "Master Degree Program";
        }
        
        $pd = new CellPDF();
        $pd->AddPage();

        $wid = $pd->w;
        $hig = $pd->h;
        //$pd->Image(ASSET_PATH.'images/logo_trans.png',($wid/4),($hig/4),($wid/2),($hig/2));
       $pd->Image(ASSET_PATH.'images/logo.png',20,5,25,25);
        
        
        $pd->SetFillColor(0,0,0);
        $pd->SetFont("Arial",'B',20);
        $pd->Cell(0,$this->columnHeight,"The University of Sindh, Jamshoro ",0,1,'C',false);
        $pd->Ln(1);
        $pd->SetFont("Times",'BU',12);
        $pd->Cell(0,$this->columnHeight,"ONLINE ADMISSION FORM (".$this->degreeProgram.")",0,1,'C',false);
        $pd->Ln(1);
        $pd->Cell(0,$this->columnHeight,"SESSION-".Variable::$SESSION_YEAR,0,1,'C',false);      
        
        $pd->Ln(4);
        $pd->Cell(($wid/2),$this->columnHeight,"Candidate's Online Registration No. ".$this->candidateId,0,1);
       // $pd->Cell(($wid/2),$this->columnHeight,"Date: ".$bean->getDateOfSumbitForm(),0,1);
//        $pd->Cell(0,$this->columnHeight,"SeatNo. ".$bean->getSeatNo(),0,1,'R');
        $pd->SetFont("Times",'BI',10);
        $pd->Cell($this->columnWidth,$this->columnHeight,"Applied Campus",1,0);      
        $campus= "";
        $c_location="";
        if(count($campusList)>0){
            $campus = $campusList[0]['name'];
            $c_location = $campusList[0]['location'];

        }
        $pd->SetFont("Times",'',10);
        $pd->Cell(0,$this->columnHeight,$campus." ".$c_location,1,1);
        $pd->SetFont("Times",'BI',10);$pd->Cell($this->columnWidth,$this->columnHeight,"Applied Category(ies)",1,0);      
        $sizeCats = count($appliedCats);
        $ac = "";
        for($i=0;$i<$sizeCats;$i++){
            if($i == $sizeCats-1){
                $ac.=Variable::$CategoryEnumDesc[$appliedCats[$i]['code']];
            }else{
                $ac.= (Variable::$CategoryEnumDesc[$appliedCats[$i]['code']].",");
            }
        }
        $pd->Cell(0,$this->columnHeight,$ac,1,1);      
        
        $pd->Ln(2);
        $pd->SetFont("Times",'B',10);
        $pd->Cell(0,$this->columnHeight,"Personal Information",0,1);
        $pd->SetFont("Times",'',10);
        $pd->Cell($this->columnWidth,$this->columnHeight,"Name",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getName() ,1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Father's Name",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getFathersName(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Surname",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getSurname(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Gender",1,0);
        $gender="";
        if($bean->getGender()==0){
            $gender="Male";
        }else{
            $gender="Female";
        }
        $pd->Cell(0,$this->columnHeight,"".$gender,1,1,'L');

        $pd->Cell($this->columnWidth,$this->columnHeight,"CNIC No.",1,0);        
        $pd->Cell(0,$this->columnHeight,"".$bean->getCnic(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"District of Domicile",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getDistrictName(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Urban/Rural",1,0);
        $area="";
        if ($bean->getAreaCode()=="U"){
            $area="Urban";
        }else{
            $area="Rural";
        }
        $pd->Cell(0,$this->columnHeight,"".$area,1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Province",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getProvinceName(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Nationality",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getCountryName(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Religion",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getReligionName(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Date of Birth",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getDateOfBirth(),1,1,'L');
       // $pd->Cell($this->columnWidth,$this->columnHeight,"Place of Birth",1,0);
      //  $pd->Cell(0,$this->columnHeight,"".$bean->getPlaceOfBirth(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Gurdian's Name",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getGurdianName(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Father's / Gurdian's Occupation",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getOccupation(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight+5,"Permenant Home Address",1,0);
        $pd->Cell(0,$this->columnHeight+5,"".$bean->getPermenentAddr(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight+5,"Present Postal Address",1,0);
        $pd->Cell(0,$this->columnHeight+5,"".$bean->getPostalAddr(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Telephone No. (Landline)",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getTelephone(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Mobile No.",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getMobile(),1,1,'L');
        $pd->Cell($this->columnWidth,$this->columnHeight,"Email Address",1,0);
        $pd->Cell(0,$this->columnHeight,"".$bean->getEmail(),1,1,'L');

        $pd->Ln(2);
        $pd->SetFont("Times",'BU',10);
        $pd->Cell(0,$this->columnHeight,"Academic Record",0,1);      
        $pd->SetFont("Times",'B',10);
        $pd->Cell($this->columnWidth,$this->columnHeight*2,"Examination Passed",1,0);
        $pd->Cell($this->columnWidth/3,$this->columnHeight*2,"Group",1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight*2,"Marks\n Obtained",1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight*2,"Total \nMarks",1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight*2,"Year",1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight*2,"Seat No.",1,0,'C');
        $pd->Cell(0,$this->columnHeight*2,"Name of Board/\nUniversity",1,1,'C');
        $pd->SetFont("Times",'B',10);
        $pd->Cell($this->columnWidth,$this->columnHeight,"S.S.C (Matriculation)",1,0);
        $pd->SetFont("Times",'',10);
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$sscInfo[0]['group_name'],1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$sscInfo[0]['marks_obtained'],1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$sscInfo[0]['total_marks'],1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$sscInfo[0]['passing_year'],1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$sscInfo[0]['seat_no'],1,0,'C');
        $pd->Cell(0,$this->columnHeight,"".$sscInfo[0]['issuer_name'],1,1,'C');
        $pd->SetFont("Times",'B',10);
        $pd->Cell($this->columnWidth,$this->columnHeight,"H.S.C (Intermediate)",1,0);
        $pd->SetFont("Times",'',10);
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$hscInfo[0]['group_name'],1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$hscInfo[0]['marks_obtained'],1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$hscInfo[0]['total_marks'],1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$hscInfo[0]['passing_year'],1,0,'C');
        $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$hscInfo[0]['seat_no'],1,0,'C');
        $pd->Cell(0,$this->columnHeight,"".$hscInfo[0]['issuer_name'],1,1,'C');
        if($bean->getProgramTypeId() == Variable::$MASTER_ID){
            $pd->Cell($this->columnWidth,$this->columnHeight,"Graduation (Bachelor Degree)",1,0);
            $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$grdInfo[0]['group_name'],1,0,'C');
            $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$grdInfo[0]['marks_obtained'],1,0,'C');
            $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$grdInfo[0]['total_marks'],1,0,'C');
            $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$grdInfo[0]['passing_year'],1,0,'C');
            $pd->Cell($this->columnWidth/3,$this->columnHeight,"".$grdInfo[0]['seat_no'],1,0,'C');
            $pd->Cell(0,$this->columnHeight,"".$grdInfo[0]['issuer_name'],1,1,'C');
            $countOptional = count($optionals);
            if($countOptional>0){
                $pd->Cell($this->columnWidth,$this->columnHeight,"Optional Subjects",1,0);
                $optionString = "";
                for($i=0;$i<$countOptional;$i++){
                    $nam = $optionals[$i]['name'];
                    $optionString .= (($i+1).".".$nam."  --  ");
                }
                $pd->Cell(0,$this->columnHeight,$optionString,1,1);
            }
        }
        
        $pd->Ln(2);
        $pd->SetFont("Times",'BU',10);
        $pd->Cell(0,$this->columnHeight,"Applied Choices",0,1);      
        $pd->SetFont("Times",'B',10);

        $morningLine = 1;

     //raja coding
        $mcSize = count($morningChoices);
        if($mcSize>0){
        $pd->Cell( ($wid/2)-10 ,$this->columnHeight,"SUBJECTS / DISCIPLINES (MORNING)",1,1);

        $pd->SetFont("Times",'',10);

    for($i=0;$i<$mcSize;$i++) {
        if ($i < $mcSize) {
           $pd->Cell(($wid / 2) - 10, $this->columnHeight, (($i + 1) . ". " . $morningChoices[$i]['name']), 1, $morningLine);
     }
    }
        }
        if($bean->getProgramTypeId() == Variable::$MASTER_ID){
            $pd->SetXY(110, 197);

        }else{
            $pd->SetXY(110, 187);
        }



        $ecSize = count($eveningChoices);
        if($ecSize> 0) {
            $pd->SetFont("Times", 'B', 10);
            $pd->Cell(0, $this->columnHeight, "SUBJECTS / DISCIPLINES (EVENING)", 1, 1);
            $pd->SetFont("Times", '', 10);

            if($bean->getProgramTypeId() == Variable::$MASTER_ID){
                $y=197;

            }else{
                $y = 187;
            }


            for ($i = 0; $i < $ecSize; $i++) {
                $y = $y + 5;
                $pd->SetXY(110, $y);
                if ($i < $ecSize) {
                    $pd->Cell(0, $this->columnHeight, (($i + 1) . ". " . $eveningChoices[$i]['name']), 1, $morningLine);
                }
            }

        }


        //end of raja coding
        /*
                if(count($eveningChoices) > 0){
                   // $pd->SetXY(140, $w);
                    $morningLine = 0;

                    $pd->Cell( ($wid/2)-10 ,$this->columnHeight,"SUBJECTS / DISCIPLINES (MERIT/SELF FINANCE)",1,0);
                    $pd->Cell( ($wid/2)-10 ,$this->columnHeight,"SUBJECTS / DISCIPLINES (EVENING)",1,1);
                }else{
                    $pd->Cell( ($wid/2)-10 ,$this->columnHeight,"SUBJECTS / DISCIPLINES (MERIT/SELF FINANCE)",1,1);
                }


                $pd->SetFont("Times",'',10);
                $mcSize = count($morningChoices);
                $ecSize = count($eveningChoices);


                for($i=0;$i<10;$i++){
                    if($i<$mcSize){
                        $pd->Cell( ($wid/2)-10 ,$this->columnHeight,(($i+1).". ".$morningChoices[$i]['name']),1,$morningLine);
                    }else{
                        $pd->Cell( ($wid/2)-10 ,$this->columnHeight,(($i+1)."."),1,$morningLine);
                    }
                    if($i<$ecSize){
                        $pd->Cell( 0,$this->columnHeight,(($i+1).". ".$eveningChoices[$i]['name']),1,1);
                    }else{
                        $pd->Cell( 0,$this->columnHeight,(($i+1)."."),1,1);
                    }

                }
                */

        //$bankName[0]['name'];
        $pd->SetXY(150, 250);
        $pd->Ln(2);
        $pd->SetFont("Times",'',10);
        $pd->Cell(80,$this->columnHeight,"Admission Form Rs. ".Variable::$FORM_RUPEES."/=      Challan No. ".$bean->getChallanNo()  ,0,0);
        $pd->SetFont("Times",'',10);
        $pd->Cell(0,$this->columnHeight,"Date: ".$bean->getChallanDate()."      HBL :".$bankName[0]['name']."  Form No: ".$bean->getFormSNo(),0,1);
        $pd->Ln(1);
        $pd->SetFont("Times",'BI',10);
        $pd->Cell(0,$this->columnHeight,"I do hereby state that all information and data given by me as above is true and correct and shall always be binding to me.",0,1);
        $pd->Ln(8);
        $pd->SetFont("Times",'B',10);
        $myDateTime= DateTime::createFromFormat('Y-m-d H:i:s',$bean->getDateOfSumbitForm());

        $newDateString = $myDateTime->format('d-m-Y H:i:s');


        $pd->Cell(0,$this->columnHeight,$newDateString."                                                                                                                            Signature of the applicant",0,0);

        $pd->SetFont("Times",'',9);
        $pd->SetXY($wid-40, 6);
        $pd->Image($this->candidateImage,($wid-35),5,22,25);
       // $pd->ob_clean();//add this line
        $pd->Output($this->candidateId.".pdf",'I');
        //$pd->Output();
    }
}

?>
