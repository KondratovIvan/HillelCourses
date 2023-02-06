class ArraySizeException extends Exception{
    public ArraySizeException(String message) {
        super(message);
    }
}
class ArrayDataException extends Exception{
    public ArrayDataException(String message) {
        super(message);
    }
}
public class ArrayValueCalculator {
    static String stringArray[][]={{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","15","16"}};
    static int intArray[][]=new int[4][4];

    public void doCalc(String stringArray[][]) throws NumberFormatException,ArraySizeException,ArrayDataException {
        int sum = 0;
        if (stringArray.length!=4||stringArray[0].length!=4){
            throw new ArraySizeException("Масив невірного розміру");
        }
        for (int i=0;i< stringArray.length;i++){
            for (int j=0;j< stringArray.length;j++){
                try {
                    intArray[i][j] = Integer.parseInt(stringArray[i][j]);
                }
                catch (NumberFormatException e){
                    throw new ArrayDataException("Помилка, в комірці ["+i+"]["+j+"] лежать невірні дані");
                }
                System.out.print(intArray[i][j]+" ");
                sum+=intArray[i][j];
            }
        }
        System.out.println("Сума всіх елементів масиву = "+sum);
    }

    public static void main(String[] args) throws NumberFormatException,ArraySizeException, ArrayDataException {
        ArrayValueCalculator avc= new ArrayValueCalculator();
        avc.doCalc(stringArray);
    }
}
