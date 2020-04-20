package com.beidao.netty.dubbo.utils;

import org.apache.log4j.Logger;

/**
 * @author twjitm - [Created on 2018-07-18 17:14]
 * @jdk java version "1.8.0_77"
 */
public class LoggerUtils {

    public static Logger getLogger(Class clzz) {
        return Logger.getLogger(clzz);
    }
}
