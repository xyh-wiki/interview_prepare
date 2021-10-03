/**
 * @Author: RookieX
 * @Date: 2021/9/14 8:00 下午
 * @Description: 饿汉式单例(立即加载)线程安全
 * 单例模式使用场景:
 *  1. 整个程序的运行中只允许有一个类的实例
 *  2. 需要频繁实例化然后销毁对象
 *  3. 创建对象时消耗过多或者耗资源过多, 但是又经常用到的对象
 */
public class Singleton1 {

    // 指向自己实例的私有静态引用, 主动创建
    private static Singleton1 singleton1 = new Singleton1();

    //私有的构造方法
    private Singleton1(){}

    //以自己实例为返回值的静态的共有方法, 静态工厂方法
    public static Singleton1 getSingleton1() {
        return singleton1;
    }
}
