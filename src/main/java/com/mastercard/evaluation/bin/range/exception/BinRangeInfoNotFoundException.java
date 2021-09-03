package com.mastercard.evaluation.bin.range.exception;

import java.util.UUID;

public class BinRangeInfoNotFoundException extends RuntimeException {
    public BinRangeInfoNotFoundException(UUID ref){
        super(ref.toString() + " not found");
    }
}
