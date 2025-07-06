package rootbox.rootboxApp.global.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import rootbox.rootboxApp.global.feign.config.KakaoFeignConfiguration;
import rootbox.rootboxApp.global.feign.dto.KakaoSocialUserDto;

@FeignClient(name = "KakaoInfoFeignClient", url = "${oauth.kakao.baseUrl}", configuration = KakaoFeignConfiguration.class)
@Component
public interface KakaoInfoFeignClient {

    @GetMapping("/v2/user/me")
    KakaoSocialUserDto getInfo(@RequestHeader(name = "Authorization") String Authorization);


}
