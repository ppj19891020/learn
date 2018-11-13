package com.fly.learn.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 独占锁自定义实现
 * @author: peijiepang
 * @date 2018/11/10
 * @Description:
 */
public class ExclusiveLock {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExclusiveLock.class);

    /**
     * 独占锁实现
     */
    private final class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            //当状态为0的时候获取锁，CAS操作成功，则state状态为1，
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
         return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //释放锁，将同步状态置为0
            if (getState() == 0) throw new IllegalMonitorStateException();

            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            // 1表示处于独占状态
            return getState() == 1;
        }
    }

    /**
     * 独占锁
     */
    private final Sync sync = new Sync();

    /**
     * 加锁-阻塞方式
     */
    public void lock(){
        sync.acquire(1);
    }

    /**
     * 尝试加锁，不一定成功
     * @return
     */
    public boolean tryLock(){
        return sync.tryAcquire(1);
    }

    /**
     * 解锁
     */
    public void unLock(){
        sync.release(1);
    }

}
