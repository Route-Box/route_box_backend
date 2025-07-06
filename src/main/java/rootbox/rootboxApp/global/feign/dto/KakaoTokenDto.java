package rootbox.rootboxApp.global.feign.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoTokenDto {

    private String token_type;
    private String access_token;
    private String id_token;
}
