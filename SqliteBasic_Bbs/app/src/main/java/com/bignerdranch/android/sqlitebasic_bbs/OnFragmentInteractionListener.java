package com.bignerdranch.android.sqlitebasic_bbs;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by YKC on 2016. 10. 10..
 */

public interface OnFragmentInteractionListener {
    // 일반 Action 을 처리하는 함수
    void onFragmentInteraction(int actionFlag, int position);
    // main의 목록을 가져오는 함수
    ArrayList<BbsData> getDatas();
    // 개별 레코드 글을 가져오는 함수
    BbsData getData(int position);


}
