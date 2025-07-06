package rootbox.rootboxApp.global.feign.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import rootbox.rootboxApp.global.feign.exception.FeignClientExceptionErrorDecoder;

public class KakaoFeignConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return  new FeignClientExceptionErrorDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
