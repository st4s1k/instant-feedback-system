package com.inther.mappers.helpers;

import com.inther.entities.Mark;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;

public class MarkHelper {

    @Named("calculateAverageMark")
    public Double calculateAverageMark(List<Mark> marks) {
        return marks == null
                ? 0d
                : marks.stream().filter(Objects::nonNull).mapToDouble(Mark::getValue).average().orElse(0d);
    }

}
