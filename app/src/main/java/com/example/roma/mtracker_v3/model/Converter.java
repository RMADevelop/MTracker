package com.example.roma.mtracker_v3.model;

/**
 * Created by Roma on 19.07.2017.
 */

public class Converter {

    public static final String OUT_RUB = "out_rub";
    public static final String OUT_USD = "out_usd";
    public static final String OUT_EUR = "out_eur";
    public static final String OUT_GBP = "out_gbp";

    public static final String IN_RUB = "in_rub";
    public static final String IN_USD = "in_usd";
    public static final String IN_EUR = "in_eur";
    public static final String IN_GBP = "in_gbp";

    float rubC = 1;
    float usdC;
    float eurC;
    float gbpC;

    public Converter(float usdC, float eurC, float gbpC, float rubC) {
        this.usdC = usdC;
        this.eurC = eurC;
        this.gbpC = gbpC;
        this.rubC = rubC;
    }

    public float convert(String currencyIn, float in, String currencyOUT) {
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

    private float convertToGBP(String currencyIn, float in) {
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

    private float convertToEUR(String currencyIn, float in) {
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

    private float convertToUSD(String currencyIn, float in) {
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
                result = in * rubC / usdC;
                break;
            default:
                result = in;
        }
        return result;
    }

    private float convertToRUB(String currencyIn, float in) {
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
