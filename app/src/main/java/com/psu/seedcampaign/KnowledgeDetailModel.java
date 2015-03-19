package com.psu.seedcampaign;

/**
 * Created by อนุรักษ์ on 19/3/2558.
 */
public class KnowledgeDetailModel {
    String scientificName,familyName,localName,commonName,usedParts,ultilization,season,biological,found,distribution,coution,notes,reference;
    // int id_User;



    public KnowledgeDetailModel(String scientificName,String familyName,String localName,String commonName,String usedParts,String ultilization,String season,String biological,String found,String distribution,String coution,String notes,String reference) {

        this.scientificName = scientificName;
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
        this.reference = reference;

    }


    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getUsedParts() {
        return usedParts;
    }

    public void setUsedParts(String usedParts) {
        this.usedParts = usedParts;
    }

    public String getUltilization() {
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

}
