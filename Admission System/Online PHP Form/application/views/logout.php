<?php
$this->load->library("variable");
session_start();
$_SESSION = array();
header("location:".Variable::$PROCESS_FILE);

?>
