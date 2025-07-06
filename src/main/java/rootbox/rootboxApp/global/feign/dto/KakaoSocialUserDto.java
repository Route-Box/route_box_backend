package rootbox.rootboxApp.global.feign.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class KakaoSocialUserDto {

    private Long id;
    private Boolean has_signed_up;
    private LocalDateTime connected_at;
    private LocalDateTime synched_at;
    private Object properties;
    private KakaoSocialKakaoAccountDto kakao_account;
    private KakaoSocialPartnerDto for_partner;

}
