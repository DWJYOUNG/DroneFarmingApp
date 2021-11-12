package com.smartFarmApp.dashboard.items;

import java.util.Iterator;

public class MarketPrice implements Visitor {
    private double childPrice;

    @Override
    public double visit(ItemLeaf item) {
        return item.getPrice();
    }

    @Override
    public double visit(ItemContainer itemContainer) {
        if(!itemContainer.getChildren().isEmpty()){
            for(Item child : itemContainer.children){
                String childClass = String.valueOf(child.getClass());
                System.out.println(childClass);
                if(childClass.equals("class com.smartFarmApp.dashboard.items.ItemLeaf")){
                    childPrice += visit((ItemLeaf) child);
                }else{
                    visit((ItemContainer) child);
                }

            }
        }
        System.out.println(childPrice);
        return childPrice;
    }


}
