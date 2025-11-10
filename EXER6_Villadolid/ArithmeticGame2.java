import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

public class ArithmeticGame2 extends JFrame {
	private final JTextField leftField;
	private final JTextField rightField;
	private final JTextField resultField;
	private final JTextField correctScoreField;
	private final JTextField wrongScoreField;
	
	private final JRadioButton addButton, subButton, mulButton, divButton;
	private final JRadioButton level1Button, level2Button, level3Button;
	private final ButtonGroup operationGroup, levelGroup;
	
	private String currentOperation = "+";
	private int minRange = 1, maxRange = 100;
	private int correctCount = 0, wrongCount = 0;
	private int num1, num2, correctAnswer;
	private JLabel operatorLabel;
	
	private static final String[] POSITIVE_MESSAGES = {
		"Great! You're awesome!",
		"Excellent! Keep it up!",
		"Fantastic! You're doing great!",
		"Perfect! You're on fire!",
		"Amazing! You're a math genius!",
		"Outstanding! Keep going!",
		"Brilliant! You're unstoppable!",
		"Wonderful! You're crushing it!",
		"Superb! You're incredible!",
		"Terrific! You're a star!",
		"Awesome! You're doing amazing!",
		"Perfect! You're a champion!"
	};

	public ArithmeticGame2() {
		super("Arithmetic Game");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		// Add window listener for exit confirmation
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmExit();
			}
		});

		// Main content panel with textured-like background
		JPanel content = new JPanel();
		content.setBorder(new EmptyBorder(20, 20, 20, 20));
		content.setBackground(new Color(0xF5F5F5));
		content.setLayout(new BorderLayout(20, 20));

		// Top section: Arithmetic expression
		JPanel expressionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		expressionPanel.setOpaque(false);
		
		leftField = createSquareField();
		leftField.setEditable(false);
		expressionPanel.add(leftField);

		operatorLabel = createOperatorLabel("+");
		expressionPanel.add(operatorLabel);

		rightField = createSquareField();
		rightField.setEditable(false);
		expressionPanel.add(rightField);

		JLabel equals = createOperatorLabel("=");
		expressionPanel.add(equals);

		resultField = createSquareField();
		addNumericFilter(resultField); // Only allow numeric input
		expressionPanel.add(resultField);

		JButton submit = new JButton("Submit");
		submit.setPreferredSize(new Dimension(100, 40));
		submit.setFont(submit.getFont().deriveFont(Font.BOLD, 14f));
		submit.addActionListener(this::onSubmit);
		
		// Add Enter key support to result field
		resultField.addActionListener(_ -> onSubmit(new ActionEvent(submit, ActionEvent.ACTION_PERFORMED, "")));
		
		JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		submitPanel.setOpaque(false);
		submitPanel.add(submit);
		expressionPanel.add(submitPanel);

		// Bottom section: Operations, Level, and Score
		JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 15, 0));
		bottomPanel.setOpaque(false);

		// Operations section
		JPanel operationsContainer = new JPanel(new BorderLayout(0, 5));
		operationsContainer.setOpaque(false);
		JLabel operationsLabel = new JLabel("Operations:");
		operationsLabel.setFont(operationsLabel.getFont().deriveFont(Font.BOLD, 12f));
		operationsContainer.add(operationsLabel, BorderLayout.NORTH);
		
		JPanel operationsPanel = createSectionPanel();
		operationGroup = new ButtonGroup();
		addButton = new JRadioButton("Addition", true);
		subButton = new JRadioButton("Subtraction");
		mulButton = new JRadioButton("Multiplication");
		divButton = new JRadioButton("Division");
		
		addButton.addActionListener(_ -> {
			currentOperation = "+";
			operatorLabel.setText("+");
			generateNewQuestion();
		});
		subButton.addActionListener(_ -> {
			currentOperation = "-";
			operatorLabel.setText("-");
			generateNewQuestion();
		});
		mulButton.addActionListener(_ -> {
			currentOperation = "*";
			operatorLabel.setText("×");
			generateNewQuestion();
		});
		divButton.addActionListener(_ -> {
			currentOperation = "/";
			operatorLabel.setText("÷");
			generateNewQuestion();
		});
		
		operationGroup.add(addButton);
		operationGroup.add(subButton);
		operationGroup.add(mulButton);
		operationGroup.add(divButton);
		
		operationsPanel.add(addButton);
		operationsPanel.add(subButton);
		operationsPanel.add(mulButton);
		operationsPanel.add(divButton);
		operationsContainer.add(operationsPanel, BorderLayout.CENTER);

		// Level section
		JPanel levelContainer = new JPanel(new BorderLayout(0, 5));
		levelContainer.setOpaque(false);
		JLabel levelLabel = new JLabel("Level:");
		levelLabel.setFont(levelLabel.getFont().deriveFont(Font.BOLD, 12f));
		levelContainer.add(levelLabel, BorderLayout.NORTH);
		
		JPanel levelPanel = createSectionPanel();
		levelGroup = new ButtonGroup();
		level1Button = new JRadioButton("Lvl-1 (1-100)", true);
		level2Button = new JRadioButton("Lvl-2 (101-500)");
		level3Button = new JRadioButton("Lvl-3 (501-1000)");
		
		level1Button.addActionListener(_ -> {
			minRange = 1;
			maxRange = 100;
			generateNewQuestion();
		});
		level2Button.addActionListener(_ -> {
			minRange = 101;
			maxRange = 500;
			generateNewQuestion();
		});
		level3Button.addActionListener(_ -> {
			minRange = 501;
			maxRange = 1000;
			generateNewQuestion();
		});
		
		levelGroup.add(level1Button);
		levelGroup.add(level2Button);
		levelGroup.add(level3Button);
		
		levelPanel.add(level1Button);
		levelPanel.add(level2Button);
		levelPanel.add(level3Button);
		levelContainer.add(levelPanel, BorderLayout.CENTER);

		// Score section
		JPanel scoreContainer = new JPanel(new BorderLayout(0, 5));
		scoreContainer.setOpaque(false);
		JLabel scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(scoreLabel.getFont().deriveFont(Font.BOLD, 12f));
		scoreContainer.add(scoreLabel, BorderLayout.NORTH);
		
		JPanel scorePanel = createSectionPanel();
		JPanel scoreFieldsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		scoreFieldsPanel.setOpaque(false);
		
		JLabel correctLabel = new JLabel("Correct:");
		correctLabel.setFont(correctLabel.getFont().deriveFont(Font.BOLD));
		scoreFieldsPanel.add(correctLabel);
		
		correctScoreField = createSmallScoreField();
		correctScoreField.setText("0");
		scoreFieldsPanel.add(correctScoreField);
		
		JLabel wrongLabel = new JLabel("Wrong:");
		wrongLabel.setFont(wrongLabel.getFont().deriveFont(Font.BOLD));
		scoreFieldsPanel.add(wrongLabel);
		
		wrongScoreField = createSmallScoreField();
		wrongScoreField.setText("0");
		scoreFieldsPanel.add(wrongScoreField);
		
		scorePanel.add(scoreFieldsPanel);
		scoreContainer.add(scorePanel, BorderLayout.CENTER);

		bottomPanel.add(operationsContainer);
		bottomPanel.add(levelContainer);
		bottomPanel.add(scoreContainer);

		content.add(expressionPanel, BorderLayout.CENTER);
		content.add(bottomPanel, BorderLayout.SOUTH);

		setContentPane(content);
		// Set submit button as default button (triggered by Enter key)
		getRootPane().setDefaultButton(submit);
		generateNewQuestion();
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel createSectionPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBackground(new Color(0xF5F5F5));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		return panel;
	}

	private JTextField createSquareField() {
		JTextField field = new JTextField();
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setPreferredSize(new Dimension(80, 80));
		field.setFont(field.getFont().deriveFont(Font.BOLD, 24f));
		field.setBorder(BorderFactory.createLineBorder(new Color(0x8A8A8A), 2));
		field.setBackground(Color.WHITE);
		return field;
	}
	
	private void addNumericFilter(JTextField field) {
		((AbstractDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
				if (string == null) return;
				if (string.matches("\\d*")) {
					super.insertString(fb, offset, string, attr);
				}
			}
			
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				if (text == null) text = "";
				StringBuilder sb = new StringBuilder();
				try {
					sb.append(fb.getDocument().getText(0, offset));
					sb.append(text);
					sb.append(fb.getDocument().getText(offset + length, fb.getDocument().getLength() - offset - length));
				} catch (BadLocationException e) {
					return;
				}
				if (sb.toString().matches("\\d*")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});
	}

	private JTextField createSmallScoreField() {
		JTextField field = new JTextField();
		field.setHorizontalAlignment(SwingConstants.CENTER);
		field.setPreferredSize(new Dimension(80, 30));
		field.setFont(field.getFont().deriveFont(Font.BOLD, 14f));
		field.setBorder(BorderFactory.createLineBorder(new Color(0x8A8A8A), 1));
		field.setBackground(Color.WHITE);
		field.setEditable(false);
		return field;
	}

	private JLabel createOperatorLabel(String op) {
		JLabel label = new JLabel(op, SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(40, 80));
		label.setFont(label.getFont().deriveFont(Font.BOLD, 28f));
		return label;
	}

	private void generateNewQuestion() {
		switch (currentOperation) {
			case "+" -> {
				// Addition: num1 + num2 = answer
				num1 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				num2 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				correctAnswer = num1 + num2;
			}
			case "-" -> {
				// Subtraction: num1 - num2 = answer (ensure non-negative result)
				num1 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				num2 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				if (num1 < num2) {
					int temp = num1;
					num1 = num2;
					num2 = temp;
				}
				correctAnswer = num1 - num2;
			}
			case "*" -> {
				// Multiplication: num1 × num2 = answer
				num1 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				num2 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				correctAnswer = num1 * num2;
			}
			case "/" -> {
				// Division: num1 ÷ num2 = answer (ensure whole number division)
				// Generate divisor first, then multiply by quotient to get dividend
				num2 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				if (num2 == 0) num2 = 1; // Avoid division by zero
				int quotient = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				num1 = num2 * quotient; // This ensures num1 is divisible by num2
				correctAnswer = quotient;
			}
			default -> {
				num1 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				num2 = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
				correctAnswer = num1 + num2;
			}
		}
		
		leftField.setText(String.valueOf(num1));
		rightField.setText(String.valueOf(num2));
		resultField.setText("");
		resultField.requestFocus();
	}

	private String getRandomPositiveMessage() {
		int index = (int) (Math.random() * POSITIVE_MESSAGES.length);
		return POSITIVE_MESSAGES[index];
	}

	private void confirmExit() {
		int result = JOptionPane.showConfirmDialog(
			this,
			"Do you really want to exit?",
			"Exit Confirmation",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	private void onSubmit(ActionEvent e) {
		if (e.getSource() == null) {
			return;
		}
		
		try {
			String answerText = resultField.getText().trim();
			if (answerText.isEmpty()) {
				return;
			}
			
			int userAnswer = Integer.parseInt(answerText);
			
			if (userAnswer == correctAnswer) {
				correctCount++;
				correctScoreField.setText(String.valueOf(correctCount));
				String message = getRandomPositiveMessage();
				JOptionPane.showMessageDialog(this, message, "Result", JOptionPane.INFORMATION_MESSAGE);
			} else {
				wrongCount++;
				wrongScoreField.setText(String.valueOf(wrongCount));
				String wrongMessage = "<html><div style='text-align: center;'>" +
					"<b>Aray Ko!</b><br>" +
					"Ang aking kasagutan ay: " + correctAnswer +
					"</div></html>";
				JOptionPane.showMessageDialog(this, wrongMessage, "Result", JOptionPane.ERROR_MESSAGE);
				resultField.setText(""); // Clear field on wrong answer
				resultField.requestFocus();
				return; // Don't generate new question on wrong answer
			}
			
			generateNewQuestion();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
			resultField.setText(""); // Clear field on invalid input
			resultField.requestFocus();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ArithmeticGame2().setVisible(true));
	}
}
