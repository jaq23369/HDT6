package uvg.edu.gt;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapFactory implements MapFactory {
    @Override
    public <K, V> Map<K, V> createMap() {
        return new TreeMap<>();
    }
}
