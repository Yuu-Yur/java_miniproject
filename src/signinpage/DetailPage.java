package signinpage;

import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DetailPage {
	JTextArea review = new JTextArea("300자까지 입력 가능",5, 30);
	JFrame frame = new JFrame();
	JPanel otherReview = new JPanel();
	JPanel userReview = new JPanel();
	public DetailPage(MainDTO dto, DTO userDto) {
		// frame property
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
		userReview.setLayout(new BoxLayout(userReview, BoxLayout.Y_AXIS));
		
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
		
		// other review add
		loadReview(dto);
		// end
		frame.setVisible(true);
	}
	
	public void submit(MainDTO dto, DTO userDto) {
		ReviewDTO reviewDTO = new ReviewDTO(dto.getId(), userDto.getName(), review.getText());
		new ReviewDAO().insertReview(reviewDTO);
		review.setText("300자까지 입력 가능");
		loadReview(dto);
		// other review에서 받기만 하면 끝
		}
	
	public void loadReview(MainDTO dto) {
		userReview.removeAll();
		List<ReviewDTO> dtoList = new ReviewDAO().loadReview(dto.getId());	
		for(ReviewDTO reviewDTO : dtoList) {
			JTextArea reviewText = new JTextArea(reviewDTO.getReview());
			reviewText.setLineWrap(true);
			reviewText.setEditable(false);
			reviewText.setSize(new Dimension(350, 100));
			reviewText.setMaximumSize(new Dimension(350, 300));
			userReview.add(new JLabel(reviewDTO.getUserName()));
			userReview.add(reviewText);
			otherReview.add(userReview);
		}
		userReview.revalidate();
		userReview.repaint();
		
		
	}
}
