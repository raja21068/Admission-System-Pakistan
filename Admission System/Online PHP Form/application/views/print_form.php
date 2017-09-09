<?php
    $form = new AppFormReport($candidateId,$candidateImage);
    $form->printReport($bean,$campusList,$appliedCats,$sscInfo,$hscInfo,$morningChoices,$eveningChoices,$grdInfo,$optionals,$bankName);
?>
