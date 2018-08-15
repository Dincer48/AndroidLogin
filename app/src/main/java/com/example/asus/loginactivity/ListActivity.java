package com.example.asus.loginactivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class ListActivity extends AppCompatActivity implements AdapterListener {

    DatabaseHelper mDatabaseHelper;
    List<TextModel> myDataset;

    Button button;
    EditText editText;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mDatabaseHelper=new DatabaseHelper(this);
        myDataset=TextModel.cursorToArray(mDatabaseHelper.getData());


        button=findViewById(R.id.nameInputButtonId);
        editText=findViewById(R.id.nameInputId);

        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.US);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String onProcess=editText.getText().toString();
                if(!onProcess.equals("")){
                    mDatabaseHelper.addData(onProcess);
                    myDataset.add(new TextModel(mDatabaseHelper.getLastId(),onProcess));
                    editText.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView=findViewById(R.id.recycler_view);
        adapter=new RecyclerViewAdapter(this,myDataset,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(View view, int position) {
        if(view.getId()==R.id.delete_button){
            mDatabaseHelper.deleteData(myDataset.get(position).getId());
            myDataset.remove(position);

        }else if(view.getId()==R.id.parent_layout){
            t1.speak(myDataset.get(position).getText(),TextToSpeech.QUEUE_FLUSH,null);
        }

    }
}
