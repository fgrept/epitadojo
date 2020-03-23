package com.cursan.homeshop;

public class NoProductInBillException extends RuntimeException {
    public NoProductInBillException(String message) {
        super(message);
    }

}
