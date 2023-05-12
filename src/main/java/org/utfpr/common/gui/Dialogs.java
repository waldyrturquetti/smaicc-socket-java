package org.utfpr.common.gui;

import javax.swing.*;

public class Dialogs {

    public static void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static Integer showOptionDialog(String message) {
        Object[] options = {"Sim", "Não"};
        return JOptionPane
                .showOptionDialog(
                        new JFrame(), message, "Confirmar", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
}
