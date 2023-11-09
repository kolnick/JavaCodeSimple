package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * Test ByteBuf
 */
@Slf4j
public class TestByteBuf {

    @Test
    public void test() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        // 默认pooled池化实现，可配置为非池化实现：-Dio.netty.allocator.type=unpooled
        log.info("ByteBuf class: {}", buf.getClass());// io.netty.buffer.PooledUnsafeDirectByteBuf
        // LOGGER.info("buf={}", buf);
        ByteBuffs.log(buf);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            sb.append("a");
        }
        buf.writeBytes(sb.toString().getBytes());
        // LOGGER.info("buf={}", buf);
        ByteBuffs.log(buf);
    }

    @Test
    public void compositeByteBuf() {
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes("12345".getBytes());

        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf2.writeBytes("678910".getBytes());

        // 将两个小的ByteBuf合并为一个ByteBuf

        // 方式一：创建一个新的ByteBuf依次写入两个小的ByteBuf。进行了数据的内存复制操作，效率不高。
        // ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        // buf.writeBytes(buf1).writeBytes(buf2);
        // ByteBuffs.log(buf);

        // 方式二：CompositeByteBuf。内部用一个数组记录每一个小的ByteBuf相对于整体的偏移量，无内存复制操作。
        CompositeByteBuf compositeByteBuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        // 第一个参数传递true，表示自动增长写指针的位置，保证ByteBuf的正常写入。
        compositeByteBuf.addComponents(true, buf1, buf2);
        ByteBuffs.log(compositeByteBuf);
    }

    @Test
    public void copy() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes("abcdefghij".getBytes());
        ByteBuffs.log(buf);

        ByteBuf copy = buf.copy();
        ByteBuffs.log(copy);

        copy.setByte(0, 'x');
        ByteBuffs.log(buf);
    }

    /**
     * * 体现了零拷贝，截取原始ByteBuf所有内容，并且没有最大容量maxCapacity的限制，与原始ByteBuf是同一块内存，读写指针是独立的。
     */
    @Test
    public void duplicate() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes("abcdefghij".getBytes());
        ByteBuffs.log(buf);

        ByteBuf duplicate = buf.duplicate();
        ByteBuffs.log(duplicate);

        duplicate.setByte(0, 'x');
        ByteBuffs.log(buf);
    }

    @Test
    public void slice() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes("abcdefghij".getBytes());
        ByteBuffs.log(buf);
        /**
         *  index 从哪个索引开始切片
         *  length 切多长
         */
        ByteBuf slice1 = buf.slice(0, 5);
        ByteBuf slice2 = buf.slice(5, 5);
        ByteBuffs.log(slice1);
        ByteBuffs.log(slice2);

        // slice对切片后得到的ByteBuf的最大容量进行了限制，无法增量写入。
        // slice1.writeByte('x');

        // 切片后得到的slice1仍是原来的buf（同一块内存）
        slice1.setByte(0, 'b');

        // 引用计数加一：防止误对原有buf调用release释放内存导致切片slice的ByteBuf不可用。
        // slice1.retain();
        // slice2.retain();
        // 如果直接对原有buf进行release释放内存，则会导致切片slice无法进行使用。
        // buf.release();

        ByteBuffs.log(slice1);
        ByteBuffs.log(buf);

        // 建议slice后的切片各自release。而不是再原有buf上进行release。
        slice1.release();
        slice2.release();
    }
}
