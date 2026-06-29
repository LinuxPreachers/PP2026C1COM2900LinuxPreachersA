package com.classes.sorcerer;

import java.util.List;

public class WizardCreator extends SorcererCreator{
	private static final List<String> STUDENT_NAMES = List.of(
        "Harry", "Luna", "Neville", "Cedric", "Cho",
        "Dean", "Seamus", "Ginny", "Colin", "Susan"
    );

    private static final List<String> AUROR_NAMES = List.of(
        "Kingsley", "Alastor", "Dawlish", "Amelia", "Gawain",
        "Aurora", "Marcus", "Helena", "Victor", "Damian"
    );

    private static final List<String> PROFESSOR_NAMES = List.of(
        "Minerva", "Severus", "Filius", "Pomona", "Horace",
        "Albus", "Remus", "Sybil", "Cedric", "Eleanor"
    );
    
    @Override
    public Sorcerer createSorcerer() {
        switch (RANDOM.nextInt(3)) {
            case 0:
                return new Student(
                    randomName(STUDENT_NAMES),
                    randomLevel(0, 40),
                    MAX_LIFE_POINTS 
                );

            case 1:
                return new Auror(
                    randomName(AUROR_NAMES),
                    randomLevel(40, 85),
                    MAX_LIFE_POINTS 
                );

            default:
                return new Professor(
                    randomName(PROFESSOR_NAMES),
                    randomLevel(60, 99),
                    MAX_LIFE_POINTS 
                );
        }
    }
}
