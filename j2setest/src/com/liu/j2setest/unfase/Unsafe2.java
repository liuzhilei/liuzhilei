package com.liu.j2setest.unfase;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by liuzhilei3 on 2018/8/20.
 * cas原子级操作，通过内存偏移地址修改变量值
 *
 * unSafe就是根据内存的偏移地址来获取数据的
 */
public class Unsafe2 {
    public static void main(String[] args) throws Exception {
        //通过反射实例化unsafe
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        //实例化Player
        Player player = (Player) unsafe.allocateInstance(Player.class);
        player.setName("lzl");
        player.setAge(26);
        for (Field field : Player.class.getDeclaredFields()) {
            System.out.println(field.getName() + ": 对应的内存偏移地址" + unsafe.objectFieldOffset(field));
            /*
             * name:对应的内存偏移地址16
             * age:对应的内存偏移地址12
             */
        }

        //通过unsafe的cas修改内存偏移地址为12的值
        boolean b = unsafe.compareAndSwapInt(player, 12, 26, 27);
        System.out.println(b);
        System.out.println("age修改后的值：" + player.getAge());

        //修改偏移地址为12的值，但是不保证能被其他线程立马看到
        unsafe.putOrderedInt(player, 12, 28);
        System.out.println("age修改后的值为：" + player.getAge());

        //修改内存偏移地址为16的值，能保证被其他线程立马看到
        unsafe.putObjectVolatile(player, 16, "刘志磊");
        System.out.println("name修改后的值为：" + player.getName());

    }
}
