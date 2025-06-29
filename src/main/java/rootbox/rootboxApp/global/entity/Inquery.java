package rootbox.rootboxApp.global.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import rootbox.rootboxApp.global.entity.enums.inquery.InqueryType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inquery")
public class Inquery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private InqueryType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "inquery")
    private List<InqueryPhoto> inqueryPhotoList = new ArrayList<>();

    public void setUser(User user) {
        if(user != null)
            this.user.getInqueryList().remove(this);
        this.user = user;
        user.getInqueryList().add(this);
    }
}
