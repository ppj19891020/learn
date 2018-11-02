package com.fly.learn.nio.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/*

���̵߳�ʵ��
Server����һ��Selector����һ���̣߳���main��������start������Ӧ��������
1.��ACCEPT�¼�������Acceptor��ѡ�У�ִ������run����������һ��Handler������ΪhandlerA��������Handler��interestOps��ʼΪREAD
2.��READ�¼�������handlerA��ѡ�У�ִ������run����������������ĵ�ǰ״̬����ִ�ж���д����
��ˣ�ÿһ��Client���ӹ�����Server�ʹ���һ��Handler���������в�������һ���߳�����

Selection Key   Channel                 Handler     Interested Operation
------------------------------------------------------------------------
SelectionKey 0  ServerSocketChannel     Acceptor    Accept
SelectionKey 1  SocketChannel 1         Handler 1   Read and Write
SelectionKey 2  SocketChannel 2         Handler 2   Read and Write
SelectionKey 3  SocketChannel 3         Handler 3   Read and Write

������ö��selector���Ǿ�����ν�ġ�Multiple Reactor Threads��������˼·���£�

Selector[] selectors; // also create threads
int next = 0;
class Acceptor { // ...
     public synchronized void run() { ...
         Socket connection = serverSocket.accept();
         if (connection != null)
             new Handler(selectors[next], connection);
         if (++next == selectors.length) next = 0;
     }
}
 */
public class Reactor implements Runnable{

    private final static Logger LOGGER = LoggerFactory.getLogger(Reactor.class);

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private boolean isWithThreadPool;

    public Reactor(int port,boolean isWithThreadPool) throws IOException {
        this.isWithThreadPool = isWithThreadPool;
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    class Acceptor implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if(null != socketChannel){
                    if(isWithThreadPool){
                        new HandlerWithThreadPool(selector,socketChannel);
                    }else{
                        new Handler(selector,socketChannel);
                    }
                }
            }catch (Exception ex){

            }
        }
    }


    @Override
    public void run() {
        LOGGER.info("server listerning to port:{}",serverSocketChannel.socket().getLocalPort());
        try{
            while(!Thread.interrupted()){
                int readSelectionKeyCount = selector.select();
                if(0 ==  readSelectionKeyCount){
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()){
                    dispatch(it.next());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * /��SelectionKey��ȡ��Handler��ִ��Handler��run������û�д������߳�
     * @param key
     */
    void dispatch(SelectionKey key){
        Runnable r = (Runnable) (key.attachment());
        if(null !=  r){
            r.run();
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 9999;
        boolean withThreadPool = false;
        Reactor reactor = new Reactor(port,withThreadPool);
        new Thread(reactor).start();
    }

}
