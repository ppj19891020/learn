package com.fly.learn.nio.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ���̰߳汾��Handler
 * ˼·���ǰѺ�ʱ�Ĳ�������IO�������ŵ������߳������ܣ�
 * ʹ��Handlerֻרע��Channel֮���IO������
 * Handler���ٵش�Channel�ж���д������ʹChannel��ʱ�ء��������Ӧ��������
 * ��ʱ�Ĳ�����ɺ󣬲���һ���¼����ı�state�����١�֪ͨ������Handler��ѯ���״̬�Ƿ��иı䣩
 * Handlerִ��Channel�Ķ�д����
 * @author: peijiepang
 * @date 2018/11/1
 * @Description:
 */
public class HandlerWithThreadPool extends Handler{

    private final static Logger LOGGER = LoggerFactory.getLogger(HandlerWithThreadPool.class);
    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    public HandlerWithThreadPool(Selector selector, SocketChannel socketChannel) throws IOException {
        super(selector, socketChannel);
    }

    /**
     * Handler��SocketChannel�ж������ݺ󣬰ѡ����ݵĴ�����������ӵ��̳߳�����ִ��
     * @throws IOException
     */
    @Override
    protected void read() throws IOException {
        int readCount = socketChannel.read(input);
        if(readCount > 0){
            state = PROCESSING;
            //execute�Ƿ������ģ�����Ҫ����һ��state��PROCESSING������ʾ�����ڴ����У�Handler������ִ��send����
            pool.execute(new Processer(readCount));
        }
        //��ʱ����Ȼ������OP_WRITE������һ�α�Handler��ѡ��ʱ����ִ��send()��������Ϊstate=PROCESSING
        //���߿��԰�������÷ŵ�Processer���棬��process��ɺ�����ΪOP_WRITE
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    class Processer implements Runnable{
        int readCount;

        public Processer(int readCount) {
            this.readCount = readCount;
        }

        @Override
        public void run() {
            processAndHandOff(readCount);
        }
    }

    synchronized void processAndHandOff(int readCount){
        readProcess(readCount);
        //Read processing done. Now the server is ready to send a message to the client.
        state = SENDINT;
    }
}
