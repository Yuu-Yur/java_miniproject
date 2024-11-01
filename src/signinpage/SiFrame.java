package signinpage;

import java.awt.Label;
import javax.swing.*;

public class SiFrame {
	
	static JTextField idInput;
	static JTextField passInput;
	static JTextField passCheck;
	static JTextField name;
	static JTextField email;
	static JFrame frame;
	static JPanel headerPanel;
	static JPanel mainPanel;
	static JPanel footerPanel;
	static JButton confirmButton;
	static ImageIcon logo = new ImageIcon(SiFrame.class.getResource("/image/logo.jpg"));
	static JLabel logoLabel = new JLabel(logo);
	static JPanel fosu = new JPanel();
	static JButton forgot = new JButton("password?");
	static JButton suButton = new JButton("first?");
	static JLabel yearIntro = new JLabel("this year");
	static JLabel atIntro = new JLabel("all time");
	static ImageIcon yearImage = new ImageIcon(SiFrame.class.getResource("/image/image1.jpg"));
	static ImageIcon atImage = new ImageIcon(SiFrame.class.getResource("/image/image2.jpg"));
	static JLabel yearImageLabel = new JLabel(yearImage);
	static JLabel atImageLabel = new JLabel(atImage);
	static JPanel intro = new JPanel();
	static JPanel imageLabel = new JPanel();

	
	// constructor
	public SiFrame(){
	// 전역변수들에 새 인스턴스 할당
	frame = new JFrame();
	headerPanel = new JPanel();
	mainPanel = new JPanel();
	footerPanel = new JPanel();
	idInput = new JTextField(10);
	passInput = new JTextField(10);
	confirmButton = new JButton("SIGN IN");
	
	// frame
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(400,700);
	frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	frame.setLocationRelativeTo(null);
	
	// frame add
	frame.add(headerPanel); 
	frame.add(mainPanel); 
	frame.add(footerPanel);
	// hpanel add
	headerPanel.add(logoLabel);
	// mpanel add
	mainPanel.add(new JLabel("ID"));
	mainPanel.add(idInput);
	mainPanel.add(new JLabel("PASSWORD"));
	mainPanel.add(passInput);
	mainPanel.add(fosu);
	fosu.add(forgot);
	fosu.add(suButton);
	mainPanel.add(confirmButton);
	// fpanel add
	footerPanel.add(intro);
	intro.add(yearIntro);
	intro.add(atIntro);
	footerPanel.add(imageLabel);
	imageLabel.add(yearImageLabel);
	imageLabel.add(atImageLabel);
	
	// header
	// main
	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	// footer
	footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
	forgot.addActionListener(e -> findPass());
	suButton.addActionListener(e -> signUp());
	
	// 끝
	frame.setVisible(true);
	}
	
	// method eventlistener에 들어갈것
	public void findPass() {
		frame = new JFrame();
		confirmButton = new JButton("아이디 비밀번호 찾기");
		name = new JTextField(10);
		email = new JTextField(10);
		
		frame.setSize(400,160);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setLocationRelativeTo(null);
		confirmButton.addActionListener(e -> {
		if(!name.getText().isBlank() && !email.getText().isBlank() && (new DAO().searchDAO(name.getText(), email.getText()) != null)) {
			DTO dto = new DAO().searchDAO(name.getText(), email.getText());
			JOptionPane.showMessageDialog(null, "id: " + dto.getId() + "\n password: " + dto.getPassword(), "계정 찾기", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "찾으시는 계정은 없습니다", "오류 발생", JOptionPane.ERROR_MESSAGE);
		}
		});

		frame.add(new Label("NAME"));
		frame.add(name);
		frame.add(new Label("E-MAIL"));
		frame.add(email);
		frame.add(confirmButton);
		
		frame.setVisible(true);
	}

	public void signUp() {
		// 변수
		frame = new JFrame();
		idInput = new JTextField(10);
		passInput = new JTextField(10);
		name = new JTextField(10);
		email = new JTextField(10);
		passCheck = new JTextField(10);
		confirmButton = new JButton("회원 가입");

		
		// frame prop
		frame.setSize(400,300);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setLocationRelativeTo(null);

		// 이벤트리스너
		confirmButton.addActionListener(e -> {
			if(passCheck.getText().equals(passInput.getText()) && !idInput.getText().isBlank() && !passInput.getText().isBlank() && !name.getText().isBlank() && !email.getText().isBlank()) {
			new DAO().insertDAO(new DTO(idInput.getText(), passInput.getText(), name.getText(), email.getText()));
			frame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "입력을 다시 한번 확인해주세요", "오류", JOptionPane.ERROR_MESSAGE);
				idInput.setText("");
				passInput.setText("");
				passCheck.setText("");
				name.setText("");
				email.setText("");
			}
		});
		
		// add
		frame.add(new Label("ID"));
		frame.add(idInput);
		frame.add(new Label("PASSWORD"));
		frame.add(passInput);
		frame.add(new Label("PASSWORD CHECK"));
		frame.add(passCheck);
		frame.add(new Label("NAME"));
		frame.add(name);
		frame.add(new Label("E-MAIL"));
		frame.add(email);
		frame.add(confirmButton);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new SiFrame();
	}
}
