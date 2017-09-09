<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>

	<!-- Fevicon
    ================================================== -->
	<link rel="shortcut icon" href="<?Php echo ASSET_PATH ?>favicon.ico">
	<link rel="icon" type="image/gif" href="<?Php echo ASSET_PATH ?>images/animated_favicon1.gif">

	<!-- Basic Page Needs
    ================================================== -->
	<meta charset="utf-8">
	<title>Directorate of Admissions</title>

	<!-- Mobile Specific Metas
    ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<!-- CSS
    ================================================== -->
	<!-- CSS
    ================================================== -->
	<link rel="stylesheet" href="<?Php echo ASSET_PATH ?>css/style.css">
	<!--<link rel="stylesheet" href="--><?Php //echo ASSET_PATH ?><!--css/colors/blue.css" id="colors">-->
	<!--<link rel="stylesheet" href="--><?Php //echo ASSET_PATH ?><!--css/bootstrap.min.css">-->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

	<link rel="stylesheet" href="http://exam.usindh.edu.pk/css/colors/blue.css" id="colors">

	<link rel="stylesheet" href="http://exam.usindh.edu.pk/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="http://exam.usindh.edu.pk/bootstrap/css/bootstrap.min.css">

	<!--[if lt IE 9]>
	<script src="<?Php echo ASSET_PATH; ?>/js/html5.js"></script>
	<![endif]-->

	<!-- Java Script
    ================================================== -->
	<script src="<?Php echo ASSET_PATH; ?>/js/jquery-1.11.2.min.js"></script>
	<script src="http://exam.usindh.edu.pk/scripts/jquery-ui.min.js"></script>
	<script src="http://exam.usindh.edu.pk/scripts/jquery.selectnav.js"></script>
	<script src="<?Php echo ASSET_PATH; ?>/js/bootstrap.min.js"></script>
		<script src="<?Php echo ASSET_PATH; ?>/scripts/chosen.jquery.js"></script>


	<script src="http://exam.usindh.edu.pk/scripts/custom.js"></script>

	<!--for date picker -->
	<!--<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">-->
	<!--<link rel="stylesheet" href="--><?Php //echo ASSET_PATH ?><!--datepicker/res/css/dateselector.min.css" type="text/css" />-->
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<!--<script src="--><?Php //echo ASSET_PATH ?><!--datepicker/res/libs/dateselector.min.js"></script>-->
	<script src="<?Php echo ASSET_PATH; ?>datepicker/dropdowndate/jquery.date-dropdowns.js"></script>
	<script src="<?Php echo ASSET_PATH; ?>js/jquery.maskedinput.js"></script>



<body>

<!-- Wrapper / Start -->
<div id="wrapper">

	<!-- Header
    ================================================== -->

	<!-- 960 Container -->
	<div class="container">

		<!-- Header -->
		<header id="header">

			<!-- Logo -->
			<div class="ten columns">
				<div id="logo">
					<h1><a href="index.php"><img src="<?Php echo ASSET_PATH ?>images/logo.png" alt="Exma- University of Sindh" />
							<div id="tagline">University of Sindh </br><p style='font-size:23px;'> Directorate of Admission</p></div>

				</div>
			</div>

			<!-- Social / Contact -->
			<div class="six columns">

				<!-- Social Icons -->
				<ul class="social-icons">
					<li class="twitter"><a href="https://twitter.com/usindh" target="_blank">Twitter</a></li>
					<li class="facebook"><a href="https://www.facebook.com/usindh" target="_blank">Facebook</a></li>

					<li class="linkedin"><a href="https://www.linkedin.com/edu/school?id=15897&trk=edu-cp-title" target="_blank">LinkedIn</a></li>
					<li class="rss"><a href="http://usindh.edu.pk/blogs/" target="_blank">RSS</a></li>
				</ul>

				<div class="clearfix"></div>

				<!-- Contact Details -->

				<div class="clearfix"></div>


			</div>
		</header>
		<!-- Header / End -->

		<div class="clearfix"></div>

	</div>
	<!-- 960 Container / End -->

	<?php
	//			if (!isset($_SESSION)) include('admin/session.php');//session_start();
	?>
	<!-- Navigation ================================================== -->
	<nav id="navigation" class="style-1">

		<div class="left-corner"></div>
		<div class="right-corner"></div>

		<ul class="menu" id="responsive">

