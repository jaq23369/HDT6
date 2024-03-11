package uvg.edu.gt;
/**
 * Selecciona una fábrica de mapas según el tipo especificado.
 */
public class MapFactorySelector {

    /**
     * Obtiene una fábrica de mapas según el tipo especificado.
     *
     * @param mapType Tipo de mapa ("hashmap", "treemap" o "linkedhashmap")
     * @return Fábrica de mapas correspondiente al tipo especificado
     * @throws IllegalArgumentException si el tipo de mapa es desconocido
     */
    public static MapFactory getFactory(String mapType) {
        switch (mapType.toLowerCase()) {
            case "hashmap":
                return new HashMapFactory();
            case "treemap":
                return new TreeMapFactory();
            case "linkedhashmap":
                return new LinkedHashMapFactory();
            default:
                throw new IllegalArgumentException("Tipo de mapa desconocido");
        }
    }
}

