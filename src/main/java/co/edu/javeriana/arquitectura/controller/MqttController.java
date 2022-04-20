package co.edu.javeriana.arquitectura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.arquitectura.MqttGateway;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class MqttController {
	@Autowired
	MqttGateway mqtGateway;

	@PostMapping("/sendMessage")
	public ResponseEntity<?> publish(@RequestBody String mqttMessage) {

		try {
			JsonObject convertObject = new Gson().fromJson(mqttMessage, JsonObject.class);
			// mqtGateway.senToMqtt(convertObject.get("message").toString(),
			// convertObject.get("topic").toString());
			mqtGateway.sendToMqtt(convertObject.get("message").toString());
			return ResponseEntity.ok("Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.ok("fail");
		}
	}

}
