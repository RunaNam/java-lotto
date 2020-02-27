package lotto.domain.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoNumberFactory;

public class AutoLottoPurchaseStrategy implements LottoPurchaseStrategy {
	@Override
	public Lotto generate() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumberFactory.values());
		Collections.shuffle(lottoNumbers);

		return new Lotto(lottoNumbers.stream()
			.limit(Lotto.CORRECT_SIZE)
			.collect(Collectors.toSet()));
	}
}