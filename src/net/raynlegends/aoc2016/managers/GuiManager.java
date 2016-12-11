package net.raynlegends.aoc2016.managers;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class GuiManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4472592067316936435L;
	private static final Color mainForeground = new Color(240, 240, 240);
	private static final Color mainBackground = new Color(64, 64, 64);
	private static final Color componentForeground = new Color(240, 240, 240);
	private static final Color componentBackground = new Color(96, 96, 96);

	private JLayeredPane wrapper;
	
	private JPanel main;
	private GridBagLayout mainLayout;
	private JEditorPane inputField;
	private JButton calculateButton;
	private JTextField outputField;
	private JTextField timeField;
	private JComboBox<String> puzzleComboBox;
	private JComboBox<String> partComboBox;
	
	private JPanel display;
	private GridBagLayout displayLayout;
	private JEditorPane displayField;
	private JButton displayCloseButton;

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

		wrapper = new JLayeredPane();
		wrapper.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				resizeComponents(e.getComponent().getWidth(), e.getComponent().getHeight());
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				//
			}

			@Override
			public void componentShown(ComponentEvent e) {
				//
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				//
			}
			
		});
		
		loadMain();
		loadDisplay();
		
		display.setVisible(false);
		main.setVisible(true);
		setVisible(true);
		getContentPane().add(wrapper);
	}
	
	private void resizeComponents(int width, int height) {
		display.setBounds(0, 0, width, height);
		main.setBounds(0, 0, width, height);
	}
	
	private void loadMain() {
		main = new JPanel();
		main.setSize(535, 260);
		mainLayout = new GridBagLayout();
		mainLayout.columnWidths = new int[]{0, 0, 0, 0};
		mainLayout.rowHeights = new int[]{200, 50, 50, 0};
		mainLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		mainLayout.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		main.setForeground(mainForeground);
		main.setBackground(mainBackground);
		main.setLayout(mainLayout);

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
		main.add(inputFieldPane, gbcInput);

		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String input = inputField.getText();
				Puzzle puzzle = AoC.getInstance().getPuzzleManager().getPuzzle((String) puzzleComboBox.getSelectedItem());
				String part = (String) partComboBox.getSelectedItem();

				String result;
				try {
					long start = System.nanoTime();
					if (part.equals("Part 2")) {
						result = puzzle.calculatePart2(input) + "";
					} else {
						result = puzzle.calculatePart1(input) + "";
					}
					timeField.setText("Time: " + String.format("%.03f", (System.nanoTime() - start) / 1000000D) + "ms");
				} catch (Exception e) {
					result = "Invalid input";
					e.printStackTrace();
				}
				
				if (!result.contains("\n")) {
					outputField.setText(result + "");
					return;
				}
				
				outputField.setText("Displaying result...");
				displayField.setText(result);
				
				wrapper.setComponentZOrder(display, 0);
				display.setVisible(true);
				main.setVisible(false);
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
		main.add(calculateButton, gbcCalculate);

		puzzleComboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(5, 10, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		main.add(puzzleComboBox, gbc_comboBox);

		for (String puzzle : AoC.getInstance().getPuzzleManager().getPuzzles()) {
			puzzleComboBox.addItem(puzzle);
		}

		partComboBox = new JComboBox<String>();
		GridBagConstraints gbc_partComboBox = new GridBagConstraints();
		gbc_partComboBox.insets = new Insets(5, 5, 5, 5);
		gbc_partComboBox.fill = GridBagConstraints.BOTH;
		gbc_partComboBox.gridx = 1;
		gbc_partComboBox.gridy = 1;
		main.add(partComboBox, gbc_partComboBox);

		partComboBox.addItem("Part 1");
		partComboBox.addItem("Part 2");

		outputField = new JTextField();
		outputField.setBorder(new LineBorder(Color.GRAY, 1));
		outputField.setEditable(true);
		outputField.setBorder(BorderFactory.createCompoundBorder(outputField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gbc_outputField = new GridBagConstraints();
		gbc_outputField.gridwidth = 2;
		gbc_outputField.insets = new Insets(5, 10, 10, 5);
		gbc_outputField.fill = GridBagConstraints.BOTH;
		gbc_outputField.gridx = 0;
		gbc_outputField.gridy = 2;
		outputField.setForeground(componentForeground);
		outputField.setBackground(componentBackground);
		main.add(outputField, gbc_outputField);
		
		timeField = new JTextField();
		timeField.setForeground(SystemColor.menu);
		timeField.setEditable(false);
		timeField.setBorder(BorderFactory.createCompoundBorder(outputField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		GridBagConstraints gbc_timeField = new GridBagConstraints();
		gbc_timeField.insets = new Insets(5, 5, 10, 10);
		gbc_timeField.fill = GridBagConstraints.BOTH;
		gbc_timeField.gridx = 2;
		gbc_timeField.gridy = 2;
		timeField.setForeground(componentForeground);
		timeField.setBackground(componentBackground);
		main.add(timeField, gbc_timeField);

		GridBagConstraints gbc_main = new GridBagConstraints();
		gbc_main.fill = GridBagConstraints.BOTH;
		wrapper.add(main, gbc_main);
	}
	
	private void loadDisplay() {
		display = new JPanel();
		display.setSize(535, 260);
		displayLayout = new GridBagLayout();
		displayLayout.columnWidths = new int[]{0};
		displayLayout.rowHeights = new int[]{200, 50, 0};
		displayLayout.columnWeights = new double[]{1.0};
		displayLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		display.setForeground(mainForeground);
		display.setBackground(mainBackground);
		display.setLayout(displayLayout);
		
		displayField = new JEditorPane();
		displayField.setFont(UIManager.getFont("TextArea.font"));
		displayField.setBorder(null);
		displayField.setBorder(BorderFactory.createCompoundBorder(displayField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 25)));
		GridBagConstraints gbc_displayField = new GridBagConstraints();
		gbc_displayField.insets = new Insets(10, 10, 5, 10);
		gbc_displayField.fill = GridBagConstraints.BOTH;
		gbc_displayField.gridx = 0;
		gbc_displayField.gridy = 0;
		displayField.setForeground(componentForeground);
		displayField.setBackground(componentBackground);
		JScrollPane displayFieldPane = new JScrollPane(displayField);
		displayFieldPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		display.add(displayFieldPane, gbc_displayField);
		
		displayCloseButton = new JButton("Close");
		displayCloseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				wrapper.setComponentZOrder(main, 0);
				display.setVisible(false);
				main.setVisible(true);
			}
			
		});
		displayCloseButton.setMargin(new Insets(0, 0, 0, 0));
		GridBagConstraints gbc_displayCloseButton = new GridBagConstraints();
		gbc_displayCloseButton.fill = GridBagConstraints.BOTH;
		gbc_displayCloseButton.insets = new Insets(5, 10, 10, 10);
		gbc_displayCloseButton.gridx = 0;
		gbc_displayCloseButton.gridy = 1;
		displayCloseButton.setForeground(mainBackground);
		display.add(displayCloseButton, gbc_displayCloseButton);
		
		GridBagConstraints gbc_display = new GridBagConstraints();
		gbc_display.fill = GridBagConstraints.BOTH;
		wrapper.add(display, gbc_display);
	}

}
