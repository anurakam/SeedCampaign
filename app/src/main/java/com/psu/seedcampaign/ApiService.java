package com.psu.seedcampaign;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by อนุรักษ์ on 17/3/2558.
 */
public interface ApiService {

    @GET("/Noname1.php")
    void getNoname1ByMethodWithCallback(Callback<List<PlantModel>> callback);

    @GET("/knowledge.php")
    void getKnowledgeByMethodWithCallback(Callback<List<KnowledgeModel>> callback);

    @GET("/knowledge_detail.php")
    void getKnowledgeDetailByNameWithCallback(@Query("localName") String local_name,Callback<KnowledgeDetailModel> callback);

    @GET("/countpeople.php")
    void getCountPeopleByMethodWithCallback(Callback<List<CountPeopleModel>> callback);
}
