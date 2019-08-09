package com.weshare.asset.common.util;

import com.weshare.asset.common.exception.ServiceException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.*;

@Slf4j
public class ResourceUtils {
    /**
     * 读取文件中的内容，并反序列化成指定对象
     * @param filename
     * @param type
     * @param <T>
     * @return
     */
    @NonNull public static <T> T deserialize(String filename, Class<T> type) throws ServiceException {
        String content = getContent(filename);
        if (content == null || content.length() == 0) {
            log.warn("配置文件[{}]为空，请检查配置！", filename);
            throw new ServiceException("配置文件[{0}]为空，请检查配置！", filename);
        }

        try {
            return POJOUtils.deserialize(content, type);
        } catch (Throwable th) {
            throw new ServiceException("从文件[{0}]中读取对象失败，请检查配置！", th, filename);
        }
    }

    public static InputStream getInputStream(String filename) {
        try {
            log.debug("从配置文件中读取[{}]文件", filename);
            return new FileInputStream(getConfigFile(filename));
        } catch (IOException e) {
            log.debug("未能从配置文件中读取[{}]文件", filename);
            try {
                log.debug("从jar/resource中读取[{}]文件", filename);
                return new FileInputStream(getDefaultFile(filename));
            } catch (IOException e1) {
                log.debug("未能从jar/resource中读取[{}]文件", filename);
                log.warn("无法读取配置文件，请检查系统配置是否正确！", e1);
            }
        }
        return null;
    }

    public static String getContent(String filename) {
        try {
            log.debug("从配置文件中读取[{}]文件", filename);
            return getConfigContent(getConfigFile(filename));
        } catch (IOException e) {
            log.debug("未能从配置文件中读取[{}]文件", filename);
            try {
                log.debug("从jar/resource中读取[{}]文件", filename);
                return getConfigContent(getDefaultFile(filename));
            } catch (IOException e1) {
                log.debug("未能从jar/resource中读取[{}]文件", filename);
                log.warn("无法读取配置文件，请检查系统配置是否正确！", e1);
            }
        }
        return null;
    }

    public static String getConfigContent(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\r\n");
            }
            return sb.toString();
        }
    }

    private static File getConfigFile(String filename) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource("file:./config/" + filename).getFile();
    }

    private static File getDefaultFile(String filename) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource(filename).getFile();
    }
}
