package Coffee_house;

public abstract class Payment {
    private double drinksEarning;
    private double dessertsEarning;
    private double sandwichesEarning;
    private double fullEarning;
}
class CashPay extends Payment{
    public void MakeCashPay(){};
    public void GetChange(){};
}
class CardPay extends Payment{
    public void MakeCardPay(){};
    public void Cashback(){};
}
class BonusPay extends Payment{
    private int BonusAmount;
    public void MakeBonusPay(){};
}