<div class="page-content">

    <div class="container">
        <div class="row">
            <div class='col-md-2'></div>
            <div class='col-md-9'>

                <div class="continer">
                    <p><em>  <strong>It is mandatory to provide all required information which is highlighted by <font color="red">*</font> </strong></em> </p>
                </div>
            </div>


        </div>
        <div class="row">
            <div class='col-md-10'>
                <?php echo form_open_multipart('app_form/personal_information_save');?>

                <input type="hidden" name="<?php echo Variable::TAB()?>" value="<?php echo ''.Variable::TAB_PERSONAL();?>" >

                <!-- Left Side -->
                <div class="col-md-3"></div>
                <div class="col-md-8">
                    <div class="form-group">
                        <div class="form-group" >

                            <label class="col-sm-3 control-label" > Apply For </font> <font color="red">*</font></label>

                            <div class="col-sm-9">
                                <select class="form-control" style="font-size: large;" name="<?php echo Variable::PROGRAM_TYPE()?>"  required="required">
                                    <option value="">--Choose--</option>
                                    <option value="1" <?php if($candidate_bean->getProgramTypeId() == 1){echo "selected";}?>>Bachelor</option>
                                    <?php// if($candidate_bean->getProgramTypeId() == 2){ ?>
                                    <option value="2" <?php if($candidate_bean->getProgramTypeId() == 2){echo "selected";}?>>Master</option>
                                    <?php // } ?>
                                </select>
                            </div>
                        </div>
						</br>
						</br>

                        <?php
                        if($admin=="superUser"){

                            ?>
                            <?php
                            echo("Seat No:<font color='red'> ".$candidate_bean->getSeatNo()."</font>");
                            ?>
                            <div class="panel-heading" style="background-color:#555;margin: 0 -1px 10px -1px;padding: 0 15px;font-family: 'Trebuchet MS',Arial,Helvetica,sans-serif;font-size: 14px;line-height: 26px;text-shadow: 1px 1px 1px #000;border-radius: 7px 7px 0 0;color: white;"> Objection</div>

                            <div class="form-group">
                                <label for="pwd">Is Objection</label>
                                <div class="form-control">
                                    <label class="radio-inline">
                                        <input type="radio" name="<?php echo Variable::IS_OBJECTION()?>" value="1" <?php if($candidate_bean->getIsObjection() == 1){ echo "checked='checked'"; }  ?> />Objection
                                    </label>

                                    <label class="radio-inline">
                                        <input type="radio" name="<?php echo Variable::IS_OBJECTION()?>" value="0" <?php if($candidate_bean->getIsObjection() == 0){ echo "checked='checked'"; }  ?> />No Objection
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="pwd">Objection Remarks</label>
                                <input type="text" class="form-control"  name="<?php echo Variable::$OBJECTION_REMARKS?>" value="<?php echo $candidate_bean->getObjectionRemarks();?>" />
                            </div>


                            <?php
                        }
                        ?>
                        </br>

                        <div class="col-sm-12">
                            <div class="panel-heading" style="background-color:#555;margin: 0 -1px 10px -1px;padding: 0 15px;font-family: 'Trebuchet MS',Arial,Helvetica,sans-serif;font-size: 14px;line-height: 26px;text-shadow: 1px 1px 1px #000;border-radius: 7px 7px 0 0;color: white;"> Personal Details</div>
                        </div>
                        <div class="form-group">

                            <label for="pwd" class="col-sm-3 control-label"  >Name<font color="red">* </font></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" pattern="([ a-zA-Z]){3,120}" title="Only Alphabets minimum 3 characters" name="<?php echo Variable::$STUDENT_NAME?>" value="<?php echo $candidate_bean->getName();?>" required>
                            </div>
                        </div>
                        <div class="form-group">

                            <label for="pwd" class="col-sm-3 control-label">Father's Name<font color="red">*</font></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" pattern="([ a-zA-Z]){3,120}" title="Only Alphabets minimum 3 characters" name="<?php echo Variable::$FATHER_NAME?>" value="<?php echo $candidate_bean->getFathersName(); ?>" required>
                            </div>
                        </div>




                        <div class="form-group" >
						
                            <label for="pwd" class="col-sm-3 control-label">Surname</label>
                            <div class="col-sm-9">

                                <input type="text"  pattern="([ a-zA-Z]){3,120}" title="Only Alphabets minimum 3 characters" name="<?php echo Variable::$SURNAME?>" value="<?php echo $candidate_bean->getSurname(); ?>" >
                            </div>

                        </div>

                        <div class="form-group" >
                            <label for="pwd" class="col-sm-3 control-label">Gender<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <div class="form-control">

                                    <label class="radio-inline">
                                        <input type="radio" name="<?php echo Variable::GENDER()?>" value="0" <?php if($candidate_bean->getGender() == 0){ echo "checked='checked'"; }  ?>  required/>Male
                                    </label>

                                    <label class="radio-inline">
                                        <input type="radio" name="<?php echo Variable::GENDER()?>" value="1" <?php if($candidate_bean->getGender() == 1){ echo "checked='checked'"; }  ?> />Female
                                    </label>
                                </div>
                            </div>

                        </div>

                        <div class="form-group" >
                            <label for="pwd" class="col-sm-3 control-label" >CNIC No<span>'</span>s <font color="red">*</font></label>
                            <div class="col-sm-5">

                                <input type="text" name="<?php echo Variable::CNIC();?>" class="form-control" id="idcard" maxlength="15" pattern="[0-9]{5}-[0-9]{7}-[0-9]{1}" title="i.e. 00000-0000000-0" placeholder="i.e. 00000-0000000-0" value="<?php echo $candidate_bean->getCnic(); ?>" required>
                                </div>
								<div class='col-sm-2'>
								<label class="radio-inline">
								
                                    <input type="radio" name="cninc_of" value="S" <?php if($candidate_bean->getCnicOf() == "S"){echo "checked='checked'";}?> required>Self
                                </label>
							</div>
							<div class='col-sm-2'>

                                <label class="radio-inline">

                                    <input type="radio"  name="cninc_of" value="F" <?php if($candidate_bean->getCnicOf() == "F"){echo "checked='checked'";}?> required>Father
                                </label>

                            </div>

                        </div>
					

                        <div class="form-group">
						<div class='col-md-12'></div>
                            <label for="pwd" class="col-sm-3 control-label">Date of Birth<font color="red">*</font></label>
                            <div class="col-sm-9">
                                <input type="text" readonly="readonly" class="form-control" id="birth-date"  name="<?php echo Variable::DATE_OF_BIRTH()?>" value="<?php echo $candidate_bean->getDateOfBirth(); ?>" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Religion<font color="red">*</font></label>
                            <div class="col-sm-9">
                                <select class="form-control" name="<?php echo Variable::RELIGION()?>" required>

                                    <option value="">--Choose--</option>

                                    <?php
                                    foreach( $religion as $religons )

                                    {
                                        $religion_id=$religons['religion_id'];
                                        $religion_name=	$religons['name'];

                                        ?>
                                        <option value="<?php echo '' . $religion_id ?>"
                                            <?php if($candidate_bean->getReligion() == $religion_id){echo "selected";}?>
                                            > <?php echo '' . $religion_name ?>  </option>

                                        <?php
                                    }
                                    ?>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Nationality<font color="red">*</font></label>
                            <div class="col-sm-9">
                                <select class="form-control"  id="country" name="<?php echo Variable::$COUNTRY?>" required>
                                    <option value="" >--Choose--</option>
                                    <?php
                                    if($candidate_bean->getNationality()=="") { $candidate_bean->setNationality(168); }

                                    foreach( $country as $coun )

                                    {
                                        $country_id=$coun['country_id'];
                                        $country_name=	$coun['name'];

                                        ?>
                                        <option value="<?php echo '' . $country_id ?>" <?php if($candidate_bean->getNationality() == $country_id){echo "selected";}   ?>> <?php echo '' . $country_name ?>  </option>

                                        <?php
                                    }
                                    ?>
                                </select>
                            </div>
                        </div>

                        <hr class="divider">

                        <div class="panel-heading" style="background-color:#555;margin: 0 -1px 10px -1px;padding: 0 15px;font-family: 'Trebuchet MS',Arial,Helvetica,sans-serif;font-size: 14px;line-height: 26px;text-shadow: 1px 1px 1px #000;border-radius: 7px 7px 0 0;color: white;"> District Information</div>

                        <div class="form-group">

                            <label for="pwd" class="col-sm-3 control-label">District of Domicile</label>
                            <div class="col-sm-9">

                                <label class="radio-inline">

                                    <input type="radio" name="province" id="sindh" value="SINDH" <?php if($province_id == Variable::SINDH_PROVINCE_ID()){echo "checked='checked'";}?>  checked="false" required>Sindh
                                </label>
                                <label class="radio-inline">
                                    <input type="radio"  name="province"  id="all" value="ALL" <?php if($province_id != Variable::SINDH_PROVINCE_ID() && $province_id != -1){echo "checked='checked'";}?> required>Other
                                </label>

                                <select name="<?php echo Variable::$DISTRICT?>" class="form-control" id="pro" required>

                                    <?php
                                    /*
                                    foreach( $district as $dist )

                                    {
                                        $district_id=$dist['district_id'];
                                        $district_name=	$dist['name'];

                                        ?>
                                        <option value="<?php echo '' . $district_id ?>" <?php if($candidate_bean->getDistrict() == $district_id){echo "selected";}?>> <?php echo '' . $district_name ?>  </option>

                                        <?php
                                    }
                                    */
                                    ?>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Urban/Rural<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <div class="form-control">
                                    <label class="radio-inline">
                                        <input type="radio" id="urban" name="<?php echo Variable::$AREA?>" value="0" <?php if($candidate_bean->getArea() == 0){echo "checked='checked'";}?> required>Urban
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" id="rural" name="<?php echo Variable::$AREA?>" value="1" <?php if($candidate_bean->getArea() == 1){echo "checked='checked'";}?>>Rural
                                    </label>
                                </div>
                            </div>
                        </div>
                        <hr class="divider">

                        <div class="panel-heading" style="background-color:#555;margin: 0 -1px 10px -1px;padding: 0 15px;font-family: 'Trebuchet MS',Arial,Helvetica,sans-serif;font-size: 14px;line-height: 26px;text-shadow: 1px 1px 1px #000;border-radius: 7px 7px 0 0;color: white;"> Contact Information</div>


                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Mobile Number<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <input type="text" maxlength="11" class="form-control"  name="<?php echo Variable::$MOBILE?>" value="<?php echo $candidate_bean->getMobile(); ?>" pattern="[0-9]{11}" title="i.e. 03330000000" placeholder="i.e. 0333000000" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Email Address<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <input type="email" class="form-control" name="<?php echo Variable::$EMAIL?>" value="<?php echo $candidate_bean->getEmail(); ?>" required>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Telephone Number</label>
                            <div class="col-sm-9">
                                <input type="text" maxlength="11" class="form-control" name="<?php echo Variable::$TELEPHONE?>" value="<?php echo $candidate_bean->getTelephone(); ?>"   />
                            </div>
                        </div>
						
						

                        <div class="form-group">
						<div class='col-md-12'></div>
                            <div class="col-md-3">
							<label for="pwd" >Permanent Home Address<font color="red">*</font></label>
                            
							</div>
							     <div class="col-md-9">

                                <textarea  id="permanent" class="form-control" name="<?php echo Variable::$PERMENENT_ADDRESS?>" required><?php echo $candidate_bean->getPermenentAddr(); ?></textarea>
                            </div>
                        </div>


                        <div class="form-group">
							<div class="col-sm-3"></div>
                            <label for="pwd"  class="col-sm-3 control-label" >Same As Above</label>
                            <input type="checkbox"  id="sameAsAbove"></br>
							<div class='col-md-12'></div>
                        
                            <label for="pwd" class="col-sm-3 control-label">     Postal Address<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <textarea id="postal" class="form-control" name="<?php echo Variable::$POSTAL_ADDRESS?>" required><?php echo $candidate_bean->getPostalAddr(); ?></textarea>
                            </div>
                        </div>

                        <hr class="divider">

                        <div class="panel-heading" style="background-color:#555;margin: 0 -1px 10px -1px;padding: 0 15px;font-family: 'Trebuchet MS',Arial,Helvetica,sans-serif;font-size: 14px;line-height: 26px;text-shadow: 1px 1px 1px #000;border-radius: 7px 7px 0 0;color: white;"> Guardian<span>'</span>s / Sponsor Information </div>

                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Guardian Name<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <input type="text" class="form-control" name="<?php echo Variable::GUARDIAN_NAME()?>" value="<?php echo $candidate_bean->getGurdianName(); ?>" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Relationship with Guardian<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <select class="form-control" name="<?php echo Variable::$GUARDIAN_RELATIONSHIP?>" required>
                                    <option value="" >--choose--</option>
                                    <option value="Uncle" <?php if($candidate_bean->getRelationship() == "Uncle"){echo "selected";}?>>Uncle</option>
                                    <option value="Uncle" <?php if($candidate_bean->getRelationship() == "Aunty"){echo "selected";}?>>Aunty</option>
                                    <option value="Brother" <?php if($candidate_bean->getRelationship() == "Brother"){echo "selected";}?>>Brother</option>
                                    <option value="Sister" <?php if($candidate_bean->getRelationship() == "Sister"){echo "selected";}?>>Sister</option>
                                    <option value="Father" <?php if($candidate_bean->getRelationship() == "Father"){echo "selected";}?>>Father</option>
                                    <option value="Mother" <?php if($candidate_bean->getRelationship() == "Mother"){echo "selected";}?>>Mother</option>
                                    <option value="Grand Mother" <?php if($candidate_bean->getRelationship() == "Grand Mother"){echo "selected";}?>>Grand Mother</option>
                                    <option value="Grand Father" <?php if($candidate_bean->getRelationship() == "Grand Father"){echo "selected";}?>>Grand Father</option>
                                    <option value="Other" <?php if($candidate_bean->getRelationship() == "Other"){echo "selected";}?>>Other</option>
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
						<div class='col-md-12'></div>
                        
                            <label for="pwd" class="col-sm-3 control-label">Occupation of Father/Guardian<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <select class="form-control" name="<?php echo Variable::$GUARDIAN_OCCUPATION?>" required>
                                    <option value="" >--choose--</option>
                                    <option value="Business Man" <?php if($candidate_bean->getOccupation() == "Business Man"){echo "selected";}?>>Business Man</option>
                                    <option value="Engineer" <?php if($candidate_bean->getOccupation() == "Engineer"){echo "selected";}?>>Engineer</option>
                                    <option value="Doctor" <?php if($candidate_bean->getOccupation() == "Doctor"){echo "selected";}?>>Doctor</option>
                                    <option value="Farmer" <?php if($candidate_bean->getOccupation() == "Farmer"){echo "selected";}?>>Farmer</option>
                                    <option value="Government Employee" <?php if($candidate_bean->getOccupation() == "Government Employee"){echo "selected";}?>>Government Employee</option>
                                    <option value="Private Company Employee" <?php if($candidate_bean->getOccupation() == "Private Company Employee"){echo "selected";}?>>Private Company Employee</option>
                                    <option value="Landlord" <?php if($candidate_bean->getOccupation() == "Landlord"){echo "selected";}?>>Landlord</option>
                                    <option value="Retired" <?php if($candidate_bean->getOccupation() == "Retired"){echo "selected";}?>>Retired</option>
                                    <option value="Other" <?php if($candidate_bean->getOccupation() == "Other"){echo "selected";}?>>Other</option>
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
						<div class='col-md-12'></div>
                        
                            <label for="pwd" class="col-sm-3 control-label">Guardian<span>'</span>s Mobile Number</label>
                            <div class="col-sm-9">

                                <input type="text" maxlength="11"  class="form-control"  name="<?php echo Variable::$FAMILY_MOBIE?>" value="<?php echo $candidate_bean->getFamilyMobile(); ?>" pattern="[0-9]{11}" title="i.e. 03330000000" placeholder="i.e. 0333000000" >

                            </div>
                        </div>


                        <div class="form-group">
                            <input type="checkbox"  name="same" id="sameAsPostal">
                            <label for="pwd">Same As Postal Address</label>
                            <input type="checkbox"   name="same" id="sameAsPermanent">
                            <label for="pwd">Same As Permanent  Address</label>
                            </br>


                            <label for="pwd" class="col-sm-3 control-label">Guardian Address<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <textarea id="guardianAddress" class="form-control" name="<?php echo Variable::$GUARDIAN_ADDRESS?>" required><?php echo $candidate_bean->getGuardianAddr(); ?></textarea>
                            </div>
                        </div>

                        <hr class="divider">


                        <div class="panel-heading" style="background-color:#555;margin: 0 -1px 10px -1px;padding: 0 15px;font-family: 'Trebuchet MS',Arial,Helvetica,sans-serif;font-size: 14px;line-height: 26px;text-shadow: 1px 1px 1px #000;border-radius: 7px 7px 0 0;color: white;"> Bank Information </div>


                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Form Serial No <font color="red">*</font></label>
                            <div class="col-sm-9">

                                <input type="number"  name="formNo" class="form-control" maxlength="8" value="<?php echo $candidate_bean->getFormSNo();?>" required>
                            </div>
                        </div>



                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">HBL Branch. <font color="red">*</font></label>
                            <div class="col-sm-9">

                                <select name="<?php echo Variable::$BANK_BRANCH?>" class="form-control" required>
                                    <option value="">--Choose--</option>
                                    <?php
                                    foreach( $bankbranch as $bank )

                                    {
                                        $bank_branch_id=$bank['bank_branch_id'];
                                        $bank_branch_name=	$bank['name'];
                                        //echo("".$bank_branch_name);

                                        ?>
                                        <option value="<?php echo '' . $bank_branch_id ?>" <?php if($candidate_bean->getBankBranch() == $bank_branch_id){echo "selected";}?>> <?php echo '' . $bank_branch_name ?>  </option>

                                        <?php
                                    }
                                    ?>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" >
                            <label for="pwd" class="col-sm-3 control-label">Challan No. <font color="red">*</font></label>
                            <div class="col-sm-9">

                                <input type="text"  name="<?php echo Variable::CHALLAN_NO();?>" class="form-control" maxlength="4" pattern="([0-9]{1,4})" value="<?php echo $candidate_bean->getChallanNo();?>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pwd" class="col-sm-3 control-label">Challan Date<font color="red">*</font></label>
                            <div class="col-sm-9">

                                <input type="text" readonly="readonly" class="form-control" id="challan-date"  name="<?php echo Variable::CHALLAN_DATE()?>" value="<?php echo $candidate_bean->getChallanDate(); ?>" >
                            </div>
                        </div>


                        <hr class="divider">

                        <div class="panel-heading" style="background-color:#555;margin: 0 -1px 10px -1px;padding: 0 15px;font-family: 'Trebuchet MS',Arial,Helvetica,sans-serif;font-size: 14px;line-height: 26px;text-shadow: 1px 1px 1px #000;border-radius: 7px 7px 0 0;color: white;">Picture (Please upload your recent passport size photograph with white background) </div>

                        <div class="form-group">
                            <div class="col-sm-4">
                                <input type="file" class="form-control" name="<?php echo Variable::$STUDENT_PICTURE;?>"  accept="image/jpeg" id="input-pic" <?php echo $img_input;?>>
                                <label for="pwd">Only JPG File is allowed</label>
                            </div>

                            <div class="col-sm-8">
                                <div style="border:1px solid black; width: 25%;" id="div-pic">
                                    <img <?php echo $img_src;?>>
                                </div>
                            </div>
                        </div>




















                        <!--    <input type="submit" class="btn btn-default pull-right" value='Next &Gt;' >-->
                        <button type="submit" class="btn btn-info">Next
                            <i class="glyphicon glyphicon-arrow-right"></i>
                        </button>
                        <?php echo form_close(); ?>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <?php
    $distId=$candidate_bean->getDistrict();
    //echo("qwer".$a);
    ?>




    <script>

        //-------------------------Copy text in permanent to postal address and guardian address

        $('#sameAsPermanent').click(function() {
            if ($(this).is(':checked')) {
                var peramanent =$('#permanent').val();
                $('#guardianAddress').text(peramanent);
                $("#sameAsPostal").prop("disabled", true);
                //   $("input.group1").prop("disabled", false);

            }else{
                $("#sameAsPostal").prop("disabled", false);
            }
        });
        $('#sameAsPostal').click(function() {
            if ($(this).is(':checked')) {
                var peramanent =$('#postal').val();
                $('#guardianAddress').text(peramanent);
                $("#sameAsPermanent").prop("disabled", true);

            }
            else{
                $("#sameAsPermanent").prop("disabled", false);

            }
        });

        $('#sameAsAbove').click(function() {
            if ($(this).is(':checked')) {
                var peramanent =$('#permanent').val();
                $('#postal').text(peramanent);
            }
        });


        // country combo change then disabled provence and area

        $("#country").change(function() {

            var txt = $("#country option:selected").text();
            var value = $(this).val();
            //168 for PAKISTAN
            if(value != 168){
                DisabledDistrictInformation();

            }else if(value == 168){
                $("#sindh").prop('disabled', false);
                $("#all").prop('disabled', false);
                $("#urban").prop('disabled', false);
                $("#rural").prop('checked', false);


                $("#pro").prop("disabled", false);

            }


            //  alert(txt);

        });
        function DisabledDistrictInformation(){
            $("#sindh").prop('checked', false);
            $("#sindh").prop('disabled', true);
            $("#all").prop('checked', false);
            $("#all").prop('disabled', true);

            $("#urban").prop('checked', false);
            $("#urban").prop('disabled', true);
            $("#rural").prop('checked', false);
            $("#rural").prop('disabled', true);

            $('#pro option').remove();

            $("#pro").prop("disabled", true);

        }
        // ---------------------------end of country combo -------------------------------



        // ---------------------------Province Radio changed other sindh   -------------------------------


        $('#pro option').remove();

        var province= $('input:radio[name=province]:checked').val();
        //alert(province);
        //   $("#selfMorning").prop('disabled', false);


        getProvance(province);

        var countryId="<?php echo ($candidate_bean->getNationality());?>";
        if(countryId != ""){
            //pakistan =168
            // alert(countryId);
            if(countryId != 168){
                DisabledDistrictInformation();
            }

        }

        $("input:radio[name=province]").click(function() {
            // alert(province);
            var value = $(this).val();
            $('#pro option').remove();
            //  alert(value);
            getProvance(value);
            //  alert(value);
            //disabled Area
            if (value == "ALL") {

                $("#urban").prop('checked', false);
                $("#urban").prop('disabled', true);
                $("#rural").prop('checked', false);
                $("#rural").prop('disabled', true);
            }else{
                $("#urban").prop('disabled', false);
                $("#rural").prop('disabled', false);

            }
        });



        // ---------------------------end   -------------------------------



        function getProvance(value){
            //  var DistId ="";
            var distId="<?php echo ($candidate_bean->getDistrict());?>";
            //alert(distId);

            $.post("<?php echo (base_url().Variable::PROVINCE_CHOICE());?>",
                {provanceName: value}
                , function(data) {
                    //      alert(data);
                    var al = jQuery.parseJSON(data);
                    //    alert(al.length);



                    $("#pro").append("<option value=''>--Choose--</option>");

                    var sel='';
                    for(var i=0;i<al.length;i++){


                        $("#pro").append("<option value='"+al[i].id+"' "+sel+"'>"+al[i].name+"</option>");
                        $('#pro').val(distId);

                    }

                }


            );
        }


        //---------------id card  setting ------------------------------------------------
        jQuery(function($){
            $("#idcard").mask("99999-9999999-9");
        });

        //-----------------------------picture setting -----------------------------------

        function putPicture(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#div-pic').html("<img src='' id='img-pic' >");
                    $('#img-pic').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }
        $("#input-pic").change(function(){
            putPicture(this);
        });

    </script>
    <script>

        //-----------------------------challan Date and Date Of Birth Setting ----------------------------------
        $(document).ready(function() {
            $('#birth-date').dateDropdowns({submitFormat: "dd-mm-yyyy",submitFieldName: 'birth-date',minAge: 16, minYear:1940, required: true,defaultValue: "<?php echo $candidate_bean->getDateOfBirth();?>"});
            $('#birth-date').attr('type','text');
            $('#challan-date').dateDropdowns({submitFormat: "dd-mm-yyyy",submitFieldName: 'challan-date',minYear: 2015, required: true,defaultValue: "<?php echo $candidate_bean->getChallanDate();?>"});
            $('#challan-date').attr('type','text');

        });
    </script>

