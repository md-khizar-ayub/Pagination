package com.mka.pagination.exception;

public class ProductNotFountException extends  RuntimeException{
    private String message;

    public ProductNotFountException(String message){
        this.message = message;
    }
}
