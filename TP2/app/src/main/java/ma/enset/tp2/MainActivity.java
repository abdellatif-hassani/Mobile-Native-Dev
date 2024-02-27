package ma.enset.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Name input
        EditText nameInput = findViewById(R.id.nameInput);
        //Email input
        EditText emailInput = findViewById(R.id.emailInput);
        //The button to add the items to the list
        Button addButton = findViewById(R.id.addButton);
        //The list of elements
        ListView listView = findViewById(R.id.listView);
        //Creating an array list which will store the data (names and passwords) to be displayed by the listView
        List<String> data = new ArrayList<>();
        //Creating an adapter to link the data and the list of data
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        //Setting a hundler of the addButton
        addButton.setOnClickListener(v -> {
            String name=nameInput.getText().toString();
            String email=emailInput.getText().toString();
            data.add("Name : "+name+"\nEmail : "+email);
            adapter.notifyDataSetChanged();
            nameInput.getText().clear();
            emailInput.getText().clear();
        });

    }
}