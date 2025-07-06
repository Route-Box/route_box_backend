package rootbox.rootboxApp.api.user.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


public class SocialLoginDto {

    @Builder
    @Getter
    @Setter
    public static class KakaoSocialLoginResponseDto {

        @NotNull
        String loginType;

        @NotNull
        Boolean isNew;

        @NotNull
        String accessToken;

        @NotNull
        String refreshToken;
        @NotNull
        String userSocialId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class KakaoSocialLoginRequestDto {

        @NotNull
        String kakaoToken;
    }
}
