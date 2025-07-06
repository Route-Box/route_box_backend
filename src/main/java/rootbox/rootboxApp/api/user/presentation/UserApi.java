package rootbox.rootboxApp.api.user.presentation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rootbox.rootboxApp.api.user.business.UserService;
import rootbox.rootboxApp.api.user.presentation.dto.JoinDto;
import rootbox.rootboxApp.api.user.presentation.dto.SocialLoginDto;
import rootbox.rootboxApp.global.common.CommonResponse;
import rootbox.rootboxApp.global.entity.User;
import rootbox.rootboxApp.global.security.handler.annotation.AuthMember;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "User Api", description = "rootbox 사용자 관련 Api입니다.")
@RequestMapping(value = "/api/v1/users")
public class UserApi {

    private final UserService userService;

    @GetMapping(value = "/auth/health")
    public String health() {return "I'm healthy!!!!!" ;}

    @PostMapping(value = "/auth/kakao")
    public CommonResponse<SocialLoginDto.KakaoSocialLoginResponseDto> kakaoSocialLogin(@RequestBody @Valid SocialLoginDto.KakaoSocialLoginRequestDto requestDto) {
        return CommonResponse.onSuccess(userService.socialLogin(requestDto));
    }
    @GetMapping(value = "/auth/kakao/code")
    public void kakaoSocailLoginTest(HttpServletResponse response) throws IOException {
        response.sendRedirect(userService.getKakaoCode());
    }

    @GetMapping(value = "/auth/kakao/test")
    public CommonResponse<String> getKakaoToken(@RequestParam("code") String code){
        return CommonResponse.onSuccess(userService.getKakaoToken(code));
    }

    @GetMapping("/auth/nickname")
    public CommonResponse<JoinDto.JoinNickNameCheckResponseDto> checkNickName(@RequestParam(name = "nickname") String nickname) {
        return CommonResponse.onSuccess(
                JoinDto.JoinNickNameCheckResponseDto.builder().useYn(!userService.checkNickname(nickname)).build());
    }

    @PatchMapping("/")
    public CommonResponse<JoinDto.JoinResponseDto> joinUser(@RequestBody @Valid JoinDto.JoinRequestDto requestDto, @AuthMember @Parameter(hidden = true) User user) {
        return CommonResponse.onSuccess(userService.join(requestDto, user));
    }

    @GetMapping("/")
    public String testToken(@AuthMember @Parameter(hidden = true) User user) {
        return "인증! 현 로그인 사용자 닉네임 : " + user.getNickname();
    }
}
