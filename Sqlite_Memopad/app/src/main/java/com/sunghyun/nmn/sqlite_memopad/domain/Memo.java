package com.sunghyun.nmn.sqlite_memopad.domain;

/**
 * Created by YKC on 2016. 10. 13..
 */

public class Memo {

    public int no;
    public String contents;
    public long ndate;
    public String image;

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }
}
