package win.sinno.common.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.Test;

/**
 * win.sinno.common.util.CompartorTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2017/11/10
 */
public class CompartorTest {

  @Test
  public void testA() throws InterruptedException {
    CouponActDO c3 = CouponActDO.newInstance("3", System.currentTimeMillis(), false, false);
    Thread.sleep(11l);
    CouponActDO c4 = CouponActDO.newInstance("4", System.currentTimeMillis(), true, true);
    Thread.sleep(11l);
    CouponActDO c7 = CouponActDO.newInstance("7", System.currentTimeMillis(), true, false);
    Thread.sleep(11l);
    CouponActDO c5 = CouponActDO.newInstance("5", 5l, true, false);
    Thread.sleep(11l);
    CouponActDO c8 = CouponActDO.newInstance("8", System.currentTimeMillis(), true, true);
    Thread.sleep(11l);
    CouponActDO c9 = CouponActDO.newInstance("8", 1l, true, true);
    Thread.sleep(11l);
    CouponActDO c1 = CouponActDO.newInstance("1", System.currentTimeMillis(), false, true);
    Thread.sleep(11l);
    CouponActDO c2 = CouponActDO.newInstance("2", System.currentTimeMillis(), false, true);
    Thread.sleep(11l);
    CouponActDO c6 = CouponActDO.newInstance("6", System.currentTimeMillis(), false, false);
    Thread.sleep(11l);

    WaitCouponAct waitCouponAct = new WaitCouponAct();

    waitCouponAct.forceAdd(c2);
    waitCouponAct.forceAdd(c1);
    waitCouponAct.forceAdd(c3);
    waitCouponAct.forceAdd(c4);
    waitCouponAct.forceAdd(c5);
    waitCouponAct.forceAdd(c6);
    waitCouponAct.forceAdd(c7);
    waitCouponAct.forceAdd(c8);
    waitCouponAct.forceAdd(c8);
    waitCouponAct.forceAdd(c8);
    waitCouponAct.forceAdd(c8);
    waitCouponAct.forceAdd(c9);

    System.out.println(waitCouponAct.toString());

//    while (waitCouponAct.hasNext()) {
//      CouponActDO couponActDO = waitCouponAct.next();
//      waitCouponAct.remove(couponActDO);
//
//      System.out.println(couponActDO);
//    }
//    System.out.println("size:" + waitCouponAct.size());

  }

  public static enum CouponActTypeEnum {

    SMS_COUPON_ADMIN(1, "短信插队"),
    SMS_COUPON(2, "短信"),
    COUPON_ADMIN(3, "优惠券插队"),
    COUPON(4, "优惠券"),;

    CouponActTypeEnum(int code, String descr) {
      this.code = code;
      this.descr = descr;
    }

    private int code;
    private String descr;

    public int getCode() {
      return code;
    }

    public String getDescr() {
      return descr;
    }

    public static CouponActTypeEnum getType(boolean isSms, boolean isAdmin) {
      if (isSms) {

        if (isAdmin) {
          return SMS_COUPON_ADMIN;
        }

        return SMS_COUPON;
      }

      if (isAdmin) {
        return COUPON_ADMIN;
      }

      return COUPON;
    }
  }


  public static class CouponActDO {

    private String id;
    private boolean isAdmin;
    private Long startTime;
    private boolean isSendSms;
    // 类型
    private CouponActTypeEnum type;

    private CouponActDO(String id, Long startTime, boolean isAdmin, boolean isSendSms) {
      this.id = id;
      this.startTime = startTime;
      this.isAdmin = isAdmin;
      this.isSendSms = isSendSms;

      this.type = CouponActTypeEnum.getType(isSendSms, isAdmin);
    }


    public static CouponActDO newInstance(String id, Long startTime, boolean isAdmin,
        boolean isSendSms) {
      return new CouponActDO(id, startTime, isAdmin, isSendSms);
    }

    public String getId() {
      return id;
    }

    public boolean isAdmin() {
      return isAdmin;
    }

    public Long getStartTime() {
      return startTime;
    }

    public boolean isSendSms() {
      return isSendSms;
    }

    public CouponActTypeEnum getType() {
      return type;
    }

    @Override
    public boolean equals(Object o) {

      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      CouponActDO that = (CouponActDO) o;

      return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
      return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
      return "CouponActDO{" +
          "id='" + id + '\'' +
          ", isAdmin=" + isAdmin +
          ", startTime=" + startTime +
          ", isSendSms=" + isSendSms +
          ", type=" + type +
          '}';
    }
  }

  public static class CouponActComparator implements Comparator<CouponActDO> {

    @Override
    public int compare(CouponActDO o1, CouponActDO o2) {

      if (o1.getId().equals(o2.getId())) {
        return 0;
      }

      int typeDiffVal = o1.getType().getCode() - o2.getType().getCode();
      if (typeDiffVal != 0) {
        return typeDiffVal;
      }

      long startTimeDiff = (o1.getStartTime() - o2.getStartTime());
      if (startTimeDiff != 0 && startTimeDiff < 0) {
        return -1;
      }

      return 1;
    }

  }

  public static class WaitCouponAct {

    private Comparator comparator = new CouponActComparator();

    private SortedSet<CouponActDO> couponActDOSortedSet = new TreeSet<>(comparator);

    public synchronized void add(CouponActDO couponActDO) {
      couponActDOSortedSet.add(couponActDO);
    }

    public synchronized void forceAdd(CouponActDO couponActDO) {
      if (contains(couponActDO)) {
        remove(couponActDO);
      }
      add(couponActDO);
    }

    public synchronized boolean hasNext() {
      return couponActDOSortedSet.size() > 0;
    }


    public synchronized CouponActDO next() {
      if (couponActDOSortedSet.size() == 0) {
        return null;
      }

      return couponActDOSortedSet.first();
    }

    public synchronized boolean contains(CouponActDO couponActDO) {
      return couponActDOSortedSet.contains(couponActDO);
    }

    public synchronized void remove(CouponActDO couponActDO) {

      Iterator<CouponActDO> iterator = couponActDOSortedSet.iterator();

      while (iterator.hasNext()) {
        CouponActDO actDO = iterator.next();
        if (actDO != null && actDO.getId().equals(couponActDO.getId())) {
          iterator.remove();
        }
      }

    }

    public synchronized int size() {
      return couponActDOSortedSet.size();
    }

    /**
     * 是否可以执行
     */
    public boolean isCanRunNow(CouponActDO couponActDO) {
      // 待短信 优于 不带短信
      // 插队   优于 不插队
      // 按开始时间排序 以后加，可能还要加上插队时间

      if (!hasNext()) {
        return true;
      }

      CouponActDO fristCoupon = next();
      CouponActTypeEnum fristCouponType = fristCoupon.type;

      if (fristCouponType.getCode() < couponActDO.getType().getCode()) {
        return false;
      }

      return true;
    }

    @Override
    public String toString() {
      return "WaitCouponAct{" +
          "couponActDOSortedSet=" + couponActDOSortedSet +
          '}';
    }
  }

  @Test
  public void test2() {
    SortedSet<Integer> ss = new TreeSet<>();
    ss.add(2);
    ss.add(3);
    ss.add(1);

    ss.remove(1);
    System.out.println(ss.size());
  }
}
