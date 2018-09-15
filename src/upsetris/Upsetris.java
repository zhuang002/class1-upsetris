/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsetris;

import java.util.Scanner;

/**
 *
 * @author huang
 */
public class Upsetris {

    static Scanner sc=new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i=0;i<5;i++) {
            doTestCase();
        }
    }

    private static void doTestCase() {
        Board board=readInBoard();
        board.process();
        board.print();
    }

    private static Board readInBoard() {
        
        String row=sc.nextLine();
        int width=row.length()-2;
        Board retBoard=new Board(width);
        
        while (row.charAt(1)!='=') {
            retBoard.depth++;
            for (int i=1;i<row.length()-1;i++) {
                if (row.charAt(i)=='O') {
                    retBoard.numOfOs[i-1]++;
                }
            }
            row=sc.nextLine();
        }
        return retBoard;
    }
    
}

class Board {
    int depth=0;
    int width;
    int[] numOfOs;
    
    public Board(int w) {
        this.numOfOs=new int[w];
        this.width=w;
        for (int i=0;i<w;i++) {
            this.numOfOs[i]=0;
        }
    }
    
    void process() {
        int min=Integer.MAX_VALUE;
        for (int i=0;i<this.width;i++) {
            if (this.numOfOs[i]<min)
                min=this.numOfOs[i];
        }
        for (int i=0;i<this.width;i++) {
            this.numOfOs[i]-=min;
        }
    }
    
    void print() {
        for (int i=0;i<this.depth;i++) {
            System.out.print('|');
            for (int j=this.width-1;j>=0;j--) {
                if (i<this.depth-this.numOfOs[j]) {
                    System.out.print(" ");
                } else {
                    System.out.print('O');
                }
            }
            System.out.println('|');
        }
        System.out.print('|');
        for (int i=0;i<this.width;i++) {
            System.out.print('=');
        }
        System.out.println('|');
    }
}
