package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 19/3/2558.
 */
public class KnowledgeModel {
    String localName;
    // int id_User;

    public KnowledgeModel(String localName) {

        this.localName = localName;

    }
    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

}
