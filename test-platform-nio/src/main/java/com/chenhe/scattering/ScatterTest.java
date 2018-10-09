package com.chenhe.scattering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-05-11 14:39
 * @desc
 **/
public class ScatterTest {
    final static String path = "F:\\opt\\data.txt";
    final static String tmpPath = "F:\\opt\\tmp\\";
    /**
     * 大文件拆分n小份
     */
    final static int tmpFileCount = 100;

    static Logger logger = LoggerFactory.getLogger(ScatterTest.class);

    /**
     * 限制并发数量
     */
    static Semaphore semaphore = new Semaphore(20);

    public static void main(String[] args) throws IOException {

        new Scanner(System.in).next();

        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
        long length = randomAccessFile.length();
        logger.info("解析文件内容:长度:{}", length);

        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        if (length == 0) {
            throw new RuntimeException("文件内容为空:" + path);
        }
        //大文件拆分, 每小份长度
        int modLength = (int) (length % tmpFileCount);
        //均分部分的长度
        int perLength = (int) ((length - modLength) / tmpFileCount);
        //将多余的长度,再次均分到每一份

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        int currentLength = 0;
        for (int i = 1; i <= tmpFileCount; i++) {
            int endPosition = currentLength + perLength;
            if (modLength != 0) {
                modLength--;
                endPosition++;
            }
            threadPoolExecutor.submit(new SortTask(randomAccessFile.getChannel(), currentLength, endPosition, i));
            currentLength = endPosition;
        }

    }


    static class SortTask implements Runnable {

        int startPosition;
        int endPosition;
        FileChannel fileChannel;
        int fileIndex;

        public SortTask(FileChannel fileChannel, int startPosition, int endPosition, int fileIndex) {
            this.startPosition = startPosition;
            this.endPosition = endPosition;
            this.fileChannel = fileChannel;
            this.fileIndex = fileIndex;

        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                logger.error("信号量获取异常");
                e.printStackTrace();
            }

            try {
                String fileName = createNewTmpFile(startPosition, endPosition);
                logger.info("临时文件创建完成:{}", fileName);
                sort(fileName);
            } finally {
                semaphore.release();
            }

        }

        /**
         * 文件拆分
         *
         * @param startPosition
         * @param endPosition
         */
        public String createNewTmpFile(int startPosition, int endPosition) {
            String tmpFilePath = tmpPath + "calc-" + fileIndex + ".data";
            File file = new File(tmpFilePath);
            if (file.exists()) {
                file.delete();
            }
            boolean createSuccess = true;
            try {
                createSuccess = file.createNewFile();
            } catch (IOException e) {
                logger.error("创建临时文件异常:fileName={}", tmpFilePath);
                e.printStackTrace();

            }
            if (!createSuccess) {
                logger.error("创建临时文件失败,fileName={}", tmpFilePath);
                throw new RuntimeException("创建临时文件失败");
            }
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(tmpFilePath, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileChannel writeChannel = randomAccessFile.getChannel();

            try {
                while (startPosition < endPosition) {
                    int count = endPosition - startPosition > 1024 ? 1024 : endPosition - startPosition;
                    fileChannel.transferTo(startPosition, count, writeChannel);
                    startPosition = startPosition + count;
                }
            } catch (Exception e) {
                logger.error("文件流复制异常", e);
            }
            try {
                writeChannel.close();
                randomAccessFile.close();
            } catch (IOException e) {
                logger.error("文件流关闭异常");
                e.printStackTrace();
            }
            return tmpFilePath;
        }


        /**
         * 单个文件排序
         *
         * @param fileName
         * @return
         */
        public int[] sort(String fileName) {
            logger.info("--------");
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(fileName, "rw");
            } catch (FileNotFoundException e) {
                logger.error("临时文件打开异常");
                e.printStackTrace();
            }

            FileChannel fileChannel = randomAccessFile.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();

            StringBuffer stringBuffer = new StringBuffer();
            try {
                while (fileChannel.read(byteBuffer) > 0) {
                    byteBuffer.flip();
                    stringBuffer.append(decoder.decode(byteBuffer.asReadOnlyBuffer()).toString());
                    byteBuffer.flip();
                }

                String datas[] = stringBuffer.toString().split(",");
                List<Integer> list = new ArrayList<>();
                for (int i=0; i<datas.length; i++){
                    if (StringUtils.isEmpty(datas[i])){
                        continue;
                    }
                    list.add(Integer.parseInt(datas[i]));
                }

            } catch (IOException e) {
                logger.info("临时文件读取异常");
                e.printStackTrace();
            }

            try {
                randomAccessFile.close();
                fileChannel.close();
            } catch (IOException e) {
                logger.error("临时文件流关闭异常");
                e.printStackTrace();
            }
            return new int[]{};
        }

    }


}
