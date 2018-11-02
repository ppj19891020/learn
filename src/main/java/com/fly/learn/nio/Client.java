package com.fly.learn.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * nio client
 * @author: peijiepang
 * @date 2018/11/1
 * @Description:
 */
public class Client {
    private final static Logger LOGGER = LoggerFactory.getLogger(Client.class);
    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    public void start() throws IOException {
        // ��socketͨ��
        SocketChannel sc = SocketChannel.open();
        //����Ϊ������
        sc.configureBlocking(false);
        //���ӷ�������ַ�Ͷ˿�
        sc.connect(new InetSocketAddress("localhost", 9999));
        //��ѡ����
        Selector selector = Selector.open();
        //ע�����ӷ�����socket�Ķ���
        sc.register(selector, SelectionKey.OP_CONNECT);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            //ѡ��һ���������Ӧ��ͨ����Ϊ I/O ����׼��������
            //�˷���ִ�д�������ģʽ��ѡ�������
            selector.select();
            //���ش�ѡ��������ѡ�������
            Set<SelectionKey> keys = selector.selectedKeys();
            LOGGER.info("keys=" + keys.size());
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                // �жϴ�ͨ�����Ƿ����ڽ������Ӳ�����
                if (key.isConnectable()) {
                    sc.finishConnect();
                    sc.register(selector, SelectionKey.OP_WRITE);
                    LOGGER.info("server connected...");
                    break;
                } else if (key.isWritable()) { //д����
                    LOGGER.info("please input message:");
                    String message = scanner.nextLine();
                    //ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    //������������־��λ,��Ϊ������put�����ݱ�־���ı�Ҫ����ж�ȡ���ݷ��������,��Ҫ��λ
                    writeBuffer.flip();
                    sc.write(writeBuffer);

                    //ע��д����,ÿ��chanelֻ��ע��һ�����������ע���һ����Ч
                    //�����Բ�ֹһ���¼�����Ȥ����ô�����á�λ�򡱲�������������������
                    //int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                    //ʹ��interest����
                    sc.register(selector, SelectionKey.OP_READ);
                    sc.register(selector, SelectionKey.OP_WRITE);
                    sc.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()){//��ȡ����
                    LOGGER.info("receive message:");
                    SocketChannel client = (SocketChannel) key.channel();
                    //������������Ա��´ζ�ȡ
                    readBuffer.clear();
                    int num = client.read(readBuffer);
                    LOGGER.info(new String(readBuffer.array(),0, num));
                    //ע�����������һ�ζ�ȡ
                    sc.register(selector, SelectionKey.OP_WRITE);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Client().start();
    }
}
