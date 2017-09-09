<?php $this->load->library("variable"); ?>
<?php  echo form_open("app_form/educational_information_save"); ?>

<div class="page-content">
    <div class="container">
        <div class="row">
            <div class='col-md-10'>
                <div style="color:white;background-color: red;"><?php echo $message;?></div>
<input type="hidden" name="<?php echo Variable::$TAB?>" value="<?php echo ''.Variable::$TAB_EDUCATION; ?>">
<div class="bg-danger">

</div>
<div class="col-md-12">
    <div class="form-group bg-success ">
        <label><font size="5"> Academic Record</font><font color="red">*</font></label>
    </div>
    <!-- ----- SSC / Matriculation ------ -->

    <table class="table-bordered table-responsive">
        
        <tr class="active">
        <th></th>
        <th>Board</th>
        <th>Seat No.</th>
        <th>Total Marks</th>
        <th>Obtained Marks</th>
        <th>Year</th>
        <th>Group</th>
        </tr>

       <td>SSC / Matriculation/ O Level</td>
        <td>
            <select class="form-control" name="<?php echo Variable::$SSC_BOARD?>"  required>
                <option value="">--Choose--</option>
                <?php
                foreach( $boards as $board )
                {
                    $issuer_id=$board['issuer_id'];
                    $boardname=	$board['name'];
                    $location=	$board['location'];
                    ?>
                    <option value="<?php echo '' . $issuer_id; ?>"  <?php if($ssc_bean->getIssuerId() == $issuer_id){echo "selected";}?>> <?php echo '' . $location ?>  </option>

                    <?php
                }
                ?>

            </select>
        </td>
        <td> <input type="text" class="form-control" maxlength="8" name="<?php echo Variable::$SSC_SEAT_NO?>" value="<?php echo $ssc_bean->getSeatNo();?>" required></td>
        <td>  <input type="text" class="form-control" maxlength="4" name="<?php echo Variable::$SSC_TOTAL?>" value="<?php echo $ssc_bean->getTotalMarks();?>" required></td>
        <td> <input type="text" class="form-control" maxlength="4" name="<?php echo Variable::$SSC_OBTAIN?>" value="<?php echo $ssc_bean->getObtainMarks();?>" required>
        </td>
        <td>     <select class="form-control" name="<?php echo Variable::$SSC_YEAR?>" required>
                <option value="">--Choose--</option>
                <?php
                if($ssc_bean->getPassingYear()){
                    Variable::yearOptions($ssc_bean->getPassingYear());
                }
                else if($is_master){
                    Variable::yearOptions(Variable::CURRENT_YEAR()-5);
                }else{
                    Variable::yearOptions(Variable::CURRENT_YEAR()-2);
                } ?>
            </select></td>
        <td><select class="form-control" name="<?php echo Variable::$SSC_GROUP?>" required>
                <option value="">--Choose--</option>

                <?php
                foreach( $sscgroup as $_program )

                {
                    $program_id=$_program['program_id'];
                    $program_name=	$_program['name'];

                    ?>
                    <option value="<?php echo '' . $program_id ?>" <?php if($ssc_bean->getProgramId() == $program_id){echo "selected";}?>> <?php echo '' . $program_name ?>  </option>

                    <?php
                }
                ?>

            </select>
        </td>

    </tr>
        </table>

    <div class="form-group bg-success" >
        <label >SSC / Matriculation/ O Level<font color="red">*</font></label>
    </div>

    <div class="form-group">
        <label>Board</label>
        <select class="form-control" name="<?php echo Variable::$SSC_BOARD?>"  required>
            <option value="">--Choose--</option> 
            <?php
            foreach( $boards as $board )
            {
                $issuer_id=$board['issuer_id'];
                $boardname=	$board['name'];
                ?>
                <option value="<?php echo '' . $issuer_id; ?>"  <?php if($ssc_bean->getIssuerId() == $issuer_id){echo "selected";}?>> <?php echo '' . $boardname ?>  </option>

                <?php
            }
                ?>

        </select>
    </div>

    <div class="form-group">
        <label>Seat No.</label>
        <input type="text" class="form-control" maxlength="8" name="<?php echo Variable::$SSC_SEAT_NO?>" value="<?php echo $ssc_bean->getSeatNo();?>" required>
    </div>
    <div class="form-group">
        <label>Total Marks</label>
        <input type="text" class="form-control" maxlength="4" name="<?php echo Variable::$SSC_TOTAL?>" value="<?php echo $ssc_bean->getTotalMarks();?>" required>
    </div>
    <div class="form-group">
        <label>Obtained Marks</label>
        <input type="text" class="form-control" maxlength="4" name="<?php echo Variable::$SSC_OBTAIN?>" value="<?php echo $ssc_bean->getObtainMarks();?>" required>
    </div>
    <div class="form-group">
        <label>Group</label>
        <select class="form-control" name="<?php echo Variable::$SSC_GROUP?>" required>
            <option value="">--Choose--</option>

            <?php
            foreach( $sscgroup as $_program )

            {
                $program_id=$_program['program_id'];
                $program_name=	$_program['name'];

                ?>
                <option value="<?php echo '' . $program_id ?>" <?php if($ssc_bean->getProgramId() == $program_id){echo "selected";}?>> <?php echo '' . $program_name ?>  </option>

                <?php
            }
            ?>

        </select>
    </div>
    <div class="form-group">
        <label>Year</label>
        <select class="form-control" name="<?php echo Variable::$SSC_YEAR?>" required>
            <option value="">--Choose--</option> 
            <?php
            if($ssc_bean->getPassingYear()){
                Variable::yearOptions($ssc_bean->getPassingYear());
            }
            else if($is_master){
                    Variable::yearOptions(Variable::CURRENT_YEAR()-5);
            }else{
                Variable::yearOptions(Variable::CURRENT_YEAR()-2);
            } ?>
        </select>
    </div>

    <hr>
    
    <!--  ----  HSC / Intermediate   ------------ -->
    <div class="form-group bg-info">
        <label>HSC / Intermediate / A Level<font color="red">*</font></label>
    </div>

    <div class="form-group">
        <label>Board</label>
        <select class="form-control" name="<?php echo Variable::$HSC_BOARD?>" required>
            <option value="">--Choose--</option>
            <?php
            foreach( $boards as $board )

            {
                $issuer_id=$board['issuer_id'];
                $board_name=$board['name'];

                ?>
                <option value="<?php echo '' . $issuer_id ?>" <?php if($hsc_bean->getIssuerId() == $issuer_id){echo "selected";}?>> <?php echo '' . $board_name ?>  </option>

                <?php
            }
            ?>

        </select>
    </div>

    <div class="form-group">
        <label>Seat No</label>
        <input type="text" class="form-control" maxlength="8" name="<?php echo Variable::$HSC_SEAT_NO;?>" value="<?php echo $hsc_bean->getSeatNo();?>" required>
    </div>
    <div class="form-group">
        <label>Total Marks</label>
        <input type="text" class="form-control" maxlength="4" name="<?php echo Variable::$HSC_TOTAL?>" value="<?php echo $hsc_bean->getTotalMarks();?>" required>
    </div>
    <div class="form-group">
        <label>Obtained Marks</label>
        <input type="text" class="form-control" maxlength="4" name="<?php echo Variable::$HSC_OBTAIN?>" value="<?php echo $hsc_bean->getObtainMarks();?>" required>
    </div>
    <div class="form-group">
        <label>Group</label>
        <select class="form-control" name="<?php echo Variable::$HSC_GROUP?>" required>
            <option value="">--Choose--</option>
            <?php
            foreach( $hscgroup as $bachlor_program )

            {
                $program_id=$bachlor_program['program_id'];
                $program_name=	$bachlor_program['name'];

                ?>
                <option value="<?php echo '' . $program_id ?>" <?php if($hsc_bean->getProgramId() == $program_id){echo "selected";}?>> <?php echo '' . $program_name ?>  </option>

                <?php
            }
            ?>
        </select>
    </div>
    <div class="form-group">
        <label>Year</label>
        <select class="form-control" name="<?php echo Variable::$HSC_YEAR?>" required>
            <option value="">--Choose--</option>
            <?php if($hsc_bean->getPassingYear()){
                Variable::yearOptions($hsc_bean->getPassingYear());
            }
            else if($is_master){
                Variable::yearOptions(Variable::CURRENT_YEAR()-3);
            }else{
                Variable::yearOptions(Variable::CURRENT_YEAR()-1);
            } ?>
        </select>
    </div>

    <hr>
    

    <!-- --------  Graduation / Bachelor -----  -->
    <?php if($is_master){ ?>
        <div class="form-group bg-primary">
        <label>Bachelor / Graduation<font color="red">*</font></label>
    </div>
    <div class="form-group">
        <label>University</label>
        <select class="form-control" name="<?php echo Variable::GRD_UNIVERSITY()?>" required>
            <option value="">--Choose--</option>

            <?php
            foreach( $university as $uni )

            {
                $issuer_id=$uni['issuer_id'];
                $issuer_name=	$uni['name'];

                ?>
                <option value="<?php echo '' . $issuer_id ?>" <?php if($grd_bean->getIssuerId() == $issuer_id){echo "selected";}?>> <?php echo '' . $issuer_name ?>  </option>

                <?php
            }
            ?>

        </select>
    </div>
    
    <div class="form-group">
        <label>Degree Years</label>
        <select class="form-control" name="<?php echo Variable::$GRD_DEGREE_YEARS?>" required>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>

    </div>

    <div class="form-group">
        <label>Seat No</label>
        <input type="text" class="form-control" maxlength="12" name="<?php echo Variable::$GRD_SEAT_NO;?>" value="<?php echo $grd_bean->getSeatNo();?>" required>
    </div>
    <div class="form-group">
        <label>Total Marks</label>
        <input type="text" class="form-control" maxlength="4" name="<?php echo Variable::$GRD_TOTAL?>" value="<?php echo $grd_bean->getTotalMarks();?>" required>
    </div>

    <div class="form-group">
        <label>Obtained Marks</label>
        <input type="text" class="form-control" maxlength="4" name="<?php echo Variable::$GRD_OBTAIN?>" value="<?php echo $grd_bean->getObtainMarks();?>" required>
    </div>

    <div class="form-group">
        <label>Degree</label>
        <select class="form-control" name="<?php echo Variable::$GRD_GROUP?>" id="select_degree" onchange="degreeChanged(0,0,0);" required>
            <option value="">--choose--</option>


            <?php
            foreach( $bachlorProgram as $bachlor_program )

            {
                $program_id=$bachlor_program['program_id'];
                $program_name=	$bachlor_program['name'];

                ?>
                <option value="<?php echo '' . $program_id ?>" <?php if($grd_bean->getProgramId() == $program_id){echo "selected";}?>> <?php echo '' . $program_name ?>  </option>

                <?php
            }
            ?>
        </select>
    </div>

    <div class="form-group" id="subject1-div">
        <label>Optional Subject</label>
        <select name="<?php echo Variable::GRD_SUB_ONE()?>" class="form-control" id="subject1">
            <?php if(count($candidate_optional_sub_ids)>0 ){
                foreach($optional_subjects as $os){
                   ?>
                    <option value="<?php echo $os['subject_id'];?>"><?php echo $os['name'];?></option>
                    <?php
                }
            }?>
        </select>
    </div>
    
    <div class="form-group" id="subject2-div">
        <label>Optional Subject</label>
        <select name="<?php echo Variable::GRD_SUB_TWO(); ?>" class="form-control" id="subject2" >
            <?php if(count($candidate_optional_sub_ids)>0){
                foreach($optional_subjects as $os){
                    ?>
                    <option value="<?php echo $os['subject_id'];?>"><?php echo $os['name'];?></option>
                    <?php
                }
            }?>
        </select>
    </div>
    
    <div class="form-group" id="subject3-div">
        <label>Optional Subject</label>
        <select name="<?php echo Variable::GRD_SUB_THREE()?>" class="form-control" id="subject3" >
            <?php if(count($candidate_optional_sub_ids)>0){
                foreach($optional_subjects as $os){
                    ?>
                    <option value="<?php echo $os['subject_id'];?>"><?php echo $os['name'];?></option>
                    <?php
                }
            }?>
        </select>
    </div>

    <div class="form-group">
        <label>Year</label>
        <select class="form-control" name="<?php echo Variable::GRD_YEAR(); ?>" required>
            <option value="">--Choose--</option> 
            <?php if($grd_bean->getPassingYear()){
                Variable::yearOptions($grd_bean->getPassingYear());
            }
            else{
                Variable::yearOptions();
            } ?>
        </select>
    </div>
	<?php } ?>
    
    
  
    <a href="<?php echo Variable::GOTO_TAB_PERSONAL();?>?<?php echo Variable::$REQUEST;?>=<?php echo Variable::$TAB_PERSONAL;?>" class="btn btn-default"><i class="glyphicon glyphicon-arrow-left"></i>  <?php echo Variable::$PREVIOUS;?></a>
    <button type="submit" class="btn btn-info">Next
        <i class="glyphicon glyphicon-arrow-right"></i>
    </button>
