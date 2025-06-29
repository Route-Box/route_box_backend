package rootbox.rootboxApp.global.common.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import rootbox.rootboxApp.global.common.exception.dto.ErrorResponseDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public ErrorResponseDTO getErrorReason() {
        return this.errorCode.getReason();
    }

    public ErrorResponseDTO getErrorReasonHttpStatus() {
        return this.errorCode.getReasonHttpStatus();
    }

    public String getErrorCode(){
        return this.errorCode.getReason().getCode();
    }
}
