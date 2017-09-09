    <!-- Page Content -->
    <div class="page-content">
        <div class="container">
            <div class="row">
                <div class='col-md-10'>
                    <div class="text-center"><h3><span class="label label-default">Signup for Admission<?php// echo $title;?></span></h3></div>
                    </br>
                    <div>
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                        <?php echo form_open($request_submit);?>

                    <div class="text-danger">
                    <?php echo $message;?>
                    </div>
                            <div class="form-group has-feedback">
                                <input type="text" class="form-control" placeholder="Username" name="<?php echo Variable::USERNAME();?>" required>
                                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="password" class="form-control" placeholder="Password" name="<?php echo Variable::PASSWORD();?>" required>
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                            <div class="text-danger">
                                <p>username and password are case sensitive</p>
                            </div>
                            <?php echo  $this->recaptcha->recaptcha_get_html(); ?>
                           </br>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success" align="center">Login
                                    <i class="glyphicon glyphicon-log-in"></i>
                                </button>
                            </div>




                            <?php echo form_close("");?>
                    </div>

                        <div class="col-md-4"></div>

                    </div>

                </div>

        </div>
            <!--IMPORTANT INSTRUCTION -->

            <div class="row">

                <div class="col-md-1"></div>
                <div class="col-md-11">
                    <div > <h3 div class="info">Important Instructions </h3></div>
                    <div class="text-info">
                        <ol>
                            <li>Once admit card is issued, <b>NO </b> change will be allowed in your particulars.</li>
                            <li>Use Internet Explorer (8.0) or its above versions or others browsers like Google Chrome , Firefox.</li>
                        </ol>
                    </div>
                    </div>
                </div>
            </div>
        </div>




