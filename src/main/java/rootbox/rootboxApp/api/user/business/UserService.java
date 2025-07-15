package rootbox.rootboxApp.api.user.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rootbox.rootboxApp.api.user.implementation.UserCommandAdapter;
import rootbox.rootboxApp.api.user.implementation.UserQueryAdapter;
import rootbox.rootboxApp.api.user.presentation.dto.JoinDto;
import rootbox.rootboxApp.api.user.presentation.dto.ReAuthDto;
import rootbox.rootboxApp.api.user.presentation.dto.SocialLoginDto;
import rootbox.rootboxApp.global.common.exception.base.GlobalErrorCode;
import rootbox.rootboxApp.global.common.exception.base.UserException;
import rootbox.rootboxApp.global.entity.RefreshToken;
import rootbox.rootboxApp.global.entity.User;
import rootbox.rootboxApp.global.entity.enums.user.SocialType;
import rootbox.rootboxApp.global.entity.enums.user.UserRole;
import rootbox.rootboxApp.global.feign.dto.OAuthInfoDto;
import rootbox.rootboxApp.global.feign.service.KakaoOauthService;
import rootbox.rootboxApp.global.security.provider.TokenProvider;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserQueryAdapter userQueryAdapter;

    private final UserCommandAdapter userCommandAdapter;

    private final KakaoOauthService kakaoOauthService;

    private final TokenProvider tokenProvider;


    Optional<User> findById(String id) {
        return userQueryAdapter.findUserByIdSecurity(id);
    }

    @Transactional
    public SocialLoginDto.KakaoSocialLoginResponseDto socialLogin(SocialLoginDto.KakaoSocialLoginRequestDto request) {

        String kakaoToken = request.getKakaoToken();
        String requestToken = "Bearer " + kakaoToken;

        OAuthInfoDto kakaoUserInfo = kakaoOauthService.getKakaoUserInfo(requestToken);

        Optional<User> userBySocialId = userQueryAdapter.findUserBySocialId(kakaoUserInfo.getId());

        // 로그인 처리
        if (userBySocialId.isPresent()) {
            User user = userBySocialId.get();
            if (user.getNickname() ==null){
                String accessToken = tokenProvider.createAccessToken(user, List.of(new SimpleGrantedAuthority(UserRole.USER.name())));
                return SocialLoginDto.KakaoSocialLoginResponseDto
                        .builder()
                        .loginType(SocialType.KAKAO.name())
                        .isNew(true)
                        .accessToken(accessToken)
                        .refreshToken(userCommandAdapter.saveRefreshToken(tokenProvider.createRefreshToken(),
                                user.getSocialLoginUid()).getRefreshToken())
                        .userSocialId(user.getSocialLoginUid())
                        .build();
            }
            Optional<RefreshToken> refreshTokenByUserId = userQueryAdapter.findRefreshTokenByUserId(kakaoUserInfo.getId());

            String accessToken = tokenProvider.createAccessToken(userBySocialId.get(), List.of(new SimpleGrantedAuthority(UserRole.USER.name())));

            // 리프레시 토큰이 존재할 때
            if (refreshTokenByUserId.isPresent()) {
                return SocialLoginDto.KakaoSocialLoginResponseDto.builder()
                        .accessToken(accessToken)
                        .isNew(false)
                        .loginType(SocialType.KAKAO.name())
                        .refreshToken(refreshTokenByUserId.get().getRefreshToken())
                        .userSocialId(userBySocialId.get().getSocialLoginUid())
                        .build();
            }else{
                // 리프레시 토큰 없음 만약 만료된 리프레시 토큰이면 추후에 만료 로직 탈 것이라 존재 유무만 봄
                return SocialLoginDto.KakaoSocialLoginResponseDto.builder()
                        .accessToken(accessToken)
                        .isNew(false)
                        .loginType(SocialType.KAKAO.name())
                        .refreshToken(userCommandAdapter.saveRefreshToken(tokenProvider.createRefreshToken(),
                                userBySocialId.get().getSocialLoginUid()).getRefreshToken())
                        .userSocialId(userBySocialId.get().getSocialLoginUid())
                        .build();
            }
        }else {
            // 신규 가입 + 로그인
            User user = userCommandAdapter.createUser(kakaoUserInfo.getId(), generateUniqueNickname());

            String accessToken = tokenProvider.createAccessToken(user, List.of(new SimpleGrantedAuthority(UserRole.USER.name())));
            return SocialLoginDto.KakaoSocialLoginResponseDto
                    .builder()
                    .loginType(SocialType.KAKAO.name())
                    .isNew(true)
                    .accessToken(accessToken)
                    .refreshToken(userCommandAdapter.saveRefreshToken(tokenProvider.createRefreshToken(),
                            user.getSocialLoginUid()).getRefreshToken())
                    .userSocialId(user.getSocialLoginUid())
                    .build();
        }
    }

    public String getKakaoCode(){
        return kakaoOauthService.getKakaoCodeUrl();
    }

    public String getKakaoToken(String code){
        return kakaoOauthService.getKakaoAccessToken(code);
    }

    public Boolean checkNickname(String nickname) {
        return userQueryAdapter.findUserByNickname(nickname).isPresent();
    }

    @Transactional
    public JoinDto.JoinResponseDto join(JoinDto.JoinRequestDto request, User user) {
        User joinedUser = userCommandAdapter.joinUser(request, user);

        return UserMapper.toJoinResponseDto(joinedUser);
    }

    public ReAuthDto.ReGenerateAccessTokenDto reGenerateAccessToken(String socialId) {

        Optional<RefreshToken> refreshTokenByUserId = userQueryAdapter.findRefreshTokenByUserId(socialId);
        Optional<User> userBySocialId = userQueryAdapter.findUserBySocialId(socialId);

        if (userBySocialId.isEmpty())
            throw new UserException(GlobalErrorCode.USER_NOT_FOUND);
        else {
            String accessToken = tokenProvider.createAccessToken(userBySocialId.get(), List.of(new SimpleGrantedAuthority(UserRole.USER.name())));
            return ReAuthDto.ReGenerateAccessTokenDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshTokenByUserId.get().getRefreshToken())
                    .build();

        }
    }

    @Transactional
    public ReAuthDto.ReGenerateRefreshTokenDto reGenerateRefreshToken(String socialId) {
        Optional<User> userBySocialId = userQueryAdapter.findUserBySocialId(socialId);

        if (userBySocialId.isEmpty())
            throw new UserException(GlobalErrorCode.USER_NOT_FOUND);
        else {
            userCommandAdapter.deleteRefreshToken(socialId);

            String accessToken = tokenProvider.createAccessToken(userBySocialId.get(), List.of(new SimpleGrantedAuthority(UserRole.USER.name())));
            return ReAuthDto.ReGenerateRefreshTokenDto
                    .builder()
                    .accessToken(accessToken)
                    .refreshToken(userCommandAdapter.saveRefreshToken(tokenProvider.createRefreshToken(),
                            socialId).getRefreshToken())
                    .build();
        }
    }

    private String generateUniqueNickname() {
        String name = "";
        do {
            name = RandomStringUtils.random(8, true, true);
        } while (userQueryAdapter.findUserByNickname(name).isPresent());
        return name;
    }
}
