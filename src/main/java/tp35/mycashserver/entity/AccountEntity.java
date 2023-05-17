package tp35.mycashserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name="account")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountEntity {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
    private String name;
    private Double balance;
    private Double target;
    private Boolean isLimited;
    private Double spendingLimit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccountEntity that = (AccountEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
