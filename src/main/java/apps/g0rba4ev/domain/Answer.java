package apps.g0rba4ev.domain;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(
        name = "answers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"q_id", "userName", "date"})
        }
)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "q_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String answer;

    @Column (nullable = false)
    private Date date;

    public Answer() {
    }

    public Answer(Question question, String userName, String answer, Date date) {
        this.question = question;
        this.userName = userName;
        this.answer = answer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", question=" + question +
                ", user=" + userName +
                ", answer='" + answer + '\'' +
                '}';
    }
}
