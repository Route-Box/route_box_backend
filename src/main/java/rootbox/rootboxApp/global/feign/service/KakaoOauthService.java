package rootbox.rootboxApp.global.feign.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rootbox.rootboxApp.global.feign.client.KakaoInfoFeignClient;
import rootbox.rootboxApp.global.feign.client.KakaotestFeignClient;
import rootbox.rootboxApp.global.feign.dto.KakaoSocialUserDto;
import rootbox.rootboxApp.global.feign.dto.KakaoTokenRequestDto;
import rootbox.rootboxApp.global.feign.dto.OAuthInfoDto;
import rootbox.rootboxApp.global.feign.mapper.KakaoOAuthMapper;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoOauthService {

    private final KakaoInfoFeignClient kakaoInfoFeignClient;

    private final KakaotestFeignClient kakaotestFeignClient;

    @Value("${oauth.kakao.redirectUrl}")
    private String redirectUrl;

    @Value("${oauth.kakao.clientId}")
    private String clientId;


    public OAuthInfoDto getKakaoUserInfo(String token) {
        KakaoSocialUserDto info = kakaoInfoFeignClient.getInfo(token);
        return KakaoOAuthMapper.toOAuthInfoDto(info);
    }

    public void getKakaoCode(){
        kakaotestFeignClient.getCode("code",clientId,redirectUrl);
    }

    public String getKakaoCodeUrl(){
        String baseUrl = "https://kauth.kakao.com/oauth/authorize";
        return baseUrl +
                "?response_type=code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUrl;
    }

    public String getKakaoAccessToken(String code){
        Map<String, String> params = Map.of(
                "grant_type", "authorization_code",
                "client_id", clientId,
                "redirect_uri", redirectUrl,
                "code", code
        );

        return KakaoOAuthMapper.toKakaoToken(kakaotestFeignClient.getToken(params));
    }
}
