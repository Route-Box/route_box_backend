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
@Table(name = "inquery_photo")
public class InqueryPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Inquery inquery;

    public void setInquery(Inquery inquery) {
        if (this.inquery != null)
            this.inquery.getInqueryPhotoList().remove(this);
        this.inquery = inquery;
        inquery.getInqueryPhotoList().add(this);
    }
}
