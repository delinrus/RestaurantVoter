package ru.voidelectrics.restaurantvoter.util;

public class ValidationUtil {

    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    // TODO:  implement

//    public static void assureIdConsistent(HasId bean, int id) {
////      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
//        if (bean.isNew()) {
//            bean.setId(id);
//        } else if (bean.id() != id) {
//            throw new IllegalRequestDataException(bean + " must be with id=" + id);
//        }
//    }
}
