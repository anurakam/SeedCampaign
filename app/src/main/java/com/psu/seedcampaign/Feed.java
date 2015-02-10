package com.psu.seedcampaign;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by อนุรักษ์ on 2/2/2558.
 */
public class Feed extends Activity {
    ListView list;
    String[] web = {
            "อโณชา",
            "ณัชภรณ์"
    } ;
    String[] web2 = {
            "ภูเก็ต",
            "สงขลา"
    } ;
    String[] web3 = {
            "โดเรม่อน",
            "เบ็นเท็น"
    } ;
    Integer[] imageId = {
            R.drawable.avatar,
            R.drawable.avatar

    };
    Integer[] imageId2 = {
            R.drawable.tree3,
            R.drawable.tree4

    };
    String[] web4 = {
            "ทานตะวัน",
            "ดาวเรือง"
    };
    String[] web5 = {
            "ปลูกไปแล้ว 7 ต้น",
            "ปลูกไปแล้ว 10 ต้น"
    };
    String[] web6 = {
            "รวมทั้งโครงการ 358 ต้น",
            "รวมทั้งโครงการ 365 ต้น"
    };


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        CustomList adapter = new
                CustomList(Feed.this, web, imageId, web2,web3,imageId2,web4,web5,web6);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Feed.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
            }
        });

    }

}
