package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
	private List<LottoNumber> lottoNumbers;

	@BeforeEach
	void init() {
		lottoNumbers = Arrays.asList(
			new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5"),
			new LottoNumber("6")
		);
	}

	@DisplayName("1등, getRanks 테스트")
	@Test
	void checkGetRanksByWhenFirstState() {
		List<Rank> values = new ArrayList<>();
		values.add(Rank.FIRST);

		LottoTicket lottoTicket = LottoTicket.of(lottoNumbers);
		List<LottoTicket> tickets = new ArrayList<>();
		tickets.add(lottoTicket);
		LottoTickets lottoTickets = new LottoTickets(tickets);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6", "7");

		Ranks expected = new Ranks(values);
		Ranks actual = lottoTickets.getRanksBy(winningNumbers);

		assertEquals(expected, actual);
	}

	@DisplayName("size 테스트")
	@Test
	void checkSize() {
		LottoTicket lottoTicket = LottoTicket.of(lottoNumbers);
		List<LottoTicket> tickets = new ArrayList<>();
		tickets.add(lottoTicket);
		tickets.add(lottoTicket);
		LottoTickets lottoTickets = new LottoTickets(tickets);

		int expected = 2;
		int actual = lottoTickets.size();

		assertEquals(expected, actual);
	}

	@DisplayName("getter 테스트")
	@Test
	void checkGetTickets() {
		LottoTicket lottoTicket = LottoTicket.of(lottoNumbers);
		List<LottoTicket> tickets = new ArrayList<>();
		tickets.add(lottoTicket);
		LottoTickets lottoTickets = new LottoTickets(tickets);

		List<LottoTicket> expected = Collections.singletonList(lottoTicket);
		List<LottoTicket> actual = lottoTickets.tickets();

		assertEquals(expected, actual);
	}

	@DisplayName("add 테스트 (방어적 복사)")
	@Test
	void checkAdd() {
		LottoTicket lottoTicket = LottoTicket.of(lottoNumbers);
		List<LottoTicket> tickets = new ArrayList<>();
		tickets.add(lottoTicket);
		LottoTickets lottoTickets = new LottoTickets(tickets);

		LottoTicket lottoTicket2 = LottoTicket.of(lottoNumbers);
		List<LottoTicket> tickets2 = new ArrayList<>();
		tickets2.add(lottoTicket2);
		LottoTickets lottoTickets2 = new LottoTickets(tickets);

		tickets.add(lottoTicket2);
		LottoTickets expected = new LottoTickets(tickets);
		LottoTickets actual = lottoTickets.add(lottoTickets2);
		assertEquals(expected, actual);
	}
}
