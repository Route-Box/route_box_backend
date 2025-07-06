package rootbox.rootboxApp.api.user.business;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rootbox.rootboxApp.api.user.presentation.dto.JoinDto;
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

    public static JoinDto.JoinResponseDto toJoinResponseDto(User user){

        return JoinDto.JoinResponseDto.builder()
                .alarmAgree(user.getGetAlarmYn() == 1)
                .birthDay(user.getBirthday())
                .gender(user.getSex().name())
                .nickName(user.getNickname())
                .locationAgree(user.getLocationServiceYn() == 1)
                .build();

    }
}
