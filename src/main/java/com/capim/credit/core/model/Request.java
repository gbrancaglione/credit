package com.capim.credit.core.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@Builder
@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Table(name = "requests")
public class Request {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at" )
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at" )
    private Timestamp updatedAt;

    @CPF //CPF annotation will validate format & not null
    @Column(name = "cpf" )
    private String cpf;

    @Email
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^[0-9]+$")
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
