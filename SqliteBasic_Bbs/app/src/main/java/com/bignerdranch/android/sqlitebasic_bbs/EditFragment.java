package com.bignerdranch.android.sqlitebasic_bbs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Timestamp;
import java.util.Calendar;


public class EditFragment extends Fragment {

    public static int WRITE_NEW_DATA = -1;

    private static final String TAG = "EditFragment";
    EditText title;
    EditText contents;
    EditText name;
    Button save;
    Button cancel;
    Button delete;

    // TODO: Rename and change types of parameters

    private int current_position = -1;

    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }


    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        title = (EditText) view.findViewById(R.id.title_EditText);
        contents = (EditText) view.findViewById(R.id.contents_EditText);
        name = (EditText) view.findViewById(R.id.name_EidtText);
        save = (Button) view.findViewById(R.id.save_button);
        cancel = (Button) view.findViewById(R.id.cancel_button);
        delete = (Button) view.findViewById(R.id.delete_button);

        final Context context = getContext();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_position != WRITE_NEW_DATA) {
                    Log.i(TAG,current_position+"");

                    BbsData data = mListener.getData(current_position);
                    data.no = current_position;
                    data.title = title.getText().toString();
                    data.name = name.getText().toString();
                    data.contents = contents.getText().toString();
                    Calendar now = Calendar.getInstance();
                    data.ndate = now.getTime().toString();
                } else if (current_position == WRITE_NEW_DATA) {
                    Log.i(TAG,current_position+" WIITE NEW");

                    BbsData temp = new BbsData();
                    temp.no = mListener.getDatas().size();
                    temp.title = title.getText().toString();
                    temp.name = name.getText().toString();
                    temp.contents = contents.getText().toString();
                    Calendar now = Calendar.getInstance();
                    temp.ndate = now.getTime().toString();
                    mListener.getDatas().add(temp);
                }
                downKeyboard(context,contents);
                onButtonPressed(MainActivity.ACTION_SAVE, current_position);
                current_position = WRITE_NEW_DATA;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downKeyboard(context,contents);
                onButtonPressed(MainActivity.ACTION_CANCEL, 0);
                current_position = WRITE_NEW_DATA;
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downKeyboard(context,contents);
                onButtonPressed(MainActivity.ACTION_DELETE, current_position);
                current_position = WRITE_NEW_DATA;
            }
        });
        return view;
    }


    public void onButtonPressed(int actionFlag,int position) {
        if (mListener != null) {
            mListener.onFragmentInteraction(actionFlag, position);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

    public void update(int position) {
        if(position == EditFragment.WRITE_NEW_DATA) {
            title.setText("");
            contents.setText("");
            name.setText("");
        } else {
            current_position = position;
            title.setText(mListener.getData(position).title);
            contents.setText(mListener.getData(position).contents);
            name.setText(mListener.getData(position).name);
        }
    }

    public void downKeyboard(Context context, EditText editText) {
        InputMethodManager mInputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


}
