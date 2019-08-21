package com.weshare.asset.common.util;

import org.springframework.lang.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
    @Nullable public static String errorStackMsg(Throwable error) {
        if (error == null) {
            return null;
        }

        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
