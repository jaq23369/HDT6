package uvg.edu.gt;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Fábrica que crea instancias de LinkedHashMap.
 */
public class LinkedHashMapFactory implements MapFactory {

    /**
     * Crea y devuelve una nueva instancia de LinkedHashMap.
     *
     * @param <K> Tipo de la clave
     * @param <V> Tipo del valor
     * @return Nueva instancia de LinkedHashMap
     */
    @Override
    public <K, V> Map<K, V> createMap() {
        return new LinkedHashMap<>();
    }
}