package rootbox.rootboxApp.global.feign.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import rootbox.rootboxApp.global.feign.config.KakaoFeignConfiguration;
import rootbox.rootboxApp.global.feign.dto.KakaoTokenDto;
import rootbox.rootboxApp.global.feign.dto.KakaoTokenRequestDto;

import java.util.Map;

@FeignClient(name = "KakaoTestFeignClient", url = "https://kauth.kakao.com", configuration = KakaoFeignConfiguration.class)
@Component
public interface KakaotestFeignClient {

    @GetMapping("/oauth/authorize")
    public void getCode (@RequestParam("response_type") String type, @RequestParam("client_id") String client_id, @RequestParam("redirect_uri") String redirect_uri);

    @PostMapping(value = "/oauth/token" ,consumes = "application/x-www-form-urlencoded;charset=utf-8")
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=utf-8")
    public KakaoTokenDto getToken(
            @RequestParam Map<String, ?> params);
}
