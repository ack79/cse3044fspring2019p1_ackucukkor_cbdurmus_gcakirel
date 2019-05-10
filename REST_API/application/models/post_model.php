<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Post_model extends CI_Model{

	public function createPost($data){
		$insert = $this->db->insert('post', $data);
		if ($insert == 1) {
			$lastId = $this->db->insert_id();
			$lastRecord = $this->db->get_where('post', array('post_id' => $lastId));
			return $lastRecord->result();
		}
		else{
			return FALSE;
		}
	}

}