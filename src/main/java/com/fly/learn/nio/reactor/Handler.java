package com.fly.learn.nio.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 单线程版本的Handler
 * @author: peijiepang
 * @date 2018/10/26
 * @Description:
 */
public class Handler implements Runnable{
    private final static Logger LOGGER = LoggerFactory.getLogger(Handler.class);
    protected SocketChannel socketChannel;
    protected SelectionKey selectionKey;
    protected ByteBuffer input = ByteBuffer.allocate(10);

    static final int READING = 0,SENDINT = 1,PROCESSING = 2;

    //初始状态
    int state = READING;
    String clientName = "";

    /**
     * handler作为SellectionKey的attachment。这样，handler就与SelectionKey也就是interestOps对应起来了
     * 反过来说，当interestOps发生、SelectionKey被选中时，就能从SelectionKey中取得handler
     * @param selector
     * @param socketChannel
     * @throws IOException
     */
    public Handler(Selector selector,SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);
        selectionKey = socketChannel.register(selector,0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    /**
     * 在Reactor的dispatch方法里面被调用，但是直接的方法调用，没有创建新线程
     */
    @Override
    public void run() {
        try{
            if(state == READING){
                read();
            }else if(state == SENDINT){
                send();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void read() throws IOException {
        int readCount = socketChannel.read(input);
        if(readCount > 0){
            readProcess(readCount);
        }
        state = SENDINT;
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    /**
     * 非IO操作（业务逻辑，实际应用中可能会非常耗时）：将Client发过来的信息（clientName）转成字符串形式
     * @param readCount
     */
    synchronized void readProcess(int readCount){
        StringBuilder sb = new StringBuilder();
        input.flip();
        byte[] subStringBytes = new byte[readCount];
        byte[] array = input.array();
        System.arraycopy(array,0,subStringBytes,0,readCount);
        sb.append(new String(subStringBytes));
        input.clear();
        clientName = sb.toString().trim();
    }

    protected void send() throws IOException {
        String str = "say hello to "+clientName;
        LOGGER.info(str);
        ByteBuffer output = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(output);
        selectionKey.interestOps(SelectionKey.OP_READ);
        state = READING;
    }

}
