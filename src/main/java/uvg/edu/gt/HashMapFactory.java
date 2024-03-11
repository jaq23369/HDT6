package uvg.edu.gt;

import java.util.HashMap;
import java.util.Map;

/**
 * Fábrica que crea instancias de HashMap.
 */
public class HashMapFactory implements MapFactory {

    /**
     * Crea y devuelve una nueva instancia de HashMap.
     *
     * @param <K> Tipo de la clave
     * @param <V> Tipo del valor
     * @return Nueva instancia de HashMap
     */
    @Override
    public <K, V> Map<K, V> createMap() {
        return new HashMap<K, V>();
    }
}