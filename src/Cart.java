import java.util.ArrayList;

public class Cart {

    ArrayList<Item> items;

    public Cart(){
        this.items = new ArrayList<>();
    }

    public Item getItems(int index) {
        return new Item(this.items.get(index));
    }

    public void setItems(int index, Item item) {
        this.items.set(index,new Item(item));
    }

    public boolean add(Item item){
        if(this.items.contains(item)){
            return false;
        }
        this.items.add(new Item(item));
        return true;
    }

    public boolean isEmpty(){
        return this.items.isEmpty();
    }

    public void remove(String name){
        if(items.isEmpty()){
            throw new IllegalStateException("Cannot remove items from an empty cart!");
        }
        for (int i = 0; i <this.items.size() ; i++) {
            if(this.items.get(i).getName().equals(name)){
                this.items.remove(i);
            }
        }
    }

    public String checkout(){
        if(items.isEmpty()){
            throw new IllegalStateException("Cannot checkout an empty cart!");
        }
        double[] measures = new double[3];
        for (int i = 0; i <this.items.size() ; i++) {
            measures[0] += this.items.get(i).getPrice();
        }
        measures[1]=measures[0] * 0.13;
        measures[2] = measures[0] + measures[1];

        return "\tRECEIPT\n\n"+
                "\tSubtotal: $" + measures[0] + "\n"+
                "\tTax: $"+measures[1] + "\n"+
                "\tTotal: $"+measures[2] + "\n";
    }

    public String toString(){
        String temp = "";
        for (int i = 0; i <this.items.size() ; i++) {
            temp+=this.items.get(i).toString();
            temp+="\n";
        }
        return temp;
    }
}
