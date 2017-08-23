package com.tuling.common.validator;

import com.tuling.common.exception.RRException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * 数据校验
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String err) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(err, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    public static void isNull(Object object, String err) {
        if (object == null) {
            throw new RRException(err,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }
}
