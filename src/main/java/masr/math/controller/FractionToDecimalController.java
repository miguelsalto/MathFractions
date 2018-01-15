package masr.math.controller;

import masr.math.entity.Fraction;
import masr.math.validator.DecimalValidator;
import masr.math.vo.AnswerVO;
import masr.math.vo.ExerciseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static masr.math.constants.AppConstants.EXERCISE_ATTR_NAME;
import static masr.math.constants.AppConstants.NUMBER_OF_PROBLEMS;
import static masr.math.util.FractionGeneratorUtil.generateProperAndImproperFractions;
import static masr.math.validator.BaseValidator.isValidDecimal;

@Controller
@SessionAttributes({EXERCISE_ATTR_NAME})
public class FractionToDecimalController extends BaseController<Fraction> {
    private static final String VIEW_NAME = "fractionToDecimal";

    private final DecimalValidator decimalValidator;

    @Autowired
    public FractionToDecimalController(DecimalValidator decimalValidator) {
        this.decimalValidator = decimalValidator;
    }

    @RequestMapping(value = "/fractionToDecimal", method = RequestMethod.GET)
    public ModelAndView showPage() {
        return createModelAndView(VIEW_NAME);
    }

    @Override
    List<Fraction> generateFractions() {
        return generateProperAndImproperFractions(NUMBER_OF_PROBLEMS);
    }

    @RequestMapping(value = "/fractionToDecimal/grade", method = RequestMethod.POST)
    public String grade(@Validated @ModelAttribute(EXERCISE_ATTR_NAME) ExerciseVO<Fraction> exerciseVO, BindingResult result,
                        ModelMap modelMap) {
        updateModelForGrading(exerciseVO, result, modelMap);
        return VIEW_NAME;
    }

    @RequestMapping(value = "/fractionToDecimal/verify", method = RequestMethod.POST)
    public String verify(@ModelAttribute(EXERCISE_ATTR_NAME) ExerciseVO<Fraction> exerciseVO, @SuppressWarnings("unused") BindingResult result,
                         ModelMap modelMap) {
        updateModelForVerifying(exerciseVO, modelMap);
        return VIEW_NAME;
    }

    @Override
    Validator getValidator() {
        return decimalValidator;
    }

    @Override
    boolean isAnswerCompleteForEvaluation(AnswerVO answer) {
        return isValidDecimal(answer.getDecimal());
    }

    @Override
    boolean isCorrect(Fraction fraction, AnswerVO answer) {
        return isCorrectDecimal(fraction, answer);
    }
}
