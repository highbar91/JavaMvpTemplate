package com.ashtoncoulson.javamvptemplate.utility;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;

import com.ashtoncoulson.javamvptemplate.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;

import static com.ashtoncoulson.javamvptemplate.local.Constants.BYTES_IN_GIGABYTE;
import static com.ashtoncoulson.javamvptemplate.local.Constants.BYTES_IN_KILOBYTE;
import static com.ashtoncoulson.javamvptemplate.local.Constants.BYTES_IN_MEGABYTE;
import static com.ashtoncoulson.javamvptemplate.local.Constants.EMPTY_FILE_NAME;

public class FileUtility {

    private static final String IMAGE_EXTENSION = ".jpg";
    private static final String VIDEO_EXTENSION = ".mp4";

    private Context context;

    public FileUtility(Context context) {
        this.context = context;
    }

    public File createImageFile() {
        if(isExternalStorageAvailable()) { // Ensure SD card is mounted

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), context.getString(R.string.app_name));

            if (! mediaStorageDir.exists()){ // Create the storage directory if it does not exist
                if (! mediaStorageDir.mkdirs()){
                    return null;
                }
            }

            return new File(mediaStorageDir.getPath() + File.separator + "img" + createFileTimeStamp() + IMAGE_EXTENSION);
        }
        return null;
    }
    public File createVideoFile() {
        if(isExternalStorageAvailable()) { // Ensure SD card is mounted

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), context.getString(R.string.app_name));

            if (! mediaStorageDir.exists()){ // Create the storage directory if it does not exist
                if (! mediaStorageDir.mkdirs()){
                    return null;
                }
            }

            return new File(mediaStorageDir.getPath() + File.separator + "vid" + createFileTimeStamp() + VIDEO_EXTENSION);
        }
        return null;
    }

    public String getFileName(Uri fileUri) {
        Cursor cursor;
        String fileName = "";
        try {
            if (fileUri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
                cursor = context.getContentResolver().query(fileUri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
                if(cursor != null) {
                    cursor.close();
                }
            }
            else {
                fileName = fileUri.getPath();
                int cut = fileName.lastIndexOf(File.separator);
                if (cut != -1) {  //Todo: create variable for -1
                    fileName = fileName.substring(cut + 1);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return fileName.isEmpty() ? EMPTY_FILE_NAME : fileName;
    }
    public String getFileType(Uri fileUri) {
        ContentResolver resolver = context.getContentResolver();
        return resolver.getType(fileUri);
    }

    public int getFileSizeInBytes(Uri fileUri) {
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(fileUri);
            return inputStream.available();
        }
        catch (Exception e) {
            return 0;
        }
        finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public int getVideoDurationInMilliseconds(Uri videoFileUri) {
        int timeInMilliseconds = 0;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(context, videoFileUri);
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            timeInMilliseconds = Integer.parseInt(time);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            retriever.release();
        }
        return timeInMilliseconds;
    }

    public static String formatFileSizeFromBytes(int fileSizeInBytes) {
        if(fileSizeInBytes/(BYTES_IN_GIGABYTE) > 0) {
            float fileSizeInGB = fileSizeInBytes;
            fileSizeInGB = fileSizeInGB/(BYTES_IN_GIGABYTE);
            return String.valueOf(roundDecimal(fileSizeInGB, 2)) + " GB";
        }
        else if(fileSizeInBytes/(BYTES_IN_MEGABYTE) > 0) {
            float fileSizeInMB = fileSizeInBytes;
            fileSizeInMB = fileSizeInMB/(BYTES_IN_MEGABYTE);
            return String.valueOf(roundDecimal(fileSizeInMB, 2)) + " MB";
        }
        else if(fileSizeInBytes/(BYTES_IN_KILOBYTE) > 0) {
            return String.valueOf(fileSizeInBytes/(BYTES_IN_KILOBYTE)) + " KB";
        }
        return String.valueOf(fileSizeInBytes) + " Bytes";
    }
    public static float roundDecimal(float d, int decimalPlace) { //This could be moved into a separate formatting utility
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private static String createFileTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        return Integer.toString(calendar.get(Calendar.YEAR)) +
                Integer.toString(calendar.get(Calendar.MONTH)+1) + //month index starts at 0
                Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) +
                Integer.toString(calendar.get(Calendar.HOUR)) +
                Integer.toString(calendar.get(Calendar.MINUTE)) +
                Integer.toString(calendar.get(Calendar.SECOND));
    }
    private static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
