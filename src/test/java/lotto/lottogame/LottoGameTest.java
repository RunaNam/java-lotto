package lotto.lottogame;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.LottoNumber;
import lotto.lottoticket.LottoTickets;
import lotto.lottoticket.WinnerTicket;
import lotto.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoGameTest {
    private LottoGame lottoGame;

    @BeforeEach
    void setUp() {
        LottoTickets lottoTickets = new LottoTickets(new LottoCount(1), () -> Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(5), LottoNumber.of(4), LottoNumber.of(6)));
        lottoGame = new LottoGame(lottoTickets);
    }

    @Test
    @DisplayName("통계 계산 확인")
    void calculateStatisticsTest() {
        WinnerTicket winnerTicket = new WinnerTicket("1,2,3,4,5,6");
        BonusBall bonusBall = new BonusBall("7", winnerTicket);
        lottoGame.calculateStatistics(winnerTicket, bonusBall);
        assertEquals(lottoGame.createResult(new Money("1000")).getProfit(), 2000000);
    }
}