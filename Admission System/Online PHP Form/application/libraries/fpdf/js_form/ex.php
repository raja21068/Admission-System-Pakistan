<?php
define('FPDF_FONTPATH','font/');
require('js_form.php');

$pdf=new JS_Form();
$pdf->Open();
$pdf->AddPage();

//Title
$pdf->SetFont('Arial','U',16);
$pdf->Cell(0,5,'Subscription form',0,1,'C');
$pdf->Ln(10);
$pdf->SetFont('','',12);
//First name
$pdf->Cell(35,5,'First name:');
$pdf->TextField('firstname',50,5,array('BorderColor'=>'ltGray'));
$pdf->Ln(6);
//Last name
$pdf->Cell(35,5,'Last name:');
$pdf->TextField('lastname',50,5,array('BorderColor'=>'ltGray'));
$pdf->Ln(6);
//Gender
$pdf->Cell(35,5,'Gender:');
$pdf->ComboBox('gender',10,5,array('','M','F'),array('BorderColor'=>'ltGray'));
$pdf->Ln(6);
//Adress
$pdf->Cell(35,5,'Address:');
$pdf->TextField('address',60,18,array('multiline'=>true,'BorderColor'=>'ltGray'));
$pdf->Ln(19);
//E-mail
$pdf->Cell(35,5,'E-mail:');
$pdf->TextField('email',50,5,array('BorderColor'=>'ltGray'));
$pdf->Ln(6);
//Newsletter
$pdf->Cell(35,5,'Receive our',0,1);
$pdf->Cell(35,5,'newsletter:');
$pdf->CheckBox('newsletter',5,true);
$pdf->Ln(10);
//Date of the day (determined and formatted by JS)
$pdf->Write(5,'Date: ');
$pdf->TextField('date',30,5);
$pdf->script.="getField('date').value=util.printd('dd/mm/yyyy',new Date());";
$pdf->Ln();
$pdf->Write(5,'Signature:');
$pdf->Ln(3);
//Button to validate and print
$pdf->SetX(95);
$pdf->Button('print',20,8,'Print','Print()',array('TextColor'=>'yellow','FillColor'=>'#FF5050'));

//Form validation functions
$pdf->script.="
function CheckField(name,message)
{
	f=getField(name);
	if(f.value=='')
	{
		app.alert(message);
		f.setFocus();
		return false;
	}
	return true;
}

function Print()
{
	//Validation
	if(!CheckField('firstname','First name is mandatory'))
		return;
	if(!CheckField('lastname','Last name is mandatory'))
		return;
	if(!CheckField('gender','Gender is mandatory'))
		return;
	if(!CheckField('address','Address is mandatory'))
		return;
	//Print
	print();
}
";

//We include all the generated JavaScript code into the PDF
$pdf->IncludeJS($pdf->script);
$pdf->Output();
?>
