package cooldma.clientrenamer;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class ClientRenamerCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)
                ((LiteralArgumentBuilder) ClientCommandManager.literal("clientrenamer")
                .then(ClientCommandManager.literal("toggle")
                        .executes(ctx -> ClientRenamer.toggleStatus())))
                .then(ClientCommandManager.literal("set")
                        .then(ClientCommandManager.argument("clientbrand", (ArgumentType)StringArgumentType.greedyString())
                                .executes(ctx -> ClientRenamer.changeClientBrand(StringArgumentType.getString((CommandContext<? extends Object>)ctx, "clientbrand"))))));
    }
}