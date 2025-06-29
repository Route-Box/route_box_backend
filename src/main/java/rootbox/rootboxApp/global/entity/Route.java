package rootbox.rootboxApp.global.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rootbox.rootboxApp.global.entity.common.BaseEntity;
import rootbox.rootboxApp.global.entity.enums.route.WhoWith;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "route")
public class Route extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime stdDate;

    private LocalDateTime endDate;

    private Integer publicYn = 1;

    private String title;

    @Enumerated(EnumType.STRING)
    private WhoWith whoWith;

    private Integer numberOfPeople;

    // 0 = 당일, 1 = 1박 2일 ...
    private Integer numberOfDays;

    private String transfer;

    @OneToMany(mappedBy = "route")
    private List<UserRouteStyle> userRouteStyleList = new ArrayList<>();

    @OneToMany(mappedBy = "route")
    private List<RouteActivity> userRouteActivityList = new ArrayList<>();

    @OneToMany(mappedBy = "route")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getRouteList().remove(this);
        }
        this.user = user;
        user.getRouteList().add(this);
    }

}
