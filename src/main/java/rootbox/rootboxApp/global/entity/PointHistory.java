package rootbox.rootboxApp.global.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "point_history")
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer plusYn;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
