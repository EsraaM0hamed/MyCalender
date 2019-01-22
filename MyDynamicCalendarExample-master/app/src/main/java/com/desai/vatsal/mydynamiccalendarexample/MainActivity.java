package com.desai.vatsal.mydynamiccalendarexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.DateModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    Button pre, next;
    TextView date;
    private Toolbar toolbar;
    private ArrayList<DateModel> dateModelList;
    private ArrayList<DateModelE> mylList;
    private ArrayList<DateModelE> mon_act, tus_act;
    private ArrayList<DateModelE> current_week_ac;

    private DateListAdapterE myadapter;
    private ArrayList<ShowEventsModelE> showEventsModelList;
    String name;
    int image;
    RecyclerView mon_recy,tus_recy;
    private RecyclerView.LayoutManager mLayoutManager;
    private SimpleDateFormat sdfWeekDay = new SimpleDateFormat("dd MMM");
    private Calendar calendar = Calendar.getInstance();
    Context context;
    ArrayList<Integer> weedays;
    ArrayList<String> dateStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findItemsById();
        showWeekView();
        initReci();

    }

    private void findItemsById() {
        pre = (Button) findViewById(R.id.pre);
        next = (Button) findViewById(R.id.next);
        date = (TextView) findViewById(R.id.date);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setWeekView("");
        setSupportActionBar(toolbar);
        mon_recy = (RecyclerView) findViewById(R.id.mon_recy);
        tus_recy = (RecyclerView) findViewById(R.id.tus_recy);

    }

    private void initReci() {

        mon_act = new ArrayList<>();
        weedays = new ArrayList<>();
        current_week_ac = new ArrayList<>();
        dateStrings = new ArrayList<>();
        mylList = new ArrayList<>();
        tus_act = new ArrayList<>();
        DateModelE dateModelE = new DateModelE("E1", "run", "Tus 23/01/2019", R.mipmap.row);
        mylList.add(dateModelE);
        DateModelE dateModelE2 = new DateModelE("E2", "run", "Mon 21/01/2019", R.mipmap.run);
        mylList.add(dateModelE2);
        DateModelE dateModelE4 = new DateModelE("E4", "run", "Mon 21/01/2019", R.mipmap.row);
        mylList.add(dateModelE4);
        DateModelE dateModelE3 = new DateModelE("E3", "row", "Tus 23/01/2019", R.mipmap.run);
        mylList.add(dateModelE3);
        DateModelE dateModelE5 = new DateModelE("E5", "row", "Tus 23/01/2019", R.mipmap.row);
        mylList.add(dateModelE5);
        getcurrerntdayString();
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //for calender****************
        for (int k = 0; k < mylList.size(); k++) {

            System.out.println("dates " + mylList.get(k).getDate());
            if (mylList.get(k).getDate().contains("Mon")) {
                mon_act.add(mylList.get(k));
                myadapter = new DateListAdapterE(context, mon_act);
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                mon_recy.setLayoutManager(mLayoutManager);
                mon_recy.setAdapter(myadapter);
            } else if (mylList.get(k).getDate().contains("Tus")) {
                tus_act.add(mylList.get(k));
                myadapter = new DateListAdapterE(context, tus_act);
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                tus_recy.setLayoutManager(mLayoutManager);
                tus_recy.setAdapter(myadapter);

            }
        }


    }

    private ArrayList<String> getcurrerntdayString() {
        Calendar a = Calendar.getInstance();
        ArrayList<Calendar> b = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
        for (int i = 0; i < 7; i++) {
            Log.i("tag", df.format(c.getTime()));
            c.add(Calendar.DATE, 1);
            a.add(Calendar.DATE, i > 0 ? 1 : 0);
            b.add(a);
            a = (Calendar) a.clone();

        }

        for (int j = 0; j < b.size(); j++) {
            String dateString = String.valueOf(df.format(b.get(j).getTime()));

            dateStrings.add(dateString);
        }
        return dateStrings;
    }

//****


    public void showWeekView() {
        setWeekView("");
    }


    private void setWeekView(String sign) {
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setWeekView("sub");


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWeekView("add");
            }
        });


        //**************************


        dateModelList = new ArrayList<>();

        showEventsModelList = new ArrayList<>();


        if (sign.equals("add")) {
            calendar.set(Calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH) + 7));
        } else if (sign.equals("sub")) {
            calendar.set(Calendar.DAY_OF_MONTH, (calendar.get(Calendar.DAY_OF_MONTH) - 7));
        }


        // set date start of month
        calendar.setTime(calendar.getTime());

        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        String weekStartDay = sdfWeekDay.format(calendar.getTime());

        dateModelList.clear();

        while (dateModelList.size() < 7) {
            DateModel model = new DateModel();
            model.setDates(calendar.getTime());
            model.setFlag("week");
            dateModelList.add(model);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        calendar.add(Calendar.DAY_OF_MONTH, -1);

        String weekEndDay = sdfWeekDay.format(calendar.getTime());

        date.setText(String.format("%s - %s", weekStartDay, weekEndDay));


        showEventsModelList.clear();

        int count7 = 1;

        calendar.add(Calendar.DAY_OF_MONTH, -6);

        Calendar calendar_set_hours = Calendar.getInstance();
        calendar_set_hours.set(Calendar.HOUR_OF_DAY, 0);

        while (showEventsModelList.size() < 168) {
            if (count7 < 7) {
                ShowEventsModelE model = new ShowEventsModelE();
                model.setDates(calendar.getTime());
                showEventsModelList.add(model);

                calendar.add(Calendar.DAY_OF_MONTH, 1);

                count7++;
            } else {
                ShowEventsModelE model = new ShowEventsModelE();
                model.setDates(calendar.getTime());
                showEventsModelList.add(model);

                calendar.add(Calendar.DAY_OF_MONTH, -6);

                calendar_set_hours.add(Calendar.HOUR_OF_DAY, 1);

                count7 = 1;
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_month:
                // showMonthView();
                return true;
            case R.id.action_month_with_below_events:
                //   showMonthViewWithBelowEvents();
                return true;
            case R.id.action_week:
//                  showWeekView();
                return true;
            case R.id.action_day:
                //   showDayView();
                return true;
            case R.id.action_agenda:
                // showAgendaView();
                return true;
            case R.id.action_today:
                // myCalendar.goToCurrentDate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
