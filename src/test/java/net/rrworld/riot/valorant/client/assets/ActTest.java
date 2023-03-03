package net.rrworld.riot.valorant.client.assets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.rrworld.valorant.client.assets.Act;

public class ActTest {

	@Test
	public void testRecentFirst() {
		Act [] acts = Act.recentFirst();
		for (int i = 0; i < acts.length - 1; i++) {
			Assertions.assertTrue(acts[i].getStartTime().isAfter(acts[i+1].getStartTime()));
		}
	}
}
