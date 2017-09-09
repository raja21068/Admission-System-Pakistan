<?php $this->load->library("variable"); ?>
<style>
    .custom-bg-red{
        background-color: #de5959;
        color: white;
    }
    .custom-bg-white{
        background-color: #FFFFFF;
        color: black;
    }
</style>
<?php  echo form_open("app_form/tab_apply_for_save"); ?>

<div class="page-content">
    <div class="container">
        <div class="row">
            <div class='col-md-10'>
                <div style="color:white;background-color: red;"><?php echo $message;?></div>
                <input type="hidden" name="<?php echo Variable::TAB();?>" value="<?php echo ''.Variable::TAB_APPLY_FOR(); ?>">
                <div>
                    <div class="form-group">
                        <label >Select Campus</label>
                        <select class="form-control" id="select-campus" name="<?php echo Variable::CAMPUS();?>" onchange="campusChange();" required>
                            <option value="0">--Choose--</option>                                        <?php
                            for ($index = 0; $index < count($campus); $index++) {
                                $bean = $campus[$index];
                                ?>
                                <option value="<?php echo '' . $bean['campus_id']; ?>" <?php if(in_array($bean['campus_id'],$applied_campus)){echo "selected";}?>> <?php echo '' . $bean['name'].' '.$bean['location']; ?>  </option>
                                <?php
                            }
                            ?>
                        </select>
                    </div>


                    <div class="form-group" id="div-category">
                        <label >Please select your option from the following categories and tick the appropriate box. (You can tick more than one category if applicable) </label>

                        <div class="boxborder">
                            <div class="col-md-3">

                                <label class="checkbox-inline">
                                    <input type="checkbox" id="merit" name="" value=""  checked="checked" disabled="disabled"/> General Merit/District Quota
                                </label>
                            </div>
                            <div class="col-md-3">

                                <label class="checkbox-inline">
                                    <input type="checkbox" id="selfMorning" name="<?php echo Variable::$CATEGORY;?>" value="1" <?php if(in_array(1, $applied_category)){echo "checked='checked'";}?>/>Self Finance (Morning)
                                </label>
                            </div>
                            <div class="col-md-3">

                                <label class="checkbox-inline">
                                    <input type="checkbox"   id="sf_evening"  name="<?php echo Variable::$CATEGORY;?>" value="2" <?php if(in_array(2, $applied_category)){echo "checked='checked'";}?> onclick="eveningCheck();" />Self Finance (Evening)
                                </label>
                            </div>
                            <div class="col-md-3">

                                <label class="checkbox-inline">
                                    <input type="checkbox" id="disablePerson" name="<?php echo Variable::$CATEGORY;?>" value="4" <?php if(in_array(4, $applied_category)){echo "checked='checked'";}?>/>Disabled Person Quota
                                </label>
                            </div>
                            <div class="col-md-3">

                                <label class="checkbox-inline">
                                    <input type="checkbox" id="suEmployee" name="<?php echo Variable::$CATEGORY;?>" value="3" <?php if(in_array(3, $applied_category)){echo "checked='checked'";}?>/> S.U Employees Quota
                                </label>
                            </div>
                            <div class="col-md-3">

                                <label class="checkbox-inline">
                                    <input type="checkbox" id="nomination" name="<?php echo Variable::$CATEGORY;?>" value="5" <?php if(in_array(5, $applied_category)){echo "checked='checked'";}?>/> Nomination Quota
                                </label>
                            </div>
                            <div class="col-md-3">

                                <label class="checkbox-inline">
                                    <input type="checkbox" id='affiliatedCollege' name="<?php echo Variable::$CATEGORY;?>" value="6" <?php if(in_array(6, $applied_category)){echo "checked='checked'";}?>/>Affiliated College Quota
                                </label>
                            </div>
                            <div class="col-md-3">

                                <label class="checkbox-inline">
                                    <input type="checkbox" id='sports' name="<?php echo Variable::$CATEGORY;?>" value="7" <?php if(in_array(7, $applied_category)){echo "checked='checked'";}?>/>Sports Quota
                                </label>
                            </div>

                        </div>
                    </div>

                    <hr style="width: 100%; color: black; height: 1px; background-color:white;" />
                    <div class="continer">
                        <p><em>  <strong> Please select minimum 15 choices of subjects/disciplines in order of preference according to your last qualification. </strong></em> </p>
                    </div>
                    <div class="continer">
                        <p><em>  <strong> Please check Prospectus 2016 for pre-requisite for each degree program. </strong></em> </p>
                    </div>
                    <div class="continer">
                        <p><em>  <strong class="custom-bg-red"> Red highlighted degree programs / disciplines / subjects indicates NO eligibility according to last qualification  </strong></em> </p>
                    </div>


                    <div class="col-md-6" <?php if(count($applied_campus)>0)if(count($campus_progs_morning)>0){}else{echo "style='display: none;'";}?> id="div-morning">
                        <div class="form-group">
                            <label for="pwd">Morning Degree Programs<font color="red">*</font></label>

                            <select class="select-morning-choice form-control" id="select-morning" onchange="checkChoiceEligible(this,true);" onkeydown="checkChoiceEligible(this,true);">
                                <?php
                                $scripting = "";
                                for($i=0;$i<count($campus_progs_morning);$i++){
                                    $set = $campus_progs_morning[$i];

                                    if(in_array($set['campus_program_of_study_id'],$morning_cnpos_ids)){
                                        //$scripting .= "addMorningChoice(".$set['campus_program_of_study_id'].",'".$set['Name']." (".$set['programName'].")');";
                                    }
                                    else if(in_array($set['campus_program_of_study_id'],$cmpos_morning_filteration_ids)){
                                        echo "<option class='custom-bg-white' value='".$set['campus_program_of_study_id']."' >".$set['Name']." (".$set['programName'].")</option>";
                                    }else{
                                        echo "<option class='custom-bg-red' value='0' >".$set['Name']." (".$set['programName'].")</option>";
                                    }
                                }

                                //add morning selected choices into html table
                                for($i=0;$i<count($morning_cnpos_rs);$i++){
                                    $set = $morning_cnpos_rs[$i];
                                    $scripting .= "addMorningChoice(".$set['campus_program_of_study_id'].",'".$set['name']."');";
                                }
                                ?>

                            </select>
                            <button type="button" class="btn btn-block btn-info" id="add-morning-choice-btn" >ADD</button>
                        </div>

                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <td>#</td>
                                <td>Name</td>
                                <td>X</td>
                            </tr>
                            </thead>
                            <tbody id="table-body-morning">

                            </tbody>

                        </table>

                    </div>


                    <div class="col-md-6"  <?php if(count($applied_campus)>0)if(count($campus_progs_evening)>0){}else{echo "style='display: none;'";}?> id="div-evening" >

                        <div class="form-group">
                            <label for="pwd">Evening Degree Programs<font color="red">*</font></label>

                            <select id="select-evening" class="select-evening-choice form-control" onchange="checkChoiceEligible(this,false);" onkeydown="checkChoiceEligible(this,false);">
                                <?php for($i=0;$i<count($campus_progs_evening);$i++){
                                    $set = $campus_progs_evening[$i];
                                    if(in_array($set['campus_program_of_study_id'],$evening_cnpos_ids)){
                                        //$scripting .=  "addEveningChoice(".$set['campus_program_of_study_id'].",'".$set['Name']." (".$set['programName'].")');";
                                    }
                                    else if(in_array($set['campus_program_of_study_id'],$cmpos_evening_filteration_ids)){
                                        echo "<option class='custom-bg-white' value='".$set['campus_program_of_study_id']."' >".$set['Name']." (".$set['programName'].")</option>";
                                    }else{
                                        echo "<option class='custom-bg-red' value='0' >".$set['Name']." (".$set['programName'].")</option>";
                                    }
                                    ?>
                                    <?php
                                }

                                for($i=0;$i<count($evening_cnpos_rs);$i++){
                                    $set = $evening_cnpos_rs[$i];
                                    $scripting .=  "addEveningChoice(".$set['campus_program_of_study_id'].",'".$set['name']."');";
                                }

                                ?>
                            </select>
                            <button type="button" class="btn btn-block btn-info" id="add-evening-choice-btn" >ADD</button>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <td>#</td>
                                <td>Name</td>
                                <td>X</td>
                            </tr>
                            </thead>
                            <tbody id="table-body-evening">

                            </tbody>

                        </table>
                    </div>
                    <!--
    <div class="form-group">
        <label >If You Are Not Selected In Above Campus This Campus Will Be The Optional (Donot select if you donot want to study in other campus) </label>
        <select class="form-control" id="select-campus" name="<?php //echo Variable::CAMPUS_OPTIONAL();?>" >
            <option value="0">--Choose--</option>                                        <?php
                    // for ($index = 0; $index < count($campus); $index++) {
                    //   $bean = $campus[$index];
                    ?>
                <option value="<?php// echo '' . $bean['campus_id']; ?>" <?php// if($bean['campus_id'] == $applied_optional_campus){echo "selected";}?>> <?php// echo '' . $bean['name'].' '.$bean['location']; ?>  </option>
                <?php
                    //}
                    ?>
        </select>
    </div>
