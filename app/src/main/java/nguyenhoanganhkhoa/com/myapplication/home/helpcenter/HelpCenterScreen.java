package nguyenhoanganhkhoa.com.myapplication.home.helpcenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import nguyenhoanganhkhoa.com.adapter.QuestionsAdapter;
import nguyenhoanganhkhoa.com.models.QuestionsCategories;
import nguyenhoanganhkhoa.com.myapplication.R;
import nguyenhoanganhkhoa.com.thirdlink.ReusedConstraint;

public class HelpCenterScreen extends AppCompatActivity {
    QuestionsAdapter questionsAdapter;
    RecyclerView rcvQuestions, rcvProblemCate;
    ImageView imvComebackHelpCenter;
    androidx.appcompat.widget.SearchView searchView;
    ReusedConstraint reusedConstraint = new ReusedConstraint(this);

    private void linkView() {
        rcvQuestions = findViewById(R.id.rcvQuestions);
        rcvProblemCate = findViewById(R.id.rcvProblemCate);
        imvComebackHelpCenter = findViewById(R.id.imvComebackHelpCenter);
        searchView = findViewById(R.id.svProblem);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center_screen);

        linkView();
        initAdapter(R.layout.item_problem_categories);
        initAdapter(R.layout.item_question);
        addSearchView();
        addEvents();
        reusedConstraint.openNav(this);
    }

    private void addSearchView() {
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    questionsAdapter.getFilter().filter(query);
                }
                catch (Exception e){
                    Log.d("Error", "Fail to search data in HelpCenterScreen: " + e);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    questionsAdapter.getFilter().filter(newText);
                }
                catch (Exception e){
                    Log.d("Error", "Fail to search data in HelpCenterScreen: " + e);
                }
                return false;
            }
        });
    }

    private void addEvents() {
        imvComebackHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        reusedConstraint.checkNavStatusComeBack(this);
    }

    private void initAdapter(int layout) {
        if(layout == R.layout.item_question)
        {
            try{
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
                rcvQuestions.setLayoutManager(linearLayoutManager);
                questionsAdapter = new QuestionsAdapter(getQuestionsList(),layout,this);
                rcvQuestions.setAdapter(questionsAdapter);
            }
            catch (Exception e){
                Log.d("Error", "Fail to load question adapter in HelpCenterScreen: " + e);
            }

        }
        if(layout == R.layout.item_problem_categories){
            try {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
                rcvProblemCate.setLayoutManager(linearLayoutManager);
                questionsAdapter = new QuestionsAdapter(getProblemsCateList(),layout,this);
                rcvProblemCate.setAdapter(questionsAdapter);
            }
            catch (Exception e){
                Log.d("Error", "Fail to load problem category adapter in HelpCenterScreen: " + e);

            }
        }
    }

    private List<QuestionsCategories> getProblemsCateList() {
        List<QuestionsCategories> list = new ArrayList<>();

        list.add(new QuestionsCategories("E-Wallet",
                R.drawable.ic_topup,12));
        list.add(new QuestionsCategories("Parking lot",
                R.drawable.ic_bike,8));
        list.add(new QuestionsCategories("Safety and Security",
                R.drawable.ic_safety,4));
        list.add(new QuestionsCategories("For beginners",
                R.drawable.ic_aboutus,12));
        list.add(new QuestionsCategories("User manual",
                R.drawable.ic_news,8));
        list.add(new QuestionsCategories("Canteen",
                R.drawable.ic_canteen,11));
        list.add(new QuestionsCategories("SLSPace",
                R.drawable.ic_quancafe,9));


        return list;
    }

    private List<QuestionsCategories> getQuestionsList() {
        List<QuestionsCategories> list = new ArrayList<>();


        list.add(new QuestionsCategories("How to add product into cart in Canteen?",
                R.drawable.ic_canteen));
        list.add(new QuestionsCategories("I did not see my latest history",
                R.drawable.ic_bike));
        list.add(new QuestionsCategories("I have not received money from Momo wallet",
                R.drawable.ic_topup));
        list.add(new QuestionsCategories("I did not see where I can order in SLSpace?",
                R.drawable.ic_quancafe));
        list.add(new QuestionsCategories("How to top up my wallet?",
                R.drawable.ic_topup));
        list.add(new QuestionsCategories("How to add friend into list friends",
                R.drawable.ic_topup));
        list.add(new QuestionsCategories("How to transfer money to friend",
                R.drawable.ic_topup));



        return list;

    }




}