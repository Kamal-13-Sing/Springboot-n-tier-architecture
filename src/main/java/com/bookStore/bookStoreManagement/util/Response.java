package com.bookStore.bookStoreManagement.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Response implements Serializable {

    private Boolean status;

    private String Message;

    private Object object;

}
