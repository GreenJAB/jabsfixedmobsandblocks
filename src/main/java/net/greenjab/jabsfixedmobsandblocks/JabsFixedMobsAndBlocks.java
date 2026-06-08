package net.greenjab.jabsfixedmobsandblocks;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JabsFixedMobsAndBlocks implements ModInitializer {
	public static final String MOD_ID = "jabsfixedmobsandblocks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}