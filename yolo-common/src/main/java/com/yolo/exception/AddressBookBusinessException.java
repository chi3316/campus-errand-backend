package com.yolo.exception;

public class AddressBookBusinessException extends BaseException {
     AddressBookBusinessException(){}
    public AddressBookBusinessException(String msg) {
        super(msg);
    }
}
