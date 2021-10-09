package com.example.notepadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private File filePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE},
        PackageManager.PERMISSION_GRANTED);

        editTextInput = findViewById(R.id.editTextTextPersonName);

        filePath = new File(getExternalFilesDir(null), "Test.txt");
try {
    if (!filePath.exists()) {
        filePath.createNewFile();
    }
}catch (IOException e) {
                e.printStackTrace();
            }
        }



    public void buttonCreate(View view){
        try{
            XWPFDocument xwpfDocument = new XWPFDocument();
            XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
            XWPFRun xwpfRun = xwpfParagraph.createRun();

            xwpfRun.setText(editTextInput.getText().toString());
            xwpfRun.setFontSize(24);

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            xwpfDocument.write(fileOutputStream);

            if(fileOutputStream!=null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            xwpfDocument.close();



        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}