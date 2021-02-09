package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Hero {
    protected String name;
    protected String race;
    protected int currentHealt;
    protected ArrayList<InventoryObject> inventory;
    protected boolean overloaded;
    protected int weightLimit;
    protected int count;

    public Hero(String name, String race) {
        this.name = name;
        this.race = race;
        this.currentHealt = 100;
        this.inventory=new ArrayList<>(10);
        this.overloaded = false;
        count=0;
        weightLimit=100;
    }

    public ArrayList<InventoryObject> showInventory(){
        return inventory;
    }

    public void addInventory(InventoryObject object){
        if(inventory.contains(object) && getInventoryWeigth()+object.getWeigth()<=weightLimit){
            inventory.get(inventory.indexOf(object)).increasCount();
        }else if(count<10 && getInventoryWeigth()+object.getWeigth()<=weightLimit){
            inventory.add(object);
        }
    }

    public void removeInventory(InventoryObject object){
        if(inventory.contains(object)){
            if(object.getCount()>1){
                object.decreaseCount();
            } else{
                inventory.remove(object);
            }
        }
    }

    public ArrayList<InventoryObject> getInventory(){
        return inventory;
    }

    public double getInventoryWeigth(){
        double sum=0;
        for(InventoryObject x:inventory){
            if(x!=null){
                sum+=x.getWeigth();
            }
        }
        return sum;
    }
}
