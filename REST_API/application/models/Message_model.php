<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Advert_model extends CI_Model{	
	public function addMessage($data){
		$insert = $this->db->insert('message', $data);
		if ($insert == 1) {
			$lastId = $this->db->insert_id();
			$lastRecord = $this->db->get_where('message', array('message_id' => $lastId));
			return $lastRecord->result();
		}
		else{
			return FALSE;
		}
	}
	public function getMessage($data){

		$messages = $this->db->get_where('message', ['advert_id' => $data['advert_id'], 'sender_user_id' => $data['sender_user_id'])->result_array();
		return $messages;

	}
}