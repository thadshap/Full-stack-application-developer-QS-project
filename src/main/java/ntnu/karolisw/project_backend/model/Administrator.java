package ntnu.karolisw.project_backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ntnu.karolisw.project_backend.model.user.AdminUser;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "administrators")
@ApiModel(description = "Details about tha administrator")
/**
 * This table extends the Teacher class using the Single Table Inheritance strategy. In this strategy,
 * all the classes - only administrator in this case - in a hierarchy are mapped to a single table.
 */
public class Administrator extends Person {

    // One-to-one relationship with AdminUser
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id", referencedColumnName = "id")
    @ApiModelProperty("A user that is an admin")
    private AdminUser adminUser;
}
