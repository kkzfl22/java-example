package com.liujun.javacase.example.reference.weakReference;

import com.liujun.javacase.example.reference.weakReference.entity.Coupon;
import com.liujun.javacase.example.reference.weakReference.entity.UserInfo;

import java.lang.ref.WeakReference;
import java.util.*;

/**
 * 自动清理关联的数据
 *
 * @author liujun
 * @version 0.0.1
 */
public class AutoCleanLinkedData {

  /** 优惠券信息 */
  private Map<String, Coupon> couponMap = new HashMap<>();

  /** 以弱引用的方法来保存关联 */
  private WeakHashMap<Coupon, List<WeakReference<UserInfo>>> weakHashMap = new WeakHashMap<>();

  /** 用户对象 */
  private Map<String, UserInfo> userInfoMap = new HashMap<>();

  private static final AutoCleanLinkedData INSTANCE = new AutoCleanLinkedData();

  public static final AutoCleanLinkedData getInstance() {
    return INSTANCE;
  }

  public void put(Coupon coupon, List<UserInfo> userInfo) {
    couponMap.put(coupon.getId(), coupon);

    List<WeakReference<UserInfo>> data = new ArrayList<>();
    for (UserInfo user : userInfo) {
      // 添加弱引用对象
      data.add(new WeakReference<>(user));
      userInfoMap.put(user.getUserId(), user);
    }
    weakHashMap.put(coupon, data);
  }

  /**
   * 现在用户被注销
   *
   * @param userInfo
   */
  public void userLogout(UserInfo userInfo) {
    UserInfo user = userInfoMap.remove(userInfo.getUserId());
    System.out.println("移除用户:" + user);
  }

  public void print() {
    for (Map.Entry<Coupon, List<WeakReference<UserInfo>>> itemData : weakHashMap.entrySet()) {
      System.out.println(itemData.getKey() + ":大小:" + itemData.getValue().size());
      for (WeakReference<UserInfo> wealRef : itemData.getValue()) {
        System.out.println(wealRef.get());
      }
      System.out.println("-----------------");
    }
    System.out.println("============================================");
  }

  public  void clean() {
    Coupon data = Coupon.getRandomInstance();

    UserInfo userInfo = UserInfo.getRandomInstance();

    List<UserInfo> dataList = new ArrayList<>();

    dataList.add(UserInfo.getRandomInstance());
    dataList.add(userInfo);
    dataList.add(UserInfo.getRandomInstance());
    dataList.add(UserInfo.getRandomInstance());

    AutoCleanLinkedData.getInstance().put(data, dataList);

    // 对用户2退出进行
    AutoCleanLinkedData.getInstance().print();

    AutoCleanLinkedData.getInstance().userLogout(userInfo);
  }
}
