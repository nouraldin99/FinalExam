package com.noura.anwar.finalexam.model.entity;

import java.util.Observable;

public class ObservableCurrencyInfo extends Observable {

    private CurrencyInfo currencyInfo ;

    public void setCurrencyInfo(CurrencyInfo currencyInfo) {
        this.currencyInfo = currencyInfo;
        setChanged();
        notifyObservers(currencyInfo);
    }

    public CurrencyInfo getCurrencyInfo() {
        return currencyInfo;
    }
}
