package aysta3045.itemrandomdrop;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class ModCommands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("itemrandomdrop")
                .requires(source -> source.hasPermissionLevel(2)) // 需要 OP 权限等级 2
                .then(literal("enable")
                        .executes(ModCommands::executeEnable))
                .then(literal("disable")
                        .executes(ModCommands::executeDisable))
                .then(literal("status")
                        .executes(ModCommands::executeStatus))
        );
    }

    private static int executeEnable(CommandContext<ServerCommandSource> context) {
        ItemRandomDrop.enabled = true;
        context.getSource().sendFeedback(() -> Text.literal("随机掉落已开启"), true);
        return 1;
    }

    private static int executeDisable(CommandContext<ServerCommandSource> context) {
        ItemRandomDrop.enabled = false;
        context.getSource().sendFeedback(() -> Text.literal("随机掉落已关闭"), true);
        return 1;
    }

    private static int executeStatus(CommandContext<ServerCommandSource> context) {
        String status = ItemRandomDrop.enabled ? "enabled" : "disabled";
        context.getSource().sendFeedback(() -> Text.literal("随机掉落" + status), false);
        return 1;
    }
}