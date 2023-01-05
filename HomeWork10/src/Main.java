import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class Main {
    static Map<Product, Company> products = new HashMap<>();
    public static void main(String[] args) throws Exception {

        Product product1 = new Product("Iphone");
        Product product2 = new Product("Samsung A2");
        Product product3=new Product(null);

        Company company1 = new Company("Apple");
        Company company2 = new Company("Samsung");

        Company company3 = new Company("Tesla");

        products.put(product1, company1);
        products.put(product2, company2);

        System.out.println(checkCompany(company1));
        System.out.println(checkCompany(company3));
        System.out.println(checkCompany(null));

        System.out.println(addProduct("Redmi 3S"));

        System.out.println(getCompany(product1));

        System.out.println(getProductName(product3));

        System.out.println(remakeCompanyName(company3));
    }

    public static boolean checkCompany(Company company)  {
        Optional<Company> checkComp=Optional.ofNullable(company);
        if(checkComp.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

    public static Product addProduct(String name) throws Exception {
        Optional<String> checkName = Optional.ofNullable(name);
        String nullException = checkName.orElse("Ви передали null!");
        Product product= new Product(Product.message(nullException));
        if (isExist(product)) {
            throw new Exception("Цей товар вже існує!");
        }
        else {
            return product;
        }
    }

    public static boolean isExist(Product product) {
        return products.containsKey(product);
    }
    public static Company getCompany(Product product){
        Optional<Product> checkProduct=Optional.ofNullable(product);
        if(checkProduct.isPresent()) {
            return products.get(product);
        }
        else {
            return null;
        }
    }
    public static String getProductName(Product product){
        Optional<String> getPrName=Optional.ofNullable(product.name);
        return getPrName.orElse("Цей продукт не має ім'я");
    }
    public static Optional<String> remakeCompanyName(Company company){
        Optional<String> checkName=Optional.ofNullable(company.name);
        return checkName.map(String::toLowerCase);
    }

}