<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User_model extends CI_Model{
	
	public function insertUser($data){
		$insert = $this->db->insert('user', $data);
		if ($insert == 1) {
			$lastId = $this->db->insert_id();
			$lastRecord = $this->db->get_where('user', array('user_id' => $lastId));
			return $lastRecord->result();
		}
		else{
			return FALSE;
		}
	}
	
	public function getUser($data){
		$user = $this->db->get_where('user', $data)->result();
		if (empty($user)) {
			return FALSE;
		}
		else{
			return $user;
		}
	}    
}