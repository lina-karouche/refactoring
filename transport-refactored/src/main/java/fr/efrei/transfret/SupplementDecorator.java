package fr.efrei.transfret;

public abstract class SupplementDecorator implements CalculPrix {

    protected CalculPrix composant;

    protected SupplementDecorator(CalculPrix composant) {
        this.composant = composant;
    }
}
