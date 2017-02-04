package org.gloria.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Create on 2017/2/4 14:29.
 *
 * 实现多线程下载文件功能，暂时存在问题，下载大文件会出现超时，导致下载文件不完整
 * 
 * @author : gloria.
 */
public class MultiDownloadUtil {
    

    
    public static void main(String[] args) throws IOException {

        String downloadUrl;
        String saveFilePath;
        Integer threadCount;

        do {
            System.out.println("请输入下载文件地址(输入不能为空) : ");
            downloadUrl = new Scanner(System.in).nextLine();
        } while (StringUtils.isBlank(downloadUrl));

        do {
            System.out.println("请输入文件保存路径(输入不能为空) : ");
            saveFilePath = new Scanner(System.in).nextLine();
        } while (StringUtils.isBlank(saveFilePath));

        System.out.println("输入下载线程数 : ");
        threadCount = new Scanner(System.in).nextInt();
        if (threadCount == 0) {
            threadCount = 1;
        }

        Request request = new Request.Builder()
                .url(downloadUrl).build();
        Response response = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)             
                .followSslRedirects(true)
                .build().newCall(request).execute();

        //返回200
        if (response.isSuccessful()) {
            //待下载文件的文件的大小
            long contentLength = response.body().contentLength();
            //每个线程需要下载的文件大小, 注意处理最后一个线程需要下载的文件大小
            long blockSize = contentLength / threadCount;

            //创建可随机访问的文件, 大小与待下载的文件相同
            RandomAccessFile randomAccessFile = new RandomAccessFile(saveFilePath, "rw");
            randomAccessFile.setLength(contentLength);
            randomAccessFile.close();

            for (int i = 0; i < threadCount; i++) {
                long start = i * blockSize;
                long end = (i + 1) * blockSize - 1;
                if (i == threadCount - 1) {
                    end = contentLength;
                }
                //启动下载线程
                new Download(downloadUrl, saveFilePath, i, start, end).start();
            }
            
        }
    }

    static class Download extends Thread {

        private Long start;
        private Long end;
        private Integer thread;
        private String saveFilePath;
        private String downloadUrl;

        public Download(String downloadUrl, String saveFilePath, Integer thread, Long start, Long end) {
            this.downloadUrl = downloadUrl;
            this.saveFilePath = saveFilePath;
            this.thread = thread;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            System.out.println("线程 " + thread + " 已启动 ");
            RandomAccessFile randomAccessFile = null;
            InputStream input = null;
            try {
                int bufferSize = 8192;

                Request request = new Request.Builder()
                        .url(downloadUrl).header("Range", "bytes=" + start + "-" + end).build();
                Response response = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .followRedirects(true)             //禁止自动重定向
                        .followSslRedirects(true)
                        .build().newCall(request).execute();
                if (response.isSuccessful()) {
                    input = response.body().byteStream();
                    randomAccessFile = new RandomAccessFile(saveFilePath, "rw");
                    randomAccessFile.seek(start);
                    byte[] buffer = new byte[bufferSize];
                    int length = 0;
                    long resultLength = 0;
                    while ((length = input.read(buffer)) != -1) {
                        randomAccessFile.write(buffer, 0, length);
                        resultLength += length;
                        System.out.println("线程：" + thread + "已下载" + (resultLength / (1024.0 * 1024.8)) + "  Mb");
                    }
                    
                }
               
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            System.out.println("线程 " + thread + " 下载完成 ");
        }
        

    }
}
