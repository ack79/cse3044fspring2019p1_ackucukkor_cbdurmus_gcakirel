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

	public function getTimeline($data){
		$getFriends = $this->db->query('SELECT * FROM friend WHERE first_user_id = '. $data['user_id'] .' OR second_user_id = '. $data['user_id'])->result_array();
		$friendIDArray = [$data['user_id']];
		foreach ($getFriends as $row) {
			if ($row['first_user_id'] != $data['user_id']) {
				array_push($friendIDArray, $row['first_user_id']);
			}
			elseif ($row['second_user_id'] != $data['user_id']) {
				array_push($friendIDArray, $row['second_user_id']);
			}		
		}

		$this->db->or_where_in('user_id', $friendIDArray);
		$this->db->order_by('created_date', 'DESC');
		$timeline = $this->db->get('post')->result_array();
		
		if (empty($timeline)) {
			return FALSE;
		}
		else{
			$this->db->where_in('user_id', $friendIDArray);
			$userInfo = $this->db->get('user')->result_array();
			$postAndUser = ["post" => $timeline, "user" => $userInfo];
			return $postAndUser;
		}


	}

}