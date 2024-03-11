package uvg.edu.gt;

import java.util.Map;

/**
 * Interfaz para la fabricación de mapas.
 */
public interface MapFactory {
    
    /**
     * Crea y devuelve un nuevo mapa.
     *
     * @param <K> Tipo de la clave
     * @param <V> Tipo del valor
     * @return Nuevo mapa creado
     */
    <K, V> Map<K, V> createMap();
}
