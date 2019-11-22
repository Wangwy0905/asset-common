package com.weshare.asset.common.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.weshare.asset.common.exception.AssetException;
import com.weshare.asset.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：v_lijunxiong
 * @date ：2019/10/30
 * @description：
 */

@Slf4j
public class CsvUtils {

    public static <T> void exportCsv(String[] properties, List<T> dataList, File file) throws AssetException {
        exportCsv(null, properties, dataList, file);
    }

    public static <T> void exportCsv(String[] titles, String[] properties, List<T> dataList, File file) throws AssetException {
        Assert.notNull(file, "传入的文件不能为空");
        if (!file.exists() || !file.isFile()) {
            throw new ServiceException("【{}】不存在或者不是文件", file.getAbsolutePath());
        }

        //构建输出流，同时指定编码
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"))) {
            //写标题
            if (titles != null) {
                for (String title : titles) {
                    bw.write(title);
                    bw.write(",");
                }
                bw.newLine();
            }

            if (dataList == null) {
                return;
            }
            //写行数据
            for (T t : dataList) {
                Class<?> clazz = t.getClass();
                for (String property : properties) {
                    Field field = ReflectionUtils.findField(clazz, property);
                    if (field != null) {
                        field.setAccessible(true);
                        Object value = ReflectionUtils.getField(field, t);
                        if (value == null) {
                            bw.write("");
                        } else {
                            bw.write(String.valueOf(value));
                        }
                        bw.write(",");
                    }
                }
                bw.newLine();
            }

        } catch (IOException e) {
            log.error("csv文件导出异常", e);
            throw new ServiceException("csv文件导出失败", e);
        }
    }

    public static File base64ToCsv(String base64Content) throws ServiceException {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File tempFile;

        try {
            byte[] bytes = Base64.decode(base64Content);
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            //生成临时文件
            tempFile = File.createTempFile("test", ".csv");

            fos = new FileOutputStream(tempFile);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();

            return tempFile;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("csv文件转化失败", e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
                // tempFile.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> csvParsing(File file) throws ServiceException {
        FileInputStream in = null;
        BufferedReader reader = null;
        try {
            String charset = EncodeUtils.get_charset(file);
            FileInputStream fileInputStream = new FileInputStream(file);

            if ("GBK" == charset) reader = new BufferedReader(new InputStreamReader(fileInputStream, "GBK"));
            if ("UTF-8" == charset) reader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            in = new FileInputStream(file);
            List<String> fileString = new ArrayList<>();
            String line;
            String everyLine;
            while ((line = reader.readLine()) != null) {
                everyLine = line;
                fileString.add(everyLine);
            }
            return fileString;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("csv文件解析失败", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (reader != null) {
                    reader.close();
                }

                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
