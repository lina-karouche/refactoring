package fr.efrei.transfret;

import java.util.List;

public class ColisUtils {

    public static boolean contient(List<Colis> colis, TypeMarchandise type) {
        for (Colis c : colis) {
            if (c.getType() == type) return true;
        }
        return false;
    }
}
