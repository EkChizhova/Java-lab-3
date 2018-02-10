

import java.util.ArrayList;
//import Group;

public class Student {
    private int id; //идентификационный номер
    private String fio; // ФИО студента
    private Group group; // ссылка на группу
    private ArrayList<Integer> marks = new ArrayList(); //  массив оценок
    private int num; // количество оценок

    public Student(int id,String fio){
        this.fio = fio;
        this.id = id;
    }

    public String GetFIO(){
        return fio;
    }
    public int GetID(){
        return id;
    }

    public void Set_Mark(int mark){
        marks.add(mark);
        num = marks.size();
    }

    public Double Avg_Mark(){
        double avg_ = 0;
        num = marks.size();

        for (int i = 0;i<num;i++){
            avg_ = avg_+marks.get(i);
        }
      return avg_/num;

    }
    public int GetNum(){
        num = marks.size();
        return num;
    }
    public void Set_Group (Group group){
        this.group = group;
    }
    public Group Get_St_gr(){
        return group;
    }
    public String Get_Arr_Marks(){
        String str = "";
        for (int i = 0;i<marks.size();i++){
            str = str + marks.get(i).toString()+";";
        }
        return str;
    }

}
