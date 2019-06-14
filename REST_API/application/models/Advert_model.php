<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Advert_model extends CI_Model{	
	public function addAdvert($data){
		$insert = $this->db->insert('advert', $data);
		if ($insert == 1) {
			$lastId = $this->db->insert_id();
			$lastRecord = $this->db->get_where('advert', array('advert_id' => $lastId));
			return $lastRecord->result();
		}
		else{
			return FALSE;
		}
	}

	public function removeAdvert($data){
		$this->db->where($data);
		$delete1 = $this->db->delete('advert');
		if ($delete1 == 1 ) {
			return TRUE;
		}
	}

	public function getAdverts($data){
		extract($data);
		$this->db->where('first_user_id', $user_id);
		$this->db->or_where('second_user_id',$user_id);

		$user_has_friends = $this->db->get('friend')->result();
		
		$result = [];
		foreach ($user_has_friends as $row) {
			if ($row->first_user_id == $user_id) {
				//second user id
				$book = $this->db->get_where('book', ['user_id' => $row->second_user_id])->result_array();
				$user = $this->db->get_where('user', ['user_id' => $row->second_user_id])->result_array();

			}
			else{
				//first user id
				$book = $this->db->get_where('book', ['user_id' => $row->first_user_id])->result_array();
				$user = $this->db->get_where('user', ['user_id' => $row->first_user_id])->result_array();

		}
			if (!empty($book)) {

				foreach ($book as $book) {
					$advert = $this->db->get_where('advert', ['book_id' => $book['book_id']])->result_array();
					if (!empty($advert)) {

						$resultArr['user'] = $user;
						$resultArr['book'] = $book;
						$resultArr['advertDetails'] = $advert;

						array_push($result, $resultArr);
					}
				}

			}
		}

		return $result;
	}
}