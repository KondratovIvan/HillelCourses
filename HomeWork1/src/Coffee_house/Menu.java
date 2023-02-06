package Coffee_house;

abstract public class Menu {
    private int productAmount;
    private int bonusPoints;
}
abstract class Drinks extends  Menu{
    public abstract void ExtraIngredients();
    public abstract void Temerature();
}
abstract class Desserts extends  Menu{
    public abstract void Dietic();
    public abstract void LactoseFree();
}
abstract class Sandwiches extends  Menu{
    public abstract void Sauce();
    public abstract void Spices();
}
class Coffee extends  Drinks{
    @Override
    public void ExtraIngredients(){}
    @Override
    public void Temerature(){}

    public void Late(){}
    public  void Americano(){};
    public  void Espresso(){};
}
class Tea extends Drinks{
    @Override
    public void ExtraIngredients(){}
    @Override
    public void Temerature(){}

    public  void GreenTea(){};
    public  void BlackTea(){};
}
class Juice extends Drinks{
    @Override
    public void ExtraIngredients(){}
    @Override
    public void Temerature(){}

    public  void OrangeJuice(){};
    public  void AppleJuice(){};
}
class Cake extends Desserts{
    @Override
    public  void Dietic(){};
    @Override
    public  void LactoseFree(){};

    public  void Cheesecake(){};
    public  void Tiramisu(){};
    public  void HoneyCake(){};
}
class Cookie extends Desserts{
    @Override
    public  void Dietic(){};
    @Override
    public  void LactoseFree(){};

    public  void brownie(){};
    public  void GingerMan(){};
    public  void VegetarianCookie(){};
}
class UnspicySandwich extends Sandwiches{
    @Override
    public void Sauce(){}
    @Override
    public void Spices(){}

    public  void SeafoodSandwich(){};
    public  void VegetarianSandwich(){};
}
class SpicySandwich extends Sandwiches{
    @Override
    public void Sauce(){}
    @Override
    public void Spices(){}

    public  void DevilsKiss(){};
    public  void TheHottest(){};
}