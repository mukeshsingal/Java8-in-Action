package com.java.action.impl;

import com.java.action.Apple;

public class WeightPrintFormatterImpl implements PrintFormatter {
    @Override
    public String accept(Object apple) {
        return String.valueOf(((Apple)apple).getWeight());
    }
}
