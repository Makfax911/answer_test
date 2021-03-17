import javax.swing.*; 
import javax.swing.border.Border;
import java.awt.*;  
import java.awt.event.*; 
import java.awt.color.*; 
import java.util.*; 
import java.util.Timer;
import java.util.TimerTask;
import java.text.*;
 
class pt extends JFrame { // ����� pt �������� ������ ����������� ������ JFrame

    private JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

    private JMenuBar menu = null;
    private final String fileItems[] = new String []{"New", "Statistic", "Exit"};
    private static Random generator = new Random(); // ��������� ��������� �����
    private int[][] numbers = new int[4][4];
    public TimerLabel tl = new TimerLabel();
    public TimerLabel2 tl2 = new TimerLabel2();
 
    pt() {
        setTitle("��������"); //��������� ����
        setSize (300, 300); // ������ ������� ���� ����������
        setLocationRelativeTo(null); // ���� ���������� ������������ ������������ ������
        setResizable(true); // ������ ����������� ����������� ����
        createMenu(); //�������������� ����
        setJMenuBar(menu); // ��������� ������ ���� � ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ��������� ��������� ��� �������� ����
        Container container = getContentPane(); 
        init();
        panel.setDoubleBuffered(true);
        panel.setBackground(Color.white); // ������������� ���� ����
        container.add(panel); // ���������� ����������� � ���������
        repaintField();
        menu.add(tl);
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1); // ������� ������� ������� �����
        Font font = new Font("Verdana", Font.PLAIN, 12); // ������ ��� ������, � ��� ������
        tl.setBorder(solidBorder); // ������������� �������
        tl.setFont(font); // ������������� ��� ������
        tl.setForeground(Color.RED); // ������������� ���� ������
    }

    void init() { // �������� ������ init
        int[] invariants = new int[16]; // �������������� ������ � ������ invariants �� 16 ��������� - �� 0 �� 15
        
        for (int i = 0; i < 4; i++) { // ���������� �������� i �� 0 �� 3
            for (int j = 0; j < 4; j++) { // ���������� �������� j �� 0 �� 3
                numbers[i][j] = 0; // ��������� ��� ������� � ����� ����������� � �������� ��������
                invariants[i*4 + j] = 0; // ���������� ����� �� 16 ��������� ����� = 0
            }
        }
 
        for (int i = 1; i < 16; i++) { // ���������� �������� i �� 1 �� 15
            int k; //��������� ���������� k ���� int
            int l; //��������� ���������� l ���� int
            do { // ���� � �������������
                k = generator.nextInt(100) % 4; // ���������� k ����������� ������������ ����� �� 0 �� 100 �������� �� ������ �� 4
                l = generator.nextInt(100) % 4; // ���������� l ����������� ������������ ����� �� 0 �� 100 �������� �� ������ �� 4
            }
            while (numbers[k][l] != 0); // �� ��� ��� ���� ��������� ������ numbers �� ����� 0
            numbers[k][l] = i; // ����������� ���������� ������� numbers �������� i � ����� �� 1 �� 15
            invariants[k*4+l] = i; // ���������� ������� ���� ��������� ����� 0 �� �����
        }

        boolean change = true; // � ������� ���������� change ������� �������� true
        int counter = 1; // �������������� ���������� counter ���� int � ����������� �� 1
        while (change) {
            change = false;
            for (int i = 0; i < 16; i++) {
                if (invariants[i] != i) {
                    for (int j = 0; j < 16; j++) {
                        if (invariants[j] == i) {
                            int temp = invariants[i];
                            invariants[i] = invariants[j];
                            invariants[j] = temp;
                            change = true;
                            counter++;
                            break;
                        }
                    }
                    break;
                }
            }
        }

        if (counter % 2 != 0) {
            int temp = numbers[0][0];
            numbers[0][0] = numbers[3][3];
            numbers[3][3] = temp;
        }
    }
    
    // ����� ��� �������� ������� � ������ ��� � JLabel
    class TimerLabel extends JLabel {
        public Timer timer = new Timer();

        public TimerLabel() {
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
            //public void scheduleAtFixedRate(TimerTask task, long delay, long period)
            //��������� �������� � ������
        }

        private TimerTask timerTask = new TimerTask() {

            private volatile int time = -1;
            /*����������� ���������� � �������� ������ volatile(�����������) ��������, 
            ��� �������� ���������� ����� ���������� ������� ��������.*/

            private Runnable refresher = new Runnable() {
                public void run(){
                    int t = time;
                    DecimalFormat decimalFormat = new DecimalFormat("00"); // ������ ������ ������ �����������
                    TimerLabel.this.setText(decimalFormat.format(t / 60) + ":" + decimalFormat.format(t % 60));
                }
            };

            public void run() {
                time++;
                SwingUtilities.invokeLater(refresher);
            }
        };

        public void StopTimer() {
            timer.cancel();
        }
    }

    public class TimerLabel2 extends JLabel {

        public Timer timer2 = new Timer();

        public TimerLabel2() {
            timer2.scheduleAtFixedRate(timerTask2, 0, 1000);
            //public void scheduleAtFixedRate(TimerTask task, long delay, long period)
            //��������� �������� � ������
        }

        private TimerTask timerTask2 = new TimerTask() {

            private volatile int time2 = -1;
            /*����������� ���������� � �������� ������ volatile(�����������) ��������, 
            ��� �������� ���������� ����� ���������� ������� ��������.*/

            private Runnable refresher = new Runnable() {
                public void run(){
                    int t = time2;
                    DecimalFormat decimalFormat = new DecimalFormat("00"); // ������ ������ ������ �����������
                    TimerLabel2.this.setText(decimalFormat.format(t / 60) + ":" + decimalFormat.format(t % 60));
                }
            };

            public void run() {
                time2++;
                SwingUtilities.invokeLater(refresher);
            }
        };

        public void StopTimer() {
            timer2.cancel();
        }
    }

    public void NewTimer(){
        menu.add(tl2);
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1); // ������� ������� ������� �����
        Font font = new Font("Verdana", Font.PLAIN, 12); // ������ ��� ������, � ��� ������
        tl2.setBorder(solidBorder); // ������������� �������
        tl2.setFont(font); // ������������� ��� ������
        tl2.setForeground(Color.RED); // ������������� ���� ������
    }

    public void repaintField() {  //����� ����������� ������ �� ���������� �� �����
        panel.removeAll();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton(Integer.toString(numbers[i][j]));
                button.setFocusable(false);
                panel.add(button);
                button.setBackground(Color.getHSBColor(0.1059322f, 0.5221239f, 0.8862745f)); // ������������� ���� ������
                if (numbers[i][j] == 0) {  
                    button.setVisible(false); // �������� �������� �������� �������
                } else
                    button.addActionListener(new ClickListener());
            }
        }
        panel.validate();
     }
 
    public boolean checkWin() { //����� �������� ��������
        boolean status = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j > 2) //�������� �� �� ��� ��������� ������ � ����� ������
                    break;
                if (numbers[i][j] != i * 4 + j + 1) { //�������� �� ����������� ��������� ������� ����������� � �����
                    status = false;
                }
            }
        }
        return status;
    }
 
    private void createMenu() {
        menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        for (int i = 0; i < fileItems.length; i++) {
            JMenuItem item = new JMenuItem(fileItems[i]);
            JSeparator separator = new JSeparator();
            item.setActionCommand(fileItems[i].toLowerCase());
            item.addActionListener(new NewMenuListener());
            fileMenu.add(item);
            fileMenu.add(separator);
        }
        //fileMenu.insertSeparator(1);
        menu.add(fileMenu);
    }

    private class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("exit".equals(command)) {
                System.exit(0);
            }
            if ("statistic".equals(command)){
                JOptionPane.showMessageDialog(null, "�� ��������!", "�����������", 1);
            }
            if ("new".equals(command)) {
                init();
                repaintField();
                tl.setVisible(false);
                tl.disable();
                NewTimer();
            }
        }
    }

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setVisible(false);
            String name = button.getText();
            change(Integer.parseInt(name));
        }
    }
 
    public void change(int num) { // �������� � �������� �������� ���������� ������ change ���������� num ���� int
        int i = 0, j = 0; // ����������� ��������� i � j ���� int �������� ������ 0
        for (int k = 0; k < 4; k++) { // ���������� �������� k �� 0 �� 3
            for (int l = 0; l < 4; l++) { // ���������� �������� l �� 0 �� 3
                if (numbers[k][l] == num) { // ���� ������ numbers[k][l] ������ ���������� num ��,
                    i = k; // ���������� i ������������ ���������� k
                    j = l; // ���������� j ������������ ���������� l
                }
            }
        }
        /*���������� ������ ������� ������ �� ����� 4 � 4*/
        //����� ����� �� �������
        if (i > 0) { // ������� ���������� �� �� ����� �� �������� ������ �� ������
            if (numbers[i - 1][j] == 0) { //���������� �������� ��������� �������� ������� � ������� ������� � ������� ������� ����� ����
                numbers[i - 1][j] = num; //����������� ���������� num �������� ��������� �������� ������� 
                numbers[i][j] = 0; //����������� ������� ������� ������� � ������ ������� ����� ���� ��������� � ����
            }
        }
        //����� ���� �� �������
        if (i < 3) {
            if (numbers[i + 1][j] == 0) {
                numbers[i + 1][j] = num;
                numbers[i][j] = 0;
            }
        }
        //����� ����� �� ��������
        if (j > 0) {
            if (numbers[i][j - 1] == 0) {
                numbers[i][j - 1] = num;
                numbers[i][j] = 0;
            }
        }
        //����� ������ �� ��������
        if (j < 3) {
            if (numbers[i][j + 1] == 0) {
                numbers[i][j + 1] = num;
                numbers[i][j] = 0;
            }
        }
        repaintField();
        if (checkWin()) {
            tl.StopTimer();
            tl2.StopTimer();
            JOptionPane.showMessageDialog(null, "�� ��������!", "�����������", 1);
            init();
            repaintField();
            setVisible(false);
            setVisible(true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame app = new pt();
        app.setVisible(true);
    }
}