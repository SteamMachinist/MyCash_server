package tp35.mycashserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import tp35.mycashserver.model.CategoryType;

import java.util.Objects;

@Entity
@Table(name="base_category")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    private Integer color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseCategoryEntity that = (BaseCategoryEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
