package lotto.domain;

import java.util.List;
import java.util.function.Predicate;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validDuplicate(numbers);
        validateLottoSize(numbers);
        validOutOfRange(numbers);
    }

    private void validateLottoSize(List<Integer> numbers){
        if (numbers.size() != 6 ) {
            throw new IllegalArgumentException("로또 번호는 6개 이어야 합니다.\n");
        }
    }

    private void validDuplicate(List<Integer> numbers) {
        boolean isDuplicate = numbers.stream()
                .distinct()
                .count() != numbers.size();

        if(isDuplicate) {
            throw new IllegalArgumentException("로또 번호는 중복 되어서는 안됩니다.\n");
        }
    }

    private void validOutOfRange(List<Integer> numbers) {
        boolean outOfRange = numbers.stream()
                .anyMatch(num -> num < 1 || 45 < num);

        if(outOfRange) {
            throw new IllegalArgumentException("로또 1부터 45까지 입니다.\n");
        }
    }
    public int matchNumbers(Lotto player){
        List<Integer> playerNumbers = player.getNumbers();

        List<Integer> matchList = playerNumbers.stream().filter(o -> this.numbers.stream()
                .anyMatch(Predicate.isEqual(o))).toList();

        return matchList.size();
    }

    public boolean matchBonusNum(int bonusNum){
        return numbers.contains(bonusNum);
    }

    public List<Integer> getNumbers(){
        return numbers;
    }

}
