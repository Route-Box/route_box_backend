package rootbox.rootboxApp.global.common.exception.base;

import rootbox.rootboxApp.global.common.exception.dto.ErrorResponseDTO;

public interface BaseErrorCode {

    public ErrorResponseDTO getReason();

    public ErrorResponseDTO getReasonHttpStatus();
}

