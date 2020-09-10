package edu.books.util;

import org.apache.commons.io.FilenameUtils;

import edu.books.constant.CommonConstants;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class FileUploadUtil {

    public static   List<String> imageFormats = Arrays.asList("png","gif","jpg");

    public static   List<String> formatsForImage = Arrays.asList("png","gif","jpg", "jpeg", "PNG","GIF","JPG", "JPEG");

    public static   List<String> formatsForVideo = Arrays.asList("webm","mkv","flv", "flv", ".vob", "avi", "wmv", "mp4");

    public static String renameFile(String uid, String typeImageName) {
        return uid.concat("_").concat(typeImageName).concat(".png");
    }

    public static final String replaceFileUploadName(String filename) {
        String ramdomName = UUID.randomUUID().toString();
        return ramdomName + "." + FilenameUtils.getExtension(filename);
    }

    public static boolean checkFileNameExtensionForImg(String filename) {
        return formatsForImage.indexOf(FilenameUtils.getExtension(filename)) > -1 ;
    }

    public static boolean checkFileNameExtensionForVideo(String filename) {
        return formatsForVideo.indexOf(FilenameUtils.getExtension(filename)) > -1 ;
    }

    public static String uploadFileToServer(byte[] uploadFile, String originFolder, Long id, String typeImageName,
                                            HttpServletRequest request) throws FileNotFoundException {

        String dirFile;

        if (uploadFile == null) {
            throw new FileNotFoundException("Failed to store empty file");
        }

        try {
            String newName = renameFile(String.valueOf(id), typeImageName);
            String dirFolder = Paths.get(CommonConstants.RESOURCE_SERVER, originFolder + File.separator + id).toString();
            File fileDir = new File(dirFolder);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            Path pathFile = Paths.get(dirFolder, newName);
            Files.write(pathFile, uploadFile);
            dirFile = dirFolder + File.separator + newName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Failed to store file");
        }
        return dirFile;
    }

	public static String uploadFileToServer(byte[] uploadFile, String foderName,
			String fileName, HttpServletRequest request) throws FileNotFoundException {
		String dirFile;

        if (uploadFile == null) {
            throw new FileNotFoundException("Failed to empty file");
        }
        
        try {
            String dirFolder = Paths.get(CommonConstants.RESOURCE_IMAGES, foderName).toString();
            File fileDir = new File(dirFolder);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            Path pathFile = Paths.get(dirFolder, fileName);
            Files.write(pathFile, uploadFile);
            dirFile = dirFolder + File.separator + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Failed to store file");
        }
        return dirFile;
	}

	public static String getImageFromPath(String path) throws IOException{
        File folderInput = new File(path);
        BufferedImage image = ImageIO.read(folderInput);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write( image, "png", byteArrayOutputStream );
        byteArrayOutputStream.flush();
        byte[] imageByteArr = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return Base64.getEncoder().encodeToString(imageByteArr);
    }
	
	public static boolean deleteFileOnServer(String filePath) throws FileNotFoundException {

        if (filePath == null) {
            throw new FileNotFoundException("Failed to delete empty file");
        }
        String dirFolder = Paths.get(CommonConstants.RESOURCE_SERVER, filePath).toString();
        File file  = new File(dirFolder);
		file.delete();
		return true;
	}





}
