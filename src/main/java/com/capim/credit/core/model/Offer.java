package com.capim.credit.core.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private BigDecimal totalValue;

    @Min(2)
    @Max(6)
    @NotNull
    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;

    @Min(1)
    @Column(name = "installment_value")
    private BigDecimal installmentValue;

    public void calculateInstallmentValue() {
        this.installmentValue = this.totalValue.divide(
                BigDecimal.valueOf(this.numberOfInstallments), 2, RoundingMode.HALF_UP
        );
    }
}
