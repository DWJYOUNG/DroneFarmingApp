package com.smartFarmApp.dashboard.items;

public class Price implements Visitor {

    @Override
    public double visit(ItemLeaf item) {
        return item.getPrice();
    }

    @Override
    public double visit(ItemContainer itemContainer) {
        double price = itemContainer.getPrice();
        //double parentPrice = 0;
        if(!itemContainer.getChildren().isEmpty()){
            for(Item child : itemContainer.children){
                String childClass = String.valueOf(child.getClass());
                System.out.println(childClass);
                if(childClass.equals("class com.smartFarmApp.dashboard.items.ItemLeaf")){
                    price += visit((ItemLeaf) child);
                }else{
                    price += visit((ItemContainer) child);
                }
            }
        }
            System.out.println(price);
            return price;
    }
}
