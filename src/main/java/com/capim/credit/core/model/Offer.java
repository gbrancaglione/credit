package com.capim.credit.core.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offer")
public class Offer extends GenericModel{
    @ManyToOne
    private Request request;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Min(0)
    @NotNull
    @Column(name = "total_value")
    private Double totalValue;

    @Min(2)
    @Max(6)
    @NotNull
    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;

    @Min(0)
    @Column(name = "installment_value")
    private Double installmentValue;

    public void calculateInstallmentValue() {
        this.installmentValue = this.totalValue/this.numberOfInstallments;
    }
}
