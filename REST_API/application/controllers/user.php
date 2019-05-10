<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User extends CI_Controller {

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

	private function errorResponse($errorMessage="Something went wrong."){
		header('Access-Control-Allow-Origin: *');
		header('Content-type: application/json');

		$error = new stdClass();
		$error->success = FALSE;
		$error->errorMessage = $errorMessage;
		
		echo json_encode($error,JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
	}
}
