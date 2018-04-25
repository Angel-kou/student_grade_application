package com.tw;

import java.util.*;
import java.util.stream.Collectors;

public class Library {

    private String[] info ;
    private  String serialNumbers ;
    private int number;
    private Scanner scanner = new Scanner(System.in);
    private List<Student> list = new ArrayList<>();
    public boolean someLibraryMethod() {
        return true;
    }

    public void buildMainMenu() {
        System.out.print("1. 添加学生\n");
        System.out.print("2. 生成成绩单\n");
        System.out.print("3. 退出请输入你的选择（1～3）：\n");
    }

    public void processUserRequest() {
        number = scanner.nextInt();
        switch (number){
            case 1:
                buildStudentInfoPrompt();
                processResponse();
                break;
            case  2:
                buildStudentserialNumberPrompt();
                processResponse();
                break;
            case  3:
                return;
        }

    }

    public void buildStudentserialNumberPrompt() {
        System.out.print("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n");
        serialNumbers =  scanner.next();
        while (!serialNumbers.contains(",")){
            System.out.print("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n");
            serialNumbers =  scanner.next();
        }

    }



    public void buildStudentInfoPrompt() {
        System.out.print("请输入学生信息（格式：姓名, 学号, 数学: 成绩, 语文: 成绩, 英语: 成绩, 编程: 成绩, ...），按回车提交：\n");
        info = scanner.next().split(",");
        while (info.length != 6 || (info.length==6 && Arrays.asList(info).stream().filter(x->x.split(":").length==2).count()!=4)) {
            System.out.print("请按正确的格式输入（格式：姓名, 学号, 数学: 成绩, 语文: 成绩, 英语: 成绩, 编程: 成绩, ...）：\n");
            info = scanner.next().split(",");
        }
    }



    public void processResponse() {
        switch (number){
            case 1:
                addStudentInfo(info,list);
                break;
            case 2:
                buildReport();
                break;
            case 3:
                return;
        }
        showNextAction();
    }

    private void showNextAction() {
        buildMainMenu();
        processUserRequest();
    }

    public void buildReport() {
        List<String> serials = Arrays.asList(serialNumbers.split(","));
        List<String> actualNumbers = serials.stream().filter(x->list.stream().filter(y->y.getSerialNumber().equals(x)).count()!=0).collect(Collectors.toList());
        if(actualNumbers.size() == 0){
            System.out.print("该学号的学生不存在\n");
        }else {
            System.out.print("成绩单\n");
            System.out.print("姓名|数学|语文|英语|编程|平均分|总分\n");
            System.out.print("========================\n");
            getStudentSubjectGradeInfo(serialNumbers, list);
            System.out.print("========================\n");
            getAverage(list);
            getMedian(list);
        }
    }

    public void getStudentSubjectGradeInfo(String serialNumbers,List<Student> list) {
        List<Student> printList = list;
        List<String> serials = Arrays.asList(serialNumbers.split(","));
        List<String> actualNumbers = serials.stream().filter(x->printList.stream().filter(y->y.getSerialNumber().equals(x)).count()!=0).collect(Collectors.toList());

        printList.stream().filter(x->actualNumbers.contains(x.getSerialNumber()))
                .forEach(x-> {

                    System.out.print(x.getName());
                    x.getSubjects().stream().forEach(y-> System.out.print("|"+y.getScore()));
                    System.out.print("|"+x.getSubjects().stream().mapToDouble(y->y.getScore()).average().getAsDouble());

                    x.setTotalScore(x.getSubjects().stream().mapToInt(y->y.getScore())
                            .reduce((sum,item)->sum+item).getAsInt());
                    System.out.print("|"+x.getTotalScore()+"\n");
                });
    }

    public void getAverage(List<Student> list) {
        list.stream().forEach(x->x.setTotalScore(x.getSubjects().stream().mapToInt(y->y.getScore())
                                .reduce((sum,item)->sum+item).getAsInt()));
        double average = list.stream().mapToDouble(x->x.getTotalScore()).average().getAsDouble();
        System.out.print("全班总分平均数："+average+"\n");
    }

    public void getMedian(List<Student> list) {
        List<Integer> totalScoreList = list.stream().map(x->x.getTotalScore()).collect(Collectors.toList());
        totalScoreList.sort(Comparator.naturalOrder());

        double median =totalScoreList.stream()
                .filter(x-> (totalScoreList.size() %2==0 && (totalScoreList.get(totalScoreList.size()/2-1)==x ||totalScoreList.get(totalScoreList.size()/2)==x))||
                        (totalScoreList.size() %2!=0 && totalScoreList.get(totalScoreList.size()/2)==x))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(x->new Double(x))
                .average()
                .getAsDouble();
        System.out.print("全班总分中位数："+median+"\n");
    }

    public List<Student> addStudentInfo(String[] info,List<Student> list) {
        if(list.stream().filter(x->x.getSerialNumber().equals(info[1])).count() != 0){
            System.out.print("该学生的成绩已被添加\n");
        }else {
            Student student = new Student();
            student.setName(info[0]);
            student.setSerialNumber(info[1]);
            List<Subject> subjects = new ArrayList<>();
            Arrays.asList(info).stream().filter(x -> x.split(":").length == 2)
                    .forEach(y -> {
                        Subject subject = new Subject();
                        subject.setSubName(y.split(":")[0]);
                        subject.setScore(Integer.parseInt(y.split(":")[1]));
                        subjects.add(subject);
                    });
            student.setSubjects(subjects);
            list.add(student);
            System.out.print("学生" + student.getName() + "的成绩被添加\n");
        }
        return list;
    }

    public static void main(String args[]) {
        Library library = new Library();
        library.buildMainMenu();
        library.processUserRequest();
    }

}
