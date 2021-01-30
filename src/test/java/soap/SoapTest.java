package soap;

import org.jsmart.zerocode.core.domain.Scenario;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ZeroCodeUnitRunner.class)
public class SoapTest {

    @Test
    @Scenario("soap/soap_test.json")
    public void soap_test(){}
}
