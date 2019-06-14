<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Book extends CI_Controller {
	public function addBook(){
		$user_id = $this->input->post('user_id');
		$book_name = $this->input->post('book_name');
		$book_author = $this->input->post('book_author');
		$book_desc = $this->input->post('book_desc');
		//book_img

		if ($user_id == null || $book_name == null || $book_author == null || $book_desc == null ) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		//resim kaydetme işlemi
	    $configUpload['upload_path']    = './uploads/';                 #the folder placed in the root of project
	    $configUpload['allowed_types']  = 'gif|jpg|png|bmp|jpeg';       #allowed types description
	    $configUpload['max_size']       = '5000';                          #max size
	    $configUpload['max_width']      = '5000';                          #max width
	    $configUpload['max_height']     = '5000';                          #max height
	    $configUpload['encrypt_name']   = true;                         #encrypt name of the uploaded file
	    $this->load->library('upload', $configUpload);                  #init the upload class
	    
	    if(!$this->upload->do_upload('book_img')){
	        $uploadedDetails    = $this->upload->display_errors();
	    }else{
	        $uploadedDetails    = $this->upload->data();    
	    }

	    $book_img = 'http://ahmetcankucukkor.com/libra/uploads/'.$uploadedDetails['file_name'];

		$data = array(
	        'user_id' => $user_id,
	        'book_name' => $book_name,
	        'book_author' => $book_author,
	        'book_desc' => $book_desc,
	        'img' => $book_img
		);

		$this->load->model('book_model');
		$result = $this->book_model->insertBook($data);
		
		if ($result == FALSE) {
			$this->errorResponse("DATABASE ERROR");
			exit();
		}
		else{
			header('Access-Control-Allow-Origin: *');
			header('Content-type: application/json');

			$response = new stdClass();
			$response->success = TRUE;
			$response->data = $result[0];
		
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

		}		
	}

	public function editBook(){
		$book_id = $this->input->post('book_id');
		$book_name = $this->input->post('book_name');
		$book_author = $this->input->post('book_author');
		$book_desc = $this->input->post('book_desc');
		//book_img

		if ($book_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		//resim kaydetme işlemi
	    $configUpload['upload_path']    = './uploads/';                 #the folder placed in the root of project
	    $configUpload['allowed_types']  = 'gif|jpg|png|bmp|jpeg';       #allowed types description
	    $configUpload['max_size']       = '5000';                          #max size
	    $configUpload['max_width']      = '5000';                          #max width
	    $configUpload['max_height']     = '5000';                          #max height
	    $configUpload['encrypt_name']   = true;                         #encrypt name of the uploaded file
	    $this->load->library('upload', $configUpload);                  #init the upload class
	    
	    if(!$this->upload->do_upload('book_img')){
	        $uploadedDetails    = $this->upload->display_errors();
	    }else{
	        $uploadedDetails    = $this->upload->data();    
	    }

	    $book_img = 'http://ahmetcankucukkor.com/libra/uploads/'.$uploadedDetails['file_name'];

		$data = array(
	        'book_id' => $book_id,
	        'book_name' => $book_name,
	        'book_author' => $book_author,
	        'book_desc' => $book_desc,
	        'img' => $book_img
		);

		$this->load->model('book_model');
		$result = $this->book_model->editBook($data);
		
		if ($result == FALSE) {
			$this->errorResponse("DATABASE ERROR");
			exit();
		}
		else{
			header('Access-Control-Allow-Origin: *');
			header('Content-type: application/json');

			$response = new stdClass();
			$response->success = TRUE;

			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

		}		
	}

	public function removeBook(){
		$book_id = $this->input->post('book_id');

		if ($book_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'book_id' => $book_id,
		);

		$this->load->model('book_model');
		$result = $this->book_model->removeBook($data);

		if ($result == FALSE) {
			$this->errorResponse("DATABASE ERROR");
			exit();
		}
		else{
			header('Access-Control-Allow-Origin: *');
			header('Content-type: application/json');

			$response = new stdClass();
			$response->success = TRUE;
			$response->message = "Kitap silme başarılı.";
		
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
		}
	}

	public function getLibrary(){
		$user_id = $this->input->post('user_id');

		if ($user_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'user_id' => $user_id,
		);

		$this->load->model('book_model');
		$result = $this->book_model->getLibrary($data);

		if ($result == FALSE) {
			$this->errorResponse("DATABASE ERROR");
			exit();
		}
		else{
			header('Access-Control-Allow-Origin: *');
			header('Content-type: application/json');

			$response = new stdClass();
			$response->success = TRUE;
			$response->data = $result;
		
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
		}
	}


	private function errorResponse($errorMessage="Something went wrong."){
		header('Access-Control-Allow-Origin: *');
		header('Content-type: application/json');

		$error = new stdClass();
		$error->success = FALSE;
		$error->errorMessage = $errorMessage;
		
		echo json_encode($error,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	}


}