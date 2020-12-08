package pl.coderslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements EntityModel {

//@TODO dlaczego nie tworzy tabeli users? (a inne tworzy)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[0-9]{9}")
    @Column(length = 9)
    private String comitId;

    @NotBlank
    private String password;

    @NotNull
    private UserGroup group;
    
}

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Pattern(regexp = "[0-9]{6}")
//    private Long id;
//
//    @Size(min = 5)
//    @Column(length = 140)
//    private String title;
//
//    @Size(max = 600)
//    @Column(columnDefinition = "TEXT")
//    private String description;
//
//    @Min(1)
//    @Max(10)
//    private int rating;
//
//    @Min(1)
//    private int pages;
//
//    @NotEmpty
//    @ManyToMany
//    List<Author> authors = new ArrayList<>();
//
//    @NotNull
//    @ManyToOne
//    private Publisher publisher;
//
//    @ManyToOne
//    private Category category;
