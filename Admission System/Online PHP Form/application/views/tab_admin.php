
<style>
    .custom-bg-red{
        background-color: #de5959;
        color: white;
    }
</style>

<div class="page-content">
    <div class="container">
        <?php echo form_open('slip_admin/search_candidate');?>
        <div class="row">
                <div class="col-lg-4">
                    <div class="input-group">
                        <input type="text" name="corn" class="form-control" placeholder="Candidate Online Registration No." required>
                          <span class="input-group-btn">
                            <input type="submit" class="btn btn-default" >Search</input>
                          </span>
                    </div>
                </div>
            <?php echo form_close(); ?>
            <?php
            if($is_data){ ?>
            <div class="col-lg-6">
                <table class="table table-bordered">


                    <tr>
                        <td>C.R NO</td>
                        <td> <?php echo $candidate->getCandidateId();?> </td>
                    </tr>

                    <tr>
                        <td>Form Date And Time</td>
                        <?php
                        $myDateTime= DateTime::createFromFormat('Y-m-d H:i:s',$candidate->getDateOfSumbitForm());

                        $newDateString = $myDateTime->format('d-m-Y H:i:s');

                        ?>
                        <td> <?php echo $newDateString ; ?> </td>
                    </tr>

                    <tr>

                        <td>PROGRAM NAME</td>
                        <td> <?php if($candidate->getProgramTypeId()==1){ echo "BACHELOR";} else{ echo"MASTER";} ?> </td>
                    </tr>

                    <tr>
                        <td>Name</td>
                        <td> <?php echo $candidate->getName();?> </td>
                    </tr>
                    <tr>
                        <td>Father Name</td>
                        <td> <?php echo $candidate->getFathersName();?> </td>
                    </tr>
                    <tr>
                        <td>Surname</td>
                        <td> <?php echo $candidate->getSurname();?> </td>
                    </tr>

                            <form action="<?php echo base_url("index.php/slip_admin/print_candidate") ?>" method="post" target="_blank">
                                <div class="form-group has-feedback">
                                    <input type="hidden" name="corn" value="<?php echo $corn;?>">

                                    <?php if($candidate->getFormSNo()==0){ ?>

                                    <tr>
                                       <td>FormNo</td>

                                       <td><input type="number" name="formNo" required></td>
                                   </tr>

                                        <?php
                                              }
                                        ?>


                                    <?php if($candidate->getProgramTypeId()==2){ ?>
                                    <tr>
                                        <td>GROUP</td>

                                        <td><select name="group">
                                                <?php
                                                foreach( $master_group as $master )

                                                {
                                                $id=$master['id'];
                                                $name=	$master['name'];

                                                ?>
                                                    <option  value="<?php echo '' . $id ?>" <?php if($this->candidate->getGroupId() == $id){echo "selected class='custom-bg-red' ";}?>> <?php echo '' . $name ?>  </option>

                                                <?php
                                                }
                                                ?>
                                            </select></td>

                                    </tr>

                                    <?php } ?>

                                    <tr>
                                        <td>Objection</td>
                                        <td><input type="text" name="objection" id="objection"></td>
                                    </tr>
                                    <tr>
                                        <td>Objection </td>
                                        <td><input type="checkbox" name="c" id="objectionCheckBox"> HSC Markshet </td>
                                    </tr>

                                    <tr>

                                        <td>Print</td>
                                        <td>

                                        <input type="submit" name="<?php echo Variable::LOGIN();?>" value="Print and Save">
                                    <span class="glyphicon glyphicon-print form-control-feedback"></span>
                                </div>
                            </form>
                        </td>
                    </tr>
                </table>



            </div>
                <div class="col-lg-8">
                <form action="<?php echo base_url("index.php/slip_admin/print_candidate_form") ?>" method="post" target="_blank">
                    <input type="hidden" name="corn" value="<?php echo $corn;?>">
                    <input type="submit"  name="<?php echo Variable::LOGIN();?>" value="Print Student Form">

                </form>

                    </div>
                <hr>
               <!-- <div class="col-lg-8">
                    <button  class="btn btn-danger btn-lg" id="unLock">UN LOCKED</button>
                </div> -->
            <?php } ?>

        </div>
    </div>
</div>
<script>
    $("#unLock").click(function(){
        var id = $("#select-morning").val();
        $.post("<?php echo base_url("index.php/slip_admin/unlock_candidate_form"); ?>",
            {
                corn: '<?php echo $corn;?>'
            }
            , function(data) {
                alert("Sucessfully Unlocked...");
            });


    });
</script>
