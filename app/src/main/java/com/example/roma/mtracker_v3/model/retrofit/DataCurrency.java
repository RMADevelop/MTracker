package com.example.roma.mtracker_v3.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Roma on 19.07.2017.
 */

public class DataCurrency {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("terms")
    @Expose
    private String terms;
    @SerializedName("privacy")
    @Expose
    private String privacy;
    @SerializedName("timestamp")
    @Expose
    private int timestamp;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("quotes")
    @Expose
    private Quotes quotes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }


    public class Quotes {

        @SerializedName("USDEUR")
        @Expose
        private float uSDEUR;
        @SerializedName("USDGBP")
        @Expose
        private float uSDGBP;
        @SerializedName("USDRUB")
        @Expose
        private float uSDRUB;

        public float getUSDEUR() {
            return uSDEUR;
        }

        public void setUSDEUR(float uSDEUR) {
            this.uSDEUR = uSDEUR;
        }

        public float getUSDGBP() {
            return uSDGBP;
        }

        public void setUSDGBP(float uSDGBP) {
            this.uSDGBP = uSDGBP;
        }

        public float getUSDRUB() {
            return uSDRUB;
        }

        public void setUSDRUB(float uSDRUB) {
            this.uSDRUB = uSDRUB;
        }
    }
}