package com.beecoder77.madesubmission4.db;

import com.beecoder77.madesubmission4.model.Item;

import java.util.ArrayList;

public interface DbCallback {
    void preExecute();
    void postExecute(ArrayList<Item> items);
}

