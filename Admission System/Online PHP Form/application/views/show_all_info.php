<?php $this->load->library("variable"); ?>

<div id="content">
    <!-- Page Content -->
    <div class="page-content">
        <div class="container">



            <div class="text-danger">
                <p>Your form has  been submitted successfully.</p>
            </div>
            <div class="row">
                <div class="col-md-10" >
                <table class='table table-striped'>
                    <tr>
                        <td >Candidate Online Registration No:</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getCandidateId(); ?></strong></td>
                    </tr>
                    <tr class='success'>
                        <td colspan="2" ><strong>Personal Information</strong></td>
                    </tr>
                    <tr>
                        <td >Applying For:</td>
                        <td ><strong class='text-info'><?php if($candidateBean->getProgramTypeId() == Variable::$MASTER_ID){echo "MASTER";}else{echo "BACHELOR";} ?></strong></td>
                    </tr>
                    <tr>
                        <td >Name:</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getName(); ?></strong></td>
                    </tr>
                    <tr>
                        <td >Father's Name:</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getFathersName(); ?></strong></td>
                    </tr>
                    <tr>
                        <td >Surname:</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getSurname();?></strong></td>
                    </tr>
                    <tr>
                        <td >Gender</td>
                        <td ><strong class='text-info'><?php if($candidateBean->getGender() == 0){echo "MALE";}else{echo "FEMALE";} ?></strong></td>
                    </tr>
                    <tr>
                        <td >CNIC No.</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getCnic();?></strong></td>
                    </tr>
                    <tr>
                        <td >Date of Birth</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getDateOfBirth();?></strong></td>
                    </tr>
                    <tr>
                        <td >Religion</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getReligionName();?></strong></td>
                    </tr>
