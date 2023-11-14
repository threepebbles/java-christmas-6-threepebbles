package christmas.domain.dto.output;

import java.util.List;

public class DiscountResultsDTO {
    private final List<DiscountResultDTO> discountResults;

    public DiscountResultsDTO(List<DiscountResultDTO> discountResults) {
        this.discountResults = discountResults;
    }

    public List<DiscountResultDTO> getDiscountResults() {
        return discountResults;
    }
}