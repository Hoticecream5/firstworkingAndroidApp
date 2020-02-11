package joe.com.steveapp;


import android.os.Bundle;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReceiverActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Customers> list;
    MyAdapter adapter;
   Customers customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        reference = FirebaseDatabase.getInstance().getReference().child("Customers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Customers>();
                Toast.makeText(ReceiverActivity.this,"Under construction",Toast.LENGTH_SHORT).show();

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                    customer = dataSnapshot1.getValue(Customers.class);
                    list.add(customer);
                }
                adapter = new MyAdapter(ReceiverActivity.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ReceiverActivity.this, "Opsss.... Something is wrong " + "\n" +
                        "Contact Admin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
