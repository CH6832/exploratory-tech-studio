package CreditScoringAlgo.RCA;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.inject.Named;

@ApplicationScoped
@Named
public class Comments implements Serializable {
    private final List<String> comments = new CopyOnWriteArrayList<>();

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public List<String> getComments() {
        return comments;
    }
}
