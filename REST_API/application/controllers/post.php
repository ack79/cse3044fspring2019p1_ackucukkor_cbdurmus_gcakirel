<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Post extends CI_Controller {

	public function addPost(){

		$user_id = $this->input->post('user_id');
		$post_text = $this->input->post('post_text');

		if ($user_id == null || $post_text == null ) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'user_id' => $user_id,
	        'post_text' => $post_text
		);

		$this->load->model('post_model');
		$result = $this->post_model->createPost($data);
		
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

	public function getTimeline(){
		$user_id = $this->input->post('user_id');

		if ($user_id == null ) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'user_id' => $user_id
		);

		$this->load->model('post_model');
		$result = $this->post_model->getTimeline($data);

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