package com.smartFarmApp.dashboard.items;

public class PriceCalculator implements Visitor {
    private double price;

    @Override
    public double visit(Item itemContainer) {
        price = itemContainer.getPrice();
        if (!itemContainer.getChildren().isEmpty()) {
            for (Item children : itemContainer.getChildren()) {
                price += children.getPrice();
                visit(children);
                if(children.hasNext()){
                    children.next();
                }
            }

       }
        return price;
    }
}
