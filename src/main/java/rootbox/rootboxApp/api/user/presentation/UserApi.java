package rootbox.rootboxApp.api.user.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "User Api", description = "rootbox 사용자 관련 Api입니다.")
@RequestMapping(value = "/api/v1/user")
public class UserApi {

    @GetMapping(value = "/health")
    public String health() {return "I'm healthy" ;}
}
