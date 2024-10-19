package CreditScoringAlgo.RCA;


import jakarta.validation.constraints.Null;

import java.io.Serializable;

public class CommentForm implements Serializable {
    @Null
    @jakarta.validation.constraints.Size(min = 1, max = 10)
    @jakarta.validation.constraints.Pattern(regexp = "[a-zA-Z0-9]+")
    @FormParam("comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
