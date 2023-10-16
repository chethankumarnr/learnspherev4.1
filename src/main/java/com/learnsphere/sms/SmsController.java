package com.learnsphere.sms;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class SmsController {

        @GetMapping(value = "/sendSMS")
        public ResponseEntity<String> sendSMS() {

                Twilio.init("ACedf5d3206eb7451408511240617947a9","14ce2cae16c23bc4d6c3b3a7f43ee709");

                Message.creator(new PhoneNumber("+918431888468"),
                                new PhoneNumber("+12512205278"), "Hello from Twilio 📞").create();

                return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
        }
}

