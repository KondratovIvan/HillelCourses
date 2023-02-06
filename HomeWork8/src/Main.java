import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Cafe cafe1=new Cafe("1","11",11);
        Cafe cafe2=new Cafe("2","22",22);
        Cafe cafe3=new Cafe("2","33",22);
        Cafe cafe4=new Cafe("Авангард","00",44);
        Cafe cafe5=new Cafe("premium","55",22);

        List<Cafe> cafeList=new ArrayList<>();

        cafeList.add(cafe1);
        cafeList.add(cafe2);
        cafeList.add(cafe3);
        cafeList.add(cafe4);
        cafeList.add(cafe5);
        System.out.println(findFirst(cafeList));

    }
    public static Cafe findFirst(List<Cafe> cafeList) throws Exception {
        try{
            checkIsEmpty(cafeList);
            checkIsNameEquals(cafeList);
            checkIsAddressEquals(cafeList);
            checkIsDrinksEquals(cafeList);
            checkNameLettersAmount(cafeList);
            checkIsCafePremium(cafeList);
            checkFirstNameLetter(cafeList);
            checkLastAddressLetter(cafeList);

            Cafe returnCafe=(Cafe) cafeList.get(0);
            System.out.println("Collection is not empty");
            return returnCafe;
        }
        catch (Exception e){
            throw new Exception("Collection is empty");

        }
    }
    public static int listLength(List<Cafe> cafeList){
        int count=0;
        for(Cafe cafe: cafeList){
            count++;
        }
        return count;
    }
    public static boolean checkIsEmpty(List<Cafe> cafeList){
        Iterator<Cafe> itr=cafeList.iterator();
        boolean check=itr.hasNext();
        return check;
    }
    public static void checkIsNameEquals(List<Cafe> cafeList){
        Set<String> tracking = new HashSet<>();
        for (Cafe cf: cafeList){
            if(tracking.add(cf.getName())){
                System.out.println("Ім'я елементу з індексом " +cafeList.indexOf(cf)+" ще немає в базі!");
            }
            else {
                System.out.println("Ім'я елементу з індексом "+cafeList.indexOf(cf)+" вже є в базі!");
            }
        }
        System.out.println("=============================================");
    }
    public static void checkIsAddressEquals(List<Cafe> cafeList){
        Set<String> tracking = new HashSet<>();
        for (Cafe cf: cafeList){
            if(tracking.add(cf.getAddress())){
                System.out.println("Адреса елементу з індексом " +cafeList.indexOf(cf)+" ще немає в базі!");
            }
            else {
                System.out.println("Адреса елементу з індексом "+cafeList.indexOf(cf)+" вже є в базі!");
            }
        }
        System.out.println("=============================================");
    }
    public static void checkIsDrinksEquals(List<Cafe> cafeList){
        Set<Integer> tracking = new HashSet<>();
        for (Cafe cf: cafeList){
            if(tracking.add(cf.getDrinks())){
                System.out.println("Напої елементу з індексом " +cafeList.indexOf(cf)+" ще немає в базі!");
            }
            else {
                System.out.println("Напої елементу з індексом "+cafeList.indexOf(cf)+" вже є в базі!");
            }
        }
        System.out.println("=============================================");
    }
    public static void checkIsCafePremium(List<Cafe> cafeList){
        for (Cafe cf: cafeList){
            if(cf.getName()=="premium"){
                System.out.println("Кафе за адресою "+cf.getAddress()+" преміальне");
            }
        }

    }
    public static void checkFirstNameLetter(List<Cafe> cafeList){
        for (Cafe cf: cafeList){
            char[] nameWords=cf.getName().toCharArray();
            if(nameWords[0]=='А'){
                System.out.println("Кафе  "+cf.getName()+" починається на літеру 'А'");
            }
        }
    }
    public static void checkLastAddressLetter(List<Cafe> cafeList){
        for (Cafe cf: cafeList){
            char[] addressWords=cf.getAddress().toCharArray();
            if(addressWords[addressWords.length-1]=='0'){
                System.out.println("Адресa "+ cf.getAddress()+" кафе "+cf.getName()+" закінчується на '0'");
            }
        }
    }
    public static void checkNameLettersAmount(List<Cafe> cafeList){
        for (Cafe cf: cafeList){
            char[] nameWords=cf.getName().toCharArray();
            System.out.println("Кафе "+cf.getName()+" має в своїй назві "+nameWords.length+" літер");
        }
        System.out.println("=============================================");
    }

}