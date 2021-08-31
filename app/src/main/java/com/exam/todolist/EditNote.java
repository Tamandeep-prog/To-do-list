package com.exam.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {

    EditText edtTitle, edtDescription;
    Button btnCancel, btnSave;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent intent = getIntent();

        edtTitle=findViewById(R.id.edt_edit_title);
        edtDescription= findViewById(R.id.edt_edit_descrition);
        btnCancel= findViewById(R.id.btncancel);
        btnSave=findViewById(R.id.btnsave);
        linearLayout=findViewById(R.id.btn_holder);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                onBackPressed();

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note nte= new note(edtTitle.getText().toString(),edtDescription.getText().toString());
                nte.setId(intent.getIntExtra("id", 1));
                if(new NoteHandler(EditNote.this).update(nte))
                {
                    Toast.makeText(EditNote.this, "Note updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(EditNote.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();

            }
        });

        edtTitle.setText(intent.getStringExtra("title"));
        edtDescription.setText(intent.getStringExtra("description"));

    }

    @Override
    public void onBackPressed() {
        btnCancel.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(linearLayout);
        super.onBackPressed();
    }
}