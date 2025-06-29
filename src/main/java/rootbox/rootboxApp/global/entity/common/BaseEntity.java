package rootbox.rootboxApp.global.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Column(name = "deleteYn")
    private Integer deleteYn = 0;

    @Column(nullable = false, updatable = false, name = "reg_date")
    @CreatedDate
    private LocalDateTime regDate;

    @Column(nullable = false,name = "mod_date")
    private LocalDateTime modDate;
}
