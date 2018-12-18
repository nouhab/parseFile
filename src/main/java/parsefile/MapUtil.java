package parsefile;
import java.util.Map;
/**
 * 
 * @author nouha
 * this class is an util to a map
 */
public class MapUtil {

	public static <K> Integer incrementValue(Map<K, Integer> map, K key) {
		// get value of the specified key
		Integer count = map.get(key);

		// if the map contains no mapping for the key, then
		// map the key with value of 1
		if (count == null) {
			map.put(key, 1);
			count = 1;
		}
		// else increment the found value by 1
		else {
			
			map.put(key, ++count);
		}

		return count;

	}
	
	
	public static  <K> K getKeyWithMaxValue(Map<K, Integer> map){
		return map.entrySet().stream()
		.max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		
	}
	
}
