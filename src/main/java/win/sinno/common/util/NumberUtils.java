package win.sinno.common.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2017-05-26 10:22.
 */
public class NumberUtils {

    public static Integer nextInt(int startInclusive, int endInclusive, List<Integer> exclusives) {

        Validate.isTrue(endInclusive >= startInclusive, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(startInclusive >= 0, "Both range values must be non-negative.", new Object[0]);

        if (startInclusive == endInclusive) {
            return startInclusive;
        }

        if (CollectionUtils.isEmpty(exclusives)) {
            return RandomUtils.nextInt(startInclusive, endInclusive + 1);
        }
        if ((endInclusive - startInclusive - exclusives.size()) > 10) {
            int randomCount = 3;
            for (int i = 0; i < randomCount; i++) {
                int r = RandomUtils.nextInt(startInclusive, endInclusive + 1);
                if (!exclusives.contains(r)) {
                    return r;
                }
            }
        }

        for (int i = startInclusive; i <= endInclusive; i++) {
            if (!exclusives.contains(i)) {
                return i;
            }
        }

        return null;
    }
}
