package ru.voidelectrics.restaurantvoter.util.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = RequestForbidden.FORBIDDEN_TIME_MSG) // 403
public class RequestForbidden extends RuntimeException {
    public static final String FORBIDDEN_TIME_MSG = "Changing votes after 11:00 is forbidden";

    public RequestForbidden(String msg) {
        super(msg);
    }
}
