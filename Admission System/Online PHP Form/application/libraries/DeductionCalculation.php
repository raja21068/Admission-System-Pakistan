<?php

class DeductionCalculation
{
    public static function forBachalor($currentYear,$matricYear) {
        $deductionMarks = 0;




        $difference = $currentYear - $matricYear;

        if ($difference >= 8) {
            $deductionMarks = 25;
        } else if ($difference > 3) {
            $deductionMarks = ($difference - 3) * 5;
        }

        return $deductionMarks;
    }

    public static function forMasterUSindh($currentYear,$matricYear ,$CandidateDegreePeriod) {
        $deductionMarks = 0;



        $difference = ($currentYear - $matricYear - 1);

        /**
         * 3 is basic difference at inter level
         * 5 is for total number of deduction marks
         */
        if ($difference >= (3 + $CandidateDegreePeriod + 5)) {
            $deductionMarks = 25;
        } else if (difference > (3 + $CandidateDegreePeriod)) {
            $deductionMarks = ($difference - (3 + $CandidateDegreePeriod)) * 5;
        }

        return $deductionMarks;
    }

    public static function forMasterOther($currentYear) {
        $deductionMarks = 0;
//        if(set.size() != 3) return deductionMarks;
//
//        Object[] toArray = set.toArray();
//
//        CredentialDetails matric = (CredentialDetails) toArray[0];
//        Integer currentYear = ay.getYear();
//        Integer matricYear = matric.getPassingYear();
//
//        Integer difference = (currentYear - matricYear);
//
//        if (difference >= (3 + degreePeriod + 5)) { /** 5 is for total number of deduction marks*/
//            deductionMarks = 25;
//        } else if (difference > (3 + degreePeriod)) {
//            deductionMarks = (difference - (3 + degreePeriod)) * 5;
//        }

        return $deductionMarks;
    }

}