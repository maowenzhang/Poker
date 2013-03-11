package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton btnDeal = new JButton("Deal");
	JButton btnCardExchange = new JButton("Exchange Cards");

	public ControlPanel() {
		FlowLayout controlPanelLayout = new FlowLayout(FlowLayout.CENTER,20,0);
		setLayout(controlPanelLayout);

		btnDeal.addActionListener(this);
		add(btnDeal);
		
		btnCardExchange.addActionListener(this);
		btnCardExchange.setEnabled(false);
		add(btnCardExchange);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//JButton clickedButton = (JButton)actionEvent.getSource();

		if (actionEvent.getActionCommand().equals("Deal")) {
			btnDeal.setEnabled(false);
			btnCardExchange.setEnabled(true);
			System.out.println("test: " + this.p);
		}
		
		
		if (actionEvent.getActionCommand().equals("Exchange Cards")) {
			btnCardExchange.setEnabled(false);
			btnDeal.setEnabled(true);
		}
	}
}
