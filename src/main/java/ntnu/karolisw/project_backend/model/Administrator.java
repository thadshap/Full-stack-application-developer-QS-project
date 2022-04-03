package ntnu.karolisw.project_backend.model;

import lombok.*;
import ntnu.karolisw.project_backend.model.user.AdminUser;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "administrators")
/**
 * This table extends the Teacher class using the Single Table Inheritance strategy. In this strategy,
 * all the classes - only administrator in this case - in a hierarchy are mapped to a single table.
 */
public class Administrator extends Person {

    // One-to-one relationship with AdminUser
    @OneToOne(mappedBy = "administrator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AdminUser adminUser;
}
