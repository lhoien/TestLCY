package com.calculate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
 

public class Calculator extends JFrame implements ActionListener {
    
    private final String[] KEYS = { "7", "8", "9", "/", "sqrt", "4", "5", "6",
            "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "=" };
    
    private final String[] COMMAND = { "Backspace", "CE", "C" };
    
    private final String[] M = { " ", "MC", "MR", "MS", "M+" };
    
    private JButton keys[] = new JButton[KEYS.length];
    
    private JButton commands[] = new JButton[COMMAND.length];
    
    private JButton m[] = new JButton[M.length];
    
    private JTextField resultText = new JTextField("0");
    
    
 
    public String getResultText() {
		return String.valueOf(getNumberFromText());
	}

	public void setResultText(String resultText) {
		this.resultText = new JTextField(resultText);
	}

	
    private boolean firstDigit = true;
    
    private double resultNum = 0.0;
    
    private String operator = "=";
    
    private boolean operateValidFlag = true;
 
    
    public Calculator() {
        super();
        
        init();
        
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("SX1716067LCY");
        
        this.setLocation(500, 300);
        
        this.setResizable(false);
        
        this.pack();
        
    }
 
    
    private void init() {
    	
		//this.setResultNum(15.6);
		//this.setResultText("15.6");
		//this.handleOperator("+");
		//this.handleOperator("=");
    	//System.out.println(this.getResultNum());
       
        resultText.setHorizontalAlignment(JTextField.RIGHT);
        
        resultText.setEditable(false);
        
        resultText.setBackground(Color.WHITE);
 
       
        JPanel calckeysPanel = new JPanel();
        
        calckeysPanel.setLayout(new GridLayout(4, 5, 3, 3));
        for (int i = 0; i < KEYS.length; i++) {
            keys[i] = new JButton(KEYS[i]);
            calckeysPanel.add(keys[i]);
            keys[i].setForeground(Color.blue);
        }
        
        keys[3].setForeground(Color.red);
        keys[8].setForeground(Color.red);
        keys[13].setForeground(Color.red);
        keys[18].setForeground(Color.red);
        keys[19].setForeground(Color.red);
 
        
        JPanel commandsPanel = new JPanel();
        
        commandsPanel.setLayout(new GridLayout(1, 3, 3, 3));
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i] = new JButton(COMMAND[i]);
            commandsPanel.add(commands[i]);
            commands[i].setForeground(Color.red);
        }
 
        
        JPanel calmsPanel = new JPanel();
        
        calmsPanel.setLayout(new GridLayout(5, 1, 3, 3));
        for (int i = 0; i < M.length; i++) {
            m[i] = new JButton(M[i]);
            calmsPanel.add(m[i]);
            m[i].setForeground(Color.red);
        }
 
        
        JPanel panel1 = new JPanel();
        
        panel1.setLayout(new BorderLayout(3, 3));
        panel1.add("North", commandsPanel);
        panel1.add("Center", calckeysPanel);
 
        
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add("Center", resultText);
 
        
        getContentPane().setLayout(new BorderLayout(3, 5));
        getContentPane().add("North", top);
        getContentPane().add("Center", panel1);
        getContentPane().add("West", calmsPanel);
        
        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this);
        }
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i].addActionListener(this);
        }
        for (int i = 0; i < M.length; i++) {
            m[i].addActionListener(this);
        }
    }
 
   
    public void actionPerformed(ActionEvent e) {
        
        String label = e.getActionCommand();
        if (label.equals(COMMAND[0])) {
            
            handleBackspace();
        } else if (label.equals(COMMAND[1])) {
            
            resultText.setText("0");
        } else if (label.equals(COMMAND[2])) {
            
            handleC();
        } else if ("0123456789.".indexOf(label) >= 0) {
            
            handleNumber(label);
            // handlezero(zero);
        } else {
           
            handleOperator(label);
        }
    }
 
    
    public void handleBackspace() {
        String text = resultText.getText();
        int i = text.length();
        if (i > 0) {
            
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            } else {
                
                resultText.setText(text);
            }
        }
    }
 
    /**
     *
     *
     * @param key
     */
    public void handleNumber(String key) {
        if (firstDigit) {
            
            resultText.setText(key);
        } else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {
            
            resultText.setText(resultText.getText() + ".");
        } else if (!key.equals(".")) {
            
            resultText.setText(resultText.getText() + key);
        }
        
        firstDigit = false;
    }
 
    public void handleC() {
        
        resultText.setText("0");
        firstDigit = true;
        operator = "=";
    }
 
    /**
     * 
     *
     * @param key
     */
    public void handleOperator(String key) {
        if (operator.equals("/")) {
            
            
            if (getNumberFromText() == 0.0) {
                operateValidFlag = false;
                resultText.setText("闄ゆ暟涓嶈兘涓洪�?");
            } else {
                resultNum /= getNumberFromText();
            }
        } else if (operator.equals("1/x")) {
            if (resultNum == 0.0) {
                operateValidFlag = false;
                resultText.setText("闆舵病鏈夊�掓暟");
            } else {
                resultNum = 1 / resultNum;
            }
        } else if (operator.equals("+")) {
            resultNum += getNumberFromText();
        } else if (operator.equals("-")) {
            resultNum -= getNumberFromText();
        } else if (operator.equals("*")) {
            resultNum *= getNumberFromText();
        } else if (operator.equals("sqrt")) {
            resultNum = Math.sqrt(resultNum);
        } else if (operator.equals("%")) {
            resultNum = resultNum / 100;
        } else if (operator.equals("+/-")) {
            resultNum = resultNum * (-1);
        } else if (operator.equals("=")) {
            resultNum = getNumberFromText();
        }
        if (operateValidFlag) {
            long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                resultText.setText(String.valueOf(t1));
            } else {
                resultText.setText(String.valueOf(resultNum));
            }
        }
        operator = key;
        firstDigit = true;
        operateValidFlag = true;
    }
 
    /**
     * 
     *
     * @return
     */
    public double getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException e) {
        }
        return result;
    }
 
    public static void main(String args[]) {
        Calculator calculator1 = new Calculator();
        calculator1.setVisible(true);
        calculator1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public double getResultNum() {
		return resultNum;
	}

	public void setResultNum(double resultNum) {
		this.resultNum = resultNum;
	}
    
    
}
