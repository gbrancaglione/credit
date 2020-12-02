package com.capim.credit.core.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@Builder
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offer")
public class Offer extends GenericModel{
    @ManyToOne
    private Request request;

    @Min(0)
    @Column(name = "total_value")
    private Double totalValue;

    @Min(1)
    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;

    @Min(1)
    @Column(name = "installment_value")
    private Double installmentValue;
}
