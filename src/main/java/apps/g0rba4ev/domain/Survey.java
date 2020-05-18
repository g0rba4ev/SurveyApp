package apps.g0rba4ev.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Survey {

    @Id
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Map<Integer, Question> questionMap = new HashMap<>();

    public Survey() {
    }

    public Survey(Date date) {
        this.date = date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Map<Integer, Question> getQuestionMap() {
        return questionMap;
    }

    public void setQuestion(int position, Question question) {
        questionMap.put(position, question);
    }

}
