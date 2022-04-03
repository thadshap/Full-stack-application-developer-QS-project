package ntnu.karolisw.project_backend.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntnu.karolisw.project_backend.model.Administrator;

import javax.persistence.*;
/**
 * Class for use during login
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_users")
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    // The password is hashed
    @Column(name = "password", nullable = false)
    private byte[] password;

    // The salt
    @Column(name = "salt", nullable = false)
    private byte[] salt;

    // one-to-one between user account and the person entity
    // One-to-one connection with student
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Administrator administrator;
}
