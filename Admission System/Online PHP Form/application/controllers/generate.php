<?php


class generate extends CI_Controller
{
    public function index(){
        $this->load->model("online_card");
        $this->load->library("random_generator");
        $this->load->library("my_cryptor");
        $this->load->model("online_slip_issuer");

        //vikeshkumar12345
        //R@j@
       // $c_user = My_Cryptor::myEncryption("vikeshkumar12345");
       // $c_pass= My_Cryptor::myEncryption("R@j@");
        //$this->online_slip_issuer->addSlipIssuer($c_user,$c_pass);


        for($loop = 0; $loop<500;$loop++){
            $dec_password= $this->random_generator->generateRandomPassword(7);
            $dec_username = $this->random_generator->generateRandomUsername(6);
            $enc_username = My_Cryptor::myEncryption($dec_username);
            $enc_password = My_Cryptor::myEncryption($dec_password);

            if( !$this->online_card->addOnlineCard($dec_username,$dec_password,$enc_username,$enc_password)){
                $loop--;
            }

        }

    }

}