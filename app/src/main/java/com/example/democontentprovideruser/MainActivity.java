package com.example.democontentprovideruser;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnGetAllAuthor;
    GridView gvAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetAllAuthor = findViewById(R.id.button_GetAllAuthor);
        gvAuthor = findViewById(R.id.gridView_Author);
        btnGetAllAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> listString = new ArrayList<>();
                Uri uri = Uri.parse("content://com.example.democontentprovidersqlite.AuthorProvider");
                Cursor cursor = getContentResolver().query(uri,null,null,null,"id_author");
                if(cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {
                        listString.add(cursor.getInt(0)+"");
                        listString.add(cursor.getString(1));
                        listString.add(cursor.getString(2));
                        listString.add(cursor.getString(3));
                        cursor.moveToNext();
                    } while(!cursor.isAfterLast());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1,listString);

                    gvAuthor.setAdapter(adapter);
                }
                else {
                    Toast.makeText(MainActivity.this, "Dữ liệu rỗng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
