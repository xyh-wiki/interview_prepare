/**
 * @Author: RookieX
 * @Date: 2021/9/14 8:03 下午
 * @Description: 懒汉式单例(延迟加载)线程不安全
 */
public class Singleton2 {

    //指向自己实例的私有静态引用
    private static Singleton2 singleton2;

    //私有的构造方法
    private Singleton2() {}

    //以自己实例为返回值的静态的公有方法, 静态工厂方法
    public static Singleton2 getSingleton2() {
        //被动创建, 在真正需要的时候才去创建
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
