package signinpage;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Mainframe {

	JPanel headerPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel footerPanel = new JPanel();

	// header
	ImageIcon logo = new ImageIcon(getClass().getResource("/image/logo32.jpg"));
	JLabel headerLogo;
	JLabel headerUserName;
	JLabel headerSignOut;

	// main
	JPanel mainInnerPanel;
	JLabel bookName;
	JLabel bookAuthor;
	JLabel bookRelDate;
	JLabel bookRating;
	List<MainDTO> dtoList = new MainDAO().loadBooks();

	// footer
	JLabel footerPage;
	JTextField footerGoToPage;
	int pageNum = 1;
	int pageNumMax = (dtoList.size() / 4 + 1);
	JButton footerGoButton;
	JLabel footerMaxPage;
	JButton back = new JButton("back");
	JButton next = new JButton("next");

	public Mainframe(DTO user) {
		JFrame frame = new JFrame();
		// frame property
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 770);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setLocationRelativeTo(null);

		// header property
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
		// main property
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		// footer property
		footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));

		// header inner property
		headerLogo = new JLabel(logo);
		headerUserName = new JLabel(user.getName() + "님");
		headerSignOut = new JLabel("sign out");
		headerSignOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				frame.dispose();
				new SiFrame();
			}
		});
		// footer inner property
		footerPage = new JLabel("Page");
		footerGoToPage = new JTextField(String.valueOf(pageNum), 3);
		footerGoToPage.setMaximumSize(new Dimension(50, 30));
		footerGoButton = new JButton("go");
		footerGoButton.addActionListener(e -> goPage(user));
		footerMaxPage = new JLabel(" / " + String.valueOf(dtoList.size() / 4 + 1));
		next.addActionListener(e -> next(user));
		back.addActionListener(e -> back(user));
		if (pageNum == 1) {
			back.setVisible(false);
		}
		if (pageNum == pageNumMax) {
			next.setVisible(false);
		}

		// frame add
		frame.add(headerPanel);
		frame.add(mainPanel);
		frame.add(footerPanel);

		// header add
		headerPanel.add(headerLogo);
		headerPanel.add(headerUserName);
		headerPanel.add(Box.createHorizontalGlue());
		headerPanel.add(headerSignOut);

		// main add
		// dto를 리턴하는 메서드 만들어서 정보를 패널에 담는 메서드에 넣기
		for (int i = 0; i < 4; i++) {
			if ((pageNum - 1) * 4 + i == dtoList.size())
				break;
			mainPanel.add(mainInnerBook(dtoList.get((pageNum - 1) * 4 + i), user)); // 각 DTO를 패널에 추가
		}

		mainPanel.revalidate();
		mainPanel.repaint();

		// footer add
		footerPanel.add(back);
		footerPanel.add(Box.createHorizontalGlue());
		footerPanel.add(footerPage);
		footerPanel.add(footerGoToPage);
		footerPanel.add(footerMaxPage);
		footerPanel.add(footerGoButton);
		footerPanel.add(Box.createHorizontalGlue());
		footerPanel.add(next);

		// end
		frame.setVisible(true);
	}

	public JPanel mainInnerBook(MainDTO dto, DTO userDto) {
		mainInnerPanel = new JPanel();
		bookName = new JLabel(dto.getId());
		bookAuthor = new JLabel(dto.getAuthor());
		bookRelDate = new JLabel(dto.getReleaseDate().toString());
		bookRating = new JLabel(String.valueOf(dto.getRating()));
		mainInnerPanel.add(bookName);
		mainInnerPanel.add(bookAuthor);
		mainInnerPanel.add(bookRelDate);
		mainInnerPanel.add(bookRating);
		mainInnerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				new DetailPage(dto, userDto);
			}
		});
		return mainInnerPanel;
	}

	public void goPage(DTO userDto) {
		try {
			if (0 < Integer.parseInt(footerGoToPage.getText())
					&& Integer.parseInt(footerGoToPage.getText()) <= pageNumMax) {
				mainPanel.removeAll();
				pageNum = Integer.parseInt(footerGoToPage.getText());
				for (int i = 0; i < 4; i++) {
					if ((pageNum - 1) * 4 + i == dtoList.size())
						break;
					mainPanel.add(mainInnerBook(dtoList.get((pageNum - 1) * 4 + i),userDto)); // 각 DTO를 패널에 추가
				}
				if (pageNum == 1) {
					back.setVisible(false);
				} else {
					back.setVisible(true);
				}
				if (pageNum == pageNumMax) {
					next.setVisible(false);
				} else {
					next.setVisible(true);
				}
				mainPanel.revalidate();
				mainPanel.repaint();
			} else {
				JOptionPane.showMessageDialog(null, "올바른 페이지 숫자를 입력해 주세요 1~" + (pageNumMax), "페이지 오류", JOptionPane.ERROR_MESSAGE);
				footerGoToPage.setText(String.valueOf(pageNum));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "올바른 페이지 숫자를 입력해 주세요 1~" + (pageNumMax), "페이지 오류", JOptionPane.ERROR_MESSAGE);
			footerGoToPage.setText(String.valueOf(pageNum));
		}
	}

	public void next(DTO userDto) {
		pageNum += 1;
		mainPanel.removeAll();
		for (int i = 0; i < 4; i++) {
			if ((pageNum - 1) * 4 + i == dtoList.size())
				break;
			mainPanel.add(mainInnerBook(dtoList.get((pageNum - 1) * 4 + i),userDto)); // 각 DTO를 패널에 추가
		}
		if (pageNum == 1) {
			back.setVisible(false);
		} else {
			back.setVisible(true);
		}
		if (pageNum == pageNumMax) {
			next.setVisible(false);
		} else {
			next.setVisible(true);
		}
		mainPanel.revalidate();
		mainPanel.repaint();
		footerGoToPage.setText(String.valueOf(pageNum));
	}
	public void back(DTO userDto) {
		pageNum -= 1;
		mainPanel.removeAll();
		for (int i = 0; i < 4; i++) {
			if ((pageNum - 1) * 4 + i == dtoList.size())
				break;
			mainPanel.add(mainInnerBook(dtoList.get((pageNum - 1) * 4 + i),userDto)); // 각 DTO를 패널에 추가
		}
		if (pageNum == 1) {
			back.setVisible(false);
		} else {
			back.setVisible(true);
		}
		if (pageNum == pageNumMax) {
			next.setVisible(false);
		} else {
			next.setVisible(true);
		}
		mainPanel.revalidate();
		mainPanel.repaint();
		footerGoToPage.setText(String.valueOf(pageNum));
	}
}
