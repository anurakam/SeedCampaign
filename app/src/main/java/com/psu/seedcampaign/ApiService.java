package com.psu.seedcampaign;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by อนุรักษ์ on 17/3/2558.
 */
public interface ApiService {

    @GET("/service/Noname1.php")
    void getNoname1ByMethodWithCallback(Callback<List<PlantModel>> callback);

    @GET("/service/knowledge.php")
    void getKnowledgeByMethodWithCallback(Callback<List<KnowledgeModel>> callback);

    @GET("/service/knowledge_detail.php")
    void getKnowledgeDetailByNameWithCallback(@Query("localName") String local_name,Callback<KnowledgeDetailModel> callback);

    @GET("/service/countpeople.php")
    void getCountPeopleByMethodWithCallback(Callback<CountPeopleModel> callback);

    @GET("/service/histoty_user.php")
    void getHistoryByUserNameWithCallback(@Query("userName") String user_name,Callback<List<HistoryModel>> callback);

    @GET("/service/sumnorth.php")
    void getSumNorthByMethodWithCallback(Callback<SumNorthModel> callback);
    @GET("/service/sumnortheast.php")
    void getSumNorthEastByMethodWithCallback(Callback<SumNorthEastModel> callback);
    @GET("/service/sumeast.php")
    void getSumEastByMethodWithCallback(Callback<SumEastModel> callback);
    @GET("/service/sumcenter.php")
    void getSumCentralByMethodWithCallback(Callback<SumCentralModel> callback);
    @GET("/service/sumsouth.php")
    void getSumSouthByMethodWithCallback(Callback<SumSouthModel> callback);
    @GET("/service/sumalltree.php")
    void getSumAllTreeByMethodWithCallback(Callback<SumAllTreeModel> callback);

    @GET("/service/alltree_user.php")
    void getAllTreeUserByUserNameWithCallback(@Query("userName") String user_name,Callback<AllTreeUserModel> callback);

    @GET("/service/countNo.php")
    void getCountNoByUserNameWithCallback(@Query("userName") String Username,Callback<CountNoModel> callback);
}