<!--    <input type="submit" class="btn btn-default floatr" name="--><?php //echo Variable::$REQUEST;?><!--" value="--><?php //echo Variable::$NEXT;?><!--" >-->

    <?php if($is_master){ ?>

    <script>
        function degreeChanged(a,b,c) {
            var programId = $("#select_degree").val();
            if (programId == 0) {
                $("#subject1-div").fadeOut(500);
                $("#subject2-div").fadeOut(500);
                $("#subject3-div").fadeOut(500);
                $("#subject1-div").removeAttr('required');
                $("#subject2-div").removeAttr('required');
                $("#subject3-div").removeAttr('required');
            }
            else {
                $.post("<?php echo (base_url().Variable::AJAX_OPTIONAL_SUBJECT());?>",
                        {programId: programId}
                , function(data) {
                        var al = jQuery.parseJSON(data);
                    if (al.length < 1 || al[0].id == null) {
                        $("#subject1-div").fadeOut(500);
                        $("#subject2-div").fadeOut(500);
                        $("#subject3-div").fadeOut(500);
                        $("#subject1").html("");
                        $("#subject2").html("");
                        $("#subject3").html("");
                        $("#subject1-div").removeAttr('required');
                        $("#subject2-div").removeAttr('required');
                        $("#subject3-div").removeAttr('required');
                    } else {
                        $("#subject1").html("");
                        $("#subject2").html("");
                        $("#subject3").html("");
                        $("#subject1-div").fadeIn(500);
                        $("#subject2-div").fadeIn(500);
                        $("#subject3-div").fadeIn(500);
                        $("#subject1-div").prop('required',true);
                        $("#subject2-div").prop('required',true);
                        $("#subject3-div").prop('required',true);
                        for(var i=0;i<al.length;i++){
                            $("#subject1").append("<option value='"+al[i].id+"'>"+al[i].name+"</option>");
                            $("#subject2").append("<option value='"+al[i].id+"'>"+al[i].name+"</option>");
                            $("#subject3").append("<option value='"+al[i].id+"'>"+al[i].name+"</option>");
                        }

                    }
                    if(a != 0){
                        $("select[name='<?php echo Variable::$GRD_SUB_ONE;?>']").val(a);
                    }
                    if(b!=0){
                        $("select[name='<?php echo Variable::$GRD_SUB_TWO;?>']").val(b);  
                    }
                    if(b!=0){
                        $("select[name='<?php echo Variable::$GRD_SUB_THREE;?>']").val(c);  
                    }

                });
            }

        }
    </script>
    <?php
            if(count($candidate_optional_sub_ids)>0){
            ?>

            <script>
                $("#subject1").val('<?php echo $candidate_optional_sub_ids[0]['subject_id'];?>');
                $("#subject2").val('<?php echo $candidate_optional_sub_ids[1]['subject_id'];?>');
                $("#subject3").val('<?php echo $candidate_optional_sub_ids[2]['subject_id'];?>');
            </script>
            <?php
        }else{
            ?>
        <script>
            $("#subject1-div").fadeOut(0);
            $("#subject2-div").fadeOut(0);
            $("#subject3-div").fadeOut(0);
            $("#subject1-div").removeAttr('required');
            $("#subject2-div").removeAttr('required');
            $("#subject3-div").removeAttr('required');
        </script>
        <?php
    }?>
    <?php } ?>
</div>

<?php echo form_close(); ?>

</div>
            </div>
        </div>
    </div>
