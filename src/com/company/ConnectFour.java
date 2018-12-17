package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ConnectFour extends JFrame {
    private Cell[][] cells;
    private JButton restart;
    private boolean play;
    private char whoseTurn;
    private int count = 0;
    public ConnectFour() {
        play = true;
        whoseTurn = 'R';
        cells = new Cell[6][7];
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(6, 7));
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                cells[r][c] = new Cell();
                gamePanel.add(cells[r][c]);
            }
        }
        restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int r = 0; r < 6; r++) {
                    for (int c = 0; c < 7; c++) {
                        cells[r][c].setToken(' ');
                    }
                }
                play = true;
                count = 0;
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = e.getX() / 40;
                drop(index);
                if (check() != ' ' && play) {
                    JOptionPane.showMessageDialog(null, check() + " won!");
                    play = false;
                } else if (count == 42)
                    JOptionPane.showMessageDialog(null, "It's a draw!");


            }
        });
        add(gamePanel, BorderLayout.CENTER);
        add(restart, BorderLayout.SOUTH);
    }



    public static void main(String[] args) {
        JFrame frame = new ConnectFour();
        frame.setTitle("ConnectFour");
        frame.setVisible(true);
        frame.setSize(280, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void drop(int index) {
        if (play) {
            for (int r = 5; r >= 0; --r) {
                if (!cells[r][index].isFull()) {
                    cells[r][index].setToken(whoseTurn);
                    whoseTurn = (whoseTurn == 'R') ? 'Y' : 'R';
                    count++;
                    break;

                }
            }
        }
    }

    private char check() {
        // check rows
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 4; c++) {
                if (cells[r][c].getToken() == cells[r][c+1].getToken() && cells[r][c+1].getToken() == cells[r][c+2].getToken() && cells[r][c+2].getToken() == cells[r][c+3].getToken() && cells[r][c].isFull())
                    return cells[r][c].getToken();
            }
        }

        // check columns
        for (int c = 0; c < 7; c++) {
            for (int r = 0; r < 3; r++) {
                if (cells[r][c].getToken() == cells[r+1][c].getToken() && cells[r+1][c].getToken() == cells[r+2][c].getToken() && cells[r+2][c].getToken() == cells[r+3][c].getToken()  && cells[r][c].isFull())
                    return cells[r][c].getToken();
            }
        }

        // check left-right diagonals
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                if (cells[r][c].getToken() == cells[r+1][c+1].getToken() && cells[r+1][c+1].getToken() == cells[r+2][c+2].getToken() && cells[r+2][c+2].getToken() == cells[r+3][c+3].getToken()  && cells[r][c].isFull())
                    return cells[r][c].getToken();
            }
        }

        // check right-left diagonals
        for (int r = 3; r < 6; r++) {
            for (int c = 0; c < 4; c++) {
                if (cells[r][c].getToken() == cells[r-1][c+1].getToken() && cells[r-1][c+1].getToken() == cells[r-2][c+2].getToken() && cells[r-2][c+2].getToken() == cells[r-3][c+3].getToken()  && cells[r][c].isFull())
                    return cells[r][c].getToken();
            }
        }
        return ' ';
    }
}
