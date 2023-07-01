package com.example.mainactivity;

import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button zero,one,two,three,four,five,six,seven,eight,nine,add,sub,mult,div,mod,sqrt,AC,equal,point;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewAndListener();
    }

    //
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        super.onCreateOptionsMenu(menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if(id == R.id.setting_item){
//            Toast.makeText(this, "您刚刚点击了’设置‘按钮", Toast.LENGTH_LONG).show();
//        }
//        else if(id == R.id.quit_item){
//            Toast.makeText(this, "您刚刚点击了‘退出’按钮", Toast.LENGTH_LONG).show();
//            finish();
//        }
//        return true;
//
//    }
    void initViewAndListener() {
        zero = (Button) findViewById(R.id.Button_0);
        one = (Button) findViewById(R.id.Button_1);
        two = (Button) findViewById(R.id.Button_2);
        three = (Button) findViewById(R.id.Button_3);
        four = (Button) findViewById(R.id.Button_4);
        five = (Button) findViewById(R.id.Button_5);
        six = (Button) findViewById(R.id.Button_6);
        seven = (Button) findViewById(R.id.Button_7);
        eight = (Button) findViewById(R.id.Button_8);
        nine = (Button) findViewById(R.id.Button_9);
        add = (Button) findViewById(R.id.Button_add);
        sub = (Button) findViewById(R.id.Button_sub);
        mult = (Button) findViewById(R.id.Button_mult);
        div = (Button) findViewById(R.id.Button_div);
        mod = (Button) findViewById(R.id.Button_mod);
        sqrt = (Button) findViewById(R.id.Button_sqrt);
        AC = (Button) findViewById(R.id.Button_AC);
        equal = (Button) findViewById(R.id.Button_equal);
        point = (Button) findViewById(R.id.Button_point);
        textView = findViewById(R.id.Text_result);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mult.setOnClickListener(this);
        div.setOnClickListener(this);
        mod.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        AC.setOnClickListener(this);
        equal.setOnClickListener(this);
        point.setOnClickListener(this);


    }

    private String result_now;//记录当前结果文本
    private boolean flag = false;//控制文本框内容
    int now_num = 0;

    public void onClick(View view){
        String currentText = textView.getText().toString();

        int id = view.getId();
        if (id == R.id.Button_0) {
            if ((textView.getText().toString().equals("0")))
                return;
            isflag("0");
        } else if (id == R.id.Button_1) {
            isflag("1");
        } else if (id == R.id.Button_2) {
            isflag("2");
        } else if (id == R.id.Button_3) {
            isflag("3");
        } else if (id == R.id.Button_4) {
            isflag("4");
        } else if (id == R.id.Button_5) {
            isflag("5");
        } else if (id == R.id.Button_6) {
            isflag("6");
        } else if (id == R.id.Button_7) {
            isflag("7");
        } else if (id == R.id.Button_8) {
            isflag("8");
        } else if (id == R.id.Button_9) {
            isflag("9");
        } else if (id == R.id.Button_add) {//                textView.setText(textView.getText()+"+");
            if (TextUtils.isEmpty(textView.getText())) {
                Toast.makeText(textView.getContext(),"不允许以‘-’以外的符号作为开头",Toast.LENGTH_SHORT).show();
                return;
            }
            now_num++;
            String linshi = textView.getText().toString();
            char ch0 = linshi.charAt(linshi.length()-1);
            if (now_num > 1) {
                if(!isOperator(ch0)){
                    equal();
                    isflag("+");
                }
                else {
                    Toast.makeText(textView.getContext(),"重复输入了符号，请检查输入",Toast.LENGTH_SHORT).show();
                    now_num--;
                }
            }
            else {
                isflag("+");
            }

        } else if (id == R.id.Button_div) {//                textView.setText(textView.getText()+"/");
            if (TextUtils.isEmpty(textView.getText())) {
                Toast.makeText(textView.getContext(),"不允许以‘-’以外的符号作为开头",Toast.LENGTH_SHORT).show();
                return;
            }
            now_num++;
            String linshi = textView.getText().toString();
            char ch0 = linshi.charAt(linshi.length()-1);
            if (now_num > 1) {
                if(!isOperator(ch0)){
                    equal();
                    isflag("/");
                }
                else {
                    Toast.makeText(textView.getContext(),"重复输入了符号，请检查输入",Toast.LENGTH_SHORT).show();
                    now_num--;
                }
            }
            else {
                isflag("/");
            }

        } else if (id == R.id.Button_point) {//                textView.setText(textView.getText()+".");
            if (TextUtils.isEmpty(textView.getText())) {
                Toast.makeText(textView.getContext(),"无法以‘.’作为运算式开头",Toast.LENGTH_SHORT).show();
                return;
            }
            String linshi = textView.getText().toString();
            char ch0 = linshi.charAt(linshi.length()-1);
            if(isOperator(ch0)){
                Toast.makeText(textView.getContext(),"符号错误输入，请检查输入",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!(linshi.contains("+") || (linshi.contains("-") && linshi.indexOf('-')!= 0) || linshi.contains("X") || linshi.contains("/") || linshi.contains("%"))){
                if(linshi.contains(".")){
                    Toast.makeText(textView.getContext(),"同一数字只能存在一个小数点",Toast.LENGTH_SHORT).show();
                }
                else{
                    isflag(".");
                }
            }
            else{
                if(linshi.indexOf(".") == linshi.lastIndexOf(".")){
                    isflag(".");
                }
                else{
                    Toast.makeText(textView.getContext(),"同一数字只能存在一个小数点",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
/*
*
*
*
*
*
* */


        } else if (id == R.id.Button_sub) {//                textView.setText(textView.getText()+"-");
            if (TextUtils.isEmpty(textView.getText())) {
                isflag("-");
                return;
            }
            now_num++;
            String linshi = textView.getText().toString();
            char ch0 = linshi.charAt(linshi.length()-1);
            if (now_num > 1) {
                if(!isOperator(ch0)){
                    equal();
                    isflag("-");
                }
                else {
                    Toast.makeText(textView.getContext(),"重复输入了符号，请检查输入",Toast.LENGTH_SHORT).show();
                    now_num--;
                }
            }
            else {
                isflag("-");
            }

        } else if (id == R.id.Button_mod) {
            if (TextUtils.isEmpty(textView.getText())) {
                Toast.makeText(textView.getContext(),"不允许以‘-’以外的符号作为开头",Toast.LENGTH_SHORT).show();
                return;
            }
            now_num++;
            String linshi = textView.getText().toString();
            char ch0 = linshi.charAt(linshi.length()-1);
            if (now_num > 1) {
                if(!isOperator(ch0)){
                    equal();
                    isflag("%");
                }
                else {
                    Toast.makeText(textView.getContext(),"重复输入了符号，请检查输入",Toast.LENGTH_SHORT).show();
                    now_num--;
                }
            }
            else {
                isflag("%");
            }

        } else if (id == R.id.Button_sqrt) {
            if (TextUtils.isEmpty(textView.getText())) {
                Toast.makeText(textView.getContext(),"当前无计算数据",Toast.LENGTH_SHORT).show();
                return;
            }
            String linshi = textView.getText().toString();
            //判断当前数字是否可以被开平方
            if(isCanSqrt(linshi)){
                double num1 = Double.parseDouble(linshi.substring(0,linshi.length()));
                double result = sqrt(num1);
                textView.setText(result + "");
                flag = false;
            }

            
        } else if (id == R.id.Button_mult) {//                textView.setText(textView.getText()+"*");
            if (TextUtils.isEmpty(textView.getText())) {
                Toast.makeText(textView.getContext(),"不允许以‘-’以外的符号作为开头",Toast.LENGTH_SHORT).show();
                return;
            }
            now_num++;
            String linshi = textView.getText().toString();
            char ch0 = linshi.charAt(linshi.length()-1);
            if (now_num > 1) {
                if(!isOperator(ch0)){
                    equal();
                    isflag("X");
                }
                else {
                    Toast.makeText(textView.getContext(),"重复输入了符号，请检查输入",Toast.LENGTH_SHORT).show();
                    now_num--;
                }
            }
            else {
                isflag("X");
            }

        } else if (id == R.id.Button_AC) {
            textView.setText("0");
            flag = false;
            now_num = 0;
        } else if (id == R.id.Button_equal) {
            if (TextUtils.isEmpty(textView.getText())) {
                return;
            }
            String linshi = textView.getText().toString();
            char ch0 = linshi.charAt(linshi.length()-1);
            if(isNum(linshi)){
                flag = false;
                now_num = 0;
                return;
            }
            if(!isOperator(ch0)){
                equal();
                flag = false;
                now_num = 0;
            }
            else {
                Toast.makeText(textView.getContext(),"运算式错误，请检查当前输入是否可以输出结果",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNum(String str) {
        if(str.contains("+") || str.contains("X")|| str.contains("/")|| str.contains("%")){
            return false;
        }
        else if(str.contains("-")){
            if(str.lastIndexOf("-") != 0){
                return false;
            }
            else{
                return true;
            }

        }
        return true;
    }

    private boolean isOperator(char ch0) {
        if(ch0 == '+' || ch0 == '-' || ch0 == 'X' || ch0 == '/' || ch0 == '%' || ch0 == '.'){
            return true;
        }
        return false;
    }

    private boolean isCanSqrt(String str){
        char ch0 = str.charAt(str.length()-1);
        if(str.indexOf(0) == '-'){
            Toast.makeText(textView.getContext(),"无法进行开平方运算，请检查输入",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(str.contains("+") || str.contains("-") || str.contains("X")|| str.contains("/")|| str.contains("%")){
            Toast.makeText(textView.getContext(),"无法进行开平方运算，请检查输入",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void equal(){
        textView.setText(operation(textView.getText().toString()));
    }

    private String operation(String string){
        int index;
        double num_1,num_2;
        if (string.contains("+")){
            index=string.indexOf("+");
            num_1= Double.parseDouble(string.substring(0,index));
            num_2= Double.parseDouble(string.substring(index+1,string.length()));
            return num_1+num_2+"";
        }
        if (string.contains("-")){
            index=string.indexOf("-");
            if(index==0){
                String string_2=string.substring(1,string.length());
                int index_2=string_2.indexOf("-");
                num_1= Double.parseDouble(string_2.substring(0,index_2));
                num_2= Double.parseDouble(string_2.substring(index_2+1,string_2.length()));
                return (-(num_1+num_2))+"";
            }else{
                num_1= Double.parseDouble(string.substring(0,index));
                num_2= Double.parseDouble(string.substring(index+1,string.length()));
                return num_1-num_2+"";
            }

        }
        if (string.contains("X")){
            index=string.indexOf("X");
            num_1= Double.parseDouble(string.substring(0,index));
            num_2= Double.parseDouble(string.substring(index+1,string.length()));
            return num_1*num_2+"";
        }

        if (string.contains("%")){
            index=string.indexOf("%");
            num_1= Double.parseDouble(string.substring(0,index));
            num_2= Double.parseDouble(string.substring(index+1,string.length()));
            return num_1%num_2+"";
        }

        if (string.contains("/")){
            index=string.indexOf("/");
            num_1= Double.parseDouble(string.substring(0,index));
            num_2= Double.parseDouble(string.substring(index+1,string.length()));
            if(num_2 == 0){
                return "除数不能为0";
            }
            return num_1/num_2+"";
        }
        return "运算出现未知错误!!!";
    }


    private void isflag(String info){
        if (flag){
                textView.setText(textView.getText()+info);

        }
        else{
            if(info.equals("%") || info.equals(".")||info.equals("+")||info.equals("-")||info.equals("X")||info.equals("/")){
                textView.setText(textView.getText()+info);
            }
            else {
                textView.setText(info);
            }
            flag=true;
        }
    }

}