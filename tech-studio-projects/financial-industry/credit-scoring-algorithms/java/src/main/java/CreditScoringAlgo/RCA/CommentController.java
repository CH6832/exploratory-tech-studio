package CreditScoringAlgo.RCA;

import java.lang.annotation.Target;

import jakarta.mvc.Models;
import jakarta.mvc.binding.BindingResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import jakarta.validation.executable.ValidateOnExecution;

@jakarta.mvc.Controller
@Past()
public class CommentController {

    @jakarta.inject.Inject
    private Comments comments;  // Ensure Comments is a properly defined bean

    @jakarta.inject.Inject
    private BindingResult bindingResult;

    @jakarta.inject.Inject
    private Models models;

    public String show() {
        return "comments.jsp"; // This should correspond to your JSP file location
    }

    @Past
    @CsrfValid
    @ValidateOnExecution() // Adjusted for clarity
    public String post(@Valid @BeanParam CommentForm commentForm) {
        if (bindingResult.isFailed()) {
            models.put("bindingResult", bindingResult);
            return "comments.jsp"; // Redirect back to the JSP if validation fails
        }
        comments.addComment(commentForm.getComment()); // Ensure addComment method is properly defined in Comments class
        return "redirect:/comments"; // This should match your application's routing
    }
}
