
package com.example.virlearning.common;

public class Exception extends RuntimeException {

    public Exception() {
    }

    public Exception(String message) {
        super(message);
    }

    /**
     * 丢出一个异常
     *
     * @param message
     */
    public static void fail(String message) {
        throw new Exception(message);
    }

}
