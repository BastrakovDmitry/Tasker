import javax.swing.*;

public class GUIFrame {
    private JButton load;
    private JPanel panel1;
    private JButton save;
    private JButton calc;
    private JComboBox comboBox1;
    private JTextArea answer;
    private JTextPane data;

    public GUIFrame() {
        InsertAction action = new InsertAction();
        JFrame frame = new JFrame("Tasker");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(500, 300);

        //buttoms
        load.addActionListener(action);
        save.addActionListener(action);
        calc.addActionListener(action);
        comboBox1.addActionListener(action);

    }

    public String getComboBoxTask() {
        return (String) comboBox1.getSelectedItem();
    }

    public void setComboBoxTask(String newTask) {
        comboBox1.setSelectedItem(newTask);
    }

    public String getData() {
        return data.getText();
    }

    public void setData(String newData) {
        data.setText(newData);
    }

    public void setAnswer(String text) {
        answer.setText(text);
    }

}