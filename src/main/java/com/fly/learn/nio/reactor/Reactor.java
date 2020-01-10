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
单线程的实现
Server端用一个Selector利用一个线程（在main方法里面start）来响应所有请求
1.当ACCEPT事件就绪，Acceptor被选中，执行它的run方法：创建一个Handler（例如为handlerA），并将Handler的interestOps初始为READ
2.当READ事件就绪，handlerA被选中，执行它的run方法：它根据自身的当前状态，来执行读或写操作
因此，每一个Client连接过来，Server就创建一个Handler，但都所有操作都在一个线程里面
Selection Key   Channel                 Handler     Interested Operation
------------------------------------------------------------------------
SelectionKey 0  ServerSocketChannel     Acceptor    Accept
SelectionKey 1  SocketChannel 1         Handler 1   Read and Write
SelectionKey 2  SocketChannel 2         Handler 2   Read and Write
SelectionKey 3  SocketChannel 3         Handler 3   Read and Write
如果采用多个selector，那就是所谓的“Multiple Reactor Threads”，大体思路如下：
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
     * /从SelectionKey中取出Handler并执行Handler的run方法，没有创建新线程
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