package org.pedrovh.gui;

import javax.swing.*;

public class JOPMessages {

    public static void nullSelectionMessage(){
        JOptionPane.showMessageDialog(null, "Selecione uma linha primeiro!");
    }

    public static void numberBoundsMessage(int number, int min, int max){
        if(number < min || number > max)
            JOptionPane.showMessageDialog(null, "Insira um número entre " + min +" e " + max);
    }

    public static void numberFormatMessage(){
        JOptionPane.showMessageDialog(null, "Insira um número!");
    }

    public static void cantReadSaveMessage(String filepath){
        JOptionPane.showMessageDialog(null, "O arquivo " + filepath + " não pôde ser lido.");
    }
}
