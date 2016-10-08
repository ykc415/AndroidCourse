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
    MainActivity main;
    DummyContent.DummyItem item = null;

    public FragmentTwo() {
        // Required empty public constructor
    }

    public void setData(DummyContent.DummyItem item){
        this.item = item;
    }

    @Override
    public void onStart() {
        super.onStart();
        main = (MainActivity) getActivity();
        item = main.datas.get(main.position);

        title.setText(item.content);
        contents.setText(item.details);

    }

    public void updateUI() {
        item = main.datas.get(main.position);
        title.setText(item.content);
        contents.setText(item.details);
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
