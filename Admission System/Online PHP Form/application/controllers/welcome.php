<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
require_once ("my_controller.php");
class Welcome extends My_Controller {


	public function index()
	{
		$this->session->unset_userdata(array(Variable::$message=>''));
		redirect('/app_form/index/', 'refresh');

	}
}

/* End of file welcome.php */
/* Location: ./application/controllers/welcome.php */

