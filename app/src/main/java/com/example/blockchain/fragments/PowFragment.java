package com.example.blockchain.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.blockchain.R;
import com.example.blockchain.databinding.FragmentPowBinding;
import com.example.blockchain.managers.SharedPreferencesManager;


public class PowFragment extends DialogFragment implements View.OnClickListener {

    private FragmentPowBinding viewBinding;
    private Context mcontext;
    private SharedPreferencesManager prefs;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public PowFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static PowFragment newInstance() {

        return new PowFragment() ;
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        mcontext = context.getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding=FragmentPowBinding.inflate(getLayoutInflater(),container,false);
        return  viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        prefs = new SharedPreferencesManager(mcontext);
        viewBinding.edtSetPow.setText(String.valueOf(prefs.getPowValue()));
        viewBinding.btnClose.setOnClickListener(this);
        viewBinding.btnContinue.setOnClickListener(this);
    }
    @NonNull
    @Override
    //Ise dekhna
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                dismiss();
                break;

            case R.id.btn_continue:
                if (viewBinding.edtSetPow.getText()!=null){
                    String pow = viewBinding.edtSetPow.getText().toString();
                    prefs.setPowValue(Integer.parseInt(pow));

                    if (getActivity()!=null){
                        Intent intent =mcontext.getPackageManager().getLaunchIntentForPackage(mcontext.getPackageName());
                        startActivity(intent);
                        getActivity().finish();
                    }

                    else {
                        dismiss();
                    }
                }
                break;
        }

    }
    @Override
    public void onDetach(){
        super.onDetach();
        viewBinding=null;
        mcontext=null;
    }
}