package ttu.idu0200.util;

import ttu.idu0200.model.LearningObject;

import java.util.HashMap;

/**
 * Created by mart on 23.03.16.
 */
public class LearningObjectValidator {

    public HashMap<String, String> validate(LearningObject learningObject) {
        HashMap<String, String> errors = new HashMap<>();

        if(learningObject.getTitle().length() > 30) {
            errors.put("titleError", "Pealkiri ei tohi olla üle 30 tähemärgi");
        } else if(learningObject.getType().length() > 10) {
            errors.put("typeError", "Tüüp ei tohi olla pike kui 10 tähemärki");
        }

        return errors;
    }
}
