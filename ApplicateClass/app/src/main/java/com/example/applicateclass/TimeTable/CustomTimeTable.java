package com.example.applicateclass.TimeTable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.applicateclass.R;



public class CustomTimeTable extends LinearLayout {
    private final int TIM = 0;
    private final int MON = 1;
    private final int TUE = 2;
    private final int WED = 3;
    private final int THU = 4;
    private final int FRI = 5;


    private CustomOneLine colDays[];
    private ScrollView scrollView;
    public CustomTimeTable(Context context) {
        super(context);
        initView();
    }
    public CustomTimeTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }
    public CustomTimeTable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);
    }

    private void initView() {

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.custom_timetable, this, false);
        addView(v);

        colDays = new CustomOneLine[6];
        colDays[TIM] = (CustomOneLine)findViewById(R.id.table_standard_line);
        colDays[MON] = (CustomOneLine)findViewById(R.id.table_mon_line);
        colDays[TUE] = (CustomOneLine)findViewById(R.id.table_thu_line);
        colDays[WED] = (CustomOneLine)findViewById(R.id.table_wed_line);
        colDays[THU] = (CustomOneLine)findViewById(R.id.table_thur_line);
        colDays[FRI] = (CustomOneLine)findViewById(R.id.table_fri_line);

        scrollView = (ScrollView)findViewById(R.id.table_scroll);

        colDays[TIM].setStandard();

    }


    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TableTable);
        setTypeArray(typedArray);
    }
    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TableTable, defStyle, 0);
        setTypeArray(typedArray);
    }
    private void setTypeArray(TypedArray typedArray) {

        typedArray.recycle();

    }

    public boolean addTime(CustomScheduleItem scheduleItem ){
        for(CustomTimeset i  : scheduleItem.getTimelist()){
            if(!colDays[i.getDay()].addTime(scheduleItem.getTitle(),scheduleItem.getSub(),i)){
                Toast.makeText(getContext(),"중복된 스케줄이 존재합니다",Toast.LENGTH_LONG).show();
                return false;
            }



        }
        return true;
    }



}
