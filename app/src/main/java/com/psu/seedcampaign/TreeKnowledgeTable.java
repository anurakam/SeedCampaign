package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 8/3/2558.
 */
public class TreeKnowledgeTable {

    int id;
    String treeName;

    public TreeKnowledgeTable(int no, String name) {
        super();
        this.id = id;
        this.treeName = treeName;
    }

    public TreeKnowledgeTable() {
        super();
        this.id=0;
        this.treeName = null;
    }


    public int getNo() {
        return id;
    }
    public void setNo(int no) {
        this.id = id;
    }
    public String getName() {
        return treeName;
    }
    public void setName(String treeName) {
        this.treeName = treeName;
    }
}
