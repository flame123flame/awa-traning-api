package awa.training.api.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


@Slf4j
@UtilityClass
public class ReportUtils {

    public static String determineFileType(byte[] fileBytes) {
        if (startsWithBytes(fileBytes, (byte) 0xFF, (byte) 0xD8)) {
            return "jpg";
        } else if (startsWithBytes(fileBytes, (byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G')) {
            return "png";
        } else if (startsWithBytes(fileBytes, (byte) 'B', (byte) 'M')) {
            return "bmp";
        } else if (startsWithBytes(fileBytes, (byte) 'G', (byte) 'I', (byte) 'F')) {
            return "gif";
        } else if (startsWithBytes(fileBytes, (byte) 0x25, (byte) 0x50, (byte) 0x44, (byte) 0x46)) {
            return "pdf";
        } else if (startsWithBytes(fileBytes, (byte) 0x50, (byte) 0x4B) &&
                (fileBytes[2] == 0x03 || fileBytes[2] == 0x05 || fileBytes[2] == 0x07) &&
                (fileBytes[3] == 0x04 || fileBytes[3] == 0x06 || fileBytes[3] == 0x08)) {
            return "docx";
        } else if (startsWithBytes(fileBytes, (byte) 0xD0, (byte) 0xCF, (byte) 0x11, (byte) 0xE0) ||
                startsWithBytes(fileBytes, (byte) 0x50, (byte) 0x4B, (byte) 0x03, (byte) 0x04) ||
                startsWithBytes(fileBytes, (byte) 0x50, (byte) 0x4B, (byte) 0x05, (byte) 0x06) ||
                startsWithBytes(fileBytes, (byte) 0x50, (byte) 0x4B, (byte) 0x07, (byte) 0x08)) {
            return "xlsx";
        }
        // Add more checks for other file types as needed
        return "application/octet-stream";
    }

    private static boolean startsWithBytes(byte[] byteArray, byte... prefix) {
        if (byteArray.length >= prefix.length) {
            for (int i = 0; i < prefix.length; i++) {
                if (byteArray[i] != prefix[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public byte[] decodeFile(String file) {
        String partSeparator = ",";
        if (file.contains(partSeparator)) {
            String encoded = file.split(partSeparator)[1];
            return Base64.getDecoder().decode(encoded.getBytes(StandardCharsets.UTF_8));
        } else {
            return Base64.getDecoder().decode(file);
        }
    }

    public String generateFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return now.format(formatter);
    }

    public GenerateFilePath.FileSave generateFilePath(String fullPath, String typeCode, String formattedDateTime, String fileType) {
        String fileSaveDevice = fullPath.concat("/"+typeCode)
                .concat("/image")
                .concat(formattedDateTime)
                .concat(".")
                .concat(fileType);

        String fileSaveBase = typeCode
                .concat("/image")
                .concat(formattedDateTime)
                .concat(".")
                .concat(fileType);

        return GenerateFilePath.FileSave.builder()
                .fileSaveDevice(fileSaveDevice)
                .fileSaveBase(fileSaveBase)
                .build();
    }
    public GenerateFilePath.FileSave generateFileImportPath(String fullPath, String typeCode, String formattedDateTime, String fileType) {
        String fileSaveDevice = fullPath.concat("/"+typeCode)
                .concat("/file_import")
                .concat(formattedDateTime)
                .concat(".")
                .concat(fileType);

        String fileSaveBase = typeCode
                .concat("/file_import")
                .concat(formattedDateTime)
                .concat(".")
                .concat(fileType);

        return GenerateFilePath.FileSave.builder()
                .fileSaveDevice(fileSaveDevice)
                .fileSaveBase(fileSaveBase)
                .build();
    }


}
