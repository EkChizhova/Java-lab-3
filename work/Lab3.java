import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.lang.Math;


public class Lab3 {
    public static void main (String[] args) throws TransformerException, ParserConfigurationException {
        int N_stud = 0;
        int N_group = 0;
        String str_buff = "";
        //создаем экз класса деканат
        Dekanat dek = new Dekanat();

        N_stud = dek.Get_Count_Stud(); //количество студентов
        N_group = dek.Get_Count_Group(); //количество групп

        if (N_stud != 0 && N_group != 0) {

            //разбиваем студентов на группы случайным образом
            dek.Div_stud_group(0,0);
            System.out.println("УРА! Создали ДЕКАНАТ и разбили студентов на группы");
            //выбираем старосту в каждой группе, а заодно результат разбиения запишем в файл
            for (int i=0;i<N_group;i++){
                str_buff = '\n'+dek.Get_Group(i);
                dek.Save_in_File(str_buff,"Group_Stud.txt");
                dek.InitChoisHead(i);
                str_buff = '\n'+"Староста "+ dek.Get_Head(i);
                System.out.println("Староста в группе "+dek.Get_Group(i)+"  "+dek.Get_Head(i));
                dek.Save_in_File(str_buff,"Group_Stud.txt");
                for(int j=0; j<dek.Get_Stud_in_Group(i); j++){
                     str_buff = '\n'+dek.Get_div_st(i)[j];
                     dek.Save_in_File(str_buff,"Group_Stud.txt");
                }
                System.out.println("УРА! Записали список групп в файл Group_Stud.txt");
                str_buff="";
            }

        //добавим оценки студентам в параметрах id студента и количество оценок - случайным образом от 0 до 10 оценок
            for (int i=0;i<N_stud;i++){
                dek.Add_marks(i,(int) (Math.random() * 11));
            }
            System.out.println("Прошла сессия и у студентов появились оценки:");
            for (int i=0;i<N_stud;i++){
                System.out.println(i+". "+dek.Get_Student(i)+" - "+dek.Get_Arr_Marks_St(i)+
                        '\t'+"Средний бал = "+ dek.Get_Aver_marks(i)/*new BigDecimal(dek.Get_Aver_marks(i)).setScale(2, RoundingMode.HALF_UP).floatValue()*/);
            }
            System.out.println("Выведем средний балл группы:");
            for (int i = 0;i<N_group;i++){
                System.out.println(dek.Get_Group(i)+" - "+ dek.Get_Avgmark_gr(i));
            }

            //переведем старосту из 1 группы во 2 и запишем в файл
            dek.Change_Group(dek.Search_St_FIO(dek.Get_Head(1))[0],2);
            str_buff = '\n'+"*********** После перевода старосты 1 группы в группу 2******";
            dek.Save_in_File(str_buff,"Group_Stud.txt");
            for (int i=0;i<N_group;i++){
                str_buff = '\n'+dek.Get_Group(i);
                dek.Save_in_File(str_buff,"Group_Stud.txt");
                str_buff = '\n'+"Староста "+ dek.Get_Head(i);
                System.out.println("Староста в группе "+dek.Get_Group(i)+"  "+dek.Get_Head(i));
                dek.Save_in_File(str_buff,"Group_Stud.txt");
                //System.out.println("Староста в группе "+dek.Get_Group(i)+"  "+dek.Get_Head(i));
                for(int j=0; j<dek.Get_Stud_in_Group(i); j++){
                    str_buff = '\n'+dek.Get_div_st(i)[j];
                    dek.Save_in_File(str_buff,"Group_Stud.txt");
                }}
            System.out.println("УРА! Записали список групп в файл Group_Stud.txt");

            //Отчислим студкнтов у которых средний балл меньше 3 и выведем в файл
            // То что оставлось запишем в файл xml
            dek.Clear_bad_Student(3);
            System.out.println("Мы отчислили студентов и всех их записали в файл Bad_Students.txt");
            dek.Create_XML_File();
            System.out.println("Список оставшихся записали в файл Result.xml");


        }
        else{
            System.out.println("Загрузка из файла произошла с ошибкой");
        }



    }
}
