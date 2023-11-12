package christmas.controller.dto;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class DiscountResultsDTO implements Iterable<DiscountResultDTO> {
    List<DiscountResultDTO> discountResults;

    public DiscountResultsDTO(List<DiscountResultDTO> discountResults) {
        this.discountResults = discountResults;
    }

    public List<DiscountResultDTO> getDiscountResults() {
        return discountResults;
    }

    public boolean isEmpty() {
        return discountResults.isEmpty();
    }

    @Override
    public Iterator<DiscountResultDTO> iterator() {
        return discountResults.iterator();
    }

    @Override
    public void forEach(Consumer<? super DiscountResultDTO> action) {
        discountResults.forEach(action);
    }
}