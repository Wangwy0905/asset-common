package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

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

    public static <T> void exportCsv( String[] propertys, List<T> list,File file) throws ServiceException {
            exportCsv(null, propertys, list,file);
    }

    public static <T> void exportCsv(String[] titles, String[] propertys, List<T> list,File file) throws ServiceException {
        Assert.notNull(file,"传入的文件不能为空");
        if (!file.isFile()) {
            throw new ServiceException("【导出csv文件】传入的不是文件，文件路径是："+file.getAbsolutePath());
        }

        File dir = file.getParentFile();
        if (dir.getParent() != null) {
            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    throw new ServiceException("【导出csv文件】创建目录失败，目录路径是："+dir.getAbsolutePath());
                }
            }
        }

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

            if (list == null) {
                return;
            }
            //写行数据
            for (T t : list) {
                Class<?> clazz = t.getClass();
                for (String property : propertys) {
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
            log.error("【导出csv文件】导出失败,原因是：{}",e.getMessage());
            throw new ServiceException("【导出csv文件】导出失败", e);
        }
    }
}
