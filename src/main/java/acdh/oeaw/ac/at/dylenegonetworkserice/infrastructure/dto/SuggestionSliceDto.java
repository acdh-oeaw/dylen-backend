package acdh.oeaw.ac.at.dylenegonetworkserice.infrastructure.dto;

import acdh.oeaw.ac.at.dylenegonetworkserice.domain.Suggestion;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@Data
@NoArgsConstructor
public class SuggestionSliceDto {
    private int sliceNumber;
    private boolean hasNext;
    private List<Suggestion> suggestions;

    private SuggestionSliceDto(int sliceNumber, boolean hasNext, List<Suggestion> suggestions) {
        this.sliceNumber = sliceNumber;
        this.hasNext = hasNext;
        this.suggestions = suggestions;
    }

    @JsonCreator
    public static SuggestionSliceDto of(int sliceNumber, boolean hasNext, List<Suggestion> suggestions) {
        return new SuggestionSliceDto(sliceNumber, hasNext, suggestions);
    }

    public static SuggestionSliceDto fromSlice(Slice<Suggestion> slice) {
        return new SuggestionSliceDto(slice.getNumber(), slice.hasNext(), slice.getContent());
    }
}
