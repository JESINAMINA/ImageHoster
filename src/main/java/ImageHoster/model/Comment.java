package ImageHoster.model;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import java.util.List;

//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'Comments'. Hence the table named 'Comments' will be created in the database with all the columns mapped to all the attributes in 'Comment' class
@Table(name = "Comments")
public class Comment {
  //@Id annotation specifies that the corresponding attribute is a primary key
  @Id
  //@Column annotation specifies that the attribute will be mapped to the column in the database.
  //Here the column name is explicitly mentioned as 'id'
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "comment")
  private String text;


  @Column(name = "createdDate")
  private LocalDate createdDate;

  // Write the annotation for many to many between images and tags where they are mapped by tags field in the images table
  //The 'tags' table is mapped to 'images' table with Many:Many mapping
  //One image can have multiple categories/tags and there can be multiple images under one category/tag
  //FetchType is LAZY
  //Note that no column will be generated for this attribute in the database instead a new table will be created
  //Since the mapping is Many to Many, a new table will be generated containing the two columns both referencing to the primary key of both the tables ('images', 'tags')

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "image_id")
  private Image image;


  public Comment() {
  }

  public Comment(String comment) {
    this.text = comment;
  }

  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }
  public void setText(String text) {
    this.text = text;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

}
