package application.scrutinizer.bowler.bowlerscrutinizer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllResultsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;



    //for retrival of theta

    ListView list;
    private ArrayList<String> theta = new ArrayList<>();

    //to get the key of theta
    final ArrayList<String> keyList = new ArrayList<>();

    //For bar chart
//    BarChart barChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_results);

        init();
        getResults();

//        getGraph();
    }

    private void init() {

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        list = (ListView)findViewById(R.id.List_Theta);

//        barChart = (BarChart) findViewById(R.id.BarChart1);

    }

    //Results shown
    public void getResults() {

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1,theta);
        list.setAdapter(arrayAdapter);

        mDatabase.child(mAuth.getUid()).child("Theta").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                String result = dataSnapshot.getValue(String.class);
                theta.add(result);
                arrayAdapter.notifyDataSetChanged();

                keyList.add(dataSnapshot.getKey());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                theta.remove(position);
                arrayAdapter.notifyDataSetChanged();

                //new code below
                mDatabase.child(mAuth.getUid()).child("Theta").child(keyList.get(position)).removeValue();

                keyList.remove(position);
            }
        });







    }




}
