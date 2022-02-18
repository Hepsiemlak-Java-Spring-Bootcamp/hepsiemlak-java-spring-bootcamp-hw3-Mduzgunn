package emlakburada.controller;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.Messagerequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.dto.response.MessageResponse;
import emlakburada.service.AdvertService;
import emlakburada.service.MesajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MessageController {

    @Autowired
    private MesajService mesajService;

    @GetMapping(value = "/message")
    public ResponseEntity<List<MessageResponse>> getAllMessage() {
        return new ResponseEntity<>(mesajService.getAllMessages(), HttpStatus.OK);
    }

    @PostMapping(value = "/message")
    public ResponseEntity<MessageResponse> createMessage(@RequestBody Messagerequest request) {
        return new ResponseEntity<>(mesajService.saveMessage(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/message/{messageNo}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable(required = false) int messageNo) {
        return new ResponseEntity<>(mesajService.getMessageById(messageNo), HttpStatus.OK);
    }

}
