package com.mn.pp.core.common;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/11/19 0019.
 */
public class CompatorUtils {
    public static class MyCompartor implements Comparator<Comparable>{
        public int compare(Comparable o1, Comparable o2) {
            return  o1.compareTo(o2);
        }
    }
}
