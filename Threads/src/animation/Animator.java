package animation;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Animator extends JFrame implements ActionListener{
	JButton start, stop;
	JPanel ballPanel;
	Ball ball;
	boolean animate;
    Timer timer;

	public Animator(){
		super("Animation");
		start = new JButton("Start");
		stop = new JButton("Stop");
		Box buttons = new Box(BoxLayout.X_AXIS);
		buttons.add(start);
		buttons.add(stop);
		getContentPane().add(buttons, BorderLayout.NORTH);
        timer = new Timer(100, this);
		ballPanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				ball.paint(g);
			}
		};
		ball = new Ball(ballPanel);
		getContentPane().add(ballPanel);
		
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				animate = true;
                worker.execute();
                timer.start();
			}
		});

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
	}

    SwingWorker worker = new SwingWorker<Ball, Void>(){
        @Override
        protected Ball doInBackground() throws Exception {
            animate = true;
            while(animate){
                repaint();
            }
            return ball;
        }
    };
	
	public static void main(String[] args){
		JFrame f = new Animator();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(300, 300, 300, 200);
		f.setVisible(true);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move();
    }
}
