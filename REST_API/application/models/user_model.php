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

	public function getProfile($data){
		$user = $this->db->get_where('user', $data)->result();
		if (empty($user)) {
			return FALSE;
		}
		else{
			$book = $this->db->get_where('book', $data)->result_array();
			$advertArr = [];
			foreach ($book as $book) {
				$advert = $this->db->get_where('advert', ['book_id' => $book['book_id']])->result_array();
				if (!empty($advert)) {
					array_push($advertArr, $advert[0]);
				}
			}
			return [$user,$advertArr];
		}
	}

	public function addFriend($data){
		$insert = $this->db->insert('friend', $data);
		if ($insert == 1) {
			return TRUE;
		}
		else{
			return FALSE;
		}
	}

	public function removeFriend($data){
		$this->db->where($data);
		$delete1 = $this->db->delete('friend');
		$this->db->where(array('first_user_id'  => $data['second_user_id'], 'second_user_id' => $data['first_user_id']));
 		$delete2 = $this->db->delete('friend');

		if ($delete1 == 1 || $delete2) {
			return TRUE;
		}
		else{
			return FALSE;
		}
	}

	public function searchFriend($data){

		$this->db->like('username', $data['searchString']);
		$userSearch = $this->db->get('user')->result_array();

		$searchResultArray = [];
		foreach ($userSearch as $row) {
			$isFriend = $this->db->query('SELECT * FROM friend WHERE (first_user_id ='. $row['user_id'] .' AND second_user_id ='. $data['user_id'] .') OR (first_user_id = '.$data['user_id'] .' AND second_user_id = '. $row['user_id'] .')')->result_array();
			if (empty($isFriend)) {
				$row['isFriend'] = FALSE;
			}
			else{
				$row['isFriend'] = TRUE;
			}
			array_push($searchResultArray, $row);
		}

		return $searchResultArray;
	}

	public function editUser($data){
		extract($data);
	    $this->db->where('user_id', $user_id);
	    $this->db->update('user', $data);
	    
	    return TRUE;
	}

	public function getFriends($data){
		extract($data);
		 
		$this->db->where('first_user_id', $user_id);
		$this->db->or_where('second_user_id',$user_id);

		$user_has_friends = $this->db->get('friend')->result();
		
		$resultArray = [];
		foreach ($user_has_friends as $row) {
			if ($row->first_user_id == $user_id) {
				//second user id
				$user = $this->db->get_where('user', ['user_id' => $row->second_user_id])->result_array();
			}
			else{
				//first user id
				$user = $this->db->get_where('user', ['user_id' => $row->first_user_id])->result_array();

			}
			array_push($resultArray, $user);
		}
		
		return $resultArray;

	}


}