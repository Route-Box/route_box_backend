package rootbox.rootboxApp.api.user.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rootbox.rootboxApp.api.user.implementation.UserQueryAdapter;
import rootbox.rootboxApp.global.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserQueryAdapter userQueryAdapter;


    Optional<User> findById(String id) {
        return userQueryAdapter.findUserByIdSecurity(id);
    }
}
