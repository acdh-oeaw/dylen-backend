package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.targetWord.TargetWord;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;
import java.util.List;

@Data
@NoArgsConstructor
public class TargetWordsSliceDto {
    private int sliceNumber;
    private boolean hasNext;
    private List<TargetWord> targetWords;

    private TargetWordsSliceDto(int sliceNumber, boolean hasNext, List<TargetWord> targetWords) {
        this.sliceNumber = sliceNumber;
        this.hasNext = hasNext;
        this.targetWords = targetWords;
    }

    @JsonCreator
    public static TargetWordsSliceDto of(int sliceNumber, boolean hasNext, List<TargetWord> targetWords) {
        return new TargetWordsSliceDto(sliceNumber, hasNext, targetWords);
    }

    public static TargetWordsSliceDto fromSlice(Slice<TargetWord> slice) {
        return new TargetWordsSliceDto(slice.getNumber(), slice.hasNext(), slice.getContent());
    }
}