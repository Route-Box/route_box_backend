package rootbox.rootboxApp.global.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rootbox.rootboxApp.global.entity.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "route_activity")
public class RouteActivity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sortNo;

    private String title;

    private String category;

    private LocalDateTime stdDate;

    private LocalDateTime endDate;

    private Float latitude;

    private Float longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @OneToMany(mappedBy = "routeActivity")
    private List<ActivityPhoto> activityPhotoList = new ArrayList<>();

    public void setRoute(Route route) {
        if(this.route != null)
            this.route.getUserRouteActivityList().remove(this);
        this.route = route;
        route.getUserRouteActivityList().add(this);
    }
}
