package masr.math.controller;

import masr.math.entity.Fraction;
import masr.math.util.FractionGeneratorUtil;
import masr.math.vo.ExerciseVO;
import masr.math.vo.FractionVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static masr.math.constants.AppConstants.NUMBER_OF_PROBLEMS;

@Controller
@SessionAttributes({DecimalToFractionController.NAME})
public class DecimalToFractionController {
    static final String NAME = "exercise";
    private static final int PROBLEMS_PER_TYPE = NUMBER_OF_PROBLEMS / 2;

    private static final Supplier<Fraction> PROPER_FRACTION_FUNCTION = FractionGeneratorUtil::generateProperFractionFromDecimal;
    private static final Supplier<Fraction> IMPROPER_FRACTION_FUNCTION = FractionGeneratorUtil::generateImproperFractionFromDecimal;

    @RequestMapping(value = "/decimalToFraction", method = RequestMethod.GET)
    public ModelAndView showForm() {
        ExerciseVO exercise = initExercise();
        Collections.shuffle(exercise.getFractions());
        return new ModelAndView("decimalToFraction", NAME, exercise);
    }

    private ExerciseVO initExercise() {
        ExerciseVO exerciseVO = new ExerciseVO();
        exerciseVO.setFractions(generateFractions());
        exerciseVO.setResult(initFractionsVO());
        return exerciseVO;
    }

    private List<Fraction> generateFractions() {
        List<Fraction> fractions = generateFractions(PROPER_FRACTION_FUNCTION);
        fractions.addAll(generateFractions(IMPROPER_FRACTION_FUNCTION));
        return fractions;
    }

    private List<Fraction> generateFractions(Supplier<Fraction> supplier) {
        List<Fraction> properFractions = new ArrayList<>();
        for (int i = 0; i < PROBLEMS_PER_TYPE; i++) {
            properFractions.add(supplier.get());
        }
        return properFractions;
    }

    private List<FractionVO> initFractionsVO() {
        List<FractionVO> emtpyFractions =  new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            emtpyFractions.add(new FractionVO());
        }
        return emtpyFractions;
    }

    @RequestMapping(value = "/decimalToFraction/grade", method = RequestMethod.POST)
    public String grade(@Valid @ModelAttribute(NAME) ExerciseVO exerciseVO, BindingResult result, ModelMap modelMap) {
        modelMap.addAttribute("isGraded", true);
        for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            Fraction fraction = exerciseVO.getFractions().get(i);
            FractionVO answer = exerciseVO.getResult().get(i);
            answer.setCorrect(fraction.getNumerator() == Integer.parseInt(answer.getNumerator())
                && fraction.getDenominator() == Integer.parseInt(answer.getDenominator()));
        }
        System.out.println(result.hasErrors());
        return "decimalToFraction";
    }
}
