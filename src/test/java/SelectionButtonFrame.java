import javax.swing.*;
import com.brioal.override_view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* This is the helper class of testSelectButton */
public class SelectionButtonFrame extends JFrame {
    public SelectionButtonFrame(){
        super("SelectionButtonFrame");
        this.setContentPane(this.createContentPane());
        this.setSize(600,600);
    }

    private JPanel createContentPane(){

        JPanel contentPane = new JPanel(new BorderLayout());
        JTextArea stateInput = new JTextArea();
        stateInput.setName("state");
        stateInput.setEditable(false);
        SelectButton button = createButton("test",stateInput);
        contentPane.add(stateInput, BorderLayout.NORTH);
        contentPane.add(button,BorderLayout.CENTER);
        return contentPane;
    }

    private SelectButton createButton(String text, JTextArea stateInput){
        SelectButton button = new SelectButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateInput.setText(button.getState());
            }
        });
        return button;
    }

    public static void main(String[] args){
        SelectionButtonFrame test = new SelectionButtonFrame();
        test.setVisible(true);
    }
}
