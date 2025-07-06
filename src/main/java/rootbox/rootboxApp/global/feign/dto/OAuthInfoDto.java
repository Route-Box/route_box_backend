package rootbox.rootboxApp.global.feign.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthInfoDto {

    private String email;
    private String id;
}
