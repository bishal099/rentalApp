package com.example.individualproject2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Car> carList;
    private ArrayList<Car> filteredCarList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public Car getClickedCar(int position) {
        return filteredCarList.get(position);
    }

    public ProductAdapter(ArrayList<Car> carList, OnItemClickListener listener) {
        this.carList = carList;
        this.filteredCarList = new ArrayList<>(carList);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Car car = filteredCarList.get(position);
        holder.imageView.setImageResource(car.getImageResource());
        holder.textViewName.setText(car.getName());
        holder.textViewPrice.setText(String.valueOf(car.getPrice()));
    }

    @Override
    public int getItemCount() {
        return filteredCarList.size();
    }

    // Filter the list based on the search text
    public void filterList(ArrayList<Car> filteredList) {
        filteredCarList.clear();
        filteredCarList.addAll(filteredList);
        notifyDataSetChanged();
    }

    // Clear the search and show all cars
    public void clearSearch() {
        filteredCarList.clear();
        filteredCarList.addAll(carList);
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    // Getter for the filteredCarList
    public ArrayList<Car> getFilteredCarList() {
        return filteredCarList;
    }
}
