package classes;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class AppFrame extends JFrame {
    //Constructor
    private TitleBar title;
    private List list;
    private ButtonPanel btnPanel;
    private JButton addTask;
    private JButton clear;
    private Task task;
    AppFrame()
    {
        this.setSize(400,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        title = new TitleBar();
        list = new List();
        btnPanel = new ButtonPanel();

        this.add(title, BorderLayout.NORTH);
        this.add(btnPanel, BorderLayout.SOUTH);
        this.add(list, BorderLayout.CENTER);


        addTask = btnPanel.getAddTask();
        clear = btnPanel.getClear();


        addListeners();
    }

    public void addListeners()
    {
        addTask.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Task task = new Task();
                list.add(task);
                list.updateNumbers();

                task.getDone().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        task.changeState();
                        revalidate();
                    }
                });
                revalidate();
            };
        });

        clear.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                list.removeCompletedTasks();
                repaint();
            }
        });
    }
}

