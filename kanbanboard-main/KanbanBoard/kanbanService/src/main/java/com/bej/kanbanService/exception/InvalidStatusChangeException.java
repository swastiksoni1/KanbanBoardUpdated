package com.bej.kanbanService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED,reason = "high priority task")
public class InvalidStatusChangeException extends Exception{
}
