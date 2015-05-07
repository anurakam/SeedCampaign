package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 19/3/2558.
 */
public class KnowledgeDetailModel {
    //String scientificName,familyName,localName,commonName,usedParts,ultilization,season,biological,found,distribution,coution,notes,reference;
    String scientific,family,treename,common,treedetail;
    // int id_User;


    //String scientificName,String familyName,String localName,String commonName,String usedParts,String ultilization,String season,String biological,String found,String distribution,String coution,String notes,String reference
    public KnowledgeDetailModel(String scientific,String family,String treename,String common,String treedetail) {

      /*  this.scientificName = scientificName;
        this.familyName = familyName;
        this.localName = localName;
        this.commonName = commonName;
        this.usedParts = usedParts;
        this.ultilization = ultilization;
        this.season = season;
        this.biological = biological;
        this.found = found;
        this.distribution = distribution;
        this.coution = coution;
        this.notes = notes;
        this.reference = reference;*/
        this.scientific = scientific;
        this.family = family;
        this.treename = treename;
        this.common = common;
        this.treedetail = treedetail;

    }


    public String getScientificName() {
        return scientific;
    }

    public void setScientificName(String scientific) {
        this.scientific = scientific;
    }

    public String getFamilyName() {
        return family;
    }

    public void setFamilyName(String family) {
        this.family = family;
    }

    public String getLocalName() {
        return treename;
    }

    public void setLocalName(String treename) {
        this.treename = treename;
    }

    public String getCommonName() {
        return common;
    }

    public void setCommonName(String v) {
        this.common = common;
    }

    public String getUsedParts() {
        return treedetail;
    }

    public void setUsedParts(String treedetail) {
        this.treedetail = treedetail;
    }

    /*public String getUltilization() {
        return ultilization;
    }

    public void setUltilization(String ultilization) {
        this.ultilization = ultilization;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBiological() {
        return biological;
    }

    public void setBiological(String biological) {
        this.biological = biological;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getCoution() {
        return coution;
    }

    public void setCoution(String coution) {
        this.coution = coution;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
*/
}
