package Coffee_house;

public abstract class Sales {
    public abstract void SaleSuccessCount();
}
class CombinationsSales extends Sales{
    @Override
    public void SaleSuccessCount(){};

    public void LateTiramisu(){};
    public void GreenTeaBrownie(){};
    public void AppleJuiceSeafoodSandwich(){};
    public void DevilsKissHoneyCake(){};
    public void VegetarianCookieVegetarianSandwich(){};
}
class BonusPointsSales extends Sales{
    @Override
    public void SaleSuccessCount(){};

    private int visitorBonusPoints;

    public void BonusCoffee(){};
    public void BonusSandwich(){};
    public void BonusCookie(){};
}