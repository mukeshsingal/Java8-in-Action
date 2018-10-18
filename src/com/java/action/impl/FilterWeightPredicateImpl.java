package com.java.action.impl;

import com.java.action.Apple;

public class FilterWeightPredicateImpl implements FilterPredicate {
    @Override
    public boolean filter(Object o) {
        return ((Apple)o).getWeight() > 150;
    }
}
