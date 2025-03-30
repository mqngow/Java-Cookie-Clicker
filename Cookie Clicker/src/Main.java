import audio.AudioHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main implements ActionListener{

    //Separate panels
    JPanel cookie = new JPanel(); //Displays the clickable cookie
    JPanel clickerUpgrades = new JPanel(); //Where upgrades are shown
    JPanel passiveUpgrades = new JPanel(); //The upgrade buttons

    //Dimension and screen sizing
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double width = screenSize.getWidth();
    private final double height = screenSize.getHeight();
    private int widthInt =  (int) screenSize.getWidth();
    private int heightInt =  (int) screenSize.getHeight();
    private int timerSpeed;
    private double cookiesPerSecond, cookieCount;
    private boolean timerOn = false;
    Timer timer;
    AudioHandler audioH = new AudioHandler();

    //Images
    Image cookieUnpressed = ImageIO.read(Main.class.getResource("/images/cookieUnpressed.png")).getScaledInstance((widthInt * 400)/2560, (heightInt*400)/1440, Image.SCALE_DEFAULT);
    Image cookiePressed = ImageIO.read(Main.class.getResource("/images/cookiePressed.png")).getScaledInstance((widthInt * 400)/2560, (heightInt*400)/1440, Image.SCALE_DEFAULT);
    Image pu1 = ImageIO.read(Main.class.getResource("/images/PU1.png"));
    Image pu2 = ImageIO.read(Main.class.getResource("/images/PU2.png"));
    Image pu3 = ImageIO.read(Main.class.getResource("/images/PU3.png"));
    Image pu4 = ImageIO.read(Main.class.getResource("/images/PU4.png"));
    Image pu5 = ImageIO.read(Main.class.getResource("/images/PU5.png"));
    Image pu6 = ImageIO.read(Main.class.getResource("/images/PU6.png"));
    Image cu1 = ImageIO.read(Main.class.getResource("/images/CU1.png"));
    Image cu2 = ImageIO.read(Main.class.getResource("/images/CU2.png"));
    Image cu3 = ImageIO.read(Main.class.getResource("/images/CU3.png"));
    Image cu4 = ImageIO.read(Main.class.getResource("/images/CU4.png"));
    Image cu5 = ImageIO.read(Main.class.getResource("/images/CU5.png"));
    Image cu6 = ImageIO.read(Main.class.getResource("/images/CU6.png"));
    String purchase = "C:\\Users\\magnu\\Documents\\Cookie Clicker\\src\\audio\\purchase.wav";
    String cannotPurchase = "C:\\Users\\magnu\\Documents\\Cookie Clicker\\src\\audio\\cannotPurchase.wav";

    //Cookie
    JButton cookieIcon = new JButton(new ImageIcon(cookieUnpressed));
    JLabel cookieC = new JLabel("Cookies: 0", SwingConstants.CENTER);
    JLabel cookiesPS = new JLabel("Cookie Per Second: 0", SwingConstants.CENTER);
    private int cookieInc = 1;

    //Clicker Upgrades
    JLabel cuTitle = new JLabel("Upgrade", SwingConstants.CENTER);
    JLabel cuTitle2 = new JLabel("your", SwingConstants.CENTER);
    JLabel cuTitle3 = new JLabel("clicker!", SwingConstants.CENTER);

    //Upgrade 1
    JLabel CU1 = new JLabel(new ImageIcon(cu1));
    JButton CU1Buy = new JButton("100 Cookies");
    JLabel CU1s = new JLabel("Not Purchased", SwingConstants.CENTER);
    private boolean CU1Bought = false;

    //Upgrade 2
    JLabel CU2 = new JLabel(new ImageIcon(cu2));
    JButton CU2Buy = new JButton("500 Cookies");
    JLabel CU2s = new JLabel("Not Purchased", SwingConstants.CENTER);
    private boolean CU2Bought = false;

    //Upgrade 3
    JLabel CU3 = new JLabel(new ImageIcon(cu3));
    JButton CU3Buy = new JButton("2500 Cookies");
    JLabel CU3s = new JLabel("Not Purchased", SwingConstants.CENTER);
    private boolean CU3Bought = false;

    //Upgrade 4
    JLabel CU4 = new JLabel(new ImageIcon(cu4));
    JButton CU4Buy = new JButton("10000 Cookies");
    JLabel CU4s = new JLabel("Not Purchased", SwingConstants.CENTER);
    private boolean CU4Bought = false;

    //Upgrade 5
    JLabel CU5 = new JLabel(new ImageIcon(cu5));
    JButton CU5Buy = new JButton("100000 Cookies");
    JLabel CU5s = new JLabel("Not Purchased", SwingConstants.CENTER);
    private boolean CU5Bought = false;

    //Upgrade 6
    JLabel CU6 = new JLabel(new ImageIcon(cu6));
    JButton CU6Buy = new JButton("1000000 Cookies");
    JLabel CU6s = new JLabel("Not Purchased", SwingConstants.CENTER);
    private boolean CU6Bought = false;

    //Passive Upgrades
    JLabel puTitle = new JLabel("Get", SwingConstants.CENTER);
    JLabel puTitle2 = new JLabel("passive", SwingConstants.CENTER);
    JLabel puTitle3 = new JLabel("cookies!", SwingConstants.CENTER);

    //Upgrade 1
    JLabel PU1 = new JLabel(new ImageIcon(pu1));
    JButton PU1Buy = new JButton("100 Cookies");
    JLabel PU1s = new JLabel("0", SwingConstants.CENTER);
    private int amt1 = 0;

    //Upgrade 2
    JLabel PU2 = new JLabel(new ImageIcon(pu2));
    JButton PU2Buy = new JButton("1000 Cookies");
    JLabel PU2s = new JLabel("0", SwingConstants.CENTER);
    private int amt2 = 0;

    //Upgrade 3
    JLabel PU3 = new JLabel(new ImageIcon(pu3));
    JButton PU3Buy = new JButton("5000 Cookies");
    JLabel PU3s = new JLabel("0", SwingConstants.CENTER);
    private int amt3 = 0;

    //Upgrade 4
    JLabel PU4 = new JLabel(new ImageIcon(pu4));
    JButton PU4Buy = new JButton("10000 Cookies");
    JLabel PU4s = new JLabel("0", SwingConstants.CENTER);
    private int amt4 = 0;

    //Upgrade 5
    JLabel PU5 = new JLabel(new ImageIcon(pu5));
    JButton PU5Buy = new JButton("100000 Cookies");
    JLabel PU5s = new JLabel("0", SwingConstants.CENTER);
    private int amt5 = 0;

    //Upgrade 6
    JLabel PU6 = new JLabel(new ImageIcon(pu6));
    JButton PU6Buy = new JButton("100000000 Cookies");
    JLabel PU6s = new JLabel("0", SwingConstants.CENTER);
    private int amt6 = 0;

    //Spaces
    JLabel bottomText = new JLabel("More", SwingConstants.CENTER);
    JLabel bottomText2 = new JLabel("coming", SwingConstants.CENTER);
    JLabel bottomText3 = new JLabel("soon!", SwingConstants.CENTER);
    JLabel bottomText4 = new JLabel("More", SwingConstants.CENTER);
    JLabel bottomText5 = new JLabel("coming", SwingConstants.CENTER);
    JLabel bottomText6 = new JLabel("soon!", SwingConstants.CENTER);

    //Constraints

    GridBagConstraints cons = new GridBagConstraints();
    GridBagConstraints consC = new GridBagConstraints(); //cons for cookie
    GridBagConstraints consCU = new GridBagConstraints(); //cons for clicker upgrades
    GridBagConstraints consPU = new GridBagConstraints(); //cons for upgrade buttons

    public Main() throws IOException {

        JFrame mainFrame = new JFrame();

        mainFrame.setSize(1250, 1250);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the window when the close button is clicked
        mainFrame.setTitle("Cookie Clicker");
        mainFrame.setUndecorated(true);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);

        //Cookie

        cookieIcon.setPressedIcon(new ImageIcon(cookiePressed));
        cookieIcon.setBorder(null);
        cookieIcon.setContentAreaFilled(false);
        cookieIcon.setFocusPainted(false);
        cookieIcon.setBorderPainted(false);

        //ToolTips

        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        UIManager.put("ToolTip.font", new Font("Century Gothic", Font.PLAIN, 14));

        //Cookie Counter

        cookieCount = 0;

        //Three Main Panels

        //Cookie Panel

        cookie.setBounds(widthInt/4, 0, widthInt/2, heightInt);
        cookie.setLayout(new GridLayout(3,1));
        consC.gridx = 0;
        consC.gridy = 0;
        consC.anchor = GridBagConstraints.CENTER;
        cookie.add(cookieC, consC);
        consC.gridx = 1;
        cookie.add(cookieIcon, consC);
        consC.gridx = 2;
        cookie.add(cookiesPS, consC);

        cookieC.setFont(new Font("Monospaced", Font.BOLD, 35));
        cookiesPS.setFont(new Font("Monospaced", Font.BOLD, 35));

        //Clicker Upgrades Panel

        clickerUpgrades.setBounds(0, 0, widthInt/4, heightInt);
        clickerUpgrades.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        clickerUpgrades.setLayout(new GridLayout(8, 3));
        consCU.gridx = 0;
        consCU.gridy = 0;

        cuTitle.setFont(new Font("Monospaced", Font.BOLD, 30));
        clickerUpgrades.add(cuTitle, consCU);

        consCU.gridy = 1;

        cuTitle2.setFont(new Font("Monospaced", Font.BOLD, 30));
        clickerUpgrades.add(cuTitle2, consCU);

        consCU.gridy = 2;

        cuTitle3.setFont(new Font("Monospaced", Font.BOLD, 30));
        clickerUpgrades.add(cuTitle3, consCU);

        //Upgrade 1

        consCU.gridx = 1;
        consCU.gridy = 0;
        clickerUpgrades.add(CU1, consCU);

        consCU.gridy = 1;

        clickerUpgrades.add(CU1Buy, consCU);
        CU1Buy.setToolTipText("<html>Ice Pointer<br>The cold makes you shiver, clicking faster.<br>(Doubles the amount you get per click)");
        CU1Buy.setContentAreaFilled(false);
        CU1Buy.setFocusPainted(false);

        consCU.gridy = 2;

        clickerUpgrades.add(CU1s, consCU);

        //Upgrade 2

        consCU.gridx = 2;
        consCU.gridy = 0;

        clickerUpgrades.add(CU2, consCU);
        consCU.gridy = 1;

        clickerUpgrades.add(CU2Buy, consCU);
        CU2Buy.setToolTipText("<html>Fire Pointer<br>The fire puts you in a panic.<br>(Triples the amount you get per click)");
        CU2Buy.setContentAreaFilled(false);
        CU2Buy.setFocusPainted(false);

        consCU.gridy = 2;

        clickerUpgrades.add(CU2s, consCU);

        //Upgrade 3

        consCU.gridx = 3;
        consCU.gridy = 0;

        clickerUpgrades.add(CU3, consCU);
        consCU.gridy = 1;

        clickerUpgrades.add(CU3Buy, consCU);
        CU3Buy.setToolTipText("<html>Reinforced Pointer<br>The metal reinforces your clicks.<br>(Quadruples the amount you get per click)");
        CU3Buy.setContentAreaFilled(false);
        CU3Buy.setFocusPainted(false);

        consCU.gridy = 2;

        clickerUpgrades.add(CU3s, consCU);

        //Upgrade 4

        consCU.gridx = 4;
        consCU.gridy = 0;

        clickerUpgrades.add(CU4, consCU);
        consCU.gridy = 1;

        clickerUpgrades.add(CU4Buy, consCU);
        CU4Buy.setToolTipText("<html>Gold Pointer<br>You've gotten far, the gold makes your clicks extra potent.<br>(Quintuples the amount you get per click)");
        CU4Buy.setContentAreaFilled(false);
        CU4Buy.setFocusPainted(false);

        consCU.gridy = 2;

        clickerUpgrades.add(CU4s, consCU);

        //Upgrade 5

        consCU.gridx = 5;
        consCU.gridy = 0;

        clickerUpgrades.add(CU5, consCU);
        consCU.gridy = 1;

        clickerUpgrades.add(CU5Buy, consCU);
        CU5Buy.setToolTipText("<html>Explosive Pointer<br>Makes your clicks explosive blowing up cookies.<br>(Sextuples the amount you get per click)");
        CU5Buy.setContentAreaFilled(false);
        CU5Buy.setFocusPainted(false);

        consCU.gridy = 2;

        clickerUpgrades.add(CU5s, consCU);

        //Upgrade 6

        consCU.gridx = 6;
        consCU.gridy = 0;

        clickerUpgrades.add(CU6, consCU);
        consCU.gridy = 1;

        clickerUpgrades.add(CU6Buy, consCU);
        CU6Buy.setToolTipText("<html>Black Hole<br>The sheer gravity of your clicks demolish cookies.<br>(Multiplies the amount of cookies you get per click by 10)");
        CU6Buy.setContentAreaFilled(false);
        CU6Buy.setFocusPainted(false);

        consCU.gridy = 2;

        clickerUpgrades.add(CU6s, consCU);

        //Placeholder

        consCU.gridx = 7;
        consCU.gridy = 0;

        bottomText.setFont(new Font("Monospaced", Font.BOLD, 30));
        clickerUpgrades.add(bottomText, consCU);

        consCU.gridy = 1;

        bottomText2.setFont(new Font("Monospaced", Font.BOLD, 30));
        clickerUpgrades.add(bottomText2, consCU);

        consCU.gridy = 2;

        bottomText3.setFont(new Font("Monospaced", Font.BOLD, 30));
        clickerUpgrades.add(bottomText3, consCU);

        //Passive Upgrades Panel

        passiveUpgrades.setBounds(widthInt * 3/4, 0, widthInt/4, heightInt);
        passiveUpgrades.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        passiveUpgrades.setLayout(new GridLayout(8, 3));
        consPU.gridx = 0;
        consPU.gridy = 0;

        puTitle.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(puTitle, consPU);

        consPU.gridy = 1;
        puTitle2.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(puTitle2, consPU);

        consPU.gridy = 2;
        puTitle3.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(puTitle3, consPU);

        //Upgrade 1

        consPU.gridx = 1;
        consPU.gridy = 0;

        passiveUpgrades.add(PU1, consPU);

        consPU.gridy = 1;
        passiveUpgrades.add(PU1Buy, consPU);
        PU1Buy.setToolTipText("<html>Cursor<br>Helps you click cookies.<br>(Generates 1 cookie per second)");
        PU1Buy.setContentAreaFilled(false);
        PU1Buy.setFocusPainted(false);

        consPU.gridy = 2;
        PU1s.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(PU1s, consPU);

        //Upgrade 2

        consPU.gridx = 2;
        consPU.gridy = 0;

        passiveUpgrades.add(PU2, consPU);

        consPU.gridy = 1;
        passiveUpgrades.add(PU2Buy, consPU);
        PU2Buy.setToolTipText("<html>Grandpa<br>Bakes a batch of cookies.<br>(Generates 12 cookies per second)");
        PU2Buy.setContentAreaFilled(false);
        PU2Buy.setFocusPainted(false);

        consPU.gridy = 2;
        PU2s.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(PU2s, consPU);

        //Upgrade 3

        consPU.gridx = 3;
        consPU.gridy = 0;

        passiveUpgrades.add(PU3, consPU);

        consPU.gridy = 1;
        passiveUpgrades.add(PU3Buy, consPU);
        PU3Buy.setToolTipText("<html>Cookie Farm<br>The farm mass produces cookies from the ground!<br>(Generates 55 cookies per second)");
        PU3Buy.setContentAreaFilled(false);
        PU3Buy.setFocusPainted(false);

        consPU.gridy = 2;
        PU3s.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(PU3s, consPU);

        //Upgrade 4

        consPU.gridx = 4;
        consPU.gridy = 0;

        passiveUpgrades.add(PU4, consPU);

        consPU.gridy = 1;
        passiveUpgrades.add(PU4Buy, consPU);
        PU4Buy.setToolTipText("<html>Cookie Mine<br>The mining of cookies is booming!<br>(Generates 125 cookies per second)");
        PU4Buy.setContentAreaFilled(false);
        PU4Buy.setFocusPainted(false);

        consPU.gridy = 2;
        PU4s.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(PU4s, consPU);

        //Upgrade 5

        consPU.gridx = 5;
        consPU.gridy = 0;

        passiveUpgrades.add(PU5, consPU);

        consPU.gridy = 1;
        passiveUpgrades.add(PU5Buy, consPU);
        PU5Buy.setToolTipText("<html>Cookie Factory<br>Ultimate mass production of cookies.<br>(Generates 1500 cookies per second)");
        PU5Buy.setContentAreaFilled(false);
        PU5Buy.setFocusPainted(false);

        consPU.gridy = 2;
        PU5s.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(PU5s, consPU);

        //Upgrade 6

        consPU.gridx = 6;
        consPU.gridy = 0;

        passiveUpgrades.add(PU6, consPU);

        consPU.gridy = 1;
        passiveUpgrades.add(PU6Buy, consPU);
        PU6Buy.setToolTipText("<html>Cookie Bank<br>All of these cookies are going to inflate the economy!<br>(Generates 2000000 cookies per second)");
        PU6Buy.setContentAreaFilled(false);
        PU6Buy.setFocusPainted(false);

        consPU.gridy = 2;
        PU6s.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(PU6s, consPU);

        //Placeholder

        consPU.gridx = 7;
        consPU.gridy = 0;

        bottomText4.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(bottomText4, consPU);

        consPU.gridy = 1;
        bottomText5.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(bottomText5, consPU);

        consPU.gridy = 2;
        bottomText6.setFont(new Font("Monospaced", Font.BOLD, 28));
        passiveUpgrades.add(bottomText6, consPU);

        //Action Listeners

        cookieIcon.addActionListener(this);
        CU1Buy.addActionListener(this);
        CU2Buy.addActionListener(this);
        CU3Buy.addActionListener(this);
        CU4Buy.addActionListener(this);
        CU5Buy.addActionListener(this);
        CU6Buy.addActionListener(this);
        PU1Buy.addActionListener(this);
        PU2Buy.addActionListener(this);
        PU3Buy.addActionListener(this);
        PU4Buy.addActionListener(this);
        PU5Buy.addActionListener(this);
        PU6Buy.addActionListener(this);

        //Setting up main frame

        cons.gridx = 0;
        cons.gridy = 0;
        mainFrame.add(cookie, cons);

        cons.gridx = 1;
        mainFrame.add(clickerUpgrades, cons);

        cons.gridx = 2;
        mainFrame.add(passiveUpgrades);
    }

    public void setTimer() { //Set timer for cookies

        timer = new Timer(timerSpeed, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cookieCount ++;
                cookieC.setText("Cookies: " + (int)cookieCount);
            }
        });
    }

    public void updateTimer() { //Update timer speed

        if(timerOn == false) {
            timerOn = true;
        }
        else if(timerOn == true) {
            timer.stop();
        }

        double speed = (1/cookiesPerSecond)*1000; //Takes the amount of cookies per second and multiplies it by 1000 for milliseconds.
        timerSpeed = (int)Math.round(speed);

        setTimer();
        timer.start();
    }

    public static void main(String[] args) throws IOException { Main main = new Main(); }


    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == cookieIcon) {
            cookieCount += cookieInc;
            cookieC.setText("Cookies: " + (int)cookieCount);
        }
        if (evt.getSource() == CU1Buy) {
            if(cookieCount >= 100) {
                if(CU1Bought == false) {
                    CU1Bought = true;
                    cookieInc *= 2;
                    cookieCount -= 100;
                    cookieC.setText("Cookies: " + (int)cookieCount);
                    CU1s.setText("Purchased");
                    audioH.playSound(purchase);
                }
                else {
                    audioH.playSound(cannotPurchase);
                }
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == CU2Buy) {
            if(cookieCount >= 500) {
                if(CU2Bought == false) {
                    CU2Bought = true;
                    cookieInc *= 3;
                    cookieCount -= 500;
                    cookieC.setText("Cookies: " + (int)cookieCount);
                    CU2s.setText("Purchased");
                    audioH.playSound(purchase);
                }
                else {
                    audioH.playSound(cannotPurchase);
                }
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == CU3Buy) {
            if(cookieCount >= 2500) {
                if(CU3Bought == false) {
                    CU3Bought = true;
                    cookieInc *= 4;
                    cookieCount -= 2500;
                    cookieC.setText("Cookies: " + (int)cookieCount);
                    CU3s.setText("Purchased");
                    audioH.playSound(purchase);
                }
                else {
                    audioH.playSound(cannotPurchase);
                }
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == CU4Buy) {
            if(cookieCount >= 10000) {
                if(CU4Bought == false) {
                    CU4Bought = true;
                    cookieInc *= 5;
                    cookieCount -= 10000;
                    cookieC.setText("Cookies: " + (int)cookieCount);
                    CU4s.setText("Purchased");
                    audioH.playSound(purchase);
                }
                else {
                    audioH.playSound(cannotPurchase);
                }
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == CU5Buy) {
            if(cookieCount >= 100000) {
                if(CU5Bought == false) {
                    CU5Bought = true;
                    cookieInc *= 6;
                    cookieCount -= 100000;
                    cookieC.setText("Cookies: " + (int)cookieCount);
                    CU5s.setText("Purchased");
                    audioH.playSound(purchase);
                }
                else {
                    audioH.playSound(cannotPurchase);
                }
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == CU6Buy) {
            if(cookieCount >= 1000000) {
                if(CU6Bought == false) {
                    CU6Bought = true;
                    cookieInc *= 10;
                    cookieCount -= 1000000;
                    cookieC.setText("Cookies: " + (int)cookieCount);
                    CU6s.setText("Purchased");
                    audioH.playSound(purchase);
                }
                else {
                    audioH.playSound(cannotPurchase);
                }
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == PU1Buy) {
            if(cookieCount >= 100) {
                cookieCount -= 100;
                cookiesPerSecond += 1;
                amt1++;

                PU1s.setText(amt1 + "");
                cookieC.setText("Cookies: " + (int) cookieCount);
                cookiesPS.setText("Cookies Per Second: " + (int) cookiesPerSecond);
                updateTimer();
                audioH.playSound(purchase);
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == PU2Buy) {
            if(cookieCount >= 1000) {
                cookieCount -= 1000;
                cookiesPerSecond += 12;
                amt2++;

                PU2s.setText(amt2 + "");
                cookieC.setText("Cookies: " + (int) cookieCount);
                cookiesPS.setText("Cookies Per Second: " + (int) cookiesPerSecond);
                updateTimer();
                audioH.playSound(purchase);
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == PU3Buy) {
            if(cookieCount >= 5000) {
                cookieCount -= 5000;
                cookiesPerSecond += 55;
                amt3++;

                PU3s.setText(amt3 + "");
                cookieC.setText("Cookies: " + (int) cookieCount);
                cookiesPS.setText("Cookies Per Second: " + (int) cookiesPerSecond);
                updateTimer();
                audioH.playSound(purchase);
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == PU4Buy) {
            if(cookieCount >= 10000) {
                cookieCount -= 10000;
                cookiesPerSecond += 125;
                amt4++;

                PU4s.setText(amt4 + "");
                cookieC.setText("Cookies: " + (int) cookieCount);
                cookiesPS.setText("Cookies Per Second: " + (int) cookiesPerSecond);
                updateTimer();
                audioH.playSound(purchase);
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == PU5Buy) {
            if(cookieCount >= 100000) {
                cookieCount -= 100000;
                cookiesPerSecond += 1500;
                amt5++;

                PU5s.setText(amt5 + "");
                cookieC.setText("Cookies: " + (int) cookieCount);
                cookiesPS.setText("Cookies Per Second: " + (int) cookiesPerSecond);
                updateTimer();
                audioH.playSound(purchase);
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
        if (evt.getSource() == PU6Buy) {
            if(cookieCount >= 10000000) {
                cookieCount -= 10000000;
                cookiesPerSecond += 2000000;
                amt6++;

                PU6s.setText(amt6 + "");
                cookieC.setText("Cookies: " + (int) cookieCount);
                cookiesPS.setText("Cookies Per Second: " + (int) cookiesPerSecond);
                updateTimer();
                audioH.playSound(purchase);
            }
            else {
                audioH.playSound(cannotPurchase);
            }
        }
    }




}