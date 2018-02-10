import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;

public class Dekanat {

    public ArrayList TempFile = new ArrayList();
    private ArrayList <Student> students = new ArrayList ();
    private ArrayList <Group> groups = new ArrayList();

    public Dekanat(){
        CreateStudent();
        CreateGroup();
    }
    private void LoadFile (String NameFile){
        try
        {
            InputStreamReader reader = new InputStreamReader( new FileInputStream(NameFile) , "windows-1251");
            BufferedReader breader = new BufferedReader(reader);
            String line;
            while ((line=breader.readLine()) != null) {
                TempFile.add(line);
            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void CreateStudent(){
        //C:\\Users\\e_chizhova\\IdeaProjects\\Lab3\\
        LoadFile("students.txt");
        int i = 0;
        while(i < TempFile.size()){
            students.add(new Student(i,TempFile.get(i).toString()));
            i++;
        }
        TempFile.clear();
        System.out.println("�Ѩ");

    }

    private void CreateGroup(){
        //C:\\Users\\e_chizhova\\IdeaProjects\\Lab3\\
        LoadFile("groups.txt");
        int i = 0;
        while(i < TempFile.size()){
            groups.add(new Group(TempFile.get(i).toString()));
            i++;
        }
        TempFile.clear();
        System.out.println("��� ������");

    }

    public String Get_Student(int id){
        String str = "";
        str = students.get(id).GetFIO();
        return str;
    }

    public String Get_Group(int id){
        String str = "";
        try {
           str = id+".  "+ groups.get(id).GetTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    //���������� ��������� ������ ���������
    public void Add_marks(int id_st,int c){
        //a,b - ��������    c -���������� ������
        int a = 2;
        int b = 6;
        double tmp = 0;
        for (int i=0;i<c;i++) {
            tmp = ((Math.random() * (b - a)) + a);
            students.get(id_st).Set_Mark((int)tmp);
            //System.out.println("���  "+students.get(id_st).GetFIO()+"ID_ST = "+id_st+"   ������ = "+ (int)tmp);
        }
    }

    public Double Get_Aver_marks(int id_st){
        return students.get(id_st).Avg_Mark();
    }

    //��������� ��������� �� �������
    public void Div_stud_group(int id_st,int id_gr){
        if (id_st == 0 && id_gr == 0) {
            //���� ��������� �������
            int tmp;
            for (int i = 0; i < students.size(); i++) {
                tmp = (int) (Math.random() * groups.size());
                groups.get(tmp).Add_stud(students.get(i));
                students.get(i).Set_Group(groups.get(tmp));
            }
        }
        else{
            //����������� �������� � ���������� ������
            groups.get(id_gr).Add_stud(students.get(id_st));
            students.get(id_st).Set_Group(groups.get(id_gr));
        }
    }

    //�������� ��������� �� �������
    public String[] Get_div_st(int id_gr){
        String [] str= Arrays.copyOf(groups.get(id_gr).Get_stud(),groups.get(id_gr).Get_stud().length);                                                                                                                                                                                                                                       ;
        return str;
    }
    public int Get_Stud_in_Group(int id_gr){
        return groups.get(id_gr).Get_N();
    }

    //����� �������� � ������
    public void InitChoisHead (int id_gr){
        groups.get(id_gr).ChoiseElder();
    }

    //������ �������� � ������
    public String Get_Head(int id_gr){
        return groups.get(id_gr).GetHead().GetFIO();
    }

    public double Get_Avgmark_gr(int id_gr){
        return groups.get(id_gr).Get_Avgmark_gr();
    }

    public String Get_Title_Gr(int id_gr){
        return groups.get(id_gr).GetTitle();
    }

    public String Get_St_gr(int id_st){
        return students.get(id_st).Get_St_gr().GetTitle();
    }

    //����� �������� �� ��
    public String Search_St_Id(int id_st){
        String str = "";
        int a = -1;
        for (int i =0;i<groups.size();i++){
            if (groups.get(i).Search_St_ID(id_st) == null){
                a=-1; }
            else { a = i;break;}
        }
        if (a > 0) {
           str = "������� "+groups.get(a).Search_St_ID(id_st).GetFIO()+" ������ � ������ "+groups.get(a).GetTitle();
        }
        else {str = "������� �� ������";}
        return str;
    }

    //����� �������� �� ���
    public int[] Search_St_FIO(String fio){
        int[] r = new int [2]; //�� ��������, ��_������ ��������
        int a = -1;
        for (int i =0;i<groups.size();i++){
            if (groups.get(i).Search_St_FIO(fio) == null){
                a=-1; }
            else {a = i; break;}
        }
        r[1] =a;
        if (a > 0) {
            System.out.println("������� c ID "+groups.get(a).Search_St_FIO(fio).GetID()+" ������ � ������ "+groups.get(a).GetTitle());
        }
        else {System.out.println("������� �� ������");}

        for (int i = 0; i<students.size();i++){
           if (students.get(i).GetFIO().equals(fio)) {a = i;break;}
        }
        r[0] = a;
        return r;
    }

    //������� �������� � ������
    public void Change_Group(int id_st,int b){
        int a = groups.indexOf(students.get(id_st).Get_St_gr());//������
        //b = ����
        //����� �������� � ������ a � ��������� ��� �� �� ��������, ���� �������� ������� ������, ���� ��� ��� ��� - ��������
        Student hd = null;
        if (groups.get(a).Search_St_ID(id_st) == null){
            System.out.println("������� � ������ "+groups.get(a).GetTitle()+" �� ������");
        }
        else {
            //������� ���� � ������
            //��������
            hd = groups.get(a).GetHead();
            //�������
            groups.get(a).Delete_stud(id_st);
            students.get(id_st).Set_Group(null);
            //��������� ��������
            if (students.get(id_st) == hd){
                InitChoisHead(a);
            }
            //���������
            groups.get(b).Add_stud(students.get(id_st));
            students.get(id_st).Set_Group(groups.get(b));

        }
    }
    public String Get_Arr_Marks_St(int id_st){
        return students.get(id_st).Get_Arr_Marks();
    }
    private int Check_Stud_Head(int id_st){
        //�������� ������ �������
        //Student [] list_head = new Student [groups.size()];
        int k =  -1;
        for (int i=0;i<groups.size();i++){
            //list_head[i] = groups.get(i).GetHead();
            if (students.get(id_st) == groups.get(i).GetHead()) {
                k = i;
                break;
            }
        }
        return k;
    }


    //���������� ��������� �� ��������������
    public void Clear_bad_Student( double ball){
        //����� �� ���� ��������� � ������� ��� � ���� ������� ��� ������ ���������
        //���������� ������ ����������� ���������
        String str="";
        for(int i = 0; i<students.size();i++){

            if (students.get(i).Avg_Mark()<ball){

                if (Check_Stud_Head(i) > 0) {InitChoisHead(Check_Stud_Head(i));}

                str = '\n'+students.get(i).GetFIO() +"�������� �� "+ students.get(i).Get_St_gr().GetTitle();
                //System.out.println("������� "+students.get(i).GetFIO()+" ��������");
                students.get(i).Get_St_gr().Delete_stud(i);
                students.remove(i);
                Save_in_File(str,"Bad_Students.txt");
            }
        }

    }

    public int Get_Count_Stud(){
        return students.size();
    }

    public int Get_Count_Group(){
        return groups.size();
    }

    public void Save_in_File(String line, String name_fl){
        //������ � ��������� ����
        //C:\Users\e_chizhova\IdeaProjects\Lab3\
        try (Writer file = new FileWriter(name_fl,true))
        {
            file.write(line);
            file.close();
        }
        catch (IOException ex)
        {System.out.println(ex.getMessage());}
    }

    public void Create_XML_File() throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();


        Element root = doc.createElement("Dekanat");
        root.setAttribute("xmlns", "http://javacore.ru/topic/90-xml.htm");
        doc.appendChild(root);
        for (int i = 0;i<groups.size();i++) {

            Element group = doc.createElement("Group");
            group.setAttribute("Title", groups.get(i).GetTitle());
            root.appendChild(group);


            for (int j=0;j<groups.get(i).Get_N();j++) {


                Element student = doc.createElement("Student");
                //student.setAttribute("val", );
                group.appendChild(student);

                Element fio = doc.createElement("FIO");
                fio.setAttribute("FIO",  groups.get(i).Get_stud_st(j).GetFIO());
                student.appendChild(fio);

                Element mark = doc.createElement("mark");
                mark.setAttribute("mark",  groups.get(i).Get_stud_st(j).Get_Arr_Marks());
                student.appendChild(mark);

                Element avg_mark = doc.createElement("Avg_mark");
                avg_mark.setAttribute("avg_mark",  groups.get(i).Get_stud_st(j).Avg_Mark().toString());
                student.appendChild(avg_mark);
            }

            Element avg_mark_group = doc.createElement("Avg_mark_group");
            avg_mark_group.setAttribute("avg_mark_group",  groups.get(i).Get_Avgmark_gr().toString());
            group.appendChild(avg_mark_group);

        }
        //
        File file = new File("Result.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));


    }






}
