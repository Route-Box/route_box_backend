package rootbox.rootboxApp.global.common.exception.ThrowClass;

import rootbox.rootboxApp.global.common.exception.base.BaseErrorCode;
import rootbox.rootboxApp.global.common.exception.base.GeneralException;

public class CustomFeignClientException extends GeneralException {
    public CustomFeignClientException(BaseErrorCode errorCode){
        super(errorCode);
    }
}
