package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 19/3/2558.
 */
public class KnowledgeModel {
    String treename; //localName
    // int id_User;

    public KnowledgeModel(String treename) {

        this.treename = treename;

    }
    public String getLocalName() {
        return treename;
    }

    public void setLocalName(String treename) {
        this.treename = treename;
    }

}
