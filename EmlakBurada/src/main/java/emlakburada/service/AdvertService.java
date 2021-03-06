package emlakburada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.Query;

import emlakburada.dto.FavReq;
import emlakburada.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import emlakburada.client.BannerClient;
import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.model.Advert;
import emlakburada.model.RealEstate;
import emlakburada.model.User;
import emlakburada.queue.QueueService;
import emlakburada.repository.DbConnectionRepository;
import emlakburada.repository.IlanRepository;

@Service
public class AdvertService {

	@Autowired
	private IlanRepository advertRepository;

	@Autowired
	@Qualifier(value = "jdbcConnectionRepository")
	private DbConnectionRepository dbConn;

	@Autowired
	private KullaniciService kullaniciService;
	
	private static int advertNo = 38164784;
	
	@Autowired
	private BannerClient bannerClient;
	
	@Autowired
	private QueueService queueService;

	public List<AdvertResponse> getAllAdverts() {
		List<AdvertResponse> advertList = new ArrayList<>();
		for (Advert advert : advertRepository.fetchAllAdverts()) {
			advertList.add(convertToAdvertResponse(advert));
		}
		return advertList;
	}

	public AdvertResponse saveAdvert(AdvertRequest request) {
		Advert savedAdvert = advertRepository.saveAdvert(convertToAdvert(request));
		EmailMessage emailMessage = new EmailMessage("cemdrman@gmail.com");
		queueService.sendMessage(emailMessage);
		bannerClient.saveBanner();
		return convertToAdvertResponse(savedAdvert);
	}

	private AdvertResponse convertToAdvertResponse(Advert savedAdvert) {
		AdvertResponse response = new AdvertResponse();
		response.setBaslik(savedAdvert.getBaslik());
		response.setFiyat(savedAdvert.getFiyat());
		response.setAdvertNo(savedAdvert.getAdvertNo());
		return response;
	}

	private Advert convertToAdvert(AdvertRequest request) {
		Advert advert = new Advert(new RealEstate(), new User(), new String[5]);
		advertNo++;
		
		advert.setAdvertNo(advertNo);
		advert.setBaslik(request.getBaslik());
		advert.setFiyat(request.getFiyat());
		return advert;
	}

	public AdvertResponse getAdvertByAdvertId(int advertId) {
		Advert advert = advertRepository.findAdvertByAdvertId(advertId);
		return convertToAdvertResponse(advert);
	}

	public User postAdvertByUserId(FavReq favReq) {
		return advertRepository.favlar(favReq.getAdvertNo(),favReq.getUserNo());
	}
	public Set<Advert> getAdvertByUserId(int userNo) {
		return advertRepository.getfavlar(userNo);
	}

}
