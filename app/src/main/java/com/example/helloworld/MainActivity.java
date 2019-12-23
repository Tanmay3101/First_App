package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public void addInventory(View view) {
        String[][] s = MainActivity.showInventory();
    }

    public class Granite {

        private String name;

        private int quantity;

        public Granite(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }
        public int getQuantity() {
            return quantity;
        }
        public void addQuantity(int quantityAdded) {
            quantity += quantityAdded;
        }

        public void quantitySold(int quantitySold) {
            if(quantitySold > quantity) {
                throw new IllegalArgumentException("Quantity sold cannot be more than the stock of the item");
            }
            quantity -= quantitySold;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final ArrayList<Granite> inventoryList = new ArrayList<Granite>();

    public static String[][] showInventory() {
        String[][] s = null;
        for(int i = 0; i < inventoryList.size(); i++) {
            s[i][0] = inventoryList.get(i).getName();
            s[i][1] = inventoryList.get(i).getQuantity() + "";
        }
        return s;
    }

    public void addInventory(String name, int quantity) {
        Granite g = new Granite(name, quantity);
        int indexInList = 0;
        if(inventoryList.contains(g)) {
           indexInList = inventoryList.indexOf(g);
           inventoryList.get(indexInList).addQuantity(quantity);
        }
        else {
            inventoryList.add(g);
        }
    }

    public void sellInventory(String name, int quantity) {
        Granite g = new Granite(name, quantity);
        int indexInList = 0;
        if(inventoryList.contains(g)) {
            indexInList = inventoryList.indexOf(g);
            inventoryList.get(indexInList).quantitySold(quantity);
        }
        else {
            throw new IllegalArgumentException("There is no Granite with the given name.");
        }

    }

}
