package br.edu.ufrn.foodium.framework.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Persistable<Long> {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",updatable = false, nullable = false)
    protected Date createdAt;
}
