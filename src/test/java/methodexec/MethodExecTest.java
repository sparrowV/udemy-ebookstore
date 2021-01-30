package methodexec;

import org.jsmart.zerocode.core.domain.Scenario;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ZeroCodeUnitRunner.class)
@TargetEnv("test.properties")
public class MethodExecTest {

    @Test
    @Scenario("methodexec/sample_method_exec_test.json")
    public void test_method_exec(){}
}
