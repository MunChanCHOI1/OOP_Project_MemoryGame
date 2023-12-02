import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class QuizzApp extends JFrame {

	private JFrame frame;
	private JTextField answer;
	public JTextField usernumber;
	public JPanel panel;
	private JLabel life;
	private JLabel res;
	int numbercount = 5;
	int index = 0;
	int[] users;
	int[] numbers;
	int[] answering;
	JButton[] jbtn;
	int[] sortnumbers;
	boolean correct = true;
	int round = 1;


	private void initialize() {
		numbers = new int[14];
		sortnumbers = new int[14];
		users = new int[14];
		//numbercount의 역할: round별로 생성되는 숫자의 개수
		
		jbtn = new JButton[9];
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1044, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1026, 574);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		life = new JLabel("round : "+ round);
		life.setFont(new Font("Tahoma", Font.PLAIN, 40));
		life.setBounds(34, 23, 213, 46);
		panel.add(life);
		
		
		usernumber = new JTextField();
		usernumber.setFont(new Font("Tahoma", Font.PLAIN, 30));
		usernumber.setBounds(180, 232, 680, 63);
		usernumber.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(usernumber);
		usernumber.setColumns(15);
		usernumber.setEditable(false);
		usernumber.setBackground(Color.white);
		usernumber.setBorder(new LineBorder(Color.yellow, 10));
		
		
		JButton guess = new JButton("G U E S S");
		
		
		guess.setFont(new Font("Tahoma", Font.PLAIN, 50));
		guess.setBounds(162, 400, 721, 63);
		panel.add(guess);
		
		res = new JLabel("Good Luck!");
		res.setForeground(Color.BLUE);
		res.setFont(new Font("Tahoma", Font.PLAIN, 42));
		res.setHorizontalAlignment(SwingConstants.CENTER);
		res.setBounds(162, 469, 721, 57);
		panel.add(res);
		
		JLabel answering[] = new JLabel[14];
		
		
			for(int i =0; i< numbercount; i++){
				int k = (int)(Math.random()*8)+1;
				numbers[i] = k;
				sortnumbers[i] = k;
				System.out.print(numbers[i]);
				panel.add(answering[i] = new JLabel(Integer.toString(numbers[i])));
				answering[i].setBounds(30*i, 152, 680, 63);
				answering[i].setFont(new Font("Tahoma", Font.PLAIN, 40));
				answering[i].setHorizontalAlignment(SwingConstants.CENTER);
				}
			Arrays.sort(sortnumbers);
			sortnumbers = Arrays.stream(sortnumbers).distinct().toArray();
			System.out.println();
			for(int i = 1; i< sortnumbers.length;i++){
				System.out.print(sortnumbers[i]);
				panel.add(jbtn[i] = new JButton(Integer.toString(sortnumbers[i])));
				jbtn[i].setLocation(200+ 100*i, 315);
				jbtn[i].setSize(60,60);
				jbtn[i].addActionListener(new ButtonActionListener());
				jbtn[i].setHorizontalAlignment(SwingConstants.CENTER);
				}	
			

	}

    class ButtonActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		String inputValue = e.getActionCommand();
		
		usernumber.setText(usernumber.getText() + inputValue);
		users[index] = Integer.parseInt(usernumber.getText().split("")[index]);
		index++;
		
		
		if(index == numbercount){
			for(int i = 0; i< numbercount; i++){
				
					if(users[i] != numbers[i]){
					correct = false;
					res.setText("That's Wrong");
					
				}
				
			}
			if(correct == true){
				res.setText("That's Right!");
				round++;
				life.setText("round : " + round);
			}
		}
		
	}
}			
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizzApp window = new QuizzApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuizzApp() {
			initialize();
	}

}
