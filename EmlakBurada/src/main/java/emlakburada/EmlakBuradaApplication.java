package emlakburada;

import emlakburada.controller.IlanController;
import emlakburada.model.Advert;
import emlakburada.model.RealEstate;
import emlakburada.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import emlakburada.repository.IlanRepository;
import emlakburada.service.AdvertService;
import emlakburada.service.Singleton;

@SpringBootApplication
@ComponentScan(basePackages = {})
public class EmlakBuradaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmlakBuradaApplication.class, args);

	}

//	@Bean
//	public IlanService ilanService() {
//		return new IlanService();
//	}
//
//	@Bean
//	public IlanRepository ilanRespository() {
//		return new IlanRepository();
//	}

}
