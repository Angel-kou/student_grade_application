package com.tw;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LibraryTest {
    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);

    }


    @Spy
    Library library;

    @Captor
    ArgumentCaptor argCaptor;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    String[] info1 = {"张三","13111320","数学:75","语文:95","英语:80","编程:80"};
    String[] info2 = {"李四","13111321","数学:85","语文:80","英语:70","编程:90"};
    String[] info3 = {"王五","13111322","数学:86","语文:90","英语:85","编程:92"};
    String serialNumbers = "13111320,13111321,";
    List<Student> list1 = new ArrayList<>();

    @Before
    public void init() {
        library = Mockito.spy(new Library());
        System.setOut(new PrintStream(outContent));

    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void shouldShowStudentInfo() {

        library.addStudentInfo(info1,list1);
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n");
    }

    @Test
    public void shouldShowStudentInfoIsAdded() {

        library.addStudentInfo(info1,list1);
        library.addStudentInfo(info1,list1);
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n该学生的成绩已被添加\n");
    }



    @Test
    public void shouldIgnoreNotExistSerialNumberWhenPrintStudentGradeInfo() {
        List<Student> list = library.addStudentInfo(info1,list1);
        library.getStudentSubjectGradeInfo(serialNumbers,list);
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n张三|75|95|80|80|82.5|330\n");

    }

    @Test
    public void shouldPrintAllSerialNumberStudentGradeInfo() {
        List<Student> list = library.addStudentInfo(info1,list1);
        list = library.addStudentInfo(info2,list1);
        library.getStudentSubjectGradeInfo(serialNumbers,list);
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n学生李四的成绩被添加\n" +
                "张三|75|95|80|80|82.5|330\n李四|85|80|70|90|81.25|325\n");
    }

    @Test
    public void shouldReturnAllStudentTotalScoreAverage() {
        List<Student> list = library.addStudentInfo(info1,list1);
        list = library.addStudentInfo(info2,list1);
        library.getAverage(list);
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n学生李四的成绩被添加\n" +
                "全班总分平均数：327.5\n");
    }


    @Test
    public void shouldReturnAllStudentTotalScoreMedian() {
        List<Student> list = library.addStudentInfo(info1,list1);
        list = library.addStudentInfo(info2,list1);
        list = library.addStudentInfo(info3,list1);
        library.getAverage(list);
        library.getMedian(list);
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n学生李四的成绩被添加\n学生王五的成绩被添加\n" +
                "全班总分平均数：336.0\n全班总分中位数：330.0\n");
    }





}
