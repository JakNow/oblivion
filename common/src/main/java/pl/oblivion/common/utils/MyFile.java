package pl.oblivion.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFile {
    
    private static final String FILE_SEPARATOR = System.lineSeparator();
    private static final Logger logger = LogManager.getLogger(MyFile.class);
    
    private String path;
    private String name;
    
    private BufferedReader bufferedReader;
    
    public MyFile(String path) {
        this.path = FILE_SEPARATOR + path;
        String[] dirs = path.split(FILE_SEPARATOR);
        this.name = dirs[dirs.length - 1];
    }
    
    public MyFile(String... paths) {
        this.path = "";
        for (String part : paths) {
            this.path += (FILE_SEPARATOR + part);
        }
        String[] dirs = path.split(FILE_SEPARATOR);
        this.name = dirs[dirs.length - 1];
    }
    
    public MyFile(MyFile file, String subFile) {
        this.path = file.path + FILE_SEPARATOR + subFile;
        this.name = subFile;
    }
    
    public MyFile(MyFile file, String... subFiles) {
        this.path = file.path;
        for (String part : subFiles) {
            this.path += (FILE_SEPARATOR + part);
        }
        String[] dirs = path.split(FILE_SEPARATOR);
        this.name = dirs[dirs.length - 1];
    }
    
    @Override
    public String toString() {
        return getPath();
    }
    
    public String getPath() {
        return path;
    }
    
    public BufferedReader getReader(){
        try {
            bufferedReader = Files.newBufferedReader(Paths.get(path));
            return bufferedReader;
        } catch (IOException e) {
            logger.error("Couldn't get reader for %",path,e);
        }
        return null;
    }
    
    public void closeReader(){
        if(bufferedReader != null){
            try {
                bufferedReader.close();
            } catch (IOException e) {
                logger.error("Couldn't close reader", e);
            }
        }
    }
    
    public String getName() {
        return name;
    }
}
