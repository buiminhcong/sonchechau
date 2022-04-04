package com.example.buicong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private Button btnAdd, btnUpdate;
    private CheckBox c1,c2,c3;
    private EditText edtName, edtNGaySinh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<CauThu> cauThuList = new ArrayList<>();
        cauThuList.add(new CauThu(R.drawable.men, "Cong Phuong","26/09/2000", "tien dao"));
        cauThuList.add(new CauThu(R.drawable.men, "Minh Cong","26/09/2000", "tien ve"));

        initView();

        //dialog
        edtNGaySinh.setOnClickListener(this);
        edtNGaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtNGaySinh.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },y,m,d);
                dialog.show();
            }
        });


        CauThuAdapter adapter = new CauThuAdapter(cauThuList, this);
        recyclerView.setAdapter(adapter);

        //add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "";
                String ten = edtName.getText().toString();
                String ngaySinh = edtNGaySinh.getText().toString();

                if(c1.isChecked() == true){
                    s += "tien dao";
                    s+=",";
                }
                if(c2.isChecked() == true){
                    s += "tien ve";
                    s+=",";
                }
                if(c3.isChecked() == true){
                    s += "hau ve";
                    s+=",";
                }

                if(!ten.trim().equals("") && s != "" ){


                    System.out.println(s);
                    String str[] = s.split(",");

                    for(int i=0; i<str.length; i++){
                        System.out.println(str[i]);
                        if(str[i].trim().equals("tien dao") ){
                            c1.setChecked(true);
                        }
                        if(str[i].trim().equals("tien ve")  )
                            c2.setChecked(true);

                        if(str[i].trim().equals("hau ve")  )
                            c3.setChecked(true);
                    }


                    CauThu c = new CauThu(R.drawable.men, ten,ngaySinh, s);
                    adapter.addCT(c);
                    Toast.makeText(MainActivity.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Nhap thieu", Toast.LENGTH_SHORT).show();
                }



            }
        });

        // update
        adapter.setOnMyItemClickListener(new CauThuAdapter.OnMyItemClickListener() {
            @Override
            public void doSomething(int position) {

                btnUpdate.setEnabled(true);
                btnAdd.setEnabled(false);
                CauThu c = cauThuList.get(position);
                edtName.setText(c.getName());
                String s = c.getViTri();
                System.out.println(s);
                String str[] = s.split(",");
                for (int i=0; i<str.length; i++){
                    if(str[i].trim().equals("tien dao")  )
                        c1.setChecked(true);
                    else
                        c1.setChecked(false);
                    if(str[i].trim().equals("tien ve") )
                        c2.setChecked(true);
                    else
                        c2.setChecked(false);
                    if(str[i].trim().equals("hau ve")  )
                        c3.setChecked(true);
                    else
                        c3.setChecked(false);
                }

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = "";
                        String ten = edtName.getText().toString();
                        String ngaySinh = edtNGaySinh.getText().toString();

                        if(c1.isChecked() == true){
                            s += "tien dao";
                            s+=",";
                        }
                        if(c2.isChecked() == true){
                            s += "tien ve";
                            s+=",";
                        }
                        if(c3.isChecked() == true){
                            s += "hau ve";
                            s+=",";
                        }

                        if(!ten.trim().equals("") && s != "" ){


                            System.out.println(s);
                            String str[] = s.split(",");

                            for(int i=0; i<str.length; i++){
                                System.out.println(str[i]);
                                if(str[i].trim() == "tien dao" ){
                                    c1.setChecked(true);
                                }
                                if(str[i].trim() == "tien ve" )
                                    c2.setChecked(true);

                                if(str[i].trim() == "hau ve" )
                                    c3.setChecked(true);
                            }


                            CauThu c = new CauThu(R.drawable.men, ten,ngaySinh, s);
                            adapter.updateCauThu(position,c);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Nhap thieu", Toast.LENGTH_SHORT).show();
                        }
                        btnUpdate.setEnabled(false);
                        btnAdd.setEnabled(true);

                    }
                });



            }
        });




        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));


    }

    private void initView() {
        recyclerView = findViewById(R.id.recycleview);
        btnAdd = findViewById(R.id.btnThem);
        btnUpdate = findViewById(R.id.btnSua);
        edtName = findViewById(R.id.edtTen);
        c1 = findViewById(R.id.tiendao);
        c2 = findViewById(R.id.tienve);
        c3 = findViewById(R.id.hauve);
        edtNGaySinh = findViewById(R.id.edtNgaySinh);
    }

    @Override
    public void onClick(View v) {

    }
}
