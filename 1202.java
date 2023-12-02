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
	//전체적인 frame
	public JTextField usernumber;
	//사용자가 적은 숫자를 보여주는 textfield
	//무슨 수의 버튼을 선택했는지를 보여준다.
	public JPanel panel;
	//frame 위의 panel. 
	//사실상 모든 component들이 panel에 소속되어 있는 상황.
	private JLabel life;
	// life라고 하지만, round를 표시하는 곳.(현재 무슨 round인지 알게 해줌.)
	private JLabel res;
	// 맞았는지, 틀렸는지 알려주는 text를 내보낼 곳.
	int numbercount = 5;
	// round별로 필요한 숫자의 개수
	// 1번째 round를 기준으로 초기화했기 때문에 5로 되어 있는 것.
	// round가 진행될수록 증가시켜야 함.
	int index = 0;
	// 이름은 index이지만, 역할로 보자면 사용자가 버튼을 누른 수를 세는 역할이다.
	// 버튼을 누른 수가 numbercount(주어진 숫자의 개수)와 동일한지를 확인해야 함.
	int[] users;
	// 사용자가 입력한 값들이 저장되는 배열
	int[] numbers;
	// 랜덤으로 숫자가 생성될 때, 해당 숫자들이 저장되는 배열
	// users와 numbers를 비교해서, 값이 모두 동일하면 정답으로 처리.
	JLabel[] answering;
	// 랜덤으로 생성된 숫자를 사용자에게 보여주는 label
	JButton[] jbtn;
	// 버튼을 저장하는 배열
	int[] sortnumbers;
	// 버튼은 랜덤으로 생성된 숫자들을 오름차순으로 표시해야 하기 때문에 오름차순으로 정렬한 배열.
	boolean correct = true;
	// 답이 맞았는지 틀렸는지를 확인하기 위한 변수
	int round = 1;
	// round를 세는 변수


	private void initialize() {
		numbers = new int[14];
		sortnumbers = new int[14];
		users = new int[14];
		
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
		
		answering = new JLabel[14];
		
		
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
			System.out.println();
			

	}
	
	private void generateNumbers(){
		for(int i = 0; i<numbercount; i++){
			if(answering[i] != null){
				panel.remove(answering[i]);
			}
		}
		for(int i=0; i< sortnumbers.length; i++){
			if(jbtn[i] != null){
				panel.remove(jbtn[i]);
			}
		}
		 for(int i =0; i< numbercount; i++){
	         int k = (int)(Math.random()*8)+1;
	         numbers[i] = k;
	         sortnumbers[i] = k;
	         System.out.print(numbers[i]);
	         panel.add(new JLabel(Integer.toString(numbers[i])));
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
				index = 0;
				correct = true;
				generateNumbers();
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
	
	

