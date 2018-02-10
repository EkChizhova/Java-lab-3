import java.util.ArrayList;

public class Group {
    private String Title; //название группы
    private ArrayList<Student> students = new ArrayList (); //массив из ссылок на студентов
    private int Num; //количество студентов в группе
    private Student Head; //ссылка на старосту

    public Group( String Title){
        this.Title = Title;
    }
    public String GetTitle(){
        return Title;
    }
    public Student GetHead(){
        return Head;
    }

    public void Add_stud(Student st){
        students.add(st);
        Num = students.size();
    }

    public String[] Get_stud(){
        Num = students.size();
        String [] str= new String [Num];
        //System.out.println(Title);
        for (int i=0;i<Num;i++) {
            //System.out.println(students.get(i).GetFIO());
            str[i] = students.get(i).GetFIO();
        }
        return str;
    }

    public void ChoiseElder(){
        double max_mark = 0;
        double mark = 0;

        for (int i = 0;i<Num;i++){
            mark = students.get(i).Avg_Mark();
            if (mark > max_mark) {
                max_mark = mark;
                Head = students.get(i);
            }
        }
        if (max_mark ==0){
            Head = students.get((int)Math.random()*Num);
        }
    }

    public Double Get_Avgmark_gr(){
        double mark = 0;
        int S= Num;
        for (int i = 0;i<S;i++){
            if ( Double.isNaN(students.get(i).Avg_Mark())) { S = S-1;}
            else
            {
                mark = mark + students.get(i).Avg_Mark();
            }
        }
        return mark/S;
    }
    public Student Search_St_ID (int id_st){
        Student st = null;
        for (int i = 0;i< students.size();i++){
            if (students.get(i).GetID() == id_st) {
                st = students.get(i);
            }
        }
        return st;
    }
    public Student Search_St_FIO (String fio){
        Student st = null;
        for (int i = 0;i< students.size();i++){
            if (students.get(i).GetFIO().equals(fio)) {
                st = students.get(i);
            }
        }
        return st;
    }

    public void Delete_stud(int id_st){
        Student st = Search_St_ID(id_st);
        if (st != null){
            students.remove(st);
        }
        Num = students.size();
    }

    public int Get_N(){
        return  students.size();
    }
    public Student Get_stud_st(int id_st){
        return students.get(id_st);
    }



}
