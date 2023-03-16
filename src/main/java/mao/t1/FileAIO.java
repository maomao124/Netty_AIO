package mao.t1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mao.utils.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Project name(项目名称)：Netty_AIO
 * Package(包名): mao.t1
 * Class(类名): FileAIO
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/16
 * Time(创建时间)： 21:24
 * Version(版本): 1.0
 * Description(描述)： 文件AIO
 */

@Slf4j
public class FileAIO
{
    @SneakyThrows
    public static void main(String[] args)
    {
        try
        {
            log.debug("开始读取...");
            AsynchronousFileChannel asynchronousFileChannel =
                    AsynchronousFileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(16);
            asynchronousFileChannel.read(buffer, 0, null, new CompletionHandler<Integer, ByteBuffer>()
            {
                @Override
                public void completed(Integer result, ByteBuffer attachment)
                {
                    log.debug("读取完成：" + result);
                    buffer.flip();
                    ByteBufferUtil.debugAll(buffer);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment)
                {
                    log.warn("读取失败：", exc);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Thread.sleep(1000);
    }
}
