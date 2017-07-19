package com.example.roma.mtracker_v3.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Roma on 17.07.2017.
 */

public class Currency {

    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private Rates rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public class Rates {

        @SerializedName("GBP")
        @Expose
        private float gBP;
        @SerializedName("USD")
        @Expose
        private float uSD;
        @SerializedName("EUR")
        @Expose
        private float eUR;

        public float getGBP() {
            return gBP;
        }

        public void setGBP(float gBP) {
            this.gBP = gBP;
        }

        public float getUSD() {
            return uSD;
        }

        public void setUSD(float uSD) {
            this.uSD = uSD;
        }

        public float getEUR() {
            return eUR;
        }

        public void setEUR(float eUR) {
            this.eUR = eUR;
        }

    }


}