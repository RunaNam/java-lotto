package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    LottoResult lottoResult = new LottoResult();
    Lotto winLotto = new Lotto(new HashSet<>(Arrays.asList(
        LottoNumber.of(1),
        LottoNumber.of(2),
        LottoNumber.of(3),
        LottoNumber.of(4),
        LottoNumber.of(5),
        LottoNumber.of(6)
    )));
    WinningLotto winningLotto = new WinningLotto(winLotto, LottoNumber.of(7));
    Lotto lotto = new Lotto(new HashSet<>(Arrays.asList(
        LottoNumber.of(1),
        LottoNumber.of(2),
        LottoNumber.of(3),
        LottoNumber.of(4),
        LottoNumber.of(5),
        LottoNumber.of(7)
    )));
    List<Lotto> lottos = new ArrayList<>(Collections.singletonList(lotto));
    LottoGroup lottoGroup = new LottoGroup(lottos);

    @Test
    @DisplayName("등수가 올바르게 카운트되는지 확인")
    void matchRank_count() {
        Map<Rank, Integer> ranks = lottoResult.matchRank(winningLotto, lottoGroup);
        assertThat(ranks.get(Rank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률을 올바르게 계산하는지 확인")
    void earning_rate() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Money money = new Money("5000");

        double earningRate = lottoResult.findEarningRate(money.getMoney());
        assertThat(decimalFormat.format(earningRate)).isEqualTo("0.00");
    }
}