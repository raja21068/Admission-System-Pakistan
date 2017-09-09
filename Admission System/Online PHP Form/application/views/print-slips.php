<div class="page-content">

    <div class="container">
        <div class="row">
            <div class='col-md-2'></div>
            <div class='col-md-9'>



            </div>
            <div class="row">
                <div class='col-md-10'>
                    <?php// echo form_open_multipart('slip_admin/searchStudentBySeatNo');?>
                    <?php echo form_open_multipart('print_all_slip/print_candidate', array('target'=>'_blank', 'id'=>'')); ?>


                    <!--<form action="slip_admin/searchStudentBySeatNo" method="post" target="new"> -->

                    <input type="hidden" name="<?php echo Variable::TAB()?>" value="<?php echo ''.Variable::TAB_PERSONAL();?>" >

                    <!-- Left Side -->
                    <div class="col-md-3"></div>
                    <div class="col-md-6 ">
                        <div class="form-group">
                            <div class="form-group" >

                                <select class="form-control" style="font-size: large;" name="<?php echo Variable::PROGRAM_TYPE()?>"  required="required">
                                    <option value="1" >Bachelor</option>
                                    <option value="2">Master</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="pwd">Year<font color="red">*</font></label>
                                <select class="form-control" name="admissionYear" required>

                                    <?php
                                    foreach( $year as $years )

                                    {
                                        $id=$years['admission_year_id'];
                                        $year=	$years['year'];

                                        ?>
                                        <option value="<?php echo '' . $id ?>"> <?php echo '' . $year ?>  </option>

                                        <?php
                                    }
                                    ?>
                                </select>
                            </div>


                            <div class="form-group">
                                <label for="pwd">Seat No To </label>
                                <input type="number" class="form-control" name="seat_no_to" required>
                                <label for="pwd">Seat No From </label>

                                <input type="number" class="form-control" name="seat_no_from" required>

                            </div>
                            <div class="form-group">

                                <input type="submit"  >
                            </div>
<?php echo form_close(); ?>