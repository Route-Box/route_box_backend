package rootbox.rootboxApp.api.user.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rootbox.rootboxApp.api.user.persistence.RefreshTokenRepository;
import rootbox.rootboxApp.api.user.persistence.UserRepository;
import rootbox.rootboxApp.api.user.presentation.dto.JoinDto;
import rootbox.rootboxApp.global.annotations.Adapter;
import rootbox.rootboxApp.global.entity.RefreshToken;
import rootbox.rootboxApp.global.entity.User;
import rootbox.rootboxApp.global.entity.enums.user.UserRole;

@Adapter
@Slf4j
@RequiredArgsConstructor
public class UserCommandAdapter {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public User createUser(String socialUid, String username){

        User newUser = User.builder()
                .socialLoginUid(socialUid)
                .nickname(username)
                .userRole(UserRole.USER)
                .build();

        return userRepository.save(newUser);
    }

    public RefreshToken saveRefreshToken(String refreshToken, String userSocialId){

        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .userSocialId(userSocialId)
                        .refreshToken(refreshToken)
                        .build()
        );
    }

    public User joinUser(JoinDto.JoinRequestDto requestDto, User user){
        return user.joinUser(requestDto);
    }
}
