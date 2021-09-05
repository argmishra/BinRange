package com.mastercard.evaluation.bin.range.models;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@ToString
@AllArgsConstructor
public class BinRange implements Comparable<BinRange> {
  private BigDecimal start;
  private BigDecimal end;

  public BinRange(String pan) {
    this.start = new BigDecimal(pan);
    this.end = new BigDecimal(pan);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BinRange binRange = (BinRange) o;
    return Objects.equals(start, binRange.start) &&
        Objects.equals(end, binRange.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  @Override
  public int compareTo(BinRange o) {
    int result = 0;
    if (start.compareTo(o.end) > 0) {
      result = -1;
    } else if (end.compareTo(o.start) < 0) {
      result = 1;
    }

     return result;
  }
}
