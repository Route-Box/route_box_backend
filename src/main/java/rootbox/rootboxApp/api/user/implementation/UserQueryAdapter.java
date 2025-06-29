package rootbox.rootboxApp.api.user.implementation;

import lombok.RequiredArgsConstructor;
import rootbox.rootboxApp.api.user.persistence.UserRepository;
import rootbox.rootboxApp.global.annotations.Adapter;
import rootbox.rootboxApp.global.entity.User;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class UserQueryAdapter {

    private final UserRepository userRepository;

    public Optional<User> findUserByIdSecurity(String userId){
        return userRepository.findById(Long.valueOf(userId));
    }

}
