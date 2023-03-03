package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    public StudentService(){

    }
    @Autowired
    StudentRepository repository;

    public void add_student(Student Student){
        repository.add_student(Student);
    }
    public void add_teacher(Teacher teacher){
       repository.add_teacher(teacher);
    }

    public void add_student_teacher_pair(String studName,  String teachName){
       repository.add_student_teacher_pair(studName,teachName);
    }
    public Student get_student_by_name(String name){

        return repository.get_student_by_name(name);
    }

    public Teacher get_teacher_by_name(String name){
        return repository.get_teacher_by_name(name);
    }

    public List<String> get_students_by_teacher_name(String name){
        return repository.get_students_by_teacher_name(name);
    }

    public List<String> get_all_students(){
        return repository.get_all_students();
    }


    public void delete_teacher_by_name(String name){
      repository.delete_teacher_by_name(name);
    }

    public void delete_all_teachers(){
       repository.delete_all_teachers();
    }
}
