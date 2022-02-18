package emlakburada.controller;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class FeignController {
    @Autowired
    private FeignUtil feignUtil;

    @GetMapping(value = "/banners")
    public List<BannerResponse> getAllBanners() {
       return feignUtil.getAllBanners();
    }
    @PostMapping(value = "/banners")
    public ResponseEntity<BannerResponse> saveBanner(@RequestBody BannerRequest request) {
        return feignUtil.saveBanner(request);
    }
}
