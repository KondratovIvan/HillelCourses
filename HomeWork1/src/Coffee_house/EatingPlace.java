package Coffee_house;

public abstract class EatingPlace {
    private int napkinsAmount;
    private int sugarAmount;
    private int juiceStrawAmount;
    public abstract void GiveNapkins();
    public abstract void GiveSugar();
    public abstract void GiveJuiceStraw();
}
class EatingInCafe extends EatingPlace{
    @Override
    public void GiveNapkins(){}
    @Override
    public void GiveSugar(){}
    @Override
    public void GiveJuiceStraw(){}

    public void NoPack(){};
}
class EatingOutside extends EatingPlace{
    @Override
    public void GiveNapkins(){}
    @Override
    public void GiveSugar(){}
    @Override
    public void GiveJuiceStraw(){}

    public void Packing(){};
}