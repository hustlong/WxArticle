package com.autoinhome.autolight.util;

import java.util.List;

/**
 * Created by yanglong on 2016/10/25.
 */

public class EmptyUtil {

    public static <D> boolean isEmpty(List<D> list) {
        return (list == null || list.isEmpty());
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.isEmpty());
    }
}
