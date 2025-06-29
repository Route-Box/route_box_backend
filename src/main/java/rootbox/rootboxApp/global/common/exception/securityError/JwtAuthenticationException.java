package rootbox.rootboxApp.global.common.exception.securityError;

import org.springframework.security.core.AuthenticationException;
import rootbox.rootboxApp.global.common.exception.base.GlobalErrorCode;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(GlobalErrorCode code) {
        super(code.name());
    }
}