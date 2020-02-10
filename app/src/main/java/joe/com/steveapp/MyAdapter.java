package joe.com.steveapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Customers> customer;

    public MyAdapter(Context c , ArrayList<Customers> p)
    {
        context = c;
        customer = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.name.setText(customer.get(position).getName());
        holder.phone_Num.setText(customer.get(position).getPhone_Number());
        holder.prod_Name.setText(customer.get(position).getProduct_Name());
        holder.customer_Res_Name.setText(customer.get(position).getResidence_Name());

        holder.restaurant_Address.setText(customer.get(position).getRestaurant_Address());
        holder.restaurant_Name.setText(customer.get(position).getRestaurant_Name());
        holder.room_Number.setText(customer.get(position).getRoom_Number());
        holder.block_Number.setText(customer.get(position).getBlock_Numner());

        holder.product_Price.setText(customer.get(position).getProduct_Price());
        holder.order_Number.setText(customer.get(position).getOrder_Number());
        holder.address.setText(customer.get(position).getAddress());
        holder.house_Number.setText(customer.get(position).getHouse_Number());
        holder.counter.setText(customer.get(position).getNumber_Of_Rides());
        holder.customer_Address.setText(customer.get(position).getUser_Address());

        if(customer.get(position).getPermission()) {
            holder.checkBtn.setVisibility(View.VISIBLE);
            holder.onClick(position);
        }
    }

    @Override
    public int getItemCount() {
        return customer.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,phone_Num,prod_Name,customer_Res_Name,message,
        restaurant_Address,restaurant_Name,room_Number,
        block_Number,product_Price,order_Number,address,house_Number,
        counter,customer_Address;
        Button checkBtn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.customerName);
            phone_Num =  itemView.findViewById(R.id.customer_Phone_Number);
            prod_Name =  itemView.findViewById(R.id.customerProdName);
            customer_Res_Name =  itemView.findViewById(R.id.customerResidenceName);
            restaurant_Address =  itemView.findViewById(R.id.customerRestAddrs);
            restaurant_Name =  itemView.findViewById(R.id.customerRestName);
            room_Number =  itemView.findViewById(R.id.customerRoomNum);
            block_Number =  itemView.findViewById(R.id.customerBlckNum);
            product_Price =  itemView.findViewById(R.id.customerProdPrice);
            order_Number =  itemView.findViewById(R.id.customerOrderNum);
            address =  itemView.findViewById(R.id.customerAddrs);
            house_Number =  itemView.findViewById(R.id.customerHseNum);
            counter =  itemView.findViewById(R.id.customerCount);
            customer_Address =  itemView.findViewById(R.id.customerUserAddres);
            checkBtn =  itemView.findViewById(R.id.customerCheckDetails);
        }
        public void onClick(final int position)
        {
            checkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
