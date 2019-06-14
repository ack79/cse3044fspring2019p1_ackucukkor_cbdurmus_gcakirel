<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Book_model extends CI_Model{
	
	public function insertBook($data){
		$insert = $this->db->insert('book', $data);
		if ($insert == 1) {
			$lastId = $this->db->insert_id();
			$lastRecord = $this->db->get_where('book', array('book_id' => $lastId));
			return $lastRecord->result();
		}
		else{
			return FALSE;
		}
	}

	public function editBook($data){
		
		extract($data);
	    $this->db->where('book_id', $book_id);
	    $this->db->update('book', $data);
	    
	    return TRUE;
	}

	public function removeBook($data){
		$this->db->where($data);
		$delete = $this->db->delete('book');

		if ($delete == 1) {
			return TRUE;
		}
		else{
			return FALSE;
		}
	}

	public function getLibrary($data){
		$book = $this->db->get_where('book', $data)->result();
		if (empty($book)) {
			return FALSE;
		}
		else{
			return $book;
		}
	}

}