package com.example.yangchenglei.tenday_04;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/*

* 撕衣服demo
* 原理：
* 1.触摸的时候使其变的透明
*
* */
public class MainActivity extends Activity {

    private ImageView iv;
    private Bitmap srcBitmap;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
        //获取原图
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pre);
        //创建副本
        //1.创建一个和原图一样大的模版
         final Bitmap alterBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        //2.创建一个画笔和一个和原图一样大的画布
        Paint paint = new Paint();
        Canvas canvas = new Canvas(alterBitmap);
        //对画布上创建一个大小一样副本图片并且显示出来
        canvas.drawBitmap(srcBitmap, new Matrix(), paint);
        iv.setImageBitmap(alterBitmap);
        //设置一个触摸事件
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch (action) {
                    case MotionEvent.ACTION_MOVE:
                        for (int i = -15; i < 15; i++) {
                            for (int j = -15; j <15 ; j++) {
                                if (Math.sqrt(i*i+j*j)<15){
                                    try {
                                        alterBitmap.setPixel(x+i,y+j,Color.TRANSPARENT);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        }

                        iv.setImageBitmap(alterBitmap);

                        break;
                    default:
                        break;

                }
                return true;
            }
        });


    }
}
