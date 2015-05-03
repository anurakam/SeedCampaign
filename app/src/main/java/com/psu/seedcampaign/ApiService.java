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
    void getCountPeopleByMethodWithCallback(Callback<CountPeopleModel> callback);

    @GET("/histoty_user.php")
    void getHistoryByUserNameWithCallback(@Query("userName") String user_name,Callback<List<HistoryModel>> callback);

    @GET("/sumnorth.php")
    void getSumNorthByMethodWithCallback(Callback<SumNorthModel> callback);
    @GET("/sumnortheast.php")
    void getSumNorthEastByMethodWithCallback(Callback<SumNorthEastModel> callback);
    @GET("/sumeast.php")
    void getSumEastByMethodWithCallback(Callback<SumEastModel> callback);
    @GET("/sumcenter.php")
    void getSumCentralByMethodWithCallback(Callback<SumCentralModel> callback);
    @GET("/sumsouth.php")
    void getSumSouthByMethodWithCallback(Callback<SumSouthModel> callback);
    @GET("/sumalltree.php")
    void getSumAllTreeByMethodWithCallback(Callback<SumAllTreeModel> callback);

    @GET("/alltree_user.php")
    void getAllTreeUserByUserNameWithCallback(@Query("userName") String user_name,Callback<AllTreeUserModel> callback);
}
