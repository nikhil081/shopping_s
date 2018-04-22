package com.example.nikma.shopping_s;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText n,d,u;
    Button button;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n = findViewById(R.id.na);
        d= findViewById(R.id.des);
        u = findViewById(R.id.we);
        button = findViewById(R.id.btn);
        databaseReference = FirebaseDatabase.getInstance().getReference("food");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addproducts();

            }
        });
    }

    private void addproducts() {
        String name = n.getText().toString().trim();
        String desc = d.getText().toString().trim();
        String image = u.getText().toString().trim();
        String id = databaseReference.push().getKey();
        food f = new food(name,desc,image);
        databaseReference.child(id).setValue(f);

    }
}
