
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;


public class Billing extends JFrame{

    private static final long serialVersionUID = 1L;

    // declaring all the GUI components
     private JLabel item,qty,price,iN,iQ,iP;
     private JTextField itemName, itemQty, itemPrice,totalT;
     private JButton add,clear,cancel,remove,save,total;
     private JTextArea itemDetails;
     private JComboBox comb;
    private JFileChooser choose;


    //declaring all the members
    static ArrayList <String> items = new ArrayList<String>();
    static ArrayList <String> showing = new ArrayList<String>();
    static ArrayList <Integer> cost = new ArrayList<Integer>();
    int t;
    String path;


    private Billing(){

        setVisible(true);
        setTitle("BILL_GENERATOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(180,30,1100,800);
        setResizable(false);
        final Container c = this.getContentPane();
        c.setBackground(new Color(156,179,163));
        c.setLayout(null);

        //adding labels
        item = new JLabel("Item Name");
        item.setBounds(15,15,100,30);
        item.setFont(new Font("arial",Font.BOLD,15));


        qty = new JLabel("Item Quantity");
        qty.setBounds(15,50,100,30);
        qty.setFont(new Font("arial",Font.BOLD,15));


        price = new JLabel("Item Price");
        price.setBounds(15,85,100,30);
        price.setFont(new Font("arial",Font.BOLD,15));

        iN = new JLabel("Item Name |");
        iN.setBounds(125,175,100,30);
        iN.setFont(new Font("arial",Font.BOLD,15));

        iQ = new JLabel(" Item Qty |");
        iQ.setBounds(207,175,100,30);
        iQ.setFont(new Font("arial",Font.BOLD,15));

        iP = new JLabel("Item Price");
        iP.setBounds(280,175,100,30);
        iP.setFont(new Font("arial",Font.BOLD,15));

        //adding the textFields

        itemName = new JTextField();
        itemName.setBounds(125,15,400,30);
        itemName.setFont(new Font("arial",Font.BOLD,21));


        itemQty = new JTextField();
        itemQty.setBounds(125,50,400,30);
        itemQty.setFont(new Font("arial",Font.BOLD,21));
        itemQty.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyChar()>='0' && ke.getKeyChar()<='9' || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ){
                    itemQty.setEditable(true);
                }else{
                    itemQty.setEditable(false);
                    JOptionPane.showMessageDialog(c,"Please Enter Digits Only","ERROR",JOptionPane.ERROR_MESSAGE);
                    itemQty.setEditable(true);

                }

            }

        });


        itemPrice = new JTextField();
        itemPrice.setBounds(125,85,400,30);
        itemPrice.setFont(new Font("arial",Font.BOLD,21));
        itemPrice.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke1){

                if(ke1.getKeyChar()>='0' && ke1.getKeyChar()<='9' || ke1.getKeyCode() == KeyEvent.VK_LEFT || ke1.getKeyCode() == KeyEvent.VK_RIGHT || ke1.getKeyCode() == KeyEvent.VK_BACK_SPACE ){
                    itemPrice.setEditable(true);
                }else{
                    itemPrice.setEditable(false);
                    JOptionPane.showMessageDialog(c,"Please Enter Digits Only","ERROR",JOptionPane.ERROR_MESSAGE);
                    itemPrice.setEditable(true);
                }
            }

        });


        totalT = new JTextField();
        totalT.setBounds(210,723,150,30);
        totalT.setFont(new Font("arial",Font.BOLD,21));
        totalT.setEditable(false);
        totalT.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
                if(ke.getKeyChar()>='0' && ke.getKeyChar()<='9' || ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ){
                    itemQty.setEditable(true);
                }else{
                    itemQty.setEditable(false);
                    JOptionPane.showMessageDialog(c,"Please Enter Digits Only","ERROR",JOptionPane.ERROR_MESSAGE);
                    itemQty.setEditable(true);

                }

            }

        });

        //Adding the buttons

        add = new JButton("Add");
        add.setBounds(125,130,80,30);
        add.setFont(new Font("arial",Font.BOLD,15));
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clear = new JButton("Clear");
        clear.setBounds(235,130,80,30);
        clear.setFont(new Font("arial",Font.BOLD,15));
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancel = new JButton("Close");
        cancel.setBounds(345,130,100,30);
        cancel.setFont(new Font("arial",Font.BOLD,15));
        cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        remove = new JButton("Remove");
        remove.setBounds(600,15,100,30);
        remove.setFont(new Font("arial",Font.BOLD,15));
        remove.setCursor(new Cursor(Cursor.HAND_CURSOR));


        comb = new JComboBox();
        comb.setBounds(720, 15, 300, 30);
        comb.setEditable(false);
        comb.setCursor(new Cursor(Cursor.HAND_CURSOR));


        save = new JButton("Save");
        save.setBounds(848,723,80,30);
        save.setFont(new Font("arial",Font.BOLD,15));
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));

        total = new JButton("Total");
        total.setBounds(125,723,80,30);
        total.setFont(new Font("arial",Font.BOLD,15));
        total.setCursor(new Cursor(Cursor.HAND_CURSOR));



        itemDetails = new JTextArea();
        itemDetails.setBounds(125,210,800,500);
        itemDetails.setLineWrap(true);
        itemDetails.setEditable(false);
        itemDetails.setFont(new Font("arial",Font.PLAIN,20));
        JScrollPane js = new JScrollPane(itemDetails,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setBounds(125,210,800,500);




        c.add(item);
        c.add(qty);
        c.add(price);
        c.add(iN);
        c.add(iQ);
        c.add(iP);
        c.add(itemName);
        c.add(itemQty);
        c.add(itemPrice);
        c.add(add);
        c.add(clear);
        c.add(cancel);
        c.add(remove);
        c.add(comb);
        c.add(js);
        c.add(save);


        c.add(total);
        c.add(totalT);


        //adding the event handler of the buttons
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                itemName.setText("");
                itemQty.setText("");
                itemPrice.setText("");
                totalT.setText("");

            }

        });


        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                System.exit(0);
            }
        });


        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(itemName.getText().isEmpty() || itemQty.getText().isEmpty() || itemPrice.getText().isEmpty()){
                    JOptionPane.showMessageDialog(c, "None Of The Field Can Be Empty","Error",JOptionPane.ERROR_MESSAGE);
                }else{

                    items.add(itemName.getText());
                    items.add(itemQty.getText());

                    int q = Integer.parseInt(itemQty.getText());
                    int p = Integer.parseInt(itemPrice.getText());
                    cost.add(p*q);

                    items.add(Integer.toString(p*q));


                    showing.add(( itemName.getText() + "    |    " + itemQty.getText() + "    |    " + (p*q) ));

                    itemDetails.setText("");
                    comb.removeAllItems();
                    for(int i = 0;i<showing.size();i++){
                        itemDetails.setText(itemDetails.getText() + showing.get(i) + "\n");
                        comb.addItem(showing.get(i));

                    }

                  
                    itemName.setText("");
                    itemQty.setText("");
                    itemPrice.setText("");


                }
            }
        });

        remove.addActionListener(new ActionListener(){


            public void actionPerformed(ActionEvent e){


                if(showing.isEmpty()){
                    remove.disable();

                }else{

                    int slect = comb.getSelectedIndex();
                    String reduce = (String)comb.getSelectedItem();
                    int rm = reduce.indexOf(" ");
                    String reduce1 = reduce.substring(0,rm);


                    int rm1 = items.indexOf(reduce1);
                    for(int i = 0;i<3;i++){

                        items.remove(rm1);

                    }



                    showing.remove(slect);
                    cost.remove(slect);
                    t=0;
                    
                    itemDetails.setText("");

                    comb.removeAllItems();
                    for(int i = 0;i<showing.size();i++){

                        itemDetails.setText(itemDetails.getText() + showing.get(i) + "\n");
                        comb.addItem(showing.get(i));


                    }

                    for(int i =0;i<cost.size();i++){

                        t = t + cost.get(i);
                    }

                    totalT.setText(Integer.toString(t));
                }

            }
        });

        total.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                for(int i =0;i<cost.size();i++){

                    t = t + cost.get(i);
                }


                totalT.setText(Integer.toString(t));
            }
        });

        save.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){


                if(e.getActionCommand().equalsIgnoreCase("save")){
                    choose = new JFileChooser();
                    int ap = choose.showSaveDialog(c);
                    choose.setDialogTitle("Save the File Here");

                    if(ap == JFileChooser.APPROVE_OPTION){

                        File f = new File("D:\\");
                        f = choose.getSelectedFile();
                        path = choose.getSelectedFile().getAbsolutePath();
                        try{
                            PdfWriter pw = new PdfWriter(f.getPath());
                            PdfDocument pd = new PdfDocument(pw);

                            float col[] = {400f,200f};
                            Table tab = new Table(col);
                            tab.setBackgroundColor(new DeviceRgb(192,192,192))
                                    .setFontColor(new DeviceRgb(0,0,204))
                                    .setPaddings(5f, 10f, 5f, 10f);


                            tab.addCell(new Cell().add("Invoice").setTextAlignment(TextAlignment.CENTER).setFontSize(25f));
                            tab.addCell(new Cell().add("XYZ Pvt Ltd \n" + "abc park west hills \n" + "ph.no ***********").setTextAlignment(TextAlignment.RIGHT).setFontSize(10f));

                            Paragraph ph = new Paragraph(" ");

                            float col1[] ={200f,200f,200f};
                            Table tab1 = new Table(col1);
                            tab1.setBold();

                            tab1.addCell(new Cell().add("Items").setTextAlignment(TextAlignment.CENTER).setFontSize(10f));
                            tab1.addCell(new Cell().add("Quantity").setTextAlignment(TextAlignment.CENTER).setFontSize(10f));
                            tab1.addCell(new Cell().add("Price").setTextAlignment(TextAlignment.CENTER).setFontSize(10f));

                            for(int i =0;i<items.size();i++){

                                tab1.addCell(new Cell().add(items.get(i)).setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFontColor(new DeviceRgb(0,0,0)));
                            }

                            tab1.addCell(new Cell(0,2).add("Total").setTextAlignment(TextAlignment.RIGHT).setFontSize(8f).setFontColor(new DeviceRgb(0,0,0)));
                            tab1.addCell(new Cell().add(totalT.getText()).setTextAlignment(TextAlignment.CENTER).setFontSize(8f).setFontColor(new DeviceRgb(0,0,0)));


                            Paragraph auth = new Paragraph("Sign Of Owner");
                            auth.setTextAlignment(TextAlignment.RIGHT);

                            Document d = new Document(pd);
                            d.add(tab);
                            d.add(ph);
                            d.add(tab1);
                            d.add(ph);
                            d.add(ph);
                            d.add(ph);
                            d.add(ph);
                            d.add(auth);
                            d.close();

                            items.clear();
                            cost.clear();
                            showing.clear();
                            comb.removeAllItems();
                            itemDetails.setText("");
                            totalT.setText("");



                        }catch(Exception ex){

                            JOptionPane.showMessageDialog(c,"Cant Save!!!!", "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }






            }

        });




    }

    public static void main(String[] args) throws Exception{

        Billing b = new Billing();

    }

}
