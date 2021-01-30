package methodexec;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.HashMap;
import java.util.Map;

public class MethodExec {

    @Inject
    @Named("my_test_property")
    private String testProp;

    public Map<String,Integer> square(Map<String,Integer> input){
        System.out.println(testProp);
        Map<String,Integer> wrapper = new HashMap<>();
        int res =  input.get("a")* input.get("a");
        wrapper.put("abcd",res);

        return wrapper;
    }
}
