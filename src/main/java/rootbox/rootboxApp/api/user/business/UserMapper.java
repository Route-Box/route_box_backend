package rootbox.rootboxApp.api.user.business;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rootbox.rootboxApp.global.entity.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserService userService;
    private static UserService staticUserService;

    @PostConstruct
    public void init(){
        staticUserService = this.userService;
    }

    public static Optional<User> toUserSecurity(String id){
        return staticUserService.findById(id);
    }
}
