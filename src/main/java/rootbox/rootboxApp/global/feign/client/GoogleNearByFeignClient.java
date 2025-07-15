package rootbox.rootboxApp.global.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import rootbox.rootboxApp.global.feign.config.KakaoFeignConfiguration;

@FeignClient(name = "GoogleNearByFeignClient", url = "${oauth.google.baseUrl}", configuration = GoogleNearByFeignClient.class)
@Component
public interface GoogleNearByFeignClient {

    
}
