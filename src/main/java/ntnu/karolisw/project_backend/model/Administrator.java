package ntnu.karolisw.project_backend.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
/**
 * This table extends the Teacher class using the Single Table Inheritance strategy. In this strategy,
 * all the classes - only administrator in this case - in a hierarchy are mapped to a single table.
 */
public class Administrator extends Person {
}
