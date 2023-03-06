package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private static HashMap<String,Student> hmStudent;
    private static HashMap<String,Teacher> hmTeacher;
    private static HashMap<String,String> hmStudentTeacherPair;
    private static   HashMap<String, List<String>> hmTeacherStudentList;

    public StudentRepository(){
        hmStudent = new HashMap<>();
        hmTeacher = new HashMap<>();
        hmStudentTeacherPair = new HashMap<>();
        hmTeacherStudentList = new HashMap<>();
    }
    public void add_student(Student Student){
        hmStudent.put(Student.getName(),Student);
    }
    public void add_teacher(Teacher teacher){

        hmTeacher.put(teacher.getName(),teacher);
    }

    public void add_student_teacher_pair(String studName,  String teachName){
        if(!hmStudent.containsKey(studName) || !hmTeacher.containsKey(teachName)){
            return;
        }
        hmStudentTeacherPair.put(teachName,studName);
        int noOfStudents = hmTeacher.get(teachName).getNumberOfStudents();
        hmTeacher.get(teachName).setNumberOfStudents(noOfStudents+1);
        List<String> list = new ArrayList<>();

        if(hmTeacherStudentList.containsKey(teachName)){
            list = hmTeacherStudentList.get(teachName);
        }
        list.add(studName);
        hmTeacherStudentList.put(teachName,list);
    }
    public Student get_student_by_name(String name){
        return hmStudent.get(name);
    }

    public Teacher get_teacher_by_name(String name){
        return hmTeacher.get(name);
    }

    public List<String> get_students_by_teacher_name(String teacherName){
        List<String > studentList = hmTeacherStudentList.get(teacherName);
        return studentList;
    }

    public List<String> get_all_students(){
        List<String> studentList = new ArrayList<>(hmStudent.keySet());
        return studentList;
    }


    public void delete_teacher_by_name(String name){
        List<String > students = hmTeacherStudentList.get(name);
        hmTeacherStudentList.remove(name);
        hmTeacher.remove(name);
        for(String student: students){
            hmStudentTeacherPair.remove(student);
        }
    }

    public void delete_all_teachers(){
        for(String teachName: hmTeacherStudentList.keySet()){
            List<String> studentList = hmTeacherStudentList.get(teachName);
            for(String studentName : studentList){
                hmStudentTeacherPair.remove(studentName);
               // hmStudent.remove(studentName);
            }
            hmTeacherStudentList.remove(teachName);
        }
        hmTeacher.clear();
    }
}
