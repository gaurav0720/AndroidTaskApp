package com.example.fetch_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.application.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PerformingTask extends AppCompatActivity {

    private static final String URL_DATA = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    ArrayList<ListItems> listItems = new ArrayList<>();
    RecyclerView allListItems;
    TaskAdapter adapter;
    TextView showDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performing_task);

        allListItems = findViewById(R.id.jsonData);
        allListItems.setLayoutManager(new LinearLayoutManager(this));

        showDesc = findViewById(R.id.task_desc);

        getJSONData();

    }

    private void getJSONData() {
        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setMessage("Loading Data");
        progressBar.show();
        RequestQueue queue = Volley.newRequestQueue(PerformingTask.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL_DATA,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.dismiss();
                Toast.makeText(PerformingTask.this, "Data imported successfully", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObj = response.getJSONObject(i);
                        Integer id = responseObj.getInt("id");
                        Integer listId = responseObj.getInt("listId");
                        String name = responseObj.getString("name");
                        listItems.add(new ListItems(id, listId, name));
                        buildRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PerformingTask.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    @SuppressLint("SetTextI18n")
    private void buildRecyclerView() {

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String taskNumber = bundle.getString("Task Number");

        assert taskNumber != null;
        switch (taskNumber) {
            case "Task One":
                setTitle("Welcome to Task One");
                showDesc.setText(R.string.task_one_text);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Collections.sort(listItems,
                            Comparator.comparing(ListItems::getListId));
                }
                adapter = new TaskAdapter(listItems);
                break;
            case "Task Two":
                setTitle("Welcome to Task Two");
                showDesc.setText(R.string.task_two_text);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Collections.sort(listItems,
                            Comparator.comparing(ListItems::getListId).thenComparing(ListItems::getName));
                }
                adapter = new TaskAdapter(listItems);
                break;
            case "Task Three":
                setTitle("Welcome to Task Three");
                showDesc.setText(R.string.task_three_text);
                ArrayList<ListItems> filterItems = new ArrayList<>();
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    filterItems = (ArrayList<ListItems>) listItems.stream()
                            .filter(ListItems -> ListItems.getName().equals("") || ListItems.getName().equals("null"))
                            .collect(Collectors.toList());
                }
                adapter = new TaskAdapter(filterItems);
                break;
            default:
                setTitle("Performing Task");
                showDesc.setText("Default Task");
                adapter = new TaskAdapter(listItems);
                break;
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        allListItems.setHasFixedSize(true);
        allListItems.setLayoutManager(manager);
        allListItems.setAdapter(adapter);
    }
}
