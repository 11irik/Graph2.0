//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//import static graph.mainClass.graph1;
//
//public class GUI extends JFrame {
//
//    public static JRadioButton radio1 = new JRadioButton("Ориентированный граф");
//    private JRadioButton radio2 = new JRadioButton("Неориентированный граф");
//    public JTextField text;
//    public JTextField text2;
//    public JTextField text3;
//    public JTextField text4;
//    public GUI() {
//        super("Графы");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        Container container = this.getContentPane();
//        container.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 10));
//        ButtonGroup group = new ButtonGroup();
//        group.add(radio1);
//        group.add(radio2);
//        container.add(radio1);
//        radio1.setSelected(true);
//        container.add(radio2);
//
//        JButton[] butArray = new JButton[6];
//        butArray[0] = new JButton("Вывести все вершины графа.");
//        butArray[0].addActionListener(new ButtonEventListener1());
//
//        butArray[1] = new JButton("Вывести все ребра графа.");
//        butArray[1].addActionListener(new ButtonEventListener2());
//
//        butArray[2] = new JButton("Добавить вершину в граф.");
//        butArray[2].addActionListener(new ButtonEventListener3());
//
//        butArray[3] = new JButton("Добавить ребро в граф.");
//        butArray[3].addActionListener(new ButtonEventListener4());
//
//        butArray[4] = new JButton("Удалить вершину из графа.");
//        butArray[4].addActionListener(new ButtonEventListener5());
//
//        butArray[5] = new JButton("Удалить ребро из графа.");
//        butArray[5].addActionListener(new ButtonEventListener6());
//
//        for (JButton x: butArray){
//            x.setPreferredSize(new Dimension(210, 50));
//            x.setMargin                (new Insets(10, 10, 10, 10));
//            x.setVerticalAlignment     (SwingConstants.TOP   );
//            x.setHorizontalAlignment   (SwingConstants.RIGHT );
//            x.setHorizontalTextPosition(SwingConstants.LEFT  );
//            x.setVerticalTextPosition  (SwingConstants.BOTTOM);
//        }
//
//        container.add(butArray[0]);
//        container.add(butArray[1]);
//        text = new JTextField("");
//        text.setPreferredSize(new Dimension(210, 50));
//        container.add(text);
//        container.add(butArray[2]);
//
//        text2 = new JTextField("");
//        text2.setPreferredSize(new Dimension(210, 50));
//        container.add(text2);
//        container.add(butArray[3]);
//
//        text3 = new JTextField("");
//        text3.setPreferredSize(new Dimension(210, 50));
//        container.add(text3);
//        container.add(butArray[4]);
//
//        text4 = new JTextField("");
//        text4.setPreferredSize(new Dimension(210, 50));
//        container.add(text4);
//        container.add(butArray[5]);
//
//
//        setLocation(760,400);
//        setSize(new Dimension(480,400));
//    }
//
//    class ButtonEventListener1 implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            StringBuilder messege = graph1.getNodes();
//            JOptionPane.showMessageDialog(null, messege, "Всё, что вы просили, сэр.",
//                    JOptionPane.PLAIN_MESSAGE);
//        }
//    }
//
//    class ButtonEventListener2 implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            StringBuilder messege = graph1.getEdges();
//          JOptionPane.showMessageDialog(null, messege, "Всё, что вы просили, сэр.", JOptionPane.PLAIN_MESSAGE);
//        }
//    }
//    class ButtonEventListener3 implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            StringBuilder messege = new StringBuilder();
//            if (text.getText().isEmpty()){
//                messege = new StringBuilder("Заполните строку слева хотя бы одной вершиной.");
//            }
//            else {
//                String[] input = text.getText().split(",");
//                for (String x : input) {
//                    messege.append(graph1.add(x));
//                }
//            }
//                JOptionPane.showMessageDialog(null, messege.toString(), "Всё, что вы просили, сэр.",
//                    JOptionPane.PLAIN_MESSAGE);
//                 }
//
//    }
//    class ButtonEventListener4 implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            StringBuilder messege = new StringBuilder();
//            if (text2.getText().isEmpty()) {
//                messege = new StringBuilder("Заполните строку слева хотя бы одним ребром(дугой).");
//            } else {
//                String[] input2 = text2.getText().split(" ");
//                if (input2.length == 1){
//                    messege = new StringBuilder("Неверный формат ввода данных. " +
//                            "Попробуйте: Исходящая_вершина Принимающая_вершина(либо несколько через запятую)");
//                }
//                else{
//                    messege = graph1.addEdge(input2[0], input2[1].split(","), 0);
//                }
//
//            }
//            JOptionPane.showMessageDialog(null, messege, "Всё, что вы просили, сэр.",
//                    JOptionPane.PLAIN_MESSAGE);
//        }
//    }
//    class ButtonEventListener5 implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            StringBuilder messege = new StringBuilder();
//            if (text3.getText().isEmpty()) {
//                messege = new StringBuilder("Заполните строку слева хотя бы одной вершиной.");
//            } else {
//                String[] input3 = text3.getText().split(" ");
//                for (String x : input3) {
//                    messege = graph1.remove(x);
//                }
//            }
//            JOptionPane.showMessageDialog(null, messege, "Всё, что вы просили, сэр.",
//                    JOptionPane.PLAIN_MESSAGE);
//        }
//    }
//    class ButtonEventListener6 implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            StringBuilder messege = new StringBuilder();
//            if (text4.getText().isEmpty()) {
//                messege = new StringBuilder("Заполните строку слева хотя бы одним ребром(дугой).");
//            } else {
//                String[] input4 = text4.getText().split(" ");
//                if (input4.length == 1){
//                    messege = new StringBuilder("Неверный формат ввода данных. " +
//                            "Попробуйте: Исходящая_вершина Принимающая_вершина(либо несколько через запятую)");
//                }
//                else{
//                    messege = graph1.removeEdge(input4[0], input4[1].split(","));
//                }
//
//            }
//            JOptionPane.showMessageDialog(null, messege, "Всё, что вы просили, сэр.",
//                    JOptionPane.PLAIN_MESSAGE);
//        }
//    }
//}
