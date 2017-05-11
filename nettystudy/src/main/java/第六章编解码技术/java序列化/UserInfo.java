package 第六章编解码技术.java序列化;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by liuzhilei on 2017/5/11.
 * <p/>
 * java序列化缺点：
 * 1.不支持跨语言
 * 2.序列化后的码流太大
 * 3.序列化性能太低
 * <p/>
 * java序列化代码 pojo对象类
 */
public class UserInfo implements Serializable {

    /**
     * 默认的序列号，jdk默认的序列化
     */
    private static final long serialVersionUID = 1L;

    private String userName;

    private int userID;

    public UserInfo buildUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserInfo buildUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * 使用基于byteBuffer的通用二进制编解码技术对userInfo对象进行编码，编码
     * 结果仍然是byte数组，可以和传统jdk序列化后的码流大小进行对比
     *
     * @return
     */
    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = this.userName.getBytes();
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.putInt(this.userID);
        //写模式切换为读模式
        buffer.flip();

        bytes = null;
        byte[] bytes1 = new byte[buffer.remaining()];
        buffer.get(bytes1);
        return bytes1;
    }

    public byte[] codeC(ByteBuffer buffer) {
        buffer.clear();
        byte[] bytes = this.userName.getBytes();
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.putInt(this.userID);
        //写模式切换为读模式
        buffer.flip();

        bytes = null;
        byte[] bytes1 = new byte[buffer.remaining()];
        buffer.get(bytes1);
        return bytes1;
    }

}
