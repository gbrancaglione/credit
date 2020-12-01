package com.capim.credit.core.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
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

    @CPF
    @NotEmpty(message = "CPF is required")
    @Column(name = "cpf" )
    private String cpf;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Min(0)
    @Column(name = "value")
    private Double value;

    @Min(2)
    @Max(6)
    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;
}
