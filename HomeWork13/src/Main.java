import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public Map<String, List<Product>> getGroupedList(List<Product> products){
        return products.stream()
                .collect(Collectors.groupingBy(pr->pr.getType()));
    }
    public double calculation(List<Product> products){
        double result=products.stream()
                .filter(pr->pr.getAddDate().getYear()==2023&&pr.getType()=="Book"&&pr.getPrice()<=75)
                .mapToDouble(pr ->pr.getPrice()).sum();

        return result;
    }
    public List<Product> getThreeLast(List<Product> products){
        return  products.stream()
                .sorted(Comparator.comparing(Product::getAddDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
    public Product getTheCheapest(List<Product> products) throws Exception{
        return products.stream()
                .filter(pr->pr.getType()=="Book")
                .min(Comparator.comparing(pr->pr.getPrice()))
                .orElseThrow(()->new Exception("Продукт категоріі:Book не знайдено"));

    }
    public List<Product> saleList(List<Product> products){

        return products.stream()
                .filter(pr->pr.getType()=="Book"&&pr.isSale()==true)
                .map(pr->{pr.setPrice(pr.getPrice()*0.9);
                return pr;
                })
                .collect(Collectors.toList());
    }
    public List<Product> sortList(List<Product> products){
        return products.stream()
                .filter(pr->pr.getType()=="Book"&&pr.getPrice()>250)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) throws Exception {
        List<Product> products=new ArrayList<>();

        products.add(new Product("Book",50,true, LocalDate.of(2023, 1, 20)));
        products.add(new Product("Magazine",200,false,LocalDate.of(2023, 1, 16)));
        products.add(new Product("Book",75,true,LocalDate.of(2023, 1, 19)));
        products.add(new Product("Newspaper",300,false,LocalDate.of(2021, 1, 18)));
        products.add(new Product("Book",350,true,LocalDate.of(2022, 1, 17)));
        products.add(new Product("Newspaper",400,false,LocalDate.of(2023, 1, 15)));

        Main m=new Main();

        System.out.println(m.sortList(products));
        //System.out.println(m.saleList(products));
        System.out.println(m.getTheCheapest(products));
        System.out.println(m.getThreeLast(products));
        System.out.println(m.calculation(products));
        System.out.println(m.getGroupedList(products));
    }
}
