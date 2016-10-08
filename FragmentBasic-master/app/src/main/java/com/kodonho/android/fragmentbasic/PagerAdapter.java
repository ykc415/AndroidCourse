package com.kodonho.android.fragmentbasic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

/**
 * Created by YKC on 2016. 10. 6..
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    Fragment listFragment;
    FragmentTwo detailFragement;

    // Arraylist<Fragment> datas 사용해서 넘기는게 더효율적
    public PagerAdapter(FragmentManager fm,Fragment listFragment, Fragment detailFragment) {
        super(fm);
        this.listFragment = listFragment;
        this.detailFragement = (FragmentTwo) detailFragment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = listFragment;
                break;
            case 1:
                fragment = detailFragement;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
