package com.smartFarmApp.dashboard.items;


public interface Visitor {

    double visit(ItemLeaf item);
    double visit(ItemContainer itemContainer);
}
