package com.capim.credit.core.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "cpf" )
    private String CPF;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;
}
