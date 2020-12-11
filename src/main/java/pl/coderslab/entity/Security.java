package pl.coderslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "securities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Security implements EntityModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Z]{2}[0-9]{10}")
    private String ISIN;

    @NotEmpty
    private String securityName;

    @NotNull
    private SecurityType securityType;

//@TODO update price feature to be added in future
    @NotNull
    private Float price;

//@TODO currency feature to be added in future

    @Transient
    @OneToMany(mappedBy = "security")
    private List<Trade> trades = new ArrayList<>();

}
