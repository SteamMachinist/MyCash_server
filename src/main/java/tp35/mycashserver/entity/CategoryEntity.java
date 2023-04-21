package tp35.mycashserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.awt.*;
import java.util.Objects;

@Entity
@Table(name="category")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryEntity {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "base_category_id")
    private BaseCategoryEntity baseCategory;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private Boolean isLimited;
    private Double spendingLimit;
    private Color color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CategoryEntity that = (CategoryEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
