package rootbox.rootboxApp.api.user.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

public class ReAuthDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReGenerateAccessTokenDto {

        @NotNull
        String accessToken;

        @NotNull
        String refreshToken;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReGenerateRefreshTokenDto {

        @NotNull
        String accessToken;

        @NotNull
        String refreshToken;
    }
}
