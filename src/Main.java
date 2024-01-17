import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Supplier<Double> prezzo = () -> {
            Random random = new Random();
            return random.nextDouble(50,200);
        };

        Predicate<Product> isLessThenOnehundred = prod -> prod.getPrice() > 100;

        List<Product> products = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        products.add(new Product((long)235664,"Il viaggio di Peppa","Books",prezzo.get()));
        products.add(new Product((long)234324,"Mari e monti","Books",prezzo.get()));
        products.add(new Product((long)234356,"Giallo di sera","Books",prezzo.get()));
        products.add(new Product((long) 234553,"Lavatrice","Electronic", prezzo.get()));
        products.add(new Product((long)23455,"Carrozzina","Baby",prezzo.get()));
        products.add(new Product((long)23D,"Giocattoli","Baby",prezzo.get()));


        products.forEach(prod -> System.out.println(prod.getPrice()));
        products.stream().filter(isLessThenOnehundred).filter(prod -> "Books".equals(prod.getCategory())).forEach(prod -> System.out.println(prod.getName() + " Prezzo: " + prod.getPrice()));



        LocalDate today = LocalDate.now();

        Supplier<Integer> ritiro = () ->{
            Random random = new Random();
           return random.nextInt(1,5);
        };

        LocalDate delivery = today.plusDays(5);

        orders.add(new Order((long)2344,"Active", today,delivery,products, new Customer((long)2934,"Giuseppe",2)));
        orders.add(new Order((long)2344,"Active", today,delivery,products, new Customer((long)2935,"Federico",1)));
        orders.add(new Order((long)2344,"Active", today,delivery,products, new Customer((long)3454,"Daniele",2)));

        List<Product> babyOrders = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .filter(prod -> "Baby".equals(prod.getCategory()))
                .toList();




    }
}