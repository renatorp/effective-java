package patterns.classHierarchyBuilder;

public class Main {

    public static void main(String[] args) {
        Pizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();

        Pizza calzone = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM).sauceInside().build();

        System.out.println(pizza.toppings);
        System.out.println(calzone.toppings);

    }
}
