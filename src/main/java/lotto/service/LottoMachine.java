package lotto.service;

import lotto.console.Output;
import lotto.domain.Lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {
    public static List<Lotto> buyLotto(Float price){
        long ticketNum = (long) (price / 1000);

        List<Lotto> lottoTickets = Stream.generate(LottoGenerate::generate)
                .limit(ticketNum)
                .collect(Collectors.toList());

        Output.printTickets(lottoTickets, ticketNum);

        return lottoTickets;
    }

    public static void judgeGrade(Lotto winningNumbers, int bonusNum, Lotto LottoTicket){
        int matchCnt = winningNumbers.matchNumbers(LottoTicket);
        boolean bonusMatch = LottoTicket.matchBonusNum(bonusNum);

        if(matchCnt == 6) {
            Execute.state.setGradeState(1);
            Output.printResult(1);
            return;
        }
        if(matchCnt == 5 && bonusMatch) {
            Execute.state.setGradeState(2);
            Output.printResult(2);
            return;
        }
        if(matchCnt == 5) {
            Execute.state.setGradeState(3);
            Output.printResult(3);
            return;
        }
        if(matchCnt == 4) {
            Execute.state.setGradeState(4);
            Output.printResult(4);
            return;
        }
        if(matchCnt == 3) {
            Execute.state.setGradeState(5);
            Output.printResult(5);
        }
    }
}
