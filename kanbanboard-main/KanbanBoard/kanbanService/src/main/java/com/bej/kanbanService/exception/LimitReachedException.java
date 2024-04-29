package com.bej.kanbanService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS,reason = "not more then 6 in progress task")
public class LimitReachedException extends Exception {
}
