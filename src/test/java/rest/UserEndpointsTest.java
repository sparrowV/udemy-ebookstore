package rest;

import org.jsmart.zerocode.core.domain.Scenario;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ZeroCodeUnitRunner.class)
@TargetEnv("api.properties")
public class UserEndpointsTest {

    @Test
    @Scenario("rest/user_creation_test.json")
    public void test_user_creation(){

    }


    @Test
    @Scenario("rest/user_retrieve_test.json")
    public void test_user_retrieve(){

    }
}
