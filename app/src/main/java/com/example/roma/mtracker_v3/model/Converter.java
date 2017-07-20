package com.example.roma.mtracker_v3.model;

import android.util.Log;

/**
 * Created by Roma on 19.07.2017.
 */

public class Converter {

    public static final int OUT_RUB = 0;
    public static final int OUT_USD = 1;
    public static final int OUT_EUR = 2;
    public static final int OUT_GBP = 3;

    public static final int IN_RUB = 0;
    public static final int IN_USD = 1;
    public static final int IN_EUR = 2;
    public static final int IN_GBP = 3;

    float rubC = 1;
    float usdC;
    float eurC;
    float gbpC;

    public Converter(float usdC, float eurC, float gbpC) {
        this.usdC = usdC;
        this.eurC = eurC;
        this.gbpC = gbpC;
    }

    public float convert(int currencyIn, float in, int currencyOUT) {
        float result = 0;
        switch (currencyOUT) {
            case OUT_RUB:
                result = convertToRUB(currencyIn, in);
                break;
            case OUT_USD:
                result = convertToUSD(currencyIn, in);
                break;
            case OUT_EUR:
                result = convertToEUR(currencyIn, in);
                break;
            case OUT_GBP:
                result = convertToGBP(currencyIn, in);
                break;
        }
        return result;
    }

    private float convertToGBP(int currencyIn, float in) {
        float result;
        switch (currencyIn) {
            case IN_GBP:
                result = in;
                break;
            case IN_EUR:
                result = in * eurC / gbpC;
                break;
            case IN_RUB:
                result = in * rubC / gbpC;
                break;
            case IN_USD:
                result = in * usdC / gbpC;
                break;
            default:
                result = in;

        }
        return result;
    }

    private float convertToEUR(int currencyIn, float in) {
        float result;
        switch (currencyIn) {
            case IN_EUR:
                result = in;
                break;
            case IN_GBP:
                result = in * gbpC / eurC;
                break;
            case IN_RUB:
                result = in * rubC / eurC;
                break;
            case IN_USD:
                result = in * usdC / eurC;
                break;
            default:
                result = in;

        }
        return result;
    }

    private float convertToUSD(int currencyIn, float in) {
        float result;
        switch (currencyIn) {
            case IN_USD:
                result = in;
                break;
            case IN_EUR:
                result = in * eurC / usdC;
                break;
            case IN_GBP:
                result = in * gbpC / usdC;
                break;
            case IN_RUB:
                result = in / usdC;
                Log.v("CurrencyFloat", in + " " +usdC + " " + result);
                break;
            default:
                result = in;
        }
        return result;
    }

    private float convertToRUB(int currencyIn, float in) {
        float result;
        switch (currencyIn) {
            case IN_RUB:
                result = in;
                break;
            case IN_EUR:
                result = in * eurC;
                break;
            case IN_GBP:
                result = in * gbpC;
                break;
            case IN_USD:
                result = in * usdC;
                break;
            default:
                result = in;
        }
        return result;
    }

}
