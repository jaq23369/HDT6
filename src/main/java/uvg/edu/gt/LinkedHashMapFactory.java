package uvg.edu.gt;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapFactory implements MapFactory {
    @Override
    public <K, V> Map<K, V> createMap() {
        return new LinkedHashMap<>();
    }
}