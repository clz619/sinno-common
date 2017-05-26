package win.sinno.common.util;

import java.util.Random;

/**
 * random
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017-05-25 14:30.
 */
public class RandomUtil {

    public static int random(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
}
