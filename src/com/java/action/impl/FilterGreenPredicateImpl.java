package com.java.action.impl;

import com.java.action.Apple;

public class FilterGreenPredicateImpl implements FilterPredicate {
    @Override
    public boolean filter(Object apple) {
        return "green".equals(((Apple) apple).getColor());
    }
}
