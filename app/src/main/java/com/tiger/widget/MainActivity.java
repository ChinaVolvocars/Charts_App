package com.tiger.widget;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViews();
            }
        });

        initViews();
    }

    private int maxTemp = 88;
    private int minTemp = 11;

    private void initViews() {
        Materials30View materials30View = (Materials30View) findViewById(R.id.materials);
        Rectangle30View rectangle30View = (Rectangle30View) findViewById(R.id.rectangle);
        MaterialsConsumptionView materialsconsumptionview = (MaterialsConsumptionView) findViewById(R.id.MaterialsConsumptionView);
        Random random = new Random();

        List<Materials30Item> itemList = new ArrayList<>();
        Materials30Item materials30Item;
        for (int i = 0; i < 20; i++) {
            materials30Item = new Materials30Item();
            int s = random.nextInt(maxTemp) % (maxTemp - minTemp + 1) + minTemp;
            materials30Item.setCountTon(s);
            materials30Item.setCountMaterial(i);
            materials30Item.setTime("07-" + i);
            itemList.add(materials30Item);
        }
        materials30View.setData(itemList);


        Rectangle30Item rectangle30Item;
        List<Rectangle30Item> itemList1 = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            rectangle30Item = new Rectangle30Item();
            double s = random.nextInt(26) % (maxTemp - minTemp + 1) + minTemp;
            double s2 = random.nextInt(26) % (maxTemp - minTemp + 1) + minTemp;
            rectangle30Item.setTonLeft(s);
            rectangle30Item.setTonRight(s2);
            rectangle30Item.setTime("07-" + i);
            itemList1.add(rectangle30Item);
        }

        rectangle30View.setData(itemList1);


        List<MaterialsConsumption> itemList12 = new ArrayList<>();
        MaterialsConsumption materialsconsumption;
        Random random1 = new Random();
//
        for (int i = 0; i < 6; i++) {
            materialsconsumption = new MaterialsConsumption();
            double s = random1.nextInt((int) maxTemp) % (maxTemp - minTemp + 1) + minTemp;
//            double s1 = random1.nextInt((int) maxTemp) % (maxTemp - minTemp + 1) + minTemp;
            materialsconsumption.setTotalSignTon(s);
            materialsconsumption.setTotalConsumeTon(s * 2);
            materialsconsumption.setRawMaterial("水泥" + i);
            itemList12.add(materialsconsumption);
        }

        materialsconsumptionview.setData(itemList12);

    }
}
