package com.psu.seedcampaign;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

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
        /*CustomList adapter = new
                CustomList(Feed.this, web, imageId, web2,web3,imageId2,web4,web5,web6);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Feed.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
            }
        });*/
       // List<PlantingDetailsTable> detailsTables = null;
        ///ArrayList<PlantingDetailsTable> book = new ArrayList<PlantingDetailsTable>();
        list = (ListView) findViewById(R.id.list);
        //list.setAdapter(new BookListAdapter(book));







        GsonBuilder builder = new GsonBuilder();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://anurakam.somee.com")
                .setConverter(new GsonConverter(builder.create()))
                .build();
        ApiService retrofit = restAdapter.create(ApiService.class);
        retrofit.getNoname1ByMethodWithCallback(new Callback<List<PlantModel>>() {


            @Override
            public void success(List<PlantModel> plantModels, Response response) {
                List<PlantModel> ep = plantModels;
                list.setAdapter(new BookListAdapter(ep));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Feed.this,
                        "ไม่สามารถเข้าถึงข้อมูลได้",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public class BookListAdapter extends BaseAdapter {

        List<PlantModel> Book;

        public BookListAdapter(List<PlantModel> sd) {
            Book = sd;
        }

        @Override
        public int getCount() {
            return Book.size();
        }

        @Override
        public Object getItem(int position) {
            return Book.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            TextView nickName;
            TextView userName;
            TextView flowerName;
            TextView amount;
            TextView locationName,region;
            //TextView userName;
            TextView position;
            ImageView image;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = getLayoutInflater();

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_single, parent, false);
                holder = new ViewHolder();
                holder.userName = (TextView) convertView.findViewById(R.id.txt);
                holder.locationName = (TextView) convertView.findViewById(R.id.txt1);
                holder.nickName = (TextView) convertView.findViewById(R.id.txt2);
                holder.flowerName = (TextView) convertView.findViewById(R.id.txt3);
                holder.amount = (TextView) convertView.findViewById(R.id.txt4);
                holder.image = (ImageView) convertView.findViewById(R.id.img2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            PlantModel bk = Book.get(position);
            //holder.position.setText("" + (position + 1));
            holder.userName.setText("" + bk.getUserName());
            holder.locationName.setText("" + bk.getLocationName());
            holder.nickName.setText("ชื่อต้นไม้: " + bk.getNickName());
            holder.flowerName.setText("ชื่อสามัญ: " + bk.getFlowerName());
            holder.amount.setText("ปลูกไปแล้ว: " + bk.getAmount()+"ต้น");
            Picasso.with(Feed.this).load("http://anurakam.somee.com/AndroidFileUpload/uploads/" + bk.getPicName()).into(holder.image);

            return convertView;
        }
    }

}
