package rootbox.rootboxApp.api.user.implementation;

import lombok.RequiredArgsConstructor;
import rootbox.rootboxApp.api.user.persistence.RefreshTokenRepository;
import rootbox.rootboxApp.api.user.persistence.UserRepository;
import rootbox.rootboxApp.global.annotations.Adapter;
import rootbox.rootboxApp.global.entity.RefreshToken;
import rootbox.rootboxApp.global.entity.User;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class UserQueryAdapter {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public Optional<User> findUserByIdSecurity(String userId){
        return userRepository.findById(Long.valueOf(userId));
    }

    public Optional<User> findUserByNickname(String nickname){
        return userRepository.findByNickname(nickname);
    }

    public Optional<User> findUserBySocialId(String socialId){
        return userRepository.findBySocialLoginUid(socialId);
    }

    public Optional<RefreshToken> findRefreshTokenByUserId(String userId){
        return refreshTokenRepository.findByUserSocialId(userId);
    }
}
