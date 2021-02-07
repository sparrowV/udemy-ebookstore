package kafka;

import org.jsmart.zerocode.core.domain.Scenario;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ZeroCodeUnitRunner.class)
@TargetEnv("kafka.properties")
public class KafkaTest {

    @Test
    @Scenario("kafka/producer_test.json")
    public void test_producer() {
    }
}
