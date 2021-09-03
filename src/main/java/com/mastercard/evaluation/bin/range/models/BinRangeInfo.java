package com.mastercard.evaluation.bin.range.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BinRangeInfo {

    private UUID ref;

    @NotNull
    private BigDecimal start;

    @NotNull
    private BigDecimal end;

    @NotBlank
    private String bankName;

    @NotBlank
    private String currencyCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinRangeInfo that = (BinRangeInfo) o;
        return Objects.equals(ref, that.ref) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ref, start, end, bankName, currencyCode);
    }
}
