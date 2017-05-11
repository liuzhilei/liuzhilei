package 第六章编解码技术.java序列化;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by liuzhilei on 2017/5/11.
 * 编码测试类
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("liuzhilei");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("jdk序列化后的长度是：" + b.length);
        bos.close();
        System.out.println("---------------------------------");
        System.out.println("基于byteBuffer的二进制编码技术的序列化后长度是：" + info.codeC().length);

    }
}
