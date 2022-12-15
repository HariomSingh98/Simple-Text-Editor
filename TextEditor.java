import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
    static JFrame frame;//create a frame for text editor
    static JTextArea textArea;//component of java swing
    static JMenuBar menuBar;
    static JMenu File;
    static JMenu Edit;
    static JMenu Close;
    static JMenuItem NewFile;
    static JMenuItem OpenFile;
    static JMenuItem SaveFile;
    static JMenuItem PrintFile;
    static JMenuItem Copy;
    static JMenuItem Cut;
    static JMenuItem Paste;
    static JMenuItem CloseFile;


    TextEditor() {
        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         frame.setSize( 650, 800);
        //intialising textArea and adding it
        textArea = new JTextArea("Welcome to Text Editor");
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);

        menuBar = new JMenuBar();
         //add menu  in the menu bar
        File = new JMenu("File");
        Edit= new JMenu("Edit");
        Close=new JMenu("Close");
        //create menu item for File menu and it to the File menu
        NewFile= new JMenuItem("New");
        NewFile.addActionListener(this);
        OpenFile=new JMenuItem("Open");
        OpenFile.addActionListener(this);
        SaveFile=new JMenuItem("Save");
        SaveFile.addActionListener(this);
        PrintFile= new JMenuItem("Print");
        PrintFile.addActionListener(this);
        File.add(NewFile);
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);
        //create menu item for Edit menu and add it to Edit menu
        Copy= new JMenuItem("Copy");
        Copy.addActionListener(this);
        Cut=new JMenuItem("Cut");
        Cut.addActionListener(this);
        Paste= new JMenuItem("Paste");
        Paste.addActionListener(this);
        Edit.add(Copy);
        Edit.add(Cut);
        Edit.add(Paste);
        //create menu item for Close menu and add it
        CloseFile= new JMenuItem("Close");
        CloseFile.addActionListener(this);
        Close.add(CloseFile);
        //add menu to menuBar
        menuBar.add(File);
        menuBar.add(Edit);
        menuBar.add(Close);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

    }

    public static void main(String[] args){
     TextEditor editor = new TextEditor();


    }

    @Override
    public void actionPerformed(ActionEvent e) {//to perform opeartion on components
          String s= e.getActionCommand();//store the command in string
         if(s.equals("Copy")){
             textArea.copy();
         } else if (s.equals("Cut")) {
             textArea.cut();
         } else if (s.equals("Paste")) {
             textArea.paste();
         } else if (s.equals("Close")) {
             frame.setVisible(false);
         } else if (s.equals("New")) {
            textArea.setText("");
         } else if (s.equals("Open")) {
           JFileChooser fileChooser = new JFileChooser("C:");
           int ans= fileChooser.showSaveDialog(null);
           if(ans==fileChooser.APPROVE_OPTION){
               File file = new File(fileChooser.getSelectedFile().getAbsolutePath());//get the absolute path
               String s1="",s2="";
               try{
                   BufferedReader reader= new BufferedReader(new FileReader(file));
                   s2= reader.readLine();
                   while((s1=reader.readLine())!=null){
                       s2+=s1+"\n";
                   }
                   textArea.setText(s2);
               }catch (FileNotFoundException ex){
                   throw new RuntimeException(ex);
               }catch (IOException ex){
                   throw new RuntimeException(ex);
               }

           }

         } else if (s.equals("Save")) {
             JFileChooser fileChooser = new JFileChooser("C:");
             int ans= fileChooser.showSaveDialog(null);
             if(ans==fileChooser.APPROVE_OPTION){
                 File file = new File(fileChooser.getSelectedFile().getAbsolutePath());//get the absolute path
                 BufferedWriter writer=null;//to wrire bytes on the file
                 try{
                     writer= new BufferedWriter(new FileWriter(file,false));
                     writer.write(textArea.getText());
                     writer.flush();
                     writer.close();

                 }catch(IOException ex){
                     throw new RuntimeException(ex);
                 }
             }


         }else if (s.equals("Print")){
             try{
                 textArea.print();
             }catch (PrinterException ex){
                 throw new RuntimeException(ex);
             }
         }

    }
}
