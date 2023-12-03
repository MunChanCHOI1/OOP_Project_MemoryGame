package MemoryGame1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoryGame extends JFrame {

    private Image initBackground;

    public MemoryGame() {
        setTitle("MemoryGame");
        setSize(1380, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initBackground = new ImageIcon(MemoryGame.class.getResource("initBackground.jpg")).getImage();

        // 게임 패널 초기화
        GamePanel gamePanel = new GamePanel();

        // 버튼 초기화
        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");
        JButton gameInfoButton = new JButton("Game Info");
        JButton rankingButton = new JButton("Ranking");

        // 버튼에 ActionListener 등록
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game Started!");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game Exited!");
                System.exit(0);
            }
        });

        gameInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameInfoDialog();
            }
        });

        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ranking Information");
            }
        });

        // 버튼을 담을 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(gameInfoButton);
        buttonPanel.add(rankingButton);

        // 프레임에 게임 패널과 버튼 패널 추가
        add(gamePanel);
        add(buttonPanel, "South");

        setVisible(true);
    }

    private void showGameInfoDialog() {
        JDialog gameInfoDialog = new JDialog(this, "Game Information", true);

        // 게임 규칙을 나타내는 텍스트
        String gameRules = "간단한 기억력 게임 입니다."
                + "주어지는 시간은 10~ 15초\n"
                + "화면에 주어지는 숫자 배열을 맞추는 게임입니다.\n"
                + "힌트는 총 5개가 제공 되며 맞춰야 하는 숫자 배열을 다시 한번 보여줍니다.\n"
                + "라운드는 총 10 라운드 라운드가 진행될수록 맞춰야 하는 숫자의 개수는 1개씩 늘어납니다.\n";

        JTextArea infoTextArea = new JTextArea(gameRules);
        infoTextArea.setEditable(false);
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(infoTextArea);

        gameInfoDialog.add(scrollPane);
        gameInfoDialog.setSize(400, 250);
        gameInfoDialog.setLocationRelativeTo(this);
        gameInfoDialog.setVisible(true);
    }

    public static void main(String[] args) {
        new MemoryGame();
    }

    class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(initBackground, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