<!--			<li><a href="http://admission.usindh.edu.pk/"><i class="halflings white home"></i> Home</a></li>-->
			<li><a href="<?php //echo base_url("index.php/app_form");?>"><i class="halflings white list-alt"></i>Admission Form</a></li>
<li><a href="http://admission.usindh.edu.pk/test_result/EntryTestResult.php?admission_session=2016&program_type=1&admission_list_detail=0">Bachelor Results</a>

			<li><a href="#">Master Results</a>

				<ul>
					<li><a  style='background-color:#FFF5FE;' href="http://admission.usindh.edu.pk/test_result/EntryTestResult.php?admission_session=2016&program_type=2&admission_list_detail=0">Pre-Entry Test (Search)</a></li>
			<!--
                                <li><a  style='background-color:#FFF5FE;' href="search.php?admission_session=2016&program_type=2&admission_list_detail=1">First Merit Selection (Search)</a></li>

                                            <li><a  style='background-color:#FFF5FE;' href="merit_list.php?admission_session=2015&program_type=2&admission_list_detail=1"><span></span> First Merit Selection (List)</a></li>



                                <li><a  style='background-color:#F0FCED;' href="search.php?admission_session=2015&program_type=2&admission_list_detail=2">Second Merit Selection (Search) </a></li>
                                <li><a  style='background-color:#F0FCED;' href="merit_list.php?admission_session=2015&program_type=2&admission_list_detail=2"><span></span> Second Merit Selection (List)</a></li>

                                <li><a  style='background-color:#FFFFE1;' href="search.php?admission_session=2015&program_type=2&admission_list_detail=3">Third Merit Selection (Search) </a></li>
                                <li><a  style='background-color:#FFFFE1;' href="merit_list.php?admission_session=2015&program_type=2&admission_list_detail=3"><span></span> Third Merit Selection (List)</a></li>

                                -->

				</ul>
			</li>
	<li><a href="http://admission.usindh.edu.pk/test_result/EntryTestResult.php?admission_session=2016&amp;program_type=3&amp;admission_list_detail=0">MPHIL Results</a></li>
			<li> <a href="#">Fees Structure</a>
				<ul>
					<li>	<a href="<?Php echo ASSET_PATH; ?>/pdf/FEES_BACHELOR.pdf">Bachelor</a></li>
					<li>	<a href="<?Php echo ASSET_PATH; ?>/pdf/FEES_MASTER.pdf">Master</a></li>
					<li>	<a href="<?Php echo ASSET_PATH; ?>/pdf/FEES_CAMPUS.pdf">Campuses</a></li>
				</ul>
			</li>

			<li> <a href="#">Distribution of Seats</a>
				<ul>
					<li>	<a href="<?Php echo ASSET_PATH; ?>/pdf/SEAT_DISTRIBUTION_BACHLOR.pdf">Bachelor</a></li>
					<li>	<a href="<?Php echo ASSET_PATH; ?>/pdf/SEAT_DISTRIBUTION_MASTER.pdf">Master</a></li>
					<li>	<a href="<?Php echo ASSET_PATH; ?>/pdf/SEAT_DISTRIBUTION_CAMPUS.pdf">Campuses</a></li>
				</ul>
			</li>


			<li> <a href="<?Php echo ASSET_PATH; ?>/pdf/USER_GUIDE.pdf">User Guide</a></li>
			<!--<li> <a href="<?Php echo ASSET_PATH; ?>/pdf/AFFIDAVIT.pdf">Affidavit & Undertaking </a></li>-->
			<?php if($is_logged_in){?><li> <a href="logout"><i class="halflings white expand"></i>Logout</a> </li>
			<?php }?>




		</ul>
	</nav>
	<div class="clearfix"></div>