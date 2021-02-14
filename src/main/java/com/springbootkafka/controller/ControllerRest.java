package com.springbootkafka.controller;

import com.springbootkafka.entity.Message;
import com.springbootkafka.component.KafkaProducer;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/kafka")
@Api(tags = "ControllerRest", description = "Rest spring boot kafka APIs")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The request has succeeded"),
        @ApiResponse(code = 401, message = "The request requires user authentication"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI")})
public class ControllerRest {

    @Autowired
    KafkaProducer kafkaProducer;

    @Value(value = "${kafka.topic}")
    String topic;

    @PostMapping(value = "/publish")
    @ApiOperation(value = "Publish new message" )
    public ResponseEntity<?> publish(@Valid @RequestBody Message message) {
        kafkaProducer.sendMessage(topic, message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
