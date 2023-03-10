package org.techtown.challenge22;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Fragment1 extends Fragment {
    EditText editText;
    EditText editText2;
    EditText editText3;

    OnDatabaseCallBack callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        callback = (OnDatabaseCallBack) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);

        editText = rootView.findViewById(R.id.editText);
        editText2 = rootView.findViewById(R.id.editText2);
        editText3 = rootView.findViewById(R.id.editText3);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String author = editText2.getText().toString();
                String contents = editText3.getText().toString();

                //!!!!!!!!!!!!!!!!!!
                callback.insert(name, author, contents);
                Toast.makeText(getActivity(), "데이터 삽입 완료", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}