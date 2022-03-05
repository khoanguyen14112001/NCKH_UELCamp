package nguyenhoanganhkhoa.com.myapplication.home.homepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.MonthTransAdapter;
import nguyenhoanganhkhoa.com.adapter.TransAllAdapter;
import nguyenhoanganhkhoa.com.custom.bottomsheetdialog.CustomBottomSheetFilter;
import nguyenhoanganhkhoa.com.models.Month;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransactionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionFragment newInstance(String param1, String param2) {
        TransactionFragment fragment = new TransactionFragment();
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
    RecyclerView rcvDisplayTransaction;
    MonthTransAdapter monthTransAdapter;
    ImageView imvFilterTrans;
    RadioButton radTransAllAll, radTransAllWallet, radTransAllCanteen, radTransAllParking, radTransAlLSLSpace, radTransAllTransfer;

    CustomBottomSheetFilter bottomSheetDialog = null;
    ImageView imvClose;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        linkView(view);
        initAdapter();
        addEvents();


        return view;


    }

    private void linkView(View view) {

        radTransAllAll= view.findViewById(R.id.radTransAllAll);
        radTransAllWallet= view.findViewById(R.id.radTransAllWallet);
        radTransAllTransfer= view.findViewById(R.id.radTransAllTransfer);

        radTransAllCanteen= view.findViewById(R.id.radTransAllCanteen);
        radTransAllParking= view.findViewById(R.id.radTransAllParking);
        radTransAlLSLSpace= view.findViewById(R.id.radTransAlLSLSpace);

        imvClose= view.findViewById(R.id.imvClose);
        imvFilterTrans= view.findViewById(R.id.imvFilterTrans);

        rcvDisplayTransaction = view.findViewById(R.id.rcvDisplayTransaction);
    }


    private void createBottomSheetDialog() {
        if(bottomSheetDialog ==null){
            bottomSheetDialog = new CustomBottomSheetFilter(requireContext(),R.style.BottomSheetDialogTheme,R.layout.custom_bottomdialog_filter);
        }
        bottomSheetDialog.show();

    }

    private void addEvents() {
        imvFilterTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBottomSheetDialog();
            }
        });

        radTransAllTransfer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });

        radTransAllAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });

        radTransAllWallet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });

        radTransAllCanteen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });

        radTransAllParking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });


        radTransAlLSLSpace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                initAdapter();
            }
        });


    }

    private void initAdapter() {
        try {
            monthTransAdapter= new MonthTransAdapter(getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            rcvDisplayTransaction.setLayoutManager(linearLayoutManager);

            monthTransAdapter.setData(getListMonth());
            rcvDisplayTransaction.setAdapter(monthTransAdapter);
        }
        catch (Exception e)
        {
            Log.d("Error", "Fail to load adapter in ShowAllTransactionScreen: " + e);
        }

    }

    private List<Month> getListMonth() {
        List<Month> listMonth = new ArrayList<>();

        List<Transaction> listTrans1 = new ArrayList<>();
        List<Transaction> listTrans2 = new ArrayList<>();
        List<Transaction> listTrans3 = new ArrayList<>();
        List<Transaction> listTrans4 = new ArrayList<>();

        listTrans1.add(new Transaction(TransAllAdapter.TRANSACTION_TOPUP,"20 Oct, 10:07 ",true,50000));
        listTrans1.add(new Transaction(TransAllAdapter.TRANSACTION_PARKING,"20 10 Oct, 16:19  ",false,3000, false));
        listTrans1.add(new Transaction(TransAllAdapter.TRANSACTION_CANTEEN,"10 Oct, 16:19",false,3000));

        listTrans2.add(new Transaction(TransAllAdapter.TRANSACTION_QUANCAFE,"20 Sep, 10:07 ",true,50000));
        listTrans2.add(new Transaction(TransAllAdapter.TRANSACTION_QUANCAFE,"20 10 Sep, 16:19  ",false,3000, false));

        listTrans3.add(new Transaction(TransAllAdapter.TRANSACTION_CANTEEN,"10 Aug, 16:19",false,5000));
        listTrans3.add(new Transaction(TransAllAdapter.TRANSACTION_TOPUP,"10 Aug, 16:19",true,120000));
        listTrans3.add(new Transaction(TransAllAdapter.TRANSACTION_TOPUP,"10 Aug, 16:19",true,120000));
        listTrans3.add(new Transaction(TransAllAdapter.TRANSACTION_TOPUP,"10 Aug, 16:19",true,120000));
        listTrans3.add(new Transaction(TransAllAdapter.TRANSACTION_TRANSFER,"10 Aug, 16:19",true,30000));


        listTrans4.add(new Transaction(TransAllAdapter.TRANSACTION_TRANSFER,"10 July, 16:19",false,45000));
        listTrans4.add(new Transaction(TransAllAdapter.TRANSACTION_TRANSFER,"10 July, 16:19",false,20000));
        listTrans4.add(new Transaction(TransAllAdapter.TRANSACTION_CANTEEN,"10 July, 16:19",false,49000));

        listMonth.add(new Month("Oct 2021",addGetCategory(listTrans1), totalIncome(addGetCategory(listTrans1)), totalExpense(addGetCategory(listTrans1))));
        listMonth.add(new Month("Sep 2021",addGetCategory(listTrans2), totalIncome(addGetCategory(listTrans2)), totalExpense(addGetCategory(listTrans2))));
        listMonth.add(new Month("Aug 2021",addGetCategory(listTrans3), totalIncome(addGetCategory(listTrans3)), totalExpense(addGetCategory(listTrans3))));
        listMonth.add(new Month("July 2021",addGetCategory(listTrans4), totalIncome(addGetCategory(listTrans4)), totalExpense(addGetCategory(listTrans4))));


        // Nếu tháng nào không có dữ liệu thì xóa

        return listMonth;
    }



    private List<Transaction> addGetCategory(List<Transaction> list) {
        List<Transaction> listValue = new ArrayList<>();
        if(radTransAllAll.isChecked())
        {
            return list;
        }
        if(radTransAllWallet.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_TOPUP))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }
        if(radTransAllCanteen.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_CANTEEN))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }

        if(radTransAllParking.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_PARKING))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }

        if(radTransAlLSLSpace.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_QUANCAFE))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }




        if(radTransAllTransfer.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getTypeTrans().equals(TransAllAdapter.TRANSACTION_TRANSFER))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }



        return null;

    }
    private double totalIncome(List<Transaction> list){
        if(!list.isEmpty()){
            double totalMoney = 0;
            for (int i = 0; i<list.size();i++){
                if(list.get(i).isIncome()){
                    totalMoney += list.get(i).getAmountTrans();
                }
            }
            return totalMoney;
        }
        else{
            return 0;
        }
    }

    private double totalExpense(List<Transaction> list){
        if(!list.isEmpty()){
            double totalMoney = 0;
            for (int i = 0; i<list.size();i++){
                if(!list.get(i).isIncome()){
                    totalMoney += list.get(i).getAmountTrans();
                }
            }
            return totalMoney;
        }
        else{
            return 0;
        }
    }
}