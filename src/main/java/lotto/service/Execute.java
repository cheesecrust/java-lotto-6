package lotto.service;

import lotto.console.Input;
import lotto.domain.Asset;
import lotto.domain.Lotto;
import lotto.domain.State;

import java.util.List;

public class Execute {
    public static State state = new State();
    public void start(){
        Asset asset = Input.askMoney();
        Lotto winningNum = Input.askWinningNum();
        Integer bonusNum = Input.askBonusNum();


        List<Lotto> lottoTickets = LottoMachine.buyLotto(asset.getMoney());

        lottoTickets.forEach(tickets -> LottoMachine.judgeGrade(winningNum, bonusNum, tickets));
    }
}
