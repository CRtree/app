package com.zuoxiao.app.logger;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2020/10/28 16:31
 */
@Log4j2
public class LoggerTest {

    @Test
    void test(){
        log.info("this is info log");
        log.error("this is error log");
        log.debug("this is debug log");
        log.warn("this is warn log");
        log.trace("this is trace log");
        log.fatal("this is fatal log");
    }
}
