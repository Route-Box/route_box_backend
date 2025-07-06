package rootbox.rootboxApp.global.feign.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rootbox.rootboxApp.global.common.exception.ThrowClass.CustomFeignClientException;
import rootbox.rootboxApp.global.common.exception.base.GlobalErrorCode;

public class FeignClientExceptionErrorDecoder implements ErrorDecoder {

    Logger logger = LoggerFactory.getLogger(FeignClientExceptionErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {

        String requestUrl = "Unknown URL";
        if (response.request() != null) {
            requestUrl = response.request().url();
        }

        if (response.status() >= 400 && response.status() <= 499) {
            logger.error("{}번 에러 발생 at {} : 에러 사유 : {}, 에러 사유가 null인 경우를 대비 : {}, 요청 REQUEST 정보는 : {}", response.status(),requestUrl, response.reason(), response, response.request());
            return new CustomFeignClientException(GlobalErrorCode.FEIGN_CLIENT_ERROR_400);
        } else {
            logger.error("500번대 에러 발생 at : {} 에러 사유 : {} 요청 REQUEST 정보는 : {}",requestUrl, response.reason(), response.request());
            return new CustomFeignClientException(GlobalErrorCode.FEIGN_CLIENT_ERROR_500);
        }
    }
}
