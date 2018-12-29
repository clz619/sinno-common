package win.sinno.common.util;

import java.util.List;

/**
 * win.sinno.common.util.SplitBiz
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/10/29
 */
public interface SplitBiz<T> {

  List<T> split(T t);

  boolean isNeedSplit();

}
