package com.chenhe.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenhe
 * @Date 2018-06-04 18:20
 * @desc
 **/
public class WordProcessorImpl implements IWordProcessor {
    private Logger logger = LoggerFactory.getLogger(WordProcessorImpl.class);

    @Override
    public String extractChinese(String value) {
        logger.info("[remote服务] receive string {}", value);
        return "response " + value;
    }
}
