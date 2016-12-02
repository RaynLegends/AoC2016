package net.raynlegends.aoc2016.managers;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JEditorPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.raynlegends.aoc2016.AoC;
import net.raynlegends.aoc2016.puzzles.Puzzle;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4472592067316936435L;
	private static final Color mainForeground = new Color(240, 240, 240);
	private static final Color mainBackground = new Color(64, 64, 64);
	private static final Color componentForeground = new Color(240, 240, 240);
	private static final Color componentBackground = new Color(96, 96, 96);

	private GridBagLayout layout;
	private JEditorPane inputField;
	private JButton calculateButton;
	private JTextField outputField;
	private JComboBox<String> puzzleComboBox;
	private JComboBox<String> partComboBox;

	public GuiManager() {
		super("AoC2016");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setSize(new Dimension(550, 300));
		setPreferredSize(new Dimension(550, 300));
		setMinimumSize(new Dimension(550, 300));

		layout = new GridBagLayout();
		layout.columnWidths = new int[]{0, 0, 0, 0};
		layout.rowHeights = new int[]{200, 50, 50, 0};
		layout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setForeground(mainForeground);
		getContentPane().setBackground(mainBackground);
		getContentPane().setLayout(layout);

		inputField = new JEditorPane();
		inputField.setBorder(null);
		inputField.setBorder(BorderFactory.createCompoundBorder(inputField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 25)));
		GridBagConstraints gbcInput = new GridBagConstraints();
		gbcInput.gridwidth = 3;
		gbcInput.insets = new Insets(10, 10, 5, 10);
		gbcInput.fill = GridBagConstraints.BOTH;
		gbcInput.gridx = 0;
		gbcInput.gridy = 0;
		inputField.setForeground(componentForeground);
		inputField.setBackground(componentBackground);
		JScrollPane inputFieldPane = new JScrollPane(inputField);
		inputFieldPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(inputFieldPane, gbcInput);

		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String input = inputField.getText();
				Puzzle puzzle = AoC.getInstance().getPuzzleManager().getPuzzle((String) puzzleComboBox.getSelectedItem());
				String part = (String) partComboBox.getSelectedItem();

				String result;
				try {
					if (part.equals("Part 2")) {
						result = puzzle.calculatePart2(input) + "";
					} else {
						result = puzzle.calculatePart1(input) + "";
					}
				} catch (Exception e) {
					result = "Invalid input";
					e.printStackTrace();
				}
				
				outputField.setText(result + "");
			}

		});
		calculateButton.setBorder(null);
		calculateButton.setMargin(new Insets(0, 0, 0, 0));
		GridBagConstraints gbcCalculate = new GridBagConstraints();
		gbcCalculate.fill = GridBagConstraints.BOTH;
		gbcCalculate.insets = new Insets(5, 5, 5, 10);
		gbcCalculate.gridx = 2;
		gbcCalculate.gridy = 1;
		calculateButton.setForeground(mainBackground);
		getContentPane().add(calculateButton, gbcCalculate);

		puzzleComboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(5, 10, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		getContentPane().add(puzzleComboBox, gbc_comboBox);

		for (String puzzle : AoC.getInstance().getPuzzleManager().getPuzzles()) {
			puzzleComboBox.addItem(puzzle);
		}

		partComboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(5, 5, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 1;
		getContentPane().add(partComboBox, gbc_comboBox_1);

		partComboBox.addItem("Part 1");
		partComboBox.addItem("Part 2");

		outputField = new JTextField();
		outputField.setBorder(new LineBorder(Color.GRAY, 1));
		outputField.setEditable(false);
		outputField.setBorder(BorderFactory.createCompoundBorder(outputField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(5, 10, 10, 10);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		outputField.setForeground(componentForeground);
		outputField.setBackground(componentBackground);
		getContentPane().add(outputField, gbc_textField);

		setVisible(true);
	}

}
