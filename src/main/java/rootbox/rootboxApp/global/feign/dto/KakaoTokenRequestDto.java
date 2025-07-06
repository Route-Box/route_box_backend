package rootbox.rootboxApp.global.feign.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoTokenRequestDto {

    private String grant_type;
    private String client_id;
    private String redirect_uri;
    private String code;
}