<!--                    <tr>-->
<!--                        <td >Place of Birth</td>-->
<!--                        <td ><strong class='text-info'>--><?php //echo $candidateBean->getPlaceOfBirth();?><!--</strong></td>-->
<!--                    </tr>-->
                    <tr>
                        <td >District</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getDistrictName();?></strong></td>
                    </tr>
                    <tr>
                        <td >Area</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getAreaCode();?></strong></td>
                    </tr>
                    <tr>
                        <td >Telephone</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getTelephone();?></strong></td>
                    </tr>
                    <tr>
                        <td >Mobile</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getMobile();?></strong></td>
                    </tr>
                    <tr>
                        <td >Email</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getEmail();?></strong></td>
                    </tr>
                    <tr>
                        <td >Postal Address</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getPostalAddr();?></strong></td>
                    </tr>
                    <tr>
                        <td >Permanent Address</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getPermenentAddr();?></strong></td>
                    </tr>
                    <tr class='success'>
                        <td colspan="2" ><strong>Guardian's Information</strong></td>
                    </tr>
                    <tr>
                        <td >Guardian's Name</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getGurdianName();?></strong></td>
                    </tr>
                    <tr>
                        <td >Father's / Guardian's Occupation</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getOccupation();?></strong></td>
                    </tr>
                    <tr>
                        <td >Guardian's Address</td>
                        <td ><strong class='text-info'><?php echo $candidateBean->getGuardianAddr();?></strong></td>
                    </tr>
                    <tr class='success'>
                        <td colspan="2" ><strong>Education</strong></td>
                    </tr>
                </table>
                </div>
                <div class="col-md-2" ></div>
            </div>
            <div class="row">
                <div class="col-md-10">
                <table class="table table-bordered">
                    <tr>
                        <td><strong>Qualification</strong></td>
                        <td><strong>Group</strong></td>
                        <td><strong>Total Marks</strong></td>
                        <td><strong>Marks Obtained</strong></td>
                        <td><strong>Year</strong></td>
                        <td><strong>Seat No</strong></td>
                        <td><strong>Board / University</strong></td>
                    </tr>
                    <tr>
                        <td>S.S.C </td>
                        <td><?php echo $cred->getGroupName();?></td>
                        <td><?php echo $cred->getTotalMarks();?></td>
                        <td><?php echo $cred->getObtainMarks();?></td>
                        <td><?php echo $cred->getPassingYear();?></td>
                        <td><?php echo $cred->getSeatNo();?></td>
                        <td><?php echo $cred->getIssuerName();?></td>
                    </tr>
                    <tr>
                        <td>H.S.C</td>
                        <td><?php echo $credHsc->getGroupName();?></td>
                        <td><?php echo $credHsc->getTotalMarks();?></td>
                        <td><?php echo $credHsc->getObtainMarks();?></td>
                        <td><?php echo $credHsc->getPassingYear();?></td>
                        <td><?php echo $credHsc->getSeatNo();?></td>
                        <td><?php echo $credHsc->getIssuerName();?></td>
                    </tr>
                    <?php if( $candidateBean->getProgramTypeId() == Variable::$MASTER_ID ){
                        ?>
                        <tr>
                            <td>Graduation</td>
                            <td><?php echo $credGrd->getGroupName();?></td>
                            <td><?php echo $credGrd->getTotalMarks();?></td>
                            <td><?php echo $credGrd->getObtainMarks();?></td>
                            <td><?php echo $credGrd->getPassingYear();?></td>
                            <td><?php echo $credGrd->getSeatNo();?></td>
                            <td><?php echo $credGrd->getIssuerName();?></td>
                        </tr>
                        <?php
                        $optionsSize = count($optionals);
                        if($optionsSize >0){
                            $optionString = "";
                            for($i=0;$i<$optionsSize;$i++){
                                $nam = $optionals[$i]['name'];
                                $optionString .= (($i+1).".".$nam."  --  ");
                            }
                            ?>
                            <tr>
                                <td>Optional Subjects</td>
                                <td colspan="6"><?php echo $optionString;?></td>
                            </tr>
                            <?php
                        }
                    }?>
                </table>

            </div>
        </div>

            <div class="row">
                <div class="col-md-10" >
                    <table class="table table-striped table-bordered">
                    <?php if(count($campusList)>0){
                        $campus = $campusList[0]['name'];
                        ?>
                        <tr>
                            <td>Applied Campus</td>
                            <td><?php echo $campus;?></td>
                        </tr>
                        <?php
                    }
                    ?>

                    <?php
                        $ac = "";
                        for($i=0;$i<count($appliedCats);$i++){
                            if($i == count($appliedCats)-1){
                                $ac.=Variable::$CategoryEnumDesc[$appliedCats[$i]['code']];
                            }else{
                                $ac.= (Variable::$CategoryEnumDesc[$appliedCats[$i]['code']].",");
                            }
                        }
                        ?>
                        <tr>
                            <td>Applied Category(ies)</td>
                            <td><?php echo $ac;?></td>
                        </tr>

                </table>
                </div>
            </div>

            <div class="row">
                <div class="col-md-10" >
                <?php
                $mcSize = count($morningChoices);
                echo "<table class='table table-striped'>";
                if($mcSize>0){
                    echo "<tr class='success'>
                    <td><strong>#</strong></td>
                    <td><strong>Morning Choices</strong></td>
                </tr>";
                for($i=0;$i<$mcSize;$i++){
                    echo "<tr><td>".($i+1)."</td>";
                    echo "<td>".$morningChoices[$i]['name']."</td></tr>";
                }
            }
            ?>


            <?php
            $ecSize = count($eveningChoices);
            if($ecSize>0){
                echo "<tr class='success'>
                    <td><strong>#</strong></td>
                    <td><strong>Evening Choices</strong></td>
                </tr>";
                for($i=0;$i<$ecSize;$i++){
                    echo "<tr><td>".($i+1)."</td>";
                    echo "<td>".$eveningChoices[$i]['name']."</td></tr>";
                }
            }
                echo "</table>";

                ?>
        </div>
            </div>
        </div>
        <DIV class="col-md-10">

        <div class="text-info">
            <p>Please use PDF Reader for downloading / printing your form. You can download Adobe Acrobat Reader here. </p>
            <a href="http://ardownload.adobe.com/pub/adobe/reader/win/9.x/9.1/enu/AdbeRdr910_en_US.exe" > <img style="margin: 2%" src="<?Php echo ASSET_PATH; ?>/images/download_En.jpg"> </a>
        </div>
        </DIV>
        <DIV class="col-md-3"></DIV>
        <DIV class="col-md-2">

            <?php if(!$is_slip_issued || $admin=="superUser"){?>
                <a href="<?php echo Variable::$GOTO_TAB_APPLY_FOR;?>?<?php echo Variable::$REQUEST;?>=<?php echo Variable::$TAB_PERSONAL;?>" class="btn btn-default"><i class="glyphicon glyphicon-arrow-left"></i> <?php echo Variable::$PREVIOUS;?></a>
                <br/>
                <!--            <form action="--><?php //echo Variable::$SUBMIT_CANDIDATE;?><!--" method="post">-->
                <!--                <input type="submit" value="Submit the Form">-->
                <!--            </form>-->
            <?php } ?>
        </div>
        <DIV class="col-md-4">
        <?php

        if(!$is_slip_issued || $admin=="superUser"){echo "<a class='btn btn-success' style='color:white;' href='".Variable::$PRINT_FORM."' target='_blank'>Print Form</a>";
            echo "<br/>";
        }else{
            echo "<div class='text-info'><h3>Once admit card / slip is issued, NO change will be made in Online Admission Form</h3></div>";
        }
        ?>
        </DIV>
</br></br>
        </div>
</div>


