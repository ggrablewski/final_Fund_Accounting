package pl.coderslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@Slf4j
public class User implements EntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[0-9]{9}")
    @Column(length = 9)
    private String comitId;

    @NotBlank
    private String password;

    @NotNull
    private UserGroup userGroup;

    public User() {
        log.trace("User noArgsConstructor");
    }
}
