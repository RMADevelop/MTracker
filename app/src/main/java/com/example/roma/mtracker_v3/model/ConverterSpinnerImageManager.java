package com.example.roma.mtracker_v3.model;

import com.example.roma.mtracker_v3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 20.07.2017.
 */

public class ConverterSpinnerImageManager {
    public static final int itemRUB = 0;
    public static final int itemUSD = 1;
    public static final int itemEUR = 2;
    public static final int itemGBP = 3;

    List<Integer> arrayImage;

    public ConverterSpinnerImageManager() {
        arrayImage = new ArrayList<>();
        arrayImage.add(R.mipmap.ic_rub);
        arrayImage.add(R.mipmap.ic_dollar);
        arrayImage.add(R.mipmap.ic_euro_symbol_white_48dp);
        arrayImage.add(R.mipmap.ic_gbp);
    }

    public int getImage(int id) {
       return arrayImage.get(id);
    }


}
