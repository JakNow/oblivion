package pl.oblivion.common.utils;

import org.junit.After;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MyFileTest {


    private MyFile myFile;
    
    @After
    public void closeReaderIfPresent(){
        if(myFile!=null){
            myFile.closeReader();
        }
    }
    
    @Test
    public void getBufferedReader_path_test(){
        myFile = new MyFile("tests/myfile/MyFileTest.txt");
        assertThat(myFile.getPath()).isEqualTo("/tests/myfile/MyFileTest.txt");
        assertThat(myFile.getName()).isEqualTo("MyFileTest.txt");
        assertThat(myFile.getReader()).isNotNull();
    }
    
    @Test
    public void getBufferedReader_pathArray_test(){
    myFile = new MyFile("tests", "/myfile","/MyFileTest.txt");
        assertThat(myFile.getPath()).isEqualTo("/tests/myfile/MyFileTest.txt");
        assertThat(myFile.getName()).isEqualTo("MyFileTest.txt");
        assertThat(myFile.getReader()).isNotNull();
    }
    
    @Test
    public void getBufferedReader_myFileAndPath_test(){
        MyFile testFile = new MyFile("tests");
        myFile = new MyFile(testFile, "/myfile/MyFileTest.txt");
        assertThat(myFile.getPath()).isEqualTo("/tests/myfile/MyFileTest.txt");
        //assertThat(myFile.getName()).isEqualTo("MyFileTest.txt");
        assertThat(myFile.getReader()).isNotNull();
    }
    
    @Test
    public void getBufferedReader_myFileAndPathArray_test(){
        MyFile testFile = new MyFile("tests");
        myFile = new MyFile(testFile, "/myfile","/MyFileTest.txt");
        assertThat(myFile.getPath()).isEqualTo("/tests/myfile/MyFileTest.txt");
        assertThat(myFile.getName()).isEqualTo("MyFileTest.txt");
        assertThat(myFile.getReader()).isNotNull();
    }
    
    @Test
    public void slashIsAddedIfNotPresent_path_test(){
        myFile = new MyFile("tests/myfile/MyFileTest.txt");
        assertThat(myFile.getPath()).isEqualTo("/tests/myfile/MyFileTest.txt");
    }
    
    @Test
    public void slashIsAddedIfNotPresent_pathArray_test(){
        myFile = new MyFile("tests", "/myfile","/MyFileTest.txt");
        assertThat(myFile.getPath()).isEqualTo("/tests/myfile/MyFileTest.txt");
    }
    
    @Test
    public void slashIsAddedIfNotPresent_myFileAndPath_test(){
        MyFile testFile = new MyFile("tests");
        myFile = new MyFile(testFile, "/myfile/MyFileTest.txt");
        assertThat(myFile.getPath()).isEqualTo("/tests/myfile/MyFileTest.txt");
    }
    
    @Test
    public void slashIsAddedIfNotPresent_myFileAndPathArray_test(){
        MyFile testFile = new MyFile("tests");
        myFile = new MyFile(testFile, "/myfile","/MyFileTest.txt");
        assertThat(myFile.getPath()).isEqualTo("/tests/myfile/MyFileTest.txt");
    }
}
