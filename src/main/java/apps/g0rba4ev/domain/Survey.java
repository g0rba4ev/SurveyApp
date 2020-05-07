package apps.g0rba4ev.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Survey {

    @Id
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Question> questionsSet = new LinkedHashSet<>();

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

    public Set<Question> getQuestionsSet() {
        return questionsSet;
    }

    /**
     * add question to survey
     * @param question to be added
     * @return true if this survey did not already contain the specified question
     */
    public boolean addQuestion(Question question) {
        return questionsSet.add(question);
    }

    /**
     * remove question from survey
     * @param question to be removed
     * @return true if this survey contained the specified question
     */
    public boolean removeQuestion(Question question) {
        return questionsSet.remove(question);
    }
}
