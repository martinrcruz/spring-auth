package com.auth.api.exceptions;

import lombok.Data;

@Data
public class CustomAuthException extends Throwable {
    int codError;
    String message;
}
