package com.example.demowithtests.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
public class TooMuchReturnedUsersException extends RuntimeException{
}
