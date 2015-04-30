package com.psu.seedcampaign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by อนุรักษ์ on 19/3/2558.
 */
public class Knowledge extends Activity {
    public static String local_Name;
    GridView gridView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge);

        gridView = (GridView) findViewById(R.id.grid);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KnowledgeModel localName = (KnowledgeModel) parent.getItemAtPosition(position);
                local_Name = localName.getLocalName();
                Intent knowledgeDetail = new Intent(Knowledge.this, KnowledgeDetail.class);
                startActivity(knowledgeDetail);
            }
        });


    }
    public static String getLocalName() {
        return local_Name;
    }
    public class KnowledgeListAdapter extends BaseAdapter {

        List<KnowledgeModel> knowledge;

        public KnowledgeListAdapter(List<KnowledgeModel> sd) {
            knowledge = sd;
        }

        @Override
        public int getCount() {
            return knowledge.size();
        }

        @Override
        public Object getItem(int position) {
            return knowledge.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            TextView localName;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = getLayoutInflater();

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.grid_single, parent, false);
                holder = new ViewHolder();
                holder.localName = (TextView) convertView.findViewById(R.id.grid_text);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            KnowledgeModel km = knowledge.get(position);
            //holder.position.setText("" + (position + 1));
            holder.localName.setText("" + km.getLocalName());


            return convertView;
        }

    }
}
