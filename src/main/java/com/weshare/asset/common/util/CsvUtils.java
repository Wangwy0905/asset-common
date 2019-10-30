package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author ：v_lijunxiong
 * @date ：2019/10/30
 * @description：
 */

@Slf4j
public class CsvUtils {

    public static <T> void exportCsv( String[] propertys, List<T> list,String dirName,String fileName) throws ServiceException {
            exportCsv(null, propertys, list,dirName,fileName);
    }

    public static <T> void exportCsv(String[] titles, String[] propertys, List<T> list,String dirName,String fileName) throws ServiceException {

        File dir = new File(dirName);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                log.error("【导出csv文件】创建目录失败，目录地址是：{}", dir.getAbsolutePath());
                throw new ServiceException("【导出csv文件】创建目录失败");
            }
        } else {
            if (!dir.isDirectory()) {
                log.error("【导出csv文件】路径：{}已存在且不是一个目录",dir.getAbsolutePath());
                throw new ServiceException("【导出csv文件】该路径已存在且不是一个目录");
            }
        }

        File file = new File(dir, fileName + ".csv");
        //构建输出流，同时指定编码
        try (BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), "gbk"))){
            //写标题
            if (titles != null) {
                for (String title : titles) {
                    bw.write(title);
                    bw.write(",");
                }
                bw.newLine();
            }
            
            //写行数据
            for (T t : list) {
                Class<?> clazz = t.getClass();
                for (String property : propertys) {
                    Field field = ReflectionUtils.findField(clazz, property);
                    if (field != null) {
                        field.setAccessible(true);
                        bw.write(ReflectionUtils.getField(field,t).toString());
                        bw.write(",");
                    }
                }
                bw.newLine();
            }

        } catch (IOException e) {
            log.error("【导出csv文件】导出失败,原因是：{}",e.getMessage());
            throw new ServiceException("【导出csv文件】导出失败", e);
        }
    }
}
