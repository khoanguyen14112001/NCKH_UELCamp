package nguyenhoanganhkhoa.com.fragments;

import android.content.Intent;
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
import java.util.Objects;

import nguyenhoanganhkhoa.com.adapter.DateAdapter;
import nguyenhoanganhkhoa.com.adapter.MonthTransAdapter;
import nguyenhoanganhkhoa.com.customdialog.CustomBottomSheetFilter;
import nguyenhoanganhkhoa.com.customdialog.CustomBottomSheetFilterHistory;
import nguyenhoanganhkhoa.com.models.Date;
import nguyenhoanganhkhoa.com.models.History;
import nguyenhoanganhkhoa.com.models.Month;
import nguyenhoanganhkhoa.com.models.Transaction;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.myapplication.home.HomePageScreen;
import nguyenhoanganhkhoa.com.myapplication.home.transaction.ShowAllTransactionScreen;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
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
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
    RadioButton radTransAllAll, radTransAllWallet, radTransAllCanteen, radTransAllParking, radTransAlLSLSpace, radTransAllThuQuan;

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
        radTransAllCanteen= view.findViewById(R.id.radTransAllCanteen);
        radTransAllParking= view.findViewById(R.id.radTransAllParking);
        radTransAlLSLSpace= view.findViewById(R.id.radTransAlLSLSpace);
        radTransAllThuQuan= view.findViewById(R.id.radTransAllThuQuan);

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

        radTransAllThuQuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        List<Transaction> listTrans5 = new ArrayList<>();

        listTrans1.add(new Transaction("Top up","20 Oct, 10:07 ","+50.000",R.drawable.ic_topup,R.drawable.ic_tickbutton));
        listTrans1.add(new Transaction("Parking payment","10 Oct, 16:19 ","-3.000",R.drawable.ic_bike,R.drawable.ic_warning_red));
        listTrans1.add(new Transaction("Order in Canteen","09 Oct, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans1.add(new Transaction("Order in SLSpace","09 Oct, 16:19 ","-26.000",R.drawable.ic_quancafe,R.drawable.ic_tickbutton));

        listTrans2.add(new Transaction("Payment at Stationery","20 Sep, 10:07 ","-30.000",R.drawable.ic_thuquan,R.drawable.ic_tickbutton));
        listTrans2.add(new Transaction("Transfer money","10 Sep, 16:19 ","-50.000",R.drawable.ic_transfer,R.drawable.ic_tickbutton));

        listTrans3.add(new Transaction("Payment at Stationery","20 Aug, 10:07 ","-30.000",R.drawable.ic_thuquan,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Transfer money","10 Aug, 16:19 ","-50.000",R.drawable.ic_transfer,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Order in Canteen","09 Aug, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Order in Canteen","09 Aug, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Order in Canteen","09 Aug, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Top up","20 Aug, 10:07 ","+50.000",R.drawable.ic_topup,R.drawable.ic_tickbutton));
        listTrans3.add(new Transaction("Order in SLSpace","09 Aug, 16:19 ","-26.000",R.drawable.ic_quancafe,R.drawable.ic_tickbutton));


        listTrans4.add(new Transaction("Payment at Stationery","20 July, 10:07 ","-30.000",R.drawable.ic_thuquan,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Transfer money","10 July, 16:19 ","-50.000",R.drawable.ic_transfer,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Order in Canteen","09 July, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Order in Canteen","09 July, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Payment at Stationery","20 July, 10:07 ","-30.000",R.drawable.ic_thuquan,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Transfer money","10 July, 16:19 ","-50.000",R.drawable.ic_transfer,R.drawable.ic_tickbutton));
        listTrans4.add(new Transaction("Top up","20 July, 10:07 ","+50.000",R.drawable.ic_topup,R.drawable.ic_tickbutton));

        listTrans5.add(new Transaction("Order in Canteen","09 Jun, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans5.add(new Transaction("Order in Canteen","09 Jun, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans5.add(new Transaction("Payment at Stationery","20 Jun, 10:07 ","-30.000",R.drawable.ic_thuquan,R.drawable.ic_tickbutton));
        listTrans5.add(new Transaction("Transfer money","10 Jun, 16:19 ","-50.000",R.drawable.ic_transfer,R.drawable.ic_tickbutton));
        listTrans5.add(new Transaction("Order in Canteen","09 Jun, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));
        listTrans5.add(new Transaction("Order in Canteen","09 Jun, 16:19 ","-43.000",R.drawable.ic_canteen,R.drawable.ic_tickbutton));


        listMonth.add(new Month("Oct 2021",addGetCategory(listTrans1)));
        listMonth.add(new Month("Sep 2021",addGetCategory(listTrans2)));
        listMonth.add(new Month("Aug 2021",addGetCategory(listTrans3)));
        listMonth.add(new Month("July 2021",addGetCategory(listTrans4)));
        listMonth.add(new Month("Jun 2021",addGetCategory(listTrans5)));

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
                if(list.get(i).getStatusTrans().equals("Top up")||list.get(i).getStatusTrans().equals("Transfer money"))
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
                if(list.get(i).getStatusTrans().equals("Order in Canteen"))
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
                if(list.get(i).getStatusTrans().equals("Parking payment"))
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
                if(list.get(i).getStatusTrans().equals("Order in SLSpace"))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }


        if(radTransAllThuQuan.isChecked())
        {
            for (int i = 0;i<list.size();i++)
            {
                if(list.get(i).getStatusTrans().equals("Payment at Stationery"))
                {
                    listValue.add(list.get(i));
                }
            }
            return listValue;
        }


        return null;

    }
}