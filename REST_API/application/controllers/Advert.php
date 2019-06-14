<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Advert extends CI_Controller {
	public function addAdvert(){
		$book_id = $this->input->post('book_id');
		$longitude = $this->input->post('longitude');
		$latitude = $this->input->post('latitude');
		$advert_desc = $this->input->post('advert_desc');

		if ($book_id == null || $longitude == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'book_id' => $book_id,
	        'longitude' => $longitude,
	        'latitude' => $latitude,
	        'advert_desc' => $advert_desc

		);

		$this->load->model('advert_model');
		$result = $this->advert_model->addAdvert($data);
		
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

		public function getAdverts(){
		$user_id = $this->input->post('user_id');

		if ($user_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'user_id' => $user_id
		);

		$this->load->model('advert_model');
		$result = $this->advert_model->getAdverts($data);
		
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

	public function removeAdvert(){
		$advert_id = $this->input->post('advert_id');
		

		if ($advert_id == null) {
			$this->errorResponse("Eksik parametre girdiniz.");
			exit();
		}

		$data = array(
	        'advert_id' => $advert_id

		);

		$this->load->model('advert_model');
		$result = $this->advert_model->removeAdvert($data);
		
		if ($result == FALSE) {
			$this->errorResponse("DATABASE ERROR");
			exit();
		}
		else{
			header('Access-Control-Allow-Origin: *');
			header('Content-type: application/json');

			$response = new stdClass();
			$response->success = TRUE;
			$response->message = "Silme işlemi başarılı.";		
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