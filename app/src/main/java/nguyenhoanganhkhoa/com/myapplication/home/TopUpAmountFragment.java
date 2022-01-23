package nguyenhoanganhkhoa.com.myapplication.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.AppUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopUpAmountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopUpAmountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TopUpAmountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopUpAmountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopUpAmountFragment newInstance(String param1, String param2) {
        TopUpAmountFragment fragment = new TopUpAmountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button btnRequestTransfer;
    Button btn10k, btn20k, btn50k, btn100k, btn150k, btn200k;
    EditText edtAmount;
    ImageButton imbPlus, imbMinus;

    int m10k = 10000;
    int m20k = 20000;
    int m50k = 50000;
    int m100k = 100000;
    int m150k = 150000;
    int m200k = 200000;
    int m500k = 500000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_up_amount, container, false);

        linkView(view);
        addEvents();

        return view;

    }

    private void linkView(View view) {

        imbPlus = view.findViewById(R.id.imbPlus);
        imbMinus = view.findViewById(R.id.imbMinus);

        btnRequestTransfer = view.findViewById(R.id.btnRequestTransfer);

        btn10k = view.findViewById(R.id.btn10k);
        btn20k = view.findViewById(R.id.btn20k);
        btn50k = view.findViewById(R.id.btn50k);
        btn100k = view.findViewById(R.id.btn100k);
        btn150k = view.findViewById(R.id.btn150k);
        btn200k = view.findViewById(R.id.btn200k);

        edtAmount= view.findViewById(R.id.edtAmount);
    }
    private void setStatusButton(boolean isEnable, int colorButton, int colorText) {
        btnRequestTransfer.setEnabled(isEnable);
        btnRequestTransfer.setBackground(getContext().getDrawable(colorButton));
        btnRequestTransfer.setTextColor(getResources().getColor(colorText));
    }

    private void validateAmount() {
        String s = edtAmount.getText().toString();
        if(s.isEmpty())
        {
            setStatusButton(false,R.drawable.button_login_block,R.color.xamBlcok);
        }
        else{
            if((Integer.parseInt(s)==0)){
                setStatusButton(false,R.drawable.button_login_block,R.color.xamBlcok);
            }
            else{
                setStatusButton(true,R.drawable.custom_button, R.color.black);
            }

        }
    }

    private void addEvents() {
        validateAmount();
        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imbPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edtAmount.getText().toString();
                int amountMoneyCurrent;
                if(!s.isEmpty()){
                    amountMoneyCurrent = Integer.parseInt(s);
                }
                else{
                    amountMoneyCurrent = 0;
                }
                int newAmount;
                if(!((amountMoneyCurrent + m10k) > m500k)){
                    newAmount = amountMoneyCurrent + m10k;
                    edtAmount.setText(String.valueOf(newAmount));

                }
            }
        });

        imbMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edtAmount.getText().toString();
                int amountMoneyCurrent = 0;
                if(!s.equals("")){
                    try {
                        amountMoneyCurrent = Integer.parseInt(s);
                    }
                    catch (Exception e){
                        Log.d("Error", "Fail to parseInt amount money: " + e);
                    }
                }
                else{
                    amountMoneyCurrent = 0;
                }
                int newAmount;
                if((amountMoneyCurrent - m10k) >= 0){
                    newAmount = amountMoneyCurrent - m10k;
                    edtAmount.setText(String.valueOf(newAmount));
                }
            }
        });

        btn10k.setOnClickListener(clickForMoney);
        btn20k.setOnClickListener(clickForMoney);
        btn50k.setOnClickListener(clickForMoney);
        btn100k.setOnClickListener(clickForMoney);
        btn150k.setOnClickListener(clickForMoney);
        btn200k.setOnClickListener(clickForMoney);


        btnRequestTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtil.AMOUNT_SEND = edtAmount.getText().toString();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.lnFragmentRelace,new TopUpQRCodeFragment());
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

    }


    View.OnClickListener clickForMoney = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn10k:
                    edtAmount.setText(String.valueOf(m10k));
                    break;
                case R.id.btn20k:
                    edtAmount.setText(String.valueOf(m20k));

                    break;
                case R.id.btn50k:
                    edtAmount.setText(String.valueOf(m50k));

                    break;
                case R.id.btn100k:
                    edtAmount.setText(String.valueOf(m100k));

                    break;
                case R.id.btn150k:
                    edtAmount.setText(String.valueOf(m150k));

                    break;
                case R.id.btn200k:
                    edtAmount.setText(String.valueOf(m200k));
                    break;
            }


        }
    };


}