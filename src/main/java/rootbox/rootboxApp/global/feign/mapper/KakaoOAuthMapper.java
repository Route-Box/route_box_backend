package rootbox.rootboxApp.global.feign.mapper;

import rootbox.rootboxApp.global.feign.dto.KakaoSocialUserDto;
import rootbox.rootboxApp.global.feign.dto.KakaoTokenDto;
import rootbox.rootboxApp.global.feign.dto.OAuthInfoDto;

public class KakaoOAuthMapper {

    public static OAuthInfoDto toOAuthInfoDto(KakaoSocialUserDto kakaoSocialUserDto){
        return OAuthInfoDto.builder()
                .email(kakaoSocialUserDto.getKakao_account().getEmail())
                .id(String.valueOf(kakaoSocialUserDto.getId()))
                .build();
    }

    public static String toKakaoToken(KakaoTokenDto requestDto){
        return requestDto.getAccess_token();
    }
}
