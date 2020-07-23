package ru.Bastrakov.Tasker.GUI;

import ru.Bastrakov.Tasker.Main;
import ru.Bastrakov.Tasker.WorkWithFiles.SLFile;
import ru.Bastrakov.Tasker.Tasks.TaskOne;
import ru.Bastrakov.Tasker.Tasks.TaskTwo;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InsertAction implements ActionListener {
    private static String answer;

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue;

        //получение имени задачи
        String comboName = Main.frame.getComboBoxTask();
        //получение строки исходных данных
        String data = Main.frame.getData();
        //полчучение выбранной задачи
        String command = e.getActionCommand();

        switch (command) {
            case ("comboBoxChanged"): {
                Main.frame.setData(null);
                break;
            }
            case ("сохранить"): {
                returnValue = chooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File savedFile = chooser.getSelectedFile();

                    String[] atd = {savedFile.getAbsolutePath(), comboName, data};
                    try {
                        SLFile.save(atd);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
                break;
            }
            case ("посчитать"): {
                if (comboName == "Task_1") {
                    doTaskOne(data);

                } else if (comboName == "Task_2") {
                    try {
                        doTaskTwo(Integer.valueOf(data));

                    } catch (NumberFormatException e1) {
                        JOptionPane pane = new JOptionPane();
                        JOptionPane.showMessageDialog(pane, "Введены неверные данные!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
            case ("загрузить"): {
                returnValue = chooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    try {
                        String[] loadf = SLFile.load(selectedFile.getAbsolutePath());
                        Main.frame.setData(loadf[1]);
                        Main.frame.setComboBoxTask(loadf[0]);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                }
                break;
            }

        }//Switch

    }//ActionPerformed

    private static void doTaskOne(String str) {

        if (str != null && str.contains("\n")) {
            //разделение полученной строки на две
            String[] arr = str.split("\n");

            try {
                //получение первого и второго массива строк, первая строка обрезана так как символ новой строки остаётся
                String[] arr1 = arr[0].substring(0, arr[0].length() - 1).split(", ");
                String[] arr2 = arr[1].split(", ");

                TaskOne taskOne = new TaskOne(arr1, arr2);
                answer = "";

                //запись ответа
                for (String s : taskOne.calculate()) answer = answer + " " + s;

                if (answer == "") {
                    Main.frame.setAnswer("Совпадений не найдено");

                } else Main.frame.setAnswer(answer);

            } catch (ArrayIndexOutOfBoundsException e) {
                Main.frame.setAnswer("Совпадений не найдено");
            }

        } else Main.frame.setAnswer("Совпадений не найдено");

    }

    private static void doTaskTwo(int num) {
        Main.frame.setAnswer(TaskTwo.expanded(num));
    }

}//class
