package com.kodonho.android.fragmentbasic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kodonho.android.fragmentbasic.dummy.DummyContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    TextView title;
    TextView contents;

    public FragmentTwo() {
        // Required empty public constructor
    }

    public void setData(DummyContent.DummyItem item){
        title.setText(item.content);
        contents.setText(item.details);
    }

    @Override
    public void onStart() {
        super.onStart();

        if(MainActivity.position > -1)
            setData(MainActivity.datas.get(MainActivity.position));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);

        title = (TextView)view.findViewById(R.id.title);
        contents = (TextView)view.findViewById(R.id.contents);

        return view;
    }

}
