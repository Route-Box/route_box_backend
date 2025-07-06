package rootbox.rootboxApp.api.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

public class JoinDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JoinNickNameCheckResponseDto{

        private Boolean useYn;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JoinRequestDto{

        @NotNull(message = "위치 기반 동의는 필수 입력 값입니다.")
        Boolean locationAgree;

        @NotNull(message = "알람 동의는 필수 입력 값입니다.")
        Boolean alarmAgree;

        @NotNull(message = "닉네임은 필수 입력 값입니다.")
        @Size(min=2, max=8, message = "닉네임은 2 ~ 8글자 입니다.")
        String nickName;

        @NotNull(message = "생일은 필수 입력 값입니다.")
        @JsonFormat(pattern = "yyyy-MM-dd") // JSON으로 받을 때 형식 지정
        private LocalDate birthDay;

        @NotNull(message = "성별은 필수 입력 값입니다.")
        private String gender;

    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JoinResponseDto{

        @NotNull(message = "위치 기반 동의는 필수 입력 값입니다.")
        Boolean locationAgree;

        @NotNull(message = "알람 동의는 필수 입력 값입니다.")
        Boolean alarmAgree;

        @NotNull(message = "닉네임은 필수 입력 값입니다.")
        @Size(min=2, max=8, message = "닉네임은 2 ~ 8글자 입니다.")
        String nickName;

        @NotNull(message = "생일은 필수 입력 값입니다.")
        @JsonFormat(pattern = "yyyy-MM-dd") // JSON으로 받을 때 형식 지정
        private LocalDate birthDay;

        @NotNull(message = "성별은 필수 입력 값입니다.")
        private String gender;


    }
}
