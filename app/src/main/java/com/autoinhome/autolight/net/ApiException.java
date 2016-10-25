package com.autoinhome.autolight.net;

import rx.exceptions.Exceptions;
import rx.internal.util.ExceptionsUtils;

/**
 * Created by yanglong on 2016/10/25.
 */

public class ApiException extends RuntimeException {

    static final String KEY_NOT_LEGAL = "10001";
    static final String API_REPAIRING = "10020";
    static final String API_STOP = "10021";
    static final String NO_RESULT = "22301";
    static final String CID_IS_NULL = "22302";


    public ApiException(String retCode) {
        super(getApiExceptionMessage(retCode));
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(String code){
        String message = "";
        switch (code) {
            case KEY_NOT_LEGAL:
                message = "appkey不合法";
                break;
            case API_REPAIRING:
                message = "接口维护";
                break;
            case API_STOP:
                message = "接口停用";
                break;
            case NO_RESULT:
                message = "查询不到数据";
                break;
            case CID_IS_NULL:
                message = "cid不能为空";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}
