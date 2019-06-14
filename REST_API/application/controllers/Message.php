<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Message extends CI_Controller {

	public function addMessage(){
		$advert_id = $this->input->post('advert_id');
		$sender_user_id = $this->input->post('sender_user_id');
		$message_text = $this->input->post('message_text');

		if ($advert_id == null || $sender_user_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'advert_id' => $advert_id,
	        'sender_user_id' => $sender_user_id,
	        'message_text' => $message_text
	        
		);

		$this->load->model('message_model');
		$result = $this->message_model->addMessage($data);
		
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

	public function getAdvertMessages(){
		$advert_id = $this->input->post('advert_id');
		$sender_user_id = $this->input->post('sender_user_id');

		if ($advert_id == null || $sender_user_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'advert_id' => $advert_id,
	        'sender_user_id' => $sender_user_id	        
		);

		$this->load->model('message_model');
		$result = $this->message_model->getMessage($data);
		
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