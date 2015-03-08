package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 2/2/2558.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final String[] web2;
    private final String[] web3;
    private final String[] web4;
    private final String[] web5;
    private final String[] web6;
    private final Integer[] imageId;
    private final Integer[] imageId2;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId,String[] web2,String[] web3,Integer[] imageId2,String[] web4,String[] web5,String[] web6) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.web2 = web2;
        this.web3 = web3;
        this.imageId2 = imageId2;
        this.web4 = web4;
        this.web5 = web5;
        this.web6 = web6;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.txt1);
        TextView txtTitle3 = (TextView) rowView.findViewById(R.id.txt2);
        ImageView imageView3 = (ImageView) rowView.findViewById(R.id.img2);
        TextView txtTitle4 = (TextView) rowView.findViewById(R.id.txt3);
        TextView txtTitle5 = (TextView) rowView.findViewById(R.id.txt4);
        TextView txtTitle6 = (TextView) rowView.findViewById(R.id.txt5);
        txtTitle.setText(web[position]);
        imageView.setImageResource(imageId[position]);
        txtTitle2.setText(web2[position]);
        txtTitle3.setText(web3[position]);
        imageView3.setImageResource(imageId2[position]);
        txtTitle4.setText(web4[position]);
        txtTitle5.setText(web5[position]);
        txtTitle6.setText(web6[position]);
        return rowView;
    }
}
