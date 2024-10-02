package com.example.spisopbackend.utils.aspects.logs;

import org.slf4j.LoggerFactory;

public interface Logger {

    // Logger instance
    org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);

    String RESET = "\033[0m";  // Text Reset

    //Text colors
    String RED = "\033[0;31m";     // RED
    String LIGHT_RED = "\033[1;31m";
    String GREEN = "\033[0;32m";   // GREEN
    String LIGHT_GREEN = "\033[1;32m";
    String LIGHT_YELLOW = "\033[1;33m";
    String YELLOW = "\033[0;33m";  // YELLOW
    String BLUE = "\033[0;34m";    // BLUE

    String LIGHT_BLUE = "\033[1;34m";
    String PURPLE = "\033[0;35m";  // PURPLE
    String CYAN = "\033[0;36m";    // CYAN

    //Background colors

    String BLACK_BACKGROUND = "\033[40m";  // BLACK
    String RED_BACKGROUND = "\033[41m";    // RED
    String LIGHT_RED_BACKGROUND = "\033[101m";
    String GREEN_BACKGROUND = "\033[42m";  // GREEN
    String LIGHT_GREEN_BACKGROUND = "\033[102m";
    String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    String LIGHT_YELLOW_BACKGROUND = "\033[103m";
    String BLUE_BACKGROUND = "\033[44m";   // BLUE
    String LIGHT_BLUE_BACKGROUND = "\033[104m";
    String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    String LIGHT_PURPLE_BACKGROUND = "\033[105m";
    String CYAN_BACKGROUND = "\033[46m";   // CYAN
    String WHITE_BACKGROUND = "\033[47m";  // WHITE

}
