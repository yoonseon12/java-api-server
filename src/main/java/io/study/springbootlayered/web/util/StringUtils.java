package io.study.springbootlayered.web.util;

import org.slf4j.helpers.MessageFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    public static String format(String format, Object... args) {
        return MessageFormatter.arrayFormat(format, args).getMessage();
    }

}
