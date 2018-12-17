package parsefile;

import java.util.HashMap;

import org.junit.Test;
import org.junit.gen5.api.Assertions;

public class MapUtilTest {

	    @Test
	    public void incrementValue() {


			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("1", null);
			map.put("2", 1);
			map.put("3", -1);
			
	        MapUtil.incrementValue(map,"1");
	        MapUtil.incrementValue(map,"2");	        
	        MapUtil.incrementValue(map,"3");


	        Assertions.assertEquals(map.get("1"), 1);
	        Assertions.assertEquals(map.get("2"), 2);
	        Assertions.assertEquals(map.get("3"), 0);

	             

	    }

}
