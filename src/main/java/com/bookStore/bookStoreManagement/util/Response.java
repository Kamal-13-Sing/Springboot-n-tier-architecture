package com.bookStore.bookStoreManagement.util;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
//@Builder
public class Response implements Serializable {

    private Boolean status;

    private String Message;

    private Object object;



}
