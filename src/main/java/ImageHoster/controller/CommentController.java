package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private ImageService imageService;

  //This method creates new comment for image
  @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
  public String createComment(@PathVariable("imageId") Integer imageId,@PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String comment,
      Model model ,HttpSession session) throws IOException {

    User user = (User) session.getAttribute("loggeduser");
    Image image = imageService.getImage(imageId);

    Comment comment1 = new Comment();
    comment1.setText(comment);
    comment1.setUser(user);
    comment1.setImage(image);
    comment1.setCreatedDate(LocalDate.now());
    commentService.createComment(comment1);
    return "redirect:/images/" + imageId + '/'+imageTitle;
  }
}
