package com.weshare.asset.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
    public static String errorStackMsg(Throwable error) {
        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
