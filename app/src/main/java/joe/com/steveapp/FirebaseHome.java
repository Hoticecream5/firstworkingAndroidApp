package joe.com.steveapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import joe.com.steveapp.Model.Products;

public class FirebaseHome {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefProducts;
    private List<Products> myProducts = new ArrayList<>();

    public FirebaseHome(){
        mDatabase = FirebaseDatabase.getInstance();
        mRefProducts = mDatabase.getReference("Products");
    }
}
