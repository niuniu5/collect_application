package com.niu.common;

/**
 * 雪花算法，用于创建分布式环境唯一ID
 */
public class SnowflakeIdWorker {
    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2020, 1, 1, 0, 0, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        System.out.println(calendar.getTimeInMillis());
//        System.out.println(calendar.getTime().getTime());
        System.out.println(newAccountId());
        System.out.println(newStudentId());
        System.out.println(newDesiredCollegeId());
    }

    /**
     * 开始时间：2000-01-01 00:00:00
     */
    private final long beginTs = 1580486400000L;
    private final long workerIdBits = 10;
    /**
     * 2^10 - 1 = 1023
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long sequenceBits = 12;
    /**
     * 2^12 - 1 = 4095
     */
    private final long maxSequence = -1L ^ (-1L << sequenceBits);
    /**
     * 时间戳左移22位
     */
    private final long timestampLeftOffset = workerIdBits + sequenceBits;
    /**
     * 业务ID左移12位
     */
    private final long workerIdLeftOffset = sequenceBits;
    /**
     * 合并了机器ID和数据标示ID，统称业务ID，10位
     */
    private final long workerId;
    /**
     * 毫秒内序列，12位，2^12 = 4096个数字
     */
    private long sequence = 0L;
    /**
     * 上一次生成的ID的时间戳，同一个worker中
     */
    private long lastTimestamp = -1L;
    /**
     *
     * 雪花算法工具类的构造方法，需要传入实例id和集群id，在构造函数内首先判断是否超过最大的实例id并且实例id是否小于0，
     * 同样的去判断数据中心id，如果不符合要求，则抛出参数异常
     */
    private SnowflakeIdWorker(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("WorkerId必须大于或等于0且小于或等于%d", maxWorkerId));
        }

        this.workerId = workerId;
    }
    public synchronized long nextId() {
        long ts = System.currentTimeMillis();
        if (ts < lastTimestamp) {
            throw new RuntimeException(String.format("系统时钟回退了%d毫秒", (lastTimestamp - ts)));
        }
        // 同一时间内，则计算序列号
        if (ts == lastTimestamp) {
            // 序列号溢出
            if (++sequence > maxSequence) {
                ts = tilNextMillis(lastTimestamp);
                sequence = 0L;
            }
        } else {
            // 时间戳改变，重置序列号
            sequence = 0L;
        }
        lastTimestamp = ts;
        // 左移后，低位补0，进行按位或运算相当于二进制拼接
        // 本来高位还有个0<<63，0与任何数字按位或都是本身，所以写不写效果一样
        return (ts - beginTs) << timestampLeftOffset | workerId << workerIdLeftOffset | sequence;
    }
    /**
     * 阻塞到下一个毫秒
     */
    private long tilNextMillis(long lastTimestamp) {
        long ts = System.currentTimeMillis();
        while (ts <= lastTimestamp) {
            ts = System.currentTimeMillis();
        }

        return ts;
    }
    //单例模式
    private static final SnowflakeIdWorker instance1 = new SnowflakeIdWorker(1);
    private static final SnowflakeIdWorker instance2 = new SnowflakeIdWorker(2);
    private static final SnowflakeIdWorker instance3 = new SnowflakeIdWorker(3);

    /**
     * 业务1：账号ID
     * @return
     */
    public static long newAccountId(){
        return instance1.nextId();
    }

    /**
     * 业务2：学生ID
     * @return
     */
    public static long newStudentId(){
        return instance2.nextId();
    }

    /**
     * 业务3：智能筛选结果记录ID
     * @return
     */
    public static long newDesiredCollegeId(){
        return instance3.nextId();
    }

}
