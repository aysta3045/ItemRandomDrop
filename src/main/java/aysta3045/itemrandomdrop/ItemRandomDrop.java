package aysta3045.itemrandomdrop;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemRandomDrop implements ModInitializer {
	public static final String MOD_ID = "itemrandomdrop";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Mod Loading Successfully!");
	}
}