package rootbox.rootboxApp.global.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rootbox.rootboxApp.global.entity.common.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activity_photo")
public class ActivityPhoto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private RouteActivity routeActivity;

    public void setRouteActivity(RouteActivity routeActivity) {
        if(routeActivity != null)
            this.routeActivity.getActivityPhotoList().remove(this);
        this.routeActivity = routeActivity;
        routeActivity.getActivityPhotoList().add(this);
    }
}
