<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User extends CI_Controller {

	public function index(){
		echo "Test";
	}

	public function register(){
		$username = $this->input->post('username');
		$password = $this->input->post('password');
		$user_bio = $this->input->post('user_bio');

		if ($username == null || $password == null ) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'username' => $username,
	        'password' => $password,
	        'user_bio' => $user_bio
		);

		$this->load->model('user_model');
		$result = $this->user_model->insertUser($data);
		
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

	public function getProfile(){
		$user_id = $this->input->post('user_id');

		if ($user_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'user_id' => $user_id
		);

		$this->load->model('user_model');
		$result = $this->user_model->getProfile($data);
		
		if ($result == FALSE) {
			$this->errorResponse("DATABASE ERROR");
			exit();
		}
		else{
			header('Access-Control-Allow-Origin: *');
			header('Content-type: application/json');

			$response = new stdClass();
			$response->success = TRUE;
			$response->user = $result[0];
			$response->advert = $result[1];
		
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

		}
		
	}

	public function login(){
		$username = $this->input->post('username');
		$password = $this->input->post('password');

		if ($username == null || $password == null ) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'username' => $username,
	        'password' => $password
		);
		$this->load->model('user_model');
		$result = $this->user_model->getUser($data);

		if ($result == FALSE) {
			$this->errorResponse("Kullanıcı bulunamadı.");
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

	public function addFriend(){
		$user_id = $this->input->post('user_id');
		$friend_user_id = $this->input->post('friend_user_id');

		if ($user_id == null || $friend_user_id == null ) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'first_user_id' => $user_id,
	        'second_user_id' => $friend_user_id
		);

		$this->load->model('user_model');
		$result = $this->user_model->addFriend($data);

		if ($result == FALSE) {
			$this->errorResponse("Arkadaş eklemede sorun oluştu.");
			exit();
		}
		else{
			header('Access-Control-Allow-Origin: *');
			header('Content-type: application/json');

			$response = new stdClass();
			$response->success = TRUE;
			$response->message = "Arkadaş ekleme başarılı.";
		
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
		}
	}

		public function removeFriend(){
		$user_id = $this->input->post('user_id');
		$second_user_id = $this->input->post('second_user_id');

		if ($user_id == null || $second_user_id == null ) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'first_user_id' => $user_id,
	        'second_user_id' => $second_user_id
		);

		$this->load->model('user_model');
		$result = $this->user_model->removeFriend($data);

		if ($result == FALSE) {
			$this->errorResponse("DATABASE ERROR");
			exit();
		}
		else{
			header('Access-Control-Allow-Origin: *');
			header('Content-type: application/json');

			$response = new stdClass();
			$response->success = TRUE;
			$response->message = "Arkadaş silme başarılı.";
		
			echo json_encode($response,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
		}
	}

	public function search(){
		$user_id = $this->input->post('user_id');
		$searchString = $this->input->post('searchString');

		if ($user_id == null || $searchString == null ) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'user_id' => $user_id,
	        'searchString' => $searchString
		);

		$this->load->model('user_model');
		$result = $this->user_model->searchFriend($data);

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

	public function getFriends(){
		$user_id = $this->input->post('user_id');

		if ($user_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'user_id' => $user_id,
		);

		$this->load->model('user_model');
		$result = $this->user_model->getFriends($data);

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

	public function editProfile(){
		$user_id = $this->input->post('user_id');
		$username = $this->input->post('username');
		$password = $this->input->post('password');
		$user_bio = $this->input->post('user_bio');
		

		if ( $user_id == null || $username == null || $password == null ) {
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
	    
	    if(!$this->upload->do_upload('user_img')){
	        $uploadedDetails    = $this->upload->display_errors();
	    }else{
	        $uploadedDetails    = $this->upload->data();    
	    }

	    $user_img = 'http://ahmetcankucukkor.com/libra/uploads/'.$uploadedDetails['file_name'];

		$data = array(
			'user_id' => $user_id,
	        'username' => $username,
	        'password' => $password,
	        'user_bio' => $user_bio,
	        'user_img' => $user_img
		);

		$this->load->model('user_model');
		$result = $this->user_model->editUser($data);
		
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

	private function errorResponse($errorMessage="Something went wrong."){
		header('Access-Control-Allow-Origin: *');
		header('Content-type: application/json');

		$error = new stdClass();
		$error->success = FALSE;
		$error->errorMessage = $errorMessage;
		
		echo json_encode($error,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	}
}
