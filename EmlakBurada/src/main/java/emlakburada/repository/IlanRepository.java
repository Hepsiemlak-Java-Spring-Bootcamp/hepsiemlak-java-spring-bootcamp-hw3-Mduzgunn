package emlakburada.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import emlakburada.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import emlakburada.model.Advert;
import emlakburada.model.RealEstate;

@Repository
public class IlanRepository {

	@Autowired
	UserRepository userRepository;

	private String url = "localhost";
	private String pass = "şifre";

	private static List<Advert> ilanListesi = new ArrayList<>();

	static {
		ilanListesi.add(prepareIlan(38164780, "Sahibinden Acil Satılık"));
		ilanListesi.add(prepareIlan(38164781, "Dosta GİDERRR ACİLLL!!!"));
		ilanListesi.add(prepareIlan(38164782, "Metroya Koşarak 5 dk"));
		ilanListesi.add(prepareIlan(38164783, "Öğrenciye ve Bekar uygun"));
	}
	public User favlar(int ilanNo, int userNo){
		User user = userRepository.findUserById(userNo);
		Advert advert=findAdvertByAdvertId(ilanNo);
		user.setFavoriIlanlar(Set.of(advert));
		return user;
	}

	public Set<Advert> getfavlar(int userNo){
		User user = userRepository.findUserById(userNo);
		return user.getFavoriIlanlar();
	}

	public List<Advert> fetchAllAdverts() {
		return ilanListesi;
	}

	private static Advert prepareIlan(int advertNo, String baslik) {
		Advert advert = new Advert();
		advert.setAdvertNo(advertNo);
		advert.setBaslik(baslik);
		advert.setGayrimenkul(makeGayrimenkul());
		advert.setAktifMi(true);
		advert.setResimList(makeResimList());
		return advert;
	}

	private static String[] makeResimList() {
		String[] resimList = new String[5];
		resimList[0] = "https://hecdnw01.hemlak.com/ds01/4/4/9/0/2/3/8/3/81d2e088-a551-485d-b2e9-664cc9200cdc.jpg";
		resimList[1] = "https://hecdnw01.hemlak.com/ds01/4/4/9/0/2/3/8/3/81d2e088-a551-485d-b2e9-664cc9200cdc.jpg";
		return resimList;
	}

	private static RealEstate makeGayrimenkul() {
		return new RealEstate();
	}

	public Advert saveAdvert(Advert advert) {
		ilanListesi.add(advert);
		System.out.println(advert.toString());
		return advert;
	}

	public Advert findAdvertByAdvertId(int advertNo) {
		return ilanListesi.stream().filter(advert -> advert.getAdvertNo() == advertNo).findAny().orElse(new Advert());
	}


}
