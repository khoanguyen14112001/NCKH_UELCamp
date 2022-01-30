package nguyenhoanganhkhoa.com.myapplication.home.transfer;

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
import android.widget.TextView;

import java.util.Objects;

import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.topup.TopUpAmountFragment;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransferMoneyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransferMoneyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransferMoneyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransferMoneyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransferMoneyFragment newInstance(String param1, String param2) {
        TransferMoneyFragment fragment = new TransferMoneyFragment();
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

    Button btnTransfer;
    TextView textTransfer;
    ReusedConstraint reusedConstraint = new ReusedConstraint(getContext());

    ImageButton imbPlus, imbMinus;
    EditText edtAmount;

    int m10k = 10000;
    int m500k = 500000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer_money, container, false);
        linkView(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        reusedConstraint.changeColor(textTransfer,10,16,R.color.primary_yellow, requireActivity());

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

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.lnFragmentReplaceTransfer,new TransferMoneyDetailFragment());
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }

    private void linkView(View view) {
        btnTransfer = view.findViewById(R.id.btnTransfer);
        textTransfer = view.findViewById(R.id.textTransfer);

        imbPlus = view.findViewById(R.id.imbPlus);
        imbMinus = view.findViewById(R.id.imbMinus);
        edtAmount= view.findViewById(R.id.edtAmount);
    }

    private void setStatusButton(boolean isEnable, int colorButton, int colorText) {
        btnTransfer.setEnabled(isEnable);
        btnTransfer.setBackground(getContext().getDrawable(colorButton));
        btnTransfer.setTextColor(getResources().getColor(colorText));
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

}