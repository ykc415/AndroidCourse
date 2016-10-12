package com.bignerdranch.android.sqlitebasic_bbs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    CustomAdapter adapter;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button button = (Button) view.findViewById(R.id.write_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(MainActivity.ACTION_WRITE, -1);
            }
        });

        ListView listView = (ListView) view.findViewById(R.id.bbslistview);
        adapter = new CustomAdapter(getContext());
        listView.setAdapter(adapter);
        return view;
    }


    public void onButtonPressed(int actionFlag, int position) {
        if (mListener != null) {
            mListener.onFragmentInteraction(actionFlag, position);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 부모 activity에  interface가 구현되지 않았으면 exception 을 발생시켜 강제로 app을 종료시킨다
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void update() {
        adapter.notifyDataSetChanged();
    }

    class CustomAdapter extends BaseAdapter {

        Context context;
        LayoutInflater inflater;
        ArrayList<BbsData> datas;

        public CustomAdapter(Context context){
            this.context = context;
            datas = ((MainActivity)context).getDatas();

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView == null)
                convertView = inflater.inflate(R.layout.fragment_list_item, null);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonPressed(MainActivity.ACTION_MODIFY, position);
                }
            });

            TextView title = (TextView) convertView.findViewById(R.id.title_textview);
            title.setText(datas.get(position).title);

            TextView name = (TextView) convertView.findViewById(R.id.name_textview);
            name.setText(datas.get(position).name);

            TextView contents = (TextView) convertView.findViewById(R.id.contents_textview);
            contents.setText(datas.get(position).contents);

            TextView date = (TextView) convertView.findViewById(R.id.date_textview);
            date.setText(datas.get(position).ndate);

            return convertView;
        }
    }
}


