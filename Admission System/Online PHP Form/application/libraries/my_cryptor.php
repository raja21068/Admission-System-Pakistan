<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class My_Cryptor
{
    public static function myEncryption($str){
        $enc = sha1($str);
        $half = strlen($enc) / 22;
        $front = substr($enc, 0,$half);
        $end = substr($enc, $half);
        $full = $front."f".$end;
        return $full;
    }//end myEncryption()

}