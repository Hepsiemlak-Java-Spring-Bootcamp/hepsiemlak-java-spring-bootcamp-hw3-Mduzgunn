package emlakburada.controller;

import java.util.List;
import java.util.Set;

import emlakburada.dto.FavReq;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.Advert;
import emlakburada.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.service.AdvertService;

@RestController
public class IlanController {

	@Autowired
	private AdvertService advertService;

	@GetMapping(value = "/adverts")
	public ResponseEntity<List<AdvertResponse>> getAllAdvert() {
		return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
	}

	@PostMapping(value = "/adverts")
	public ResponseEntity<AdvertResponse> createAdvert(@RequestBody AdvertRequest request) {
		return new ResponseEntity<>(advertService.saveAdvert(request), HttpStatus.CREATED);
	}

	@GetMapping(value = "/adverts/{advertNo}")
	public ResponseEntity<AdvertResponse> getAdvertByAdvertId(@PathVariable(required = false) int advertNo) {
		return new ResponseEntity<>(advertService.getAdvertByAdvertId(advertNo), HttpStatus.OK);
	}

	@PostMapping(value = "/ad/fav")
	public ResponseEntity<User> postAdvertByAdvertId(@RequestBody FavReq favReq) {
		return new ResponseEntity<>(advertService.postAdvertByUserId(favReq), HttpStatus.OK);
	}
	@GetMapping(value = "/ad/{userNo}")
	public ResponseEntity<Set<Advert>> getAdvertByUserId(@PathVariable int userNo) {
		return new ResponseEntity<>(advertService.getAdvertByUserId(userNo), HttpStatus.OK);
	}
}
