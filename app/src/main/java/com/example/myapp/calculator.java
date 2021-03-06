    package com.example.myapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.itis.libs.parserng.android.expressParser.MathExpression;

public class calculator extends AppCompatActivity {
    private EditText console,header;
    private TextView txtview;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        String firstName;
        Intent qt=getIntent();
        firstName=qt.getStringExtra("key1");

        console=findViewById(R.id.input);
        txtview = (TextView)findViewById(R.id.invisibletxtview);
        console.setShowSoftInputOnFocus(false);

        console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.console).equals(console.getText().toString())){
                           console.setText("");
                }
            }
        });
        console.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtview.setText(s.toString());
                float size=txtview.getTextSize();
                size=size/3;
                console.setTextSize(size);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
   }

   private void updateText(String strToAdd){
        String oldText=console.getText().toString();
        int cursorPos=console.getSelectionStart();
        String leftStr=oldText.substring(0,cursorPos);
        String rightStr=oldText.substring(cursorPos);
        if(getString(R.string.console).equals(console.getText().toString())){
            console.setText(strToAdd);
            console.setSelection(cursorPos + 1);
        }else {
            console.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            console.setSelection(cursorPos + 1);
        }
   }

    public void zeroBtn(View view){
        updateText("0");
    }
    public void oneBtn(View view){
        updateText("1");
    }
    public void equalBtn(View view){
        int cursorPos=console.getSelectionStart();
        String userExp=console.getText().toString();
//        userExp=userExp.replaceAll("X","*");
//
//        Expression exp=new Expression(userExp);
//
//        String ans=String.valueOf(exp.calculate());
//
//        console.setText(ans);
//        console.setSelection(ans.length());

        MathExpression expr = new MathExpression(userExp);
        console.setText(expr.solve());
        console.setSelection(cursorPos+1);
    }
    public void sixBtn(View view){
        updateText("6");
    }
    public void sevenBtn(View view){
        updateText("7");
    }

    public void eightBtn(View view){
        updateText("8");
    }
    public void nineBtn(View view){
        updateText("9");
    }
    public void threeBtn(View view){
        updateText("3");
    }
    public void twoBtn(View view){
        updateText("2");
    }
    public void multiplyBtn(View view){
        updateText("*");
    }
    public void BackspaceBtn(View view){
        int cursorPos=console.getSelectionStart();
        int textLen=console.getText().length();

        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection=(SpannableStringBuilder) console.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            console.setSelection(cursorPos-1);
        }
    }
    public void fourBtn(View view){
        updateText("4");
    }
    public void fiveBtn(View view){
        updateText("5");
    }
    public void clearBtn(View view){
        console.setText("");
    }
    public void addBtn(View view){
        updateText("+");
    }
    public void subtractBtn(View view){
        updateText("-");
    }
    public void divideBtn(View view){
        updateText("/");
    }
    public void pointBtn(View view){
        updateText(".");
    }
    public void exponentBtn(View view){
        updateText("^");
    }
   public void paranthesisBtn(View view){
        int cursorPos=console.getSelectionStart();
        int openPara=0,closedPara=0;
        int textLen=console.getText().length();

        for(int i=0;i<cursorPos;i++){
            if (console.getText().toString().substring(i,i+1).equals("(")){
                openPara+=1;
            }
            if (console.getText().toString().substring(i,i+1).equals(")")){
                closedPara+=1;
            }
        }
        if(openPara == closedPara || console.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        }
       else if(closedPara < openPara && ! console.getText().toString().substring(textLen-1,textLen).equals("(")){
           updateText(")");
       }
       console.setSelection(cursorPos+1);
   }
   public void plusMinusBtn(View view){
       updateText("-");
   }




    
}