package signinpage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DetailPage {
	JTextArea review = new JTextArea(5, 30);
	
	public DetailPage(MainDTO dto, DTO userDto) {
		// frame property
		JFrame frame = new JFrame();
		frame.setSize(400, 770);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setLocationRelativeTo(null);
		
		// book information property
		JPanel bookInform = new JPanel();
		bookInform.setLayout(new BoxLayout(bookInform, BoxLayout.X_AXIS));
		ImageIcon bookImageIcon = new ImageIcon(getClass().getResource("/image/example.jpg"));
		JLabel bookImage = new JLabel(bookImageIcon);
		JPanel bookInformDetail = new JPanel();
		bookInformDetail.setLayout(new BoxLayout(bookInformDetail, BoxLayout.Y_AXIS));
		JLabel id = new JLabel(dto.getId());
		JLabel author = new JLabel(dto.getAuthor());
		JLabel releaseDate = new JLabel(String.valueOf(dto.getReleaseDate()));
		JLabel rating = new JLabel(String.valueOf(dto.getRating()));
		// write review property
		JPanel writeReview = new JPanel();
		JButton submitButton = new JButton("submit");
		// other review property
		JPanel otherReview = new JPanel();
		
		// frame add
		frame.add(bookInform);
		frame.add(writeReview);
		frame.add(otherReview);
		
		//book information add
		bookInform.add(bookImage);
		bookInform.add(bookInformDetail);
		bookInformDetail.add(id);
		bookInformDetail.add(Box.createVerticalGlue());
		bookInformDetail.add(author);
		bookInformDetail.add(Box.createVerticalGlue());
		bookInformDetail.add(releaseDate);
		bookInformDetail.add(Box.createVerticalGlue());
		bookInformDetail.add(rating);
		
		// write review add
		writeReview.add(new JLabel("review"));
		submitButton.addActionListener(e -> submit(dto,userDto));
		writeReview.add(submitButton);
		writeReview.add(review);
		
		// end
		frame.setVisible(true);
	}
	public void submit(MainDTO dto, DTO userDto) {
		String bookName = dto.getId();
		String userName = userDto.getName();
		String submitReview = review.getText();
		// 이렇게 받은 책 이름, 유저이름, 리뷰 텍스트를 데이터베이스에 올리고
		// other review에서 받기만 하면 끝
		}
}
