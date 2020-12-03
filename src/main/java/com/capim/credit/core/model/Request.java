package com.capim.credit.core.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "request")
public class Request extends GenericModel{

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<Offer> offers;

    @NotEmpty(message = "{name.must_be_filled}")
    @Column(name = "full_name")
    private String fullName;

    @CPF(message = "{cpf.not_valid}") //CPF annotation will validate format & not null
    @Column(name = "cpf" )
    private String cpf;

    @Email(message = "{email.not_valid}")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^[0-9]+$", message = "{telephone.not_valid}")
    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Min(0)
    @Column(name = "value")
    private Double value;

    @Min(2)
    @Max(6)
    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;

    @Enumerated(EnumType.STRING)
    @Column(name = "financed_item")
    private Item financedItem;
}
