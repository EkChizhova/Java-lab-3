import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.lang.Math;


public class Lab3 {
    public static void main (String[] args) throws TransformerException, ParserConfigurationException {
        int N_stud = 0;
        int N_group = 0;
        String str_buff = "";
        //������� ��� ������ �������
        Dekanat dek = new Dekanat();

        N_stud = dek.Get_Count_Stud(); //���������� ���������
        N_group = dek.Get_Count_Group(); //���������� �����

        if (N_stud != 0 && N_group != 0) {

            //��������� ��������� �� ������ ��������� �������
            dek.Div_stud_group(0,0);
            System.out.println("���! ������� ������� � ������� ��������� �� ������");
            //�������� �������� � ������ ������, � ������ ��������� ��������� ������� � ����
            for (int i=0;i<N_group;i++){
                str_buff = '\n'+dek.Get_Group(i);
                dek.Save_in_File(str_buff,"Group_Stud.txt");
                dek.InitChoisHead(i);
                str_buff = '\n'+"�������� "+ dek.Get_Head(i);
                System.out.println("�������� � ������ "+dek.Get_Group(i)+"  "+dek.Get_Head(i));
                dek.Save_in_File(str_buff,"Group_Stud.txt");
                for(int j=0; j<dek.Get_Stud_in_Group(i); j++){
                     str_buff = '\n'+dek.Get_div_st(i)[j];
                     dek.Save_in_File(str_buff,"Group_Stud.txt");
                }
                System.out.println("���! �������� ������ ����� � ���� Group_Stud.txt");
                str_buff="";
            }

        //������� ������ ��������� � ���������� id �������� � ���������� ������ - ��������� ������� �� 0 �� 10 ������
            for (int i=0;i<N_stud;i++){
                dek.Add_marks(i,(int) (Math.random() * 11));
            }
            System.out.println("������ ������ � � ��������� ��������� ������:");
            for (int i=0;i<N_stud;i++){
                System.out.println(i+". "+dek.Get_Student(i)+" - "+dek.Get_Arr_Marks_St(i)+
                        '\t'+"������� ��� = "+ dek.Get_Aver_marks(i)/*new BigDecimal(dek.Get_Aver_marks(i)).setScale(2, RoundingMode.HALF_UP).floatValue()*/);
            }
            System.out.println("������� ������� ���� ������:");
            for (int i = 0;i<N_group;i++){
                System.out.println(dek.Get_Group(i)+" - "+ dek.Get_Avgmark_gr(i));
            }

            //��������� �������� �� 1 ������ �� 2 � ������� � ����
            dek.Change_Group(dek.Search_St_FIO(dek.Get_Head(1))[0],2);
            str_buff = '\n'+"*********** ����� �������� �������� 1 ������ � ������ 2******";
            dek.Save_in_File(str_buff,"Group_Stud.txt");
            for (int i=0;i<N_group;i++){
                str_buff = '\n'+dek.Get_Group(i);
                dek.Save_in_File(str_buff,"Group_Stud.txt");
                str_buff = '\n'+"�������� "+ dek.Get_Head(i);
                System.out.println("�������� � ������ "+dek.Get_Group(i)+"  "+dek.Get_Head(i));
                dek.Save_in_File(str_buff,"Group_Stud.txt");
                //System.out.println("�������� � ������ "+dek.Get_Group(i)+"  "+dek.Get_Head(i));
                for(int j=0; j<dek.Get_Stud_in_Group(i); j++){
                    str_buff = '\n'+dek.Get_div_st(i)[j];
                    dek.Save_in_File(str_buff,"Group_Stud.txt");
                }}
            System.out.println("���! �������� ������ ����� � ���� Group_Stud.txt");

            //�������� ��������� � ������� ������� ���� ������ 3 � ������� � ����
            // �� ��� ��������� ������� � ���� xml
            dek.Clear_bad_Student(3);
            System.out.println("�� ��������� ��������� � ���� �� �������� � ���� Bad_Students.txt");
            dek.Create_XML_File();
            System.out.println("������ ���������� �������� � ���� Result.xml");


        }
        else{
            System.out.println("�������� �� ����� ��������� � �������");
        }



    }
}
