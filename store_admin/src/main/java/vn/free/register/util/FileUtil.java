package vn.free.register.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Slf4j
public class FileUtil {

    private static final Long maxSize = 10240L;


    public static Integer getRadomImageLength() {
        Random rd = new Random();
        int high = 30;
        int low = 1;
        return rd.nextInt(high - low) + low;
    }

    public static String generateName() {
        int length = getRadomImageLength();

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);

        return year
                + RandomStringUtils.random(length, true, true)
                + RandomStringUtils.random(10, true, false)
                + RandomStringUtils.random(5, false, true);
    }

    public static String getExtentionFile(String url) {
        return "." + FilenameUtils.getExtension(url).toLowerCase();
    }

    public static String multiPartFileToFile(MultipartFile file, String dirPath) throws IllegalStateException {

        try {

            if (file.isEmpty()) {
                log.error("MultipartFile input empty");
                throw new NullPointerException();
            }

            Path dir = Paths.get(dirPath);
            if (!Files.isDirectory(dir)) {
                log.info("Create directory from dirPath {}", dirPath);
                Files.createDirectories(dir);
            }

            String fileName = generateName() + getExtentionFile(file.getOriginalFilename());
            Path filePath = Paths.get(dirPath, fileName);

            if (Files.notExists(filePath)) {
                log.info("Create file from path {}", filePath);
                Files.createFile(filePath);
            }
            File fileS = filePath.toFile();
            FileCopyUtils.copy(file.getBytes(), fileS);

            return filePath.toString();

        } catch (IOException e) {
            log.error("Parse multi file to file exception");
            e.printStackTrace();
            return "99";
        }


    }
}
