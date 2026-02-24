package aysta3045.itemrandomdrop;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemRandomDrop implements ModInitializer {
	public static final String MOD_ID = "itemrandomdrop";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean enabled = false;
	public static final Random RANDOM = new Random();
	private static List<ItemStack> randomItemCache; // 缓存所有非空气物品，数量为1

	@Override
	public void onInitialize() {
		initRandomItemCache();                 // 初始化随机物品池
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
				ModCommands.register(dispatcher)); // 注册指令
		RandomDropEventHandler.register();      // 注册事件监听

		LOGGER.info("Mod Loading Successfully!");
	}

	private void initRandomItemCache() {
		randomItemCache = new ArrayList<>();
		Registries.ITEM.stream()
				.filter(item -> item != net.minecraft.item.Items.AIR)
				.forEach(item -> randomItemCache.add(new ItemStack(item, 1)));
		LOGGER.info("Loaded {} items for random drop.", randomItemCache.size());
	}

	public static void replaceWithRandomItem(net.minecraft.entity.ItemEntity itemEntity) {
		if (randomItemCache.isEmpty()) return;

		ItemStack originalStack = itemEntity.getStack();
		int originalCount = originalStack.getCount();

		// 随机选择一个物品栈（数量为1的副本）
		ItemStack randomStack = randomItemCache.get(RANDOM.nextInt(randomItemCache.size())).copy();
		int maxCount = randomStack.getMaxCount();
		int newCount = Math.min(originalCount, maxCount);
		randomStack.setCount(newCount);

		itemEntity.setStack(randomStack);

		if (originalCount > maxCount) {
		}
	}
}