package com.psu.seedcampaign;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by อนุรักษ์ on 19/3/2558.
 */
public class KnowledgeDetail extends Activity {

    TextView scientificName,familyName,localName,commonName,usedParts,ultilization,season,biological,found,distribution,coution,notes,reference,knowledgetitle;
    String ScientificName,FamilyName,LocalName,CommonName,UsedParts,Ultilization,Season,Biological,Found,Distribution,Coution,Notes,Feferenc,Knowledgetitle;
    String local_name = Knowledge.getLocalName();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_detail);

       // ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);
       // scrollView.setScrollViewCallbacks(this);

        scientificName = (TextView) findViewById(R.id.scientificName);
        familyName = (TextView) findViewById(R.id.familyName);
        localName = (TextView) findViewById(R.id.localName);
        commonName = (TextView) findViewById(R.id.commonName);
        usedParts = (TextView) findViewById(R.id.usedParts);
        ultilization = (TextView) findViewById(R.id.ultilization);
        season = (TextView) findViewById(R.id.season);
        biological = (TextView) findViewById(R.id.biological);
        found = (TextView) findViewById(R.id.found);
        distribution = (TextView) findViewById(R.id.distribution);
        coution = (TextView) findViewById(R.id.coution);
        notes = (TextView) findViewById(R.id.notes);
        reference = (TextView) findViewById(R.id.referenc);
        knowledgetitle = (TextView) findViewById(R.id.knowledge_title);

        GsonBuilder builder = new GsonBuilder();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://anurakam.somee.com/")
                .setConverter(new GsonConverter(builder.create()))
                .build();
        ApiService retrofit = restAdapter.create(ApiService.class);
        retrofit.getKnowledgeDetailByNameWithCallback(local_name, new Callback<KnowledgeDetailModel>() {


            @Override
            public void success(KnowledgeDetailModel knowledgeDetailModel, Response response) {
                KnowledgeDetailModel Knowledge = knowledgeDetailModel;
                        ScientificName = Knowledge.getScientificName();
                        FamilyName = Knowledge.getFamilyName();
                        LocalName = Knowledge.getLocalName();
                        CommonName = Knowledge.getCommonName();
                        UsedParts = Knowledge.getUsedParts();
                        /*Ultilization = Knowledge.getUltilization();
                        Season = Knowledge.getSeason();
                        Biological = Knowledge.getBiological();
                        Found = Knowledge.getFound();
                        Distribution = Knowledge.getDistribution();
                        Coution = Knowledge.getCoution();
                        Notes = Knowledge.getNotes();
                        Feferenc = Knowledge.getReference();*/
                        Knowledgetitle = Knowledge.getLocalName();


                scientificName.setText("ชื่อวิทยาศาสตร์: " + ScientificName);
                familyName.setText("ชื่อวงศ์: " + FamilyName);
                localName.setText("ชื่อพื้นเมือง: " + LocalName);
                commonName.setText("ชื่อสามัญ: " + CommonName);
                /*usedParts.setText("ส่วนที่นำมาใช้: " + UsedParts);
                ultilization.setText("การใช้ประโยชน์: " + Ultilization);
                season.setText("ฤดูติดดอก/ออกผล: " + Season);
                biological.setText("ลักษณะ: " + Biological);
                found.setText("พื้นที่ที่พบในสวนฯ: " + Found);
                distribution.setText("การกระจายพันธุ์: " + Distribution);
                coution.setText("คำเตือน: " + Coution);
                notes.setText("หมายเหตุ: " + Notes);
                reference.setText("เอกสารอ้างอิง: " + Feferenc);*/
                knowledgetitle.setText("" + Knowledgetitle);
                usedParts.setText("ลักษณะ: " + UsedParts);

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(KnowledgeDetail.this, "ไม่สามารถเข้าถึงข้อมูลได้", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