-->
                    <div class="hidden" id="div-choice-hidden">
                    </div>
                    <div class="hidden" id="div-choice-hidden-evening">
                    </div>


                </div>
                <div class="col-md-12">
                    <a href="<?php echo Variable::GOTO_TAB_EDUCATION();?>" class="btn btn-default"><i class="glyphicon glyphicon-arrow-left"></i> <?php echo Variable::$PREVIOUS;?></a>
                    <button type="submit" id="nextbtn" class="btn btn-info">Next
                        <i class="glyphicon glyphicon-arrow-right"></i>
                    </button>
                </div>

                <?php

                // print_r($applied_category);
                $applied_campus= in_array(2, $applied_category);

                //  echo($applied_campus);
                // if(in_array(0, $applied_category)){
                //   echo ("Raja");
                // }
                ?>
                <!--  AJAX For Changing Campus  -->




                <script>
                    // $("#nextbtn").attr('disabled', 'disabled');
                    //$("#nextbtn").prop('disabled', true);
                    //$( "#cbCountry" ).combobox({ disabled: true });



                     $("#sf_evening").change(function() {
                     if(!this.checked) {

                     $("#table-body-evening tr").remove();
                     $("#div-choice-hidden-evening").empty();

                     }
                     });


                    $("#div-evening").fadeOut(500);

                    var applied_campus="<?php echo $applied_campus ?>";
                    if(applied_campus){
                        $("#div-evening").fadeIn(500);
                    }




                    // alert(applied_campus);
                    $('#select-campus').change(function() {
                        var id = $("#select-campus").val();
                        var txt = $("#select-campus option:selected").text();

                        $("#div-choice-hidden").empty();


                        var program_id="<?php echo ($candidate_bean->getProgramTypeId());?>";
                        //alert(program_id);


                        if(program_id==1) {


                            //campus jamshoro
                            if (id == 1) {
                                // do nothing

                                $("#selfMorning").prop('disabled', false);
                                $("#sf_evening").prop('disabled', false);
                                $("#disablePerson").prop('disabled', false);
                                $("#suEmployee").prop('disabled', false);
                                $("#nomination").prop('disabled', false);
                                $("#affiliatedCollege").prop('disabled', false);
                                $("#sports").prop('disabled', false);


                            } else if (id == 3 || id==4 || id==5 || id==6 || id==7 || id==8) {
                                //OTHER CAMPUS

                                $("#selfMorning").prop('checked', false);
                                $("#sf_evening").prop('checked', false);
                                $("#disablePerson").prop('checked', false);
                                $("#suEmployee").prop('checked', false);
                                $("#nomination").prop('checked', false);
                                $("#affiliatedCollege").prop('checked', false);
                                $("#sports").prop('checked', false);

                                $("#selfMorning").prop('disabled', true);
                                $("#sf_evening").prop('disabled', true);
                                $("#disablePerson").prop('disabled', true);
                                $("#suEmployee").prop('disabled', true);
                                $("#nomination").prop('disabled', true);
                                $("#affiliatedCollege").prop('disabled', true);
                                $("#sports").prop('disabled', true);




                            }
                        }// end of bachlor program

                        if(program_id==2) {

                            if (id == 1) {
                                // do nothing

                                $("#selfMorning").prop('disabled', false);
                                $("#sf_evening").prop('disabled', false);
                                $("#disablePerson").prop('disabled', false);
                                $("#suEmployee").prop('disabled', false);
                                $("#nomination").prop('disabled', false);
                                $("#affiliatedCollege").prop('disabled', false);
                                $("#sports").prop('disabled', false);


                            } else if (id == 3 || id==4 || id==5 || id==6 || id==7 || id==8) {

                                $("#selfMorning").prop('checked', false);
                                $("#sf_evening").prop('checked', false);
                                $("#disablePerson").prop('checked', false);
                                $("#suEmployee").prop('checked', false);
                                $("#nomination").prop('checked', false);
                                $("#affiliatedCollege").prop('checked', false);
                                $("#sports").prop('checked', false);

                                $("#selfMorning").prop('disabled', true);
                                $("#sf_evening").prop('disabled', false);
                                $("#disablePerson").prop('disabled', true);
                                $("#suEmployee").prop('disabled', true);
                                $("#nomination").prop('disabled', true);
                                $("#affiliatedCollege").prop('disabled', true);
                                $("#sports").prop('disabled', true);




                            }

                        }// end of master program

                        //alert(txt);

                    });


                    function addChoice(elem,id,isMorning){
                        $("#btn-next").removeAttr("disabled");
                        var name = "";
                        if(isMorning){
                            name = "<?php echo Variable::$MORNING_CHOICE;?>";

                        }else{
                            name = "<?php echo Variable::$EVENING_CHOICE;?>";
                            //$("#div-choice-hidden-evening").append("<input type='checkbox' name='"+name+"' value='"+id+"' checked>");
                        }
                        $(elem).append("<input type='checkbox' name='"+name+"' value='"+id+"' checked>");
                    }

                    function deleteChoiceElement(elem,id){
                        $(elem).find("input[value='"+id+"']").remove();


                        if( $(elem).children().length == 0 ){
                            $("#btn-next").attr("disabled","disabled");
                        }
                    }

                    function campusChange(){
                        var campusId = $("#select-campus").val();
                        if (campusId == 0) {
                            $("#div-morning").fadeOut(500);
                            $("#div-evening").fadeOut(500);
                        }else{
                            eveningCheck();
                            $("#div-morning").fadeIn(500);
                            $.post("<?php echo (base_url().Variable::AJAX_CHOICES());?>",
                                { campusId: $("#select-campus").val(),
                                    shiftId: '<?php echo Variable::$MORNING_SHIFT;?>'
                                }
                                , function(data) {
//                        alert(data);
                                    $(".select-morning-choice").html("");
                                    var al = jQuery.parseJSON(data);
                                    if (al.all_progs.length < 1 ){

                                    }
                                    else{
                                        for(var i=0;i<al.all_progs.length;i++){
                                            if(al.all_progs[i].is_okay){
                                                $(".select-morning-choice").append("<option class='custom-bg-white' value='"+al.all_progs[i].id+"'>"+al.all_progs[i].name+"</option>");
                                            }else{
                                                $(".select-morning-choice").append("<option class='custom-bg-red' value='0'>"+al.all_progs[i].name+"</option>");
                                            }
                                        }
                                    }
                                });

                            $("#table-body-morning").html('');
                            $("#table-body-evening").html('');
                        }
                    }
                    //$("#select-campus").change();

                    function eveningCheck(){
                        var campusId = $("#select-campus").val();
                        if($("#sf_evening").is(':checked') && campusId !==0 ){

                            $("#div-evening").fadeIn(500);
                            $.post("<?php echo (base_url().Variable::AJAX_CHOICES());?>",
                                { campusId: $("#select-campus").val(),
                                    shiftId: '<?php echo Variable::$EVENING_SHIFT;?>'
                                }
                                , function(data) {
                                    $(".select-evening-choice").html("");
                                    var al = jQuery.parseJSON(data);
                                    if (al.all_progs.length < 1 ){

                                    }
                                    else{
                                        for(var i=0;i<al.all_progs.length;i++){
                                            if(al.all_progs[i].is_okay){
                                                $(".select-evening-choice").append("<option class='custom-bg-white' value='"+al.all_progs[i].id+"'>"+al.all_progs[i].name+"</option>");
                                            }else{
                                                $(".select-evening-choice").append("<option class='custom-bg-red' value='0'>"+al.all_progs[i].name+"</option>");
                                            }
                                        }
                                    }
                                });

                        }else{
                            $("#div-evening").fadeOut(500);

                        }

                    }
                </script>

                <!--  AJAX For Adding Choices  -->
                <script>
                    $("#nextbtn").attr('disabled', 'disabled');
                    $("#nextbtn").removeClass('btn-default');

                    function addMorningChoice(id,txt){
                        var len = ($("#table-body-morning tr").length)+1;
                        $("#table-body-morning").append("<tr id='"+id+"'><td>"+len+"</td><td>"+txt+"</td><td><input type='button' onclick=\"deleteChoice("+id+",'"+txt+"');\" value='X' ></td></tr>");
                        $("#select-morning option[value='"+id+"']").remove();

                        //raja condiation minimum 1 choice select

                        if(len>0){
                            $("#nextbtn").attr('disabled', false);

                        }



                        var choiceLen = $("#select-morning option").length;
                        if(len >= <?php echo Variable::$CHOICE_MAX?> || choiceLen == 0){
                            $("#add-morning-choice-btn").attr('disabled','disabled');
                            $("#add-morning-choice-btn").removeClass('btn-default');

                        }
                        //    var campId = $("#select-campus").val();
                        //   minChoices(campId,len);





                        addChoice($("#div-choice-hidden"),id,true);
                    }
                    function minChoices(campusId,len){
                        var prog_id="<?php echo ($candidate_bean->getProgramTypeId());?>";
                        //       alert(prog_id);

                        if(campusId==<?php echo Variable::$ALLAM_I_I_KAZI_JAMSHORO ?> ) {
                            if (len < <?php echo Variable::$CHOICE_MAX ?>) {
                                $("#nextbtn").attr('disabled', 'disabled');
                                $("#nextbtn").removeClass('btn-default');


                            } else {
                                $("#nextbtn").attr('disabled', false);
                            }

                        }// end main campus selection

                    }



                    function addEveningChoice(id,txt){
                        var len = ($("#table-body-evening tr").length)+1;
                        $("#table-body-evening").append("<tr id='"+id+"'><td>"+len+"</td><td>"+txt+"</td><td><input type='button' onclick=\"deleteChoiceEvening("+id+",'"+txt+"');\" value='X' ></td></tr>");
                        $("#select-evening option[value='"+id+"']").remove();


                        //raja condiation minimum 1 choice select

                        if(len>0){
                            $("#nextbtn").attr('disabled', false);
                        }





                        var choiceLen = $("#select-evening option").length;
                        if(len >= <?php echo Variable::$CHOICE_MAX?> || choiceLen == 0){
                            $("#add-evening-choice-btn").attr('disabled','disabled');
                            $("#add-evening-choice-btn").removeClass('btn-default');
                        }
                        addChoice($("#div-choice-hidden-evening"),id,false);
                    }

                    function deleteChoice(elementNo,name){
                        $("#table-body-morning tr[id='"+elementNo+"']").remove();

//            if(elementNo == 0){
//                $("#select-morning").prepend("<option class='custom-bg-red' value=\""+elementNo+"\" >"+name+"</option>");
//            }else
                        {
                            $("#select-morning").prepend("<option class='custom-bg-white' value=\""+elementNo+"\" >"+name+"</option>");

                        }
                        $("#table-body-morning tr").each(function(index,elem){
                            var no = (index +1);
                            var d = $(elem).children().get(0);
                            $(d).html(no);

                        });
                        var len = ($("#table-body-morning tr").length);


                        if(len==0){
                            $("#nextbtn").attr('disabled', true);

                        }
                        if(len < <?php echo Variable::$CHOICE_MAX?>){
                            $("#add-morning-choice-btn").removeAttr('disabled');
                            $("#add-morning-choice-btn").addClass('btn-default');
                        }
                        // var campId = $("#select-campus").val();
                        // minChoices(campId,len);
                        deleteChoiceElement($("#div-choice-hidden"),elementNo);
                    }

                    /*delete evening choice from table and div-hidden-choice-evening*/
                    function deleteChoiceEvening(elementNo,name){
                        $("#table-body-evening tr[id='"+elementNo+"']").remove();

                        $("#select-evening").prepend("<option value=\""+elementNo+"\" >"+name+"</option>");

                        $("#table-body-evening tr").each(function(index,elem){
                            var no = (index +1);
                            var d = $(elem).children().get(0);
                            $(d).html(no);

                        });

                        var len = ($("#table-body-evening tr").length);

                        if(len < <?php echo Variable::$CHOICE_MAX?>){
                            $("#add-evening-choice-btn").removeAttr('disabled');
                            $("#add-evening-choice-btn").addClass('btn-default');
                        }
                        deleteChoiceElement($("#div-choice-hidden-evening"),elementNo);
                    }

                    function checkChoiceEligible(element,isMorning){
                        var id = $(element).val();
                        var len = ($("#table-body-evening tr").length);
                        var len2 = ($("#table-body-morning tr").length);
                        if(isMorning){
                            if(len2 >= <?php echo Variable::$CHOICE_MAX?>){
                                $("#add-morning-choice-btn").attr('disabled','disabled');
                                $("#add-morning-choice-btn").removeClass('btn-default');
                                return;
                            }
                        }else{
                            if(len >= <?php echo Variable::$CHOICE_MAX?>){
                                $("#add-evening-choice-btn").attr('disabled','disabled');
                                $("#add-evening-choice-btn").removeClass('btn-default');
                                return;
                            }
                        }


                        if(isMorning){
                            if(id == 0){
                                $("#add-morning-choice-btn").attr('disabled','disabled');
                                $("#add-morning-choice-btn").removeClass('btn-default');
                                $(element).addClass("custom-bg-red");
                                $(element).removeClass("custom-bg-white");
                            }else{
                                $("#add-morning-choice-btn").removeAttr('disabled');
                                $("#add-morning-choice-btn").addClass('btn-default');
                                $(element).removeClass("custom-bg-red");
                                $(element).addClass("custom-bg-white");
                            }
                        }else{
                            if(id == 0){
                                $("#add-evening-choice-btn").attr('disabled','disabled');
                                $("#add-evening-choice-btn").removeClass('btn-default');
                                $(element).addClass("custom-bg-red");
                                $(element).removeClass("custom-bg-white");
                            }else{
                                $("#add-evening-choice-btn").removeAttr('disabled');
                                $("#add-evening-choice-btn").addClass('btn-default');
                                $(element).removeClass("custom-bg-red");
                                $(element).addClass("custom-bg-white");
                            }
                        }
                    }

                    $("#add-morning-choice-btn").click(function(){
                        var id = $("#select-morning").val();
                        if(id != 0){
                            var txt = $("#select-morning option:selected").text();
                            addMorningChoice(id,txt);
                        }else{
                            alert("You are not eligible for this course");
                            $(this).attr('disabled','disabled');
                            $(this).removeClass('btn-default');
                        }
                    });
                    $("#add-evening-choice-btn").click(function(){
                        var id = $("#select-evening").val();
                        if(id != 0){
                            var txt = $("#select-evening option:selected").text();
                            addEveningChoice(id,txt);
                        }else{
                            alert("You are not eligible for this course");
                            $(this).attr('disabled','disabled');
                            $(this).removeClass('btn-default');
                        }
                    });

                    function sortComboBox(listId){
                        $(listId).html($(listId).sort(function(x, y) {
                            return $(x).text() < $(y).text() ? -1 : 1;
                        }))
                        $(listId).get(0).selectedIndex = 0;
                        e.preventDefault();

                    }





                    <?php echo $scripting; ?>


                </script>
                <?php echo form_close(); ?>

            </div>
        </div>
    </div>
</div>
