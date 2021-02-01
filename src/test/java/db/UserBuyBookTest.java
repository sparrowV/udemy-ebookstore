package db;

import org.jsmart.zerocode.core.domain.Scenario;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ZeroCodeUnitRunner.class)
@TargetEnv("database.properties")
public class UserBuyBookTest {

    @Test
    @Scenario("db/user_buy_book_test.json")
    public void test_user_buy_book(){}
}
