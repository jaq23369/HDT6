package uvg.edu.gt;

public class MapFactorySelector {

    public static MapFactory getFactory(String mapType) {
        switch (mapType.toLowerCase()) {
            case "hashmap":
                return new HashMapFactory();
            case "treemap":
                return new TreeMapFactory();
            case "linkedhashmap":
                return new LinkedHashMapFactory();
            default:
                throw new IllegalArgumentException("Unknown Map Type");
        }
    }
}

