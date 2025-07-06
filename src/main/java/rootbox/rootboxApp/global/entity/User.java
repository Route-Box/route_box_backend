package rootbox.rootboxApp.global.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import rootbox.rootboxApp.api.user.presentation.dto.JoinDto;
import rootbox.rootboxApp.global.entity.common.BaseEntity;
import rootbox.rootboxApp.global.entity.enums.user.SocialType;
import rootbox.rootboxApp.global.entity.enums.user.UserRole;
import rootbox.rootboxApp.global.entity.enums.user.UserSexType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private UserSexType sex;

    private String socialLoginUid;

    @Enumerated(EnumType.STRING)
    private SocialType socialLoginType;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String introduction;

    private LocalDate deleteDate;

    private String ProfileImageUrl;

    private Integer marketingYn = 0;

    private Integer travelPhotoWn = 0;

    private Integer getAlarmYn = 0;

    private Integer locationServiceYn = 0;

    @OneToMany(mappedBy = "user")
    private List<Route> routeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Inquery> inqueryList = new ArrayList<>();

    public User joinUser(JoinDto.JoinRequestDto joinRequestDto){
        this.nickname = joinRequestDto.getNickName();
        this.birthday = joinRequestDto.getBirthDay();
        this.locationServiceYn = joinRequestDto.getLocationAgree() ? 1 : 0;
        this.getAlarmYn = joinRequestDto.getAlarmAgree() ? 1 : 0;
        if (joinRequestDto.getGender().equals("male")){
            this.sex = UserSexType.MALE;
        }
        else if (joinRequestDto.getGender().equals("female")){
            this.sex = UserSexType.FEMALE;
        }
        else
            this.sex = UserSexType.SECRET;

        return this;
    }
}
