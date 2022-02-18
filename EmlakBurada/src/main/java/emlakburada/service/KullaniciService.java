package emlakburada.service;

import emlakburada.dto.Messagerequest;
import emlakburada.dto.UserRequest;
import emlakburada.dto.response.MessageResponse;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.Message;
import emlakburada.model.User;
import emlakburada.repository.MessageRepository;
import emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import emlakburada.repository.DbConnectionRepository;
import emlakburada.repository.IlanRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KullaniciService {

	@Autowired
	@Qualifier(value = "mongoConnectionRepository")
	private DbConnectionRepository dbConn;

	@Autowired
	private IlanRepository ilanRepository;

	@Autowired
	private UserRepository userRepository;

	private static int userNo = 1;

	public List<UserResponse> getAllUsers() {
		List<UserResponse> userList = new ArrayList<>();
		for (User user : userRepository.fetchAllUsers()) {
			userList.add(convertToUserResponse(user));
		}
		return userList;
	}

	public UserResponse saveUser(UserRequest request) {
		User saveUser = userRepository.saveUser(convertToUser(request));
		return convertToUserResponse(saveUser);
	}

	private UserResponse convertToUserResponse(User savedMessage) {
		UserResponse response = new UserResponse();
		response.setIsim(savedMessage.getIsim());
		response.setKullaniciTipi(savedMessage.getKullaniciTipi());
		response.setUserNo(savedMessage.getUserNo());
		return response;
	}

	private User convertToUser(UserRequest request) {
		User user = new User();
		userNo++;

		user.setUserNo(userNo);
		user.setIsim(request.getIsim());
		user.setKullaniciTipi(request.getKullaniciTipi());
		return user;
	}

	public UserResponse getUserById(int userId) {
		User user = userRepository.findUserById(userId);
		return convertToUserResponse(user);
	}
}
