package performance.load;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.runner.parallel.ZeroCodeLoadRunner;
import org.junit.runner.RunWith;
import rest.UserEndpointsTest;

@LoadWith("load.properties")
@RunWith(ZeroCodeLoadRunner.class)
@TestMapping(testClass = UserEndpointsTest.class,testMethod = "test_user_creation")
public class LoadTest {
}
