package com.bookStore.bookStoreManagement.util;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response implements Serializable {

    private Boolean status;

    private String Message;

    private Object object;



}
