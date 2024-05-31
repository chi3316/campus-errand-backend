import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class TestExpireTime {
    @Test
    public void test() {
        // 假设 JWT 过期时间为 7200000 毫秒
        long jwtExpireTime = 7200000L;

        // 获取当前时间的时间戳
        long currentTimeStamp = System.currentTimeMillis();

        // 计算过期时间戳
        long expireTimeStamp = currentTimeStamp + jwtExpireTime;

        // 返回给前端的过期时间戳
        System.out.println("expireTimeStamp: " + expireTimeStamp);
    }
}
