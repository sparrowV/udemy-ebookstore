package performance.load;


import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;
import org.jsmart.zerocode.core.runner.parallel.ZeroCodeMultiLoadRunner;
import org.junit.runner.RunWith;
import rest.UserEndpointsTest;

@LoadWith("load.properties")
@RunWith(ZeroCodeMultiLoadRunner.class)
@TestMappings({
        @TestMapping(testClass = UserEndpointsTest.class,testMethod = "test_user_creation"),
        @TestMapping(testClass = UserEndpointsTest.class,testMethod = "test_user_retrieve")
})
public class MultiLoadTest {
}
