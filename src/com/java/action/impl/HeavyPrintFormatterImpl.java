package com.java.action.impl;

import com.java.action.Apple;

public class HeavyPrintFormatterImpl implements PrintFormatter {
    @Override
    public String accept(Object apple) {

        return ((Apple) apple).getWeight() > 150 ? "Heavy" : "Light" ;
    }
}
