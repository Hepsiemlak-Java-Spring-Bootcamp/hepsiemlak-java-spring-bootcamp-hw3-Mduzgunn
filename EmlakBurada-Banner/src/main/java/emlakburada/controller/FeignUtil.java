package emlakburada.controller;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(value = "feignDemo", url = "http://localhost:8081/")
public interface FeignUtil {

    @GetMapping("/banners")
    List<BannerResponse> getAllBanners();

    @PostMapping(value = "/banners")
    ResponseEntity<BannerResponse> saveBanner(@RequestBody BannerRequest request);

}
